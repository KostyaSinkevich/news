package by.htp.ex.service.impl;

import by.htp.ex.bean.UserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.IUserService;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
//	private final UserDataValidation userDataValidation = ValidationProvider.getIntsance().getUserDataVelidation();

    @Override
    public String signIn(String login, String password) throws ServiceException {

        /*
         * if(!userDataValidation.checkAUthData(login, password)) { throw new
         * ServiceException("login ...... "); }
         */

        try {
            if (userDAO.logination(login, password)) {
                return userDAO.getRole(login, password);
            } else {
                return "user";
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public String registration(String login, String password, String name, String email) throws ServiceException {
        try {
            if (userDAO.registration(login, password, name, email)) {
                return "user";
            } else {
                return userDAO.getRole(login, password);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
