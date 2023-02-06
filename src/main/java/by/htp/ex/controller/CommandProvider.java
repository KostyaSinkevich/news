package by.htp.ex.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.ex.controller.impl.*;

public class CommandProvider {
    private final Map<CommandName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
        commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPageCommand());
        commands.put(CommandName.DO_SIGN_IN, new DoSIgnIn());
        commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());
        commands.put(CommandName.GO_TO_NEWS_LIST, new GoToNewsList());
        commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
        commands.put(CommandName.DELETE_NEWS, new DeleteNews());
        commands.put(CommandName.SAVE_EDITED_NEWS, new SaveEditedNews());
        commands.put(CommandName.GO_TO_EDIT_NEWS, new GoToEditNews());
        commands.put(CommandName.ADD_NEWS, new AddNews());
        commands.put(CommandName.GO_TO_ADD_NEWS, new GoToAddNews());
        commands.put(CommandName.CHANGE_LOCALE, new ChangeLocale());
    }


    public Command getCommand(String name) {
        CommandName commandName = CommandName.valueOf(name.toUpperCase());
        return commands.get(commandName);
    }
}
