package cn.icanci.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import cn.icanci.dao.IResultSetHander;
import cn.icanci.dao.IUserSpaceDao;
import cn.icanci.domain.User;
import cn.icanci.domain.UserSpace;
import cn.icanci.hander.ListBeanHander;
import cn.icanci.page.PageResult;
import cn.icanci.template.JDBCTemplate;

public class UserSpaceDaoImpl implements IUserSpaceDao {

	@Override
	public void save(UserSpace userSpace) {
		// 编写sql语句
		String sql = "insert into userspace (userId,userMessages,userImage,outputTime,outPrint) values (?,?,?,?,?)";
		JDBCTemplate.update(sql, userSpace.getUserId(), userSpace.getUserMessages(), userSpace.getUserImage(),
				userSpace.getOutputTime(),userSpace.getOutPrint());
	}

	@Override
	public void delete(Long id) {
		// 编写sql语句
		String sql = "delete from userspace where id = ?";
		JDBCTemplate.update(sql, id);
	}

	@Override
	public List<UserSpace> get(String matching) {
		// 编写sql语句
		String sql = "select id,userId,userMessages,userImage,outputTime,outPrint from userspace where userMessages like ?  order by outputTime desc";
		return JDBCTemplate.query(sql, new ListBeanHander<>(UserSpace.class), matching);
	}

	@Override
	public List<UserSpace> listAll() {
		String sql = "select id,userId,userMessages,userImage,outputTime,outPrint from userspace order by outputTime desc";
		return JDBCTemplate.query(sql, new ListBeanHander<>(UserSpace.class));
	}

	@Override
	public List<UserSpace> listAll(Long userId) {
		String sql = "select id,userId,userMessages,userImage,outputTime,outPrint from userspace where userId = ? order by outputTime desc";
		return JDBCTemplate.query(sql, new ListBeanHander<>(UserSpace.class), userId);
	}

	@Override
	public List<UserSpace> listAll(Long userId, String database) {
		String sql = "select userspace.id,userspace.userId,userspace.userMessages,userspace.userImage,userspace.outputTime,userspace.outPrint from ?,userspace where ?.id=? order by outputTime desc";
		return JDBCTemplate.query(sql, new ListBeanHander<>(UserSpace.class), database, database, userId);
	}

	public PageResult query(Integer currentPage, Integer pageSize, Long id, String matching) {
		List<UserSpace> listData = null;
		listData = get(matching);
		int totalCount = 0;
		if (listData != null) {
			totalCount = listData.size();
		}
		// 查总个数
		// ------------------------------
		// 查询结果集
//		List<UserSpace> listData = null;
		if (totalCount == 0) { // 没有需要查询的数据
			listData = null;
		} else {
			
			//String sql = "select id,userId,userMessages,userImage,outputTime from userspace where userMessages like ?  order by outputTime desc";
			String resultSql = "select id,userId,userMessages,userImage,outputTime,outPrint from userspace where userMessages like ? ORDER BY outputTime DESC LIMIT ?,?";
//			String resultSql = "select * from userspace where userMessages like '%"+matching+"%' ORDER BY outputTime DESC LIMIT ?,?";
			Object[] params = { matching,(currentPage - 1) * pageSize, pageSize };
			listData = JDBCTemplate.query(resultSql, new ListBeanHander<>(UserSpace.class), params);
			for (Object ld : listData) {
				System.out.println("userSpaceDaoImpl:" + ld);
			}
		}
		return new PageResult(listData, totalCount, currentPage, pageSize);
	}

	@Override
	public PageResult query(Integer currentPage, Integer pageSize, Long id) {
		// 查总个数
		String countSql = "select count(userspace.id) from userspace where userspace.userId=" + id;
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
		List<UserSpace> listData = null;
		if (totalCount == 0) { // 没有需要查询的数据
			listData = null;
		} else {
			String resultSql = "select * from userspace where userId =? ORDER BY outputTime DESC LIMIT ?,?";
			Object[] params = {id, (currentPage - 1) * pageSize, pageSize };
			listData = JDBCTemplate.query(resultSql, new ListBeanHander<>(UserSpace.class), params);
			for (Object ld : listData) {
				System.out.println("userSpaceDaoImpl:" + ld);
			}
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

}
