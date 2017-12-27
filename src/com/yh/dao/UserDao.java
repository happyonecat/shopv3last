package com.yh.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yh.pojo.User;
import com.yh.utils.DataSourceUtils;

public class UserDao {

	public int regist(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		int update = runner.update(sql, user.getUid(),user.getUsername(),user.getPassword(),
				user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),
				user.getSex(),user.getState(),user.getCode());
		return update;
	}

	//激活
	public void active(String activeCode) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set state=? where code=?";
		runner.update(sql, 1,activeCode);
	}

	//校验用户名是否存在
	public Long checkUsername(String username) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from user where username=?";
		Long query = (Long) runner.query(sql, new ScalarHandler(), username);
		return query;
	}

	    //用户登录的方法
		public User login(String username, String password) throws SQLException {
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "select * from user where username=? and password=?";
			return runner.query(sql, new BeanHandler<User>(User.class), username,password);
		}
        //查找code,是否存在
		public User findByCode(String activeCode) throws SQLException {
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "select * from user where code=?";
			return runner.query(sql, new BeanHandler<User>(User.class), activeCode);
		}

		public void updateUser(User exitUser) throws SQLException {
			QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
			String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
			int update = runner.update(sql, exitUser.getUid(),exitUser.getUsername(),exitUser.getPassword(),
					exitUser.getName(),exitUser.getEmail(),exitUser.getTelephone(),exitUser.getBirthday(),
					exitUser.getSex(),exitUser.getState(),exitUser.getCode());
			
		}

}
