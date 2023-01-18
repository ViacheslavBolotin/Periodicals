package com.epam.bolotin.periodicals.controller.command.login;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String errorMessage = "No such command";
        request.setAttribute("errorMessage", errorMessage);
        return PagePath.PAGE_ERROR;
    }
}
