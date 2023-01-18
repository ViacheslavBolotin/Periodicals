package com.epam.bolotin.periodicals.model.builder;

import com.epam.bolotin.periodicals.model.db.QueryExecuter;
import com.epam.bolotin.periodicals.model.db.entity.Entity;
import com.epam.bolotin.periodicals.model.db.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 26.12.2022
 */
public class UserQueryBuilder extends QueryExecuter {
    @Override
    public List<User> getListOfResult(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            UserBuilder.fillUser(rs, user);
            users.add(user);
        }
        return users;
    }

    @Override
    public Entity getResult(ResultSet rs) throws SQLException {
        User user = new User();
        while (rs.next()) {
            UserBuilder.fillUser(rs, user);
        }
        return user;
    }
}
