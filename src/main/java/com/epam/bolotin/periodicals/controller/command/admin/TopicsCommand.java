package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Viacheslav Bolotin
 * @date: 02.01.2023
 */
public class TopicsCommand implements Command {

    private TopicService topicService = AppServices.getInstance().getTopicService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("topics", topicService.findAll());
        return PagePath.PAGE_TOPICS;
    }
}
