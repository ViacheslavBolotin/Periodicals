package com.epam.bolotin.periodicals.controller.command.client;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.model.db.entity.Account;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.service.AccountService;
import com.epam.bolotin.periodicals.model.service.AppServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Viacheslav Bolotin
 * @date: 24.12.2022
 */
public class AccountCommand implements Command {

    private final AccountService accountService =  AppServices.getInstance().getAccountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = (User) request.getSession().getAttribute("user");
        Account account = accountService.findByUserId(user.getId());
        request.setAttribute("account", account);

        return PagePath.PAGE_ACCOUNT;
    }
}
