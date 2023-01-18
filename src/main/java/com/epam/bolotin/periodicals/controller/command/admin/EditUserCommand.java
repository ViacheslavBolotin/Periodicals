package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.model.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: Viacheslav Bolotin
 * @date: 11.01.2023
 */
public class EditUserCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String forward;
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            return PagePath.PAGE_ERROR;
        }

        if (user.getId() != 0) {

            request.setAttribute("user", user);
            forward = PagePath.PAGE_USER_PROFILE;

        } else {
            forward = PagePath.PAGE_ERROR;
        }
        return forward;
    }
}
