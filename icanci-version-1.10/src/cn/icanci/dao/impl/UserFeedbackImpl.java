package cn.icanci.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import cn.icanci.dao.IResultSetHander;
import cn.icanci.dao.IUserFeedbackDao;
import cn.icanci.domain.UserFeedback;
import cn.icanci.hander.ListBeanHander;
import cn.icanci.page.PageResult;
import cn.icanci.template.JDBCTemplate;

@SuppressWarnings("all")
public class UserFeedbackImpl implements IUserFeedbackDao {

	@Override
	public void save(UserFeedback u) {
		// 编写sql语句 用来向数据库保存信息
		String sql = "insert into userFeedback (feedbackTheme,feedbackContent,feedbackTime,outPrint) values (?,?,?,?)";
		JDBCTemplate.update(sql, u.getFeedbackTheme(), u.getFeedbackContent(), u.getFeedbackTime(), u.getOutPrint());
	}

	@Override
	public void delete(Long id) {
		// 编写sql语句
		String sql = "delete from userFeedback where id = ?";
		JDBCTemplate.update(sql, id);
	}

	@Override
	public List<UserFeedback> listAll() {
		return JDBCTemplate.query("select id,feedbackTheme,feedbackContent,feedbackTime,outPrint from userFeedback ",
				new ListBeanHander<>(UserFeedback.class));
	}

	@Override
	public PageResult query(Integer currentPage, Integer pageSize) {
		// 查总个数
		String countSql = "select count(id) from userfeedback";
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
		List<UserFeedback> listData = null;
		if (totalCount == 0) { // 没有需要查询的数据
			listData = null;
		} else {
			String resultSql = "select * from userfeedback ORDER BY feedbackTime DESC LIMIT ?,?";
			Object[] params = { (currentPage - 1) * pageSize, pageSize };
			listData = JDBCTemplate.query(resultSql, new ListBeanHander<>(UserFeedback.class), params);
			for (Object ld : listData) {
				System.out.println("UserFeedbackDaoImpl:" + ld);
			}
		}
		return new PageResult(listData, totalCount, currentPage, pageSize);
	}

	@Override
	public PageResult query(Integer currentPage, Integer pageSize, String matching) {
		List<UserFeedback> listData = null;
		listData = get(matching);
		int totalCount = 0;
		if (listData != null) {
			totalCount = listData.size();
		}
		// 查总个数
		if (totalCount == 0) { // 没有需要查询的数据
			listData = null;
		} else {
			String sql = "select * from userfeedback where feedbackTheme like ? or feedbackContent like ?  ORDER BY feedbackTime DESC LIMIT ?,?";
//			String resultSql = "select * from userfeedback where feedbackContent like ? or feedbackTheme like ? ORDER BY feedbackTime DESC LIMIT ?,?";
			Object[] params = { matching, matching, (currentPage - 1) * pageSize, pageSize };
			listData = JDBCTemplate.query(sql, new ListBeanHander<>(UserFeedback.class), params);
			for (Object ld : listData) {
				System.out.println("userfeedbackDaoImpl:" + ld);
			}
		}
		return new PageResult(listData, totalCount, currentPage, pageSize);
	}
	@Override
	public List<UserFeedback> get(String matching) {
		// 编写sql语句
		String sql = "select * from userfeedback where feedbackTheme like ? or feedbackContent like ? ORDER BY feedbackTime DESC";
		return JDBCTemplate.query(sql, new ListBeanHander<>(UserFeedback.class), matching,matching);
	}
}
