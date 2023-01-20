package com.epam.bolotin.periodicals.controller.command.login;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: Viacheslav Bolotin
 * @date: 02.01.2023
 */
public class UpdateUserCommand implements Command {
    private static final Logger LOG = Logger.getLogger(UpdateUserCommand.class);
    private UserService userService = AppServices.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String resp;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        long id = Long.parseLong(request.getParameter("user_id"));
        user.setId(id);

        try {

            if (userService.validateAndFillUser(user, request)) {

                userService.update(user);

                if (user.getUserRoleId() == 1) {
                    resp = PagePath.COMMAND_SHOW_USERS;
                }
                else {
                    resp = PagePath.COMMAND_PERSONAL_CABINET;
                }

                LOG.info("User update (id = " + user.getId() + ")");
            } else {
                resp = PagePath.PAGE_ERROR;
                LOG.info("User (id = " + user.getId() + ") cannot update");
            }

            response.sendRedirect(resp);
            resp = PagePath.COMMAND_REDIRECT;
            LOG.debug("User update (COMMAND_REDIRECT)");
        } catch (Exception e) {
            resp = PagePath.PAGE_ERROR;
            LOG.error("User (id = " + user.getId() + ") update error (" + e.getMessage() + ")");
        }
        return resp;
    }
}
