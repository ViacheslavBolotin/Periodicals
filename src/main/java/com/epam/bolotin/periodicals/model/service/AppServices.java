package com.epam.bolotin.periodicals.model.service;

import com.epam.bolotin.periodicals.model.db.repository.AccountRepository;
import com.epam.bolotin.periodicals.model.db.repository.PublicationRepository;
import com.epam.bolotin.periodicals.model.db.repository.TopicRepository;
import com.epam.bolotin.periodicals.model.db.repository.UserRepository;
import com.epam.bolotin.periodicals.model.db.repository.mysql.AccountRepositoryMySql;
import com.epam.bolotin.periodicals.model.db.repository.mysql.PublicationRepositoryMySql;
import com.epam.bolotin.periodicals.model.db.repository.mysql.TopicRepositoryMySql;
import com.epam.bolotin.periodicals.model.db.repository.mysql.UserRepositoryMySql;
import com.epam.bolotin.periodicals.model.service.implementation.AccountServiceImpl;
import com.epam.bolotin.periodicals.model.service.implementation.PublicationServiceImpl;
import com.epam.bolotin.periodicals.model.service.implementation.TopicServiceImpl;
import com.epam.bolotin.periodicals.model.service.implementation.UserServiceImpl;

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
}
