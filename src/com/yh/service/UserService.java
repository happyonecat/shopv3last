package com.yh.service;
import java.sql.SQLException;

import com.yh.dao.UserDao;
import com.yh.pojo.User;
public interface UserService {
	public boolean regist(User user);

	//激活
	public void active(String activeCode);

	//校验用户名是否存在
	public boolean checkUsername(String username);


	//用户登录的方法
	public User login(String username, String password) throws SQLException;
	
	
}
