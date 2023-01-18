package com.epam.bolotin.periodicals.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * @author: Viacheslav Bolotin
 * @date: 16.12.2022
 */
public interface Command {
    /**
     * Execution method for command.
     *
     * @return Address to go once the command is executed.
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
