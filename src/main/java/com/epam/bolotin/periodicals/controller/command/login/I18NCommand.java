package com.epam.bolotin.periodicals.controller.command.login;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

/**
 *  * Internationalization controller command.
 *
 * @author: Viacheslav Bolotin
 * @date: 23.12.2022
 */
public class I18NCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String fmtLocale = "javax.servlet.jsp.jstl.fmt.locale";
        String defaultLocale = "defaultLocale";

        if (request.getParameter("uk") != null) {
            Config.set(session, fmtLocale, PagePath.LOCALE_NAME_UK);
            session.setAttribute(defaultLocale, "uk");

        } else {
            Config.set(session, fmtLocale, "en");
            session.setAttribute(defaultLocale, PagePath.LOCALE_NAME_EN);
        }

        return session.getAttribute("current_action").toString();
    }
}
