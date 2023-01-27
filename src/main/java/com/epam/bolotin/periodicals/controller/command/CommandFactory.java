package com.epam.bolotin.periodicals.controller.command;

import com.epam.bolotin.periodicals.controller.command.admin.*;
import com.epam.bolotin.periodicals.controller.command.client.*;
import com.epam.bolotin.periodicals.controller.command.login.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *  * Main Command factory for Controller.
 *
 * @author: Viacheslav Bolotin
 * @date: 16.12.2022
 */
public class CommandFactory {
    private static final Logger LOG = Logger.getLogger(CommandFactory.class);
    private static CommandFactory factory;
    private static final Map<String, Command> commands = new HashMap<>();
    private CommandFactory() {}

    /**
     * Singleton.
     */
    public static CommandFactory getCommandFactory() {
        if (factory == null) {
            factory = new CommandFactory();
        }
        return factory;
    }

    static {
        // common commands
        commands.put("no_command", new NoCommand());
        commands.put("redirect", null);
        commands.put("i18n", new I18NCommand());
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("new_user", new NewUserCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("user_profile", new EditUserCommand());
        commands.put("update_user", new UpdateUserCommand());

        // admin commands
        commands.put("users", new ShowUserListCommand());
        commands.put("block_unblock_user", new BlockUnblockUserCommand());
        commands.put("accounts", new AccountsCommand());

        commands.put("topics", new TopicsCommand());
        commands.put("add_topic", new AddTopicCommand());
        commands.put("edit_topic", new EditTopicCommand());
        commands.put("update_topic", new UpdateTopicCommand());
        commands.put("delete_topic", new DeleteTopicCommand());

        commands.put("publications", new PublicationsCommand());
        commands.put("add_publication", new AddPublicationCommand());
        commands.put("edit_publication", new EditPublicationCommand());
        commands.put("update_publication", new UpdatePublicationCommand());
        commands.put("delete_publication", new DeletePublicationCommand());

        commands.put("reports", new ReportsCommand());

        // user commands
        commands.put("personal_cabinet", new PersonalCabinetCommand());
        commands.put("account", new AccountCommand());
        commands.put("update_account", new UpdateAccountCommand());
        commands.put("subscribe_publication", new SubscribePublicationCommand());
        commands.put("view_publication", new ViewPublicationCommand());

        LOG.debug("Command container was successfully initialized");
    }

    public Command getCommand(HttpServletRequest request) {
        // extract command name from the request
        String commandName = request.getParameter("action");
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }
        LOG.trace("Request parameter: command --> " + commandName);
        return commands.get(commandName);
    }
}
