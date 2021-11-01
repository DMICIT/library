package com.project.web.commands;

import com.project.dao.UserDao;
import com.project.dao.impl.UserDaoImpl;
import com.project.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminUsersCommand extends AbstractCommand {

    private static final Logger LOG = Logger.getLogger(AdminUsersCommand.class);

    @Override
    protected String executeGet(HttpServletRequest request, HttpServletResponse response) {
        UserDao userDao = UserDaoImpl.getInstance();
        List<User> users = userDao.getAll();
        request.setAttribute("users", users);
        return "admin-users.jsp";
    }

    @Override
    protected String executePost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action.equals("delete")) {
            LOG.info("Delete: " + request.getParameter("userId"));

        }
        return "redirect:admin-users";
    }
}
