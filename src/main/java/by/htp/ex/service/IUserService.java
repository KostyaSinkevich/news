package by.htp.ex.service;

import by.htp.ex.bean.UserInfo;

public interface IUserService {

    String signIn(String login, String password) throws ServiceException;

    String registration(String login, String password, String name, String email) throws ServiceException;

}
