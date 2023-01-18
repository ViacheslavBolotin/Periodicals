package com.epam.bolotin.periodicals.controller.command.client;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.controller.util.RequestUtils;
import com.epam.bolotin.periodicals.model.db.entity.Account;
import com.epam.bolotin.periodicals.model.service.AccountService;
import com.epam.bolotin.periodicals.model.service.AppServices;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * @author: Viacheslav Bolotin
 * @date: 02.01.2023
 */
public class UpdateAccountCommand implements Command {

    private static final Logger LOG = Logger.getLogger(UpdateAccountCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        AccountService accountService = AppServices.getInstance().getAccountService();
        String resp;
        BigDecimal replenishSum = new BigDecimal(0);

        Account account = new Account();
        long id = Long.parseLong(request.getParameter("account_id"));
        replenishSum = RequestUtils.getBigDecimalParameter(request, "replenish_sum");
        account.setId(id);

        try {

            if (replenishSum.doubleValue() > 0) {

                account = accountService.findByID(account.getId());
                account.setAmount(account.getAmount().add(replenishSum));
                accountService.update(account);
                resp = PagePath.COMMAND_ACCOUNT;

                LOG.info("Account update (id = " + account.getId() + ")");

                response.sendRedirect(resp);
                resp = PagePath.COMMAND_REDIRECT;
                LOG.debug("Account update (COMMAND_REDIRECT)");

            } else {
                if (replenishSum.doubleValue() <= 0) {

                    request.setAttribute("errorMessage", "Replenish amount is less than or equal to 0!");
                    resp = PagePath.COMMAND_ACCOUNT;
                    LOG.debug("Account update (COMMAND_REDIRECT)");

                } else {
                    resp = PagePath.PAGE_ERROR;
                    LOG.info("Account (id = " + account.getId() + ") cannot update");
                }
            }

        } catch (Exception e) {
            resp = PagePath.PAGE_ERROR;
            LOG.error("Account (id = " + account.getId() + ") update error (" + e.getMessage() + ")");
        }
        return resp;
    }
}
