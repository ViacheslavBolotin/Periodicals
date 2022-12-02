package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/front")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("GET");

        String name = req.getParameter("fname");
        String lastName = req.getParameter("lname");
        String password = req.getParameter("password");
        PrintWriter out = resp.getWriter();
        out.println("<br> <h1> Are you sure you are " + lastName + " " + name + "(" + password + ")?</h1>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("POST");
        doGet(req, resp);

    }
}
