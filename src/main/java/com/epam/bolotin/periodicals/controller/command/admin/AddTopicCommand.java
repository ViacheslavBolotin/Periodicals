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
 * @date: 26.12.2022
 */
public class AddTopicCommand implements Command {
    private static final Logger LOG = Logger.getLogger(AddTopicCommand.class);
    private TopicService topicService = AppServices.getInstance().getTopicService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String resp;
        Topic topic = new Topic();

        try {
            if (topicService.validateAndFillTopic(topic, request)) {

                topicService.save(topic);
                resp = PagePath.COMMAND_TOPICS;

                LOG.debug("New topic added");
            } else {
                resp = PagePath.COMMAND_TOPICS;
                LOG.debug("New topic cannot added");
            }

            response.sendRedirect(resp);
            resp = PagePath.COMMAND_REDIRECT;
            LOG.debug("New topic added (COMMAND_REDIRECT)");
        } catch (Exception e) {
            resp = PagePath.PAGE_ERROR;
            LOG.error("New topic added error (" + e.getMessage() + ")");
        }
        return resp;
    }
}
