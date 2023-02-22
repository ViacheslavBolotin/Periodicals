package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.controller.util.PaginationSort;
import com.epam.bolotin.periodicals.controller.util.RequestUtils;
import com.epam.bolotin.periodicals.model.service.dto.AccountDto;
import com.epam.bolotin.periodicals.model.service.AccountService;
import com.epam.bolotin.periodicals.model.service.AppServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 29.12.2022
 */
public class AccountsCommand implements Command {

    private AccountService accountService = AppServices.getInstance().getAccountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        PaginationSort ps = new PaginationSort();
        ps.setSortType(RequestUtils.getStringParameter(request, PagePath.SORT_PARAMETER));
        ps.setCurrentPage(RequestUtils.getIntParameter(request, PagePath.PAGE_PARAMETER));
        ps.setNumberOfItems(accountService.findSize());
        ps.calc();

        List<AccountDto> accounts = accountService.findAllLimitSort(ps.getStartFrom(), ps.MAX_ITEM_ON_PAGE, ps.getSortType());
        request.setAttribute("fullAccount", accounts);
        request.setAttribute("ps", ps);

        return PagePath.PAGE_ACCOUNTS;
    }
}
