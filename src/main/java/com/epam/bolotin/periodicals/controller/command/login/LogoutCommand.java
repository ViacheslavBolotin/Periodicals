package com.epam.bolotin.periodicals.controller.command.login;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logout controller command.
 *
 * @author: Viacheslav Bolotin
 * @date: 23.12.2022
 */
public class LogoutCommand implements Command {
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        LOG.debug("Logout");
        return PagePath.PAGE_WELCOME;
    }
}
