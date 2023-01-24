package com.epam.bolotin.periodicals.model.builder;

import com.epam.bolotin.periodicals.model.db.Fields;
import com.epam.bolotin.periodicals.model.db.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder {

    public static void fillUser(ResultSet rs, User user) throws SQLException {
        user.setId(rs.getLong(Fields.USER_ID));
        user.setUserName(rs.getString(Fields.USER_NAME));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
        user.setLastName(rs.getString(Fields.USER_LAST_NAME));
        user.setEmail(rs.getString(Fields.USER_EMAIL));
        user.setBlocked(rs.getBoolean(Fields.USER_BLOCKED));
        user.setUserRoleId(rs.getInt(Fields.USER_ROLE_ID));
        user.setCreateTime(rs.getTimestamp(Fields.USER_CREATE_TIME));
    }
}
