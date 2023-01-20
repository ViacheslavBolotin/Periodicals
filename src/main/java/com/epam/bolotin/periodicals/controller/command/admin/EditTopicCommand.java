package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.model.db.entity.Topic;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Viacheslav Bolotin
 * @date: 02.01.2023
 */
public class EditTopicCommand implements Command {
    private TopicService topicService = AppServices.getInstance().getTopicService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String forward;

        if (request.getParameter("topicId") == null) {
            return PagePath.COMMAND_TOPICS;
        }

        long id = Long.parseLong(request.getParameter("topicId"));

        if (id != 0) {

            Topic topic = topicService.findByID(id);
            request.setAttribute("topic", topic);
            forward = PagePath.PAGE_EDIT_TOPIC;

        } else {
            forward = PagePath.COMMAND_TOPICS;
        }
        return forward;
    }
}
