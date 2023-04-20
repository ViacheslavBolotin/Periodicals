package com.epam.bolotin.periodicals.model.service;

import com.epam.bolotin.periodicals.model.ReportBuilder;
import com.epam.bolotin.periodicals.model.db.repository.*;
import com.epam.bolotin.periodicals.model.db.repository.mysql.*;
import com.epam.bolotin.periodicals.model.service.implementation.*;

/**
 * @author: Viacheslav Bolotin
 * @date: 20.12.2022
 */
public class AppServices {
    private static volatile AppServices appServices = new AppServices();
    private final UserRepository userRepository = new UserRepositoryMySql();
    private final UserService userService = new UserServiceImpl(userRepository);
    private final AccountRepository accountRepository = new AccountRepositoryMySql();
    private final AccountService accountService = new AccountServiceImpl(accountRepository);
    private final TopicRepository topicRepository = new TopicRepositoryMySql();
    private final TopicService topicService = new TopicServiceImpl(topicRepository);
    private final PublicationRepository publicationRepository = new PublicationRepositoryMySql();
    private final PublicationService publicationService = new PublicationServiceImpl(publicationRepository, accountRepository);
    private final ReportPublicationRepository reportPublicationRepository = new ReportPublicationRepositoryMySql();
    private final ReportService reportService = new ReportServiceImpl(reportPublicationRepository);
    private final ReportBuilder reportBuilder = new ReportBuilder();

    public static AppServices getInstance() {
        return appServices;
    }
    public UserService getUserService() {
        return userService;
    }
    public TopicService getTopicService() {
        return topicService;
    }
    public PublicationService getPublicationService() {
        return publicationService;
    }
    public AccountService getAccountService() {
        return accountService;
    }
    public ReportService getReportService() {
        return reportService;
    }
    public ReportBuilder getReportBuilder() {return reportBuilder;}
}
