package cn.icanci.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import cn.icanci.dao.IResultSetHander;
import cn.icanci.dao.IUserDao;
import cn.icanci.domain.User;
import cn.icanci.hander.BeanHander;
import cn.icanci.hander.ListBeanHander;
import cn.icanci.page.PageResult;
import cn.icanci.template.JDBCTemplate;

public class UserDaoImpl implements IUserDao {

	// 定义变量
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	@Override
	public void save(User user) {
		// 编写sql语句 用来向数据库保用户信息
		String sql = "insert into user (username,password,email,jointime,sex,headimage) values (?,?,?,?,?,?)";
		JDBCTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getJointime(),
				user.getSex(), user.getHeadimage());
	}

	@Override
	public void delete(Long id) {
		// 编写sql语句 根据id用来删除一个用户
		String sql = "delete from user where id = ?";
		JDBCTemplate.update(sql, id);
	}

	@Override
	public void update(Long id, User user) {
		// 编写sql语句 用来更新用户的信息
		String sql = "update user set username=?,password=?,email=?,headimage=?,sex=?,age=?,personalizedSignature=? where id = ?";
		JDBCTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getHeadimage(),
				user.getSex(),user.getAge(),user.getPersonalizedSignature(), id);
	}

	@Override
	public User get(Long id) {
		// 编写sql 语句 获取一个学生的信息
		return JDBCTemplate.query("select * from user where id = ? ", new BeanHander<>(User.class), id);
	}

	@Override
	public List<User> listAll() {
		// 编写sql语句 获取所有学生的信息'
		return JDBCTemplate.query("select * from user", new ListBeanHander<>(User.class));
	}

	@Override
	public PageResult query(Integer currentPage, Integer pageSize) {
		// 查总个数
		String countSql = "select count(id) from user";
		int totalCount = JDBCTemplate.query(countSql, new IResultSetHander<Long>() {

			@Override
			public Long hander(ResultSet rs) throws Exception {
				if (rs.next()) {
					return rs.getLong(1);
				}
				return 0L;
			}
		}).intValue();
		// ------------------------------
		// 查询结果集
		List<User> listData = null;
		if (totalCount == 0) { // 没有需要查询的数据
			listData = null;
		} else {
			String resultSql = "select * from user limit ?,?";
			Object[] params = { (currentPage - 1) * pageSize, pageSize };
			listData = JDBCTemplate.query(resultSql, new ListBeanHander<>(User.class), params);
		}
		return new PageResult(listData, totalCount, currentPage, pageSize);
	}

	@Override
	public User isRoot(User u) {
		String sql = "select * from user where username = ? and password = ?";
		return JDBCTemplate.query(sql, new IResultSetHander<User>() {

			@Override
			public User hander(ResultSet rs) throws Exception {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getLong("id"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					return user;
				}
				return null;
			}
		}, u.getUsername(), u.getPassword());
	}

	@Override
	public User getUserByEmail(String email) {
		// 编写sql语句
		return JDBCTemplate.query("select * from user where email = ? ", new BeanHander<>(User.class), email);
	}

	@Override
	public User getUserByUsername(String username) {
		// 编写sql语句
		return JDBCTemplate.query("select * from user where username = ? ", new BeanHander<>(User.class), username);
	}
}
