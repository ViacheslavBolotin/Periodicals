package com.epam.bolotin.periodicals.model.db.repository;

import com.epam.bolotin.periodicals.exception.DBException;
import com.epam.bolotin.periodicals.model.db.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {

    void create(User user) throws DBException;
    void update(User user) throws DBException;
    void delete(long id) throws DBException;
    User getById(long id);
    User getByName(String userName);
    List<User> getAll();
}
