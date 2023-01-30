package by.htp.ex.dao.impl;

import by.htp.ex.bean.UserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;

import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {

    private static List<UserInfo> userList;

    public static List<UserInfo> getUserList() {
        if (userList == null) {
            userList = new ArrayList<>();
            userList.add(new UserInfo("1", "1", "1", "1"));
        }
        return userList;
    }

    @Override
    public boolean logination(String login, String password) throws DaoException {

        for (UserInfo userInfo : getUserList()) {
            if (userInfo.getLogin().equals(login) && userInfo.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public String getRole(String login, String password) {
        return "admin";
    }

    @Override
    public boolean registration(String login, String password, String name, String email) throws DaoException {
        for (UserInfo userInfo : getUserList()) {
            if (userInfo.getLogin().equals(login)) {
                return false;
            }
        }
        addNewUser(login, password, name, email);
        return true;
    }

    @Override
    public void addNewUser(String login, String password, String name, String email) throws DaoException {
        userList.add(new UserInfo(login, password, name, email));
    }

}
