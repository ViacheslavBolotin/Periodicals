package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.TopicService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Viacheslav Bolotin
 * @date: 02.01.2023
 */
public class DeleteTopicCommand implements Command {
    private TopicService topicService = AppServices.getInstance().getTopicService();
    private static final Logger LOG = Logger.getLogger(DeleteTopicCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String resp;
        long id = Long.parseLong(request.getParameter("topicId"));

        try {
            topicService.delete(id);
            resp = PagePath.COMMAND_TOPICS;
            LOG.debug("Delete topic (id = " + id + ")");

            response.sendRedirect(resp);
            resp = PagePath.COMMAND_REDIRECT;
            LOG.debug("Delete topic (COMMAND_REDIRECT)");
        } catch (Exception e) {
            resp = PagePath.PAGE_ERROR;
            LOG.error("Delete topic (id = " + id + ") error (" + e.getMessage() + ")");
        }
        return resp;
    }
}
