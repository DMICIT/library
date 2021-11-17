package com.project.web.commands;

import com.project.dao.impl.UserDaoImpl;
import com.project.entities.User;
import com.project.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddUserCommand extends AbstractCommand{

    private UserService userService;

    public AdminAddUserCommand(){
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


        String action = request.getParameter("action");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        String role = "librarian";
        int banList = 0;
        String password = request.getParameter("password");

        if (action.equals("add")){
            User user = new User(name,email,sex,phone,role,banList,password);
            userService.createUser(user);
        }
        return "redirect:admin-users?type=librarians";
    }
}
