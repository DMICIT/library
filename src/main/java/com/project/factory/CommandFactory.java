package com.project.factory;

import com.project.web.commands.Command;
import com.project.web.commands.HomeCommand;
import com.project.web.commands.LoginCommand;
import com.project.web.commands.RegistrationCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> commandMap = new HashMap<>();
    private static Command defaultCommand = new HomeCommand();

    static {
        commandMap.put("login", new LoginCommand());
        commandMap.put("registration", new RegistrationCommand());
        commandMap.put("", new HomeCommand());
    }

    private CommandFactory() {
    }

    public static Command getCommand(String path) {
        return commandMap.getOrDefault(path, defaultCommand);
    }
}
