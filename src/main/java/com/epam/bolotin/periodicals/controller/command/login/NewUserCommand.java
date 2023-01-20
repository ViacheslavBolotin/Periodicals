package com.epam.bolotin.periodicals.controller.command.login;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.controller.util.RequestUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: Viacheslav Bolotin
 * @date: 23.12.2022
 */
public class NewUserCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("New user open window");
        request.setAttribute("errorMessage", RequestUtils.getStringParameter(request,"errorMessage"));
        return PagePath.PAGE_NEW_USER;
    }
}
