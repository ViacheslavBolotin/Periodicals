package com.epam.bolotin.periodicals.model.service;

import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.exception.DBException;
import com.epam.bolotin.periodicals.model.db.entity.User;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findByID(long id);
    User findByName(String userName);
//
//    List<User> findAllFullInfo();
//
//    User findByNameFullInfo(String login);

    void save(User user) throws AppException;
    void update(User user) throws AppException;
    void delete(long id) throws AppException;

    boolean validateAndFillUser(User user, HttpServletRequest request);

    void blockUnblockUser(User user) throws AppException;
}
