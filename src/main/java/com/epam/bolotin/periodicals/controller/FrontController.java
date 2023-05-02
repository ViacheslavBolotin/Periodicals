package com.epam.bolotin.periodicals.controller;

import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.controller.command.CommandFactory;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/frontController")
public class FrontController extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(FrontController.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("GET Servlet");
        commandProcessing(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("POST Servlet");
        commandProcessing(request, response);
    }

    private void commandProcessing(HttpServletRequest request, HttpServletResponse response) {

        try {
            LOG.debug("Front controller (command processing)");

            String commandName = request.getParameter("action");
            if (!commandName.equals("i18n")) {
                HttpSession session = request.getSession();
                session.setAttribute("current_action","frontController?action="+commandName);
            }

            CommandFactory commandFactory = CommandFactory.getCommandFactory();
            Command command = commandFactory.getCommand(request);
            String page = command.execute(request, response);
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            if (!page.equals("redirect")) {
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("errorMessage", "error.page.server_error");
            try {
                request.getRequestDispatcher(PagePath.PAGE_ERROR).forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
