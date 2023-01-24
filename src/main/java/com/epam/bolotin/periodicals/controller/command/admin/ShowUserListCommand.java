package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.controller.util.RequestUtils;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 26.12.2022
 */
public class ShowUserListCommand implements Command {

    private UserService userService = AppServices.getInstance().getUserService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<User> users = userService.findAll();
        List<User> fullUser = new ArrayList<>();
        for (User user : users) {
            user.setUserRoleId(user.getUserRoleId());
            fullUser.add(user);
        }
        request.setAttribute("errorMessage", RequestUtils.getStringParameter(request,"errorMessage"));
        request.setAttribute("fullUser", fullUser);

        return PagePath.PAGE_USER_LIST;
    }
}
