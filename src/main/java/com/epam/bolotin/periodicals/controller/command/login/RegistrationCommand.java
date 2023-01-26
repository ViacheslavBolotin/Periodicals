package com.epam.bolotin.periodicals.controller.command.login;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.controller.util.RequestUtils;
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
        String errorMessage = "";

        User user = new User();
        try {

            if (userService.validateAndFillUser(user, request)) {

                if (userService.findByName(RequestUtils.getStringParameter(request, "login")).getId() == 0) {

                    user.setUserRoleId(2);
                    user.setBlocked(false);
                    userService.save(user);
                    LOG.debug("New user added (" + user.getUserName() + ")");
                } else {

                    errorMessage = "error.add_user.already_exists";
                    request.setAttribute("errorMessage", errorMessage);
                    LOG.info("Unable to register a new user. A user with the same name already exists in the system!");
                }

                String page = request.getParameter("page").trim();
                resp = (page.equals("users")) ? PagePath.COMMAND_SHOW_USERS+"&errorMessage="+errorMessage :
                        PagePath.COMMAND_LOGIN+"&main_menu=1"+"&errorMessage="+errorMessage;


            } else {
                errorMessage = "error.add_user.incorrect_data";
                request.setAttribute("errorMessage", errorMessage);
                String page = request.getParameter("page").trim();
                resp = (page.equals("users")) ? PagePath.COMMAND_SHOW_USERS+"&errorMessage="+errorMessage :
                        PagePath.COMMAND_NEW_USER+"&errorMessage="+errorMessage;
                LOG.debug("New user cannot added. Incorrect data entered!");
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
