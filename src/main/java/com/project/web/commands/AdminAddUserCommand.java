package com.project.web.commands;

import com.project.entities.User;
import com.project.forms.AdminAddUserForm;
import com.project.services.UserService;
import com.project.services.ValidatorService;
import com.project.web.data.ValidationData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddUserCommand extends AbstractCommand {

    private UserService userService;

    public AdminAddUserCommand() {
        this(new UserService());
    }

    public AdminAddUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        return "admin-add-user.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {

        AdminAddUserForm form = getAdminAddUserForm(request);
        ValidatorService validatorService = new ValidatorService();
        String role = "librarian";
        int banList = 0;
        ValidationData validationData = validatorService.validate(form);

        if (validationData.isValidationResult()) {
            User user = new User(form.getName(), form.getEmail(), form.getSex(), form.getPhone(), role, banList, form.getPassword());
            userService.createUser(user);
        } else {
            request.setAttribute("errorMessages", validationData.getErrorCodes());
            return "admin-add-user.jsp";
        }

        return "redirect:admin-users?type=librarians";
    }

    private AdminAddUserForm getAdminAddUserForm(HttpServletRequest request) {
        return new AdminAddUserForm(request.getParameter("name"),
                request.getParameter("email"),
                request.getParameter("sex"),
                request.getParameter("phone"),
                request.getParameter("password"));
    }
}
