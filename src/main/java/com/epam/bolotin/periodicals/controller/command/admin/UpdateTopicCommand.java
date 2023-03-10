package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.model.db.entity.Topic;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.TopicService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Viacheslav Bolotin
 * @date: 02.01.2023
 */
public class UpdateTopicCommand implements Command {
    private TopicService topicService = AppServices.getInstance().getTopicService();
    private static final Logger LOG = Logger.getLogger(UpdateTopicCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String errorMessage = "";
        String resp;
        Topic topic = new Topic();
        long id = Long.parseLong(request.getParameter("topic_id"));
        topic.setId(id);

        try {

            if (topicService.validateAndFillTopic(topic, request)) {

                topicService.update(topic);
                resp = PagePath.COMMAND_TOPICS;
                response.sendRedirect(resp);
                resp = PagePath.COMMAND_REDIRECT;
                LOG.info("Topic update (id = " + topic.getId() + ")");
                LOG.debug("Topic update (COMMAND_REDIRECT)");
            } else {
                errorMessage = "error.topic.update";
                request.setAttribute("errorMessage", errorMessage);
                resp = PagePath.PAGE_ERROR;
                LOG.info("Topic (id = " + topic.getId() + ") cannot update");
            }

        } catch (Exception e) {
            errorMessage = "error.topic.update";
            request.setAttribute("errorMessage", errorMessage);
            resp = PagePath.PAGE_ERROR;
            LOG.error("Topic (id = " + topic.getId() + ") update error (" + e.getMessage() + ")");
        }
        return resp;
    }
}
