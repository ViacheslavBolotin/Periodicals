package com.epam.bolotin.periodicals.controller.command.login;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.db.entity.UserRole;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: Viacheslav Bolotin
 * @date: 23.12.2022
 */
public class RegistrationCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        UserService userService = AppServices.getInstance().getUserService();
        String resp;

        User user = new User();
        try {

            if (userService.validateAndFillUser(user, request)) {

                user.setUserRoleId(2);
                user.setBlocked(false);
                userService.save(user);

                String page = request.getParameter("page").trim();
                resp = (page.equals("users")) ? PagePath.COMMAND_SHOW_USERS : PagePath.COMMAND_LOGIN+"&main_menu=1";

                LOG.debug("New user added");
            } else {
                resp = PagePath.COMMAND_NEW_USER;
                LOG.debug("New user cannot added");
            }

            response.sendRedirect(resp);
            resp = PagePath.COMMAND_REDIRECT;
            LOG.debug("New user added (COMMAND_REDIRECT)");
        } catch (Exception e) {
            resp = PagePath.PAGE_ERROR;
        }
        return resp;
    }
}
