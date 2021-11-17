package com.project.factory;

import com.project.dao.impl.BookDao;
import com.project.dao.impl.OrderDaoImpl;
import com.project.dao.impl.UserDaoImpl;
import com.project.services.BookService;
import com.project.services.OrderService;
import com.project.services.UserService;
import com.project.services.ValidatorService;
import com.project.web.commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> commandMap = new HashMap<>();
    private static Command defaultCommand = new PageNotFoundCommand();


    static {
        commandMap.put("login", new LoginCommand(new UserService(UserDaoImpl.getInstance())));
        commandMap.put("registration", new RegistrationCommand(new UserService(UserDaoImpl.getInstance()), new ValidatorService()));
        commandMap.put("", new HomeCommand());
        commandMap.put("books", new BookCommand(new BookService(BookDao.getInstance())));
        commandMap.put("orders", new OrderCommand(new UserService(UserDaoImpl.getInstance()),new OrderService(OrderDaoImpl.getInstance())));
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("language",new LanguageCommand());
        commandMap.put("admin-books",new AdminBooksCommand());
        commandMap.put("admin-edit-book", new AdminEditBookCommand());
        commandMap.put ("admin-users", new AdminUsersCommand());
        commandMap.put( "admin-add-user", new AdminAddUserCommand());
        commandMap.put("librarian-orders", new LibrarianBookOrdersCommand());
        commandMap.put("personal-account", new PersonalAccountCommand(new UserService(UserDaoImpl.getInstance())));
        commandMap.put("user-list", new UserListCommand());
        commandMap.put("user-abonement",new UserAbonementCommand());
    }

    private CommandFactory() {
    }

    public static Command getCommand(String path) {
        return commandMap.getOrDefault(path, defaultCommand);
    }
}

