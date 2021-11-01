package com.project.web.commands;

import com.project.dao.impl.UserDaoImpl;
import com.project.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddUserCommand extends AbstractCommand{
    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
    String id = request.getParameter("id");
    if (id != null) {
        int userId = Integer.parseInt(id);
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        User user = userDao.getById(userId);
        request.setAttribute("user", user);
        request.setAttribute("action", "add");
    }
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
            UserDaoImpl userDao = UserDaoImpl.getInstance();
            userDao.create(user);
        }
        return "redirect:admin-users?type=librarians";
    }
}
