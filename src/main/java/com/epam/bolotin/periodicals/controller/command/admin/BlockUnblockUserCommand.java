package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  * Edit user controller command.
 *
 * @author: Viacheslav Bolotin
 * @date: 27.12.2022
 */
public class BlockUnblockUserCommand implements Command {

    private UserService userService = AppServices.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String forward;
        String resp = PagePath.COMMAND_SHOW_USERS;
        User user;

        long id = Long.parseLong(request.getParameter("user_id"));
        if (id != 0) {

            try {
            user = userService.findByID(id);
            userService.blockUnblockUser(user);

                response.sendRedirect(resp);
                forward = PagePath.COMMAND_REDIRECT;
            } catch (Exception e) {
                forward = PagePath.PAGE_ERROR;
            }

        } else {
            forward = PagePath.PAGE_ERROR;
        }

        return forward;
    }
}
