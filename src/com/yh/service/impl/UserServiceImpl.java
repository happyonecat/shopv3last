package com.yh.service.impl;

import java.sql.SQLException;

import javax.management.RuntimeErrorException;

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
		//1 通过激活码查询用户
		User exitUser = dao.findByCode(activeCode);
		if(exitUser == null){
			//自定义异常
			throw new RuntimeException("用户激活码无效,请重试或重新发送邮件");
		}
		dao.active(activeCode); //激活,调用userDao,把state设置为1,代表激活了
		exitUser.setCode(null);//假如激活过了,把code设置为null
		dao.updateUser(exitUser);
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
