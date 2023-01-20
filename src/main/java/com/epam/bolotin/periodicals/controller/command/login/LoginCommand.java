package com.epam.bolotin.periodicals.controller.command.login;

import com.epam.bolotin.periodicals.Crypt;
import com.epam.bolotin.periodicals.controller.FrontController;
import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.controller.util.RequestUtils;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.db.entity.UserRole;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.UserService;
import com.epam.bolotin.periodicals.model.service.implementation.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private UserService service = AppServices.getInstance().getUserService();
    private static final Logger LOG = Logger.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int mainMenu = RequestUtils.getIntParameter(request,"main_menu");

        if (mainMenu == 1) {
            request.setAttribute("errorMessage", RequestUtils.getStringParameter(request,"errorMessage"));
            return PagePath.PAGE_LOGIN;
        }

        HttpSession session = request.getSession();
        String userName = RequestUtils.getStringParameter(request,"user_name");
        String password = RequestUtils.getStringParameter(request,"password");

        // error handler
        String errorMessage;
        String forward = PagePath.PAGE_LOGIN;

        if (userName == null || password == null || userName.isEmpty() || password.isEmpty()) {
            errorMessage = "Login or password can't be empty";
            request.setAttribute("errorMessage", errorMessage);
            LOG.info(errorMessage);
            return forward;
        }

        User user = service.findByName(userName);

        if (user.getUserName() == null || !Crypt.checkPassword(password, user.getPassword())) {
            errorMessage = "Cannot find user with such login or password";
            request.setAttribute("errorMessage", errorMessage);
            LOG.info(errorMessage);
            return forward;
        } else {
            if (user.isBlocked()){
                errorMessage = "You are blocked by admin";
                request.setAttribute("errorMessage", errorMessage);
                LOG.info(errorMessage);
                return forward;
            }
            UserRole userRole = UserRole.getRole(user);

            if (userRole == UserRole.ADMIN) {
                forward = PagePath.COMMAND_SHOW_USERS;
            }

            if (userRole == UserRole.CLIENT) {
                forward = PagePath.COMMAND_PERSONAL_CABINET;
            }

            session.setAttribute("user", user);
            session.setAttribute("userRole", userRole);
            LOG.info("User " + user.getUserName() + " successfully logged in");
        }
        return forward;
    }
}
