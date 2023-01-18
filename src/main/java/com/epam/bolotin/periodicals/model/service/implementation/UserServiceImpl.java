package com.epam.bolotin.periodicals.model.service.implementation;

import com.epam.bolotin.periodicals.Crypt;
import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.model.Validator;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.db.repository.UserRepository;
import com.epam.bolotin.periodicals.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 20.12.2022
 */
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
//    private final IAccountService accountService;

    /**
     * All args constructor.
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User findByID(long id) {
        return this.userRepository.getById(id);
    }
    @Override
    public User findByName(String userName) {
        return this.userRepository.getByName(userName);
    }
    @Override
    public List<User> findAll() {
        return this.userRepository.getAll();
    }

    @Override
    public void save(User user) throws AppException {
        userRepository.create(user);
    }

    @Override
    public void update(User user) throws AppException {
        userRepository.update(user);
    }

    @Override
    public void delete(long id) throws AppException {
        userRepository.delete(id);
    }

    @Override
    public boolean validateAndFillUser(User user, HttpServletRequest request){
        String errorMessage;
        String tempString = request.getParameter("login").trim();
        errorMessage = Validator.validateLogin(tempString,16);
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            return false;
        }
        user.setUserName(tempString);

        tempString = request.getParameter("password").trim();
        errorMessage = Validator.validateSentences(tempString, "password", 45);
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            return false;
        }
        user.setPassword(Crypt.hashPassword(tempString));

        tempString = request.getParameter("firstName").trim();
        errorMessage = Validator.validateNames(tempString, "firstName", 45);
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            return false;
        }
        user.setFirstName(tempString);

        tempString = request.getParameter("lastName").trim();
        errorMessage = Validator.validateNames(tempString, "lastName", 45);
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            return false;
        }
        user.setLastName(tempString);

        tempString = request.getParameter("email").trim();
        errorMessage = Validator.validateEMail(tempString);
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            return false;
        }
        user.setEmail(tempString);

        return true;
    }

    @Override
    public void blockUnblockUser(User user) throws AppException {
        user.setBlocked(!user.isBlocked());
        this.update(user);
    }
}
