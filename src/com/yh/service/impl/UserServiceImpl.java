package com.yh.service.impl;

import java.sql.SQLException;

import com.yh.dao.UserDao;
import com.yh.pojo.User;
import com.yh.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public boolean regist(User user) {
		UserDao dao = new UserDao();
		int row = 0;
		try {
			row = dao.regist(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return row>0?true:false;
	}

	@Override
	public void active(String activeCode) {
		UserDao dao = new UserDao();
		try {
			dao.active(activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean checkUsername(String username) {
		UserDao dao = new UserDao();
		Long isExist = 0L;
		try {
			isExist = dao.checkUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist>0?true:false;
	}

	@Override
	public User login(String username, String password) throws SQLException {
		UserDao dao = new UserDao();
		return dao.login(username,password);
	}

}
