package com.project.web.commands;

import com.project.dao.UserDao;
import com.project.dao.impl.UserDaoImpl;
import com.project.entities.User;
import com.project.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminUsersCommand extends AbstractCommand {

    private static final Logger LOG = Logger.getLogger(AdminUsersCommand.class);

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {

        String type = request.getParameter("type");
        if (type.equals("users")) {
            List<User> users = UserService.getAllUsers();
            request.setAttribute("users", users);
            return "admin-users.jsp";
        } else if (type.equals("librarians")) {
            List<User> librarians = UserService.getAllLibrarians();
            request.setAttribute("users", librarians);
            return "admin-users.jsp";
        }
        return "error.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action.equals("delete")) {
            LOG.info("Delete: " + request.getParameter("userId"));
            return "redirect:admin-users?type=librarians";

        }else if (action.equals("ban")){
            LOG.info("Banned: " + request.getParameter("userId"));
            return "redirect:admin-users?type=users";
        }
        return "error.jsp";
    }
}
