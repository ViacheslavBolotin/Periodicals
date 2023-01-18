package com.epam.bolotin.periodicals.model.builder;

import com.epam.bolotin.periodicals.model.db.Fields;
import com.epam.bolotin.periodicals.model.db.dto.AccountDto;
import com.epam.bolotin.periodicals.model.db.entity.Account;
import com.epam.bolotin.periodicals.model.db.entity.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountBuilder {

    public static void fillAccount(ResultSet rs, Account account) throws SQLException {
        account.setId(rs.getLong(Fields.ACCOUNT_ID));
        account.setUserId(rs.getLong(Fields.ACCOUNT_USER_ID));
        account.setAmount(rs.getBigDecimal(Fields.ACCOUNT_AMOUNT));
    }

    public static void fillAccountDto(ResultSet rs, AccountDto account) throws SQLException {
        account.setId(rs.getLong(Fields.ACCOUNT_ID));
        account.setUserId(rs.getLong(Fields.ACCOUNT_USER_ID));
        account.setAmount(rs.getBigDecimal(Fields.ACCOUNT_AMOUNT));
        account.setUserName(rs.getString(Fields.USER_NAME));
        account.setFullName(rs.getString(Fields.USER_LAST_NAME)+" "+rs.getString(Fields.USER_FIRST_NAME));
    }
}
