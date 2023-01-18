package com.epam.bolotin.periodicals.controller;

/**
 *  * Path holder (jsp pages, controller commands).
 *
 * @author: Viacheslav Bolotin
 * @date: 16.12.2022
 */
public class PagePath {
    public static final String PAGE_WELCOME = "/index.jsp";
    public static final String PAGE_LOGIN = "/WEB-INF/jsp/login/login.jsp";
    public static final String PAGE_NEW_USER = "/WEB-INF/jsp/login/new_user.jsp";
    public static final String PAGE_USER_PROFILE = "/WEB-INF/jsp/login/edit_user.jsp";
    public static final String PAGE_USER_LIST = "/WEB-INF/jsp/admin/users.jsp";
    public static final String PAGE_PERSONAL_CABINET = "/WEB-INF/jsp/client/userPublications.jsp";
    public static final String PAGE_ACCOUNTS = "/WEB-INF/jsp/admin/accounts.jsp";
    public static final String PAGE_ACCOUNT = "/WEB-INF/jsp/client/account.jsp";
    public static final String PAGE_TOPICS = "/WEB-INF/jsp/admin/topics.jsp";
    public static final String PAGE_EDIT_TOPIC = "/WEB-INF/jsp/admin/edit_topic.jsp";
    public static final String PAGE_PUBLICATIONS = "/WEB-INF/jsp/admin/publications.jsp";
    public static final String PAGE_EDIT_PUBLICATION = "/WEB-INF/jsp/admin/edit_publication.jsp";
    public static final String PAGE_VIEW_PUBLICATION = "/WEB-INF/jsp/client/view_publication.jsp";

    // common commands
    public static final String COMMAND_REDIRECT = "redirect";
    public static final String PAGE_ERROR = "/WEB-INF/jsp/error_page.jsp";
    public static final String COMMAND_LOGIN = "frontController?action=login";
    public static final String COMMAND_NEW_USER = "frontController?action=new_user";

    // admin commands
    public static final String COMMAND_SHOW_USERS = "frontController?action=users";
    public static final String COMMAND_SHOW_ACCOUNTS = "frontController?action=accounts";
    public static final String COMMAND_TOPICS = "frontController?action=topics";
    public static final String COMMAND_PUBLICATIONS = "frontController?action=publications";
//    public static final String COMMAND_PROFILE = "frontController?action=profile";

    // client commands
    public static final String COMMAND_PERSONAL_CABINET = "frontController?action=personal_cabinet";
    public static final String COMMAND_ACCOUNT = "frontController?action=account";

    // i18n
    public static final String LOCALE_NAME_UK = "uk";
    public static final String LOCALE_NAME_EN = "en";

    //Pagination, sort, filter
    public static final String SORT_PARAMETER = "sort";
    public static final String PAGE_PARAMETER = "page";
    public static final String TOPIC_FILTER_PARAMETER = "topic_filter";
    public static final String TITLE_FILTER_PARAMETER = "title_filter";

    public static final String USER_FILTER_PARAMETER = "user_filter";
}
