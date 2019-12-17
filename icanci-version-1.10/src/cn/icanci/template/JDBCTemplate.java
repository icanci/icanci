package cn.icanci.template;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;

import cn.icanci.dao.IResultSetHander;
import cn.icanci.util.JDBCUtil;

public class JDBCTemplate {
	/**
	 * 重构DML操作
	 * 
	 * @param sql     sql语句 带有占位符
	 * @param objects Object数组
	 * @return 返回影响的行数
	 */
	public static int update(String sql, Object... objects) {
		Connection conn = null;
		PreparedStatement stmt = null;
		conn = JDBCUtil.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			for (int index = 0; index < objects.length; index++) {
				stmt.setObject(index + 1, objects[index]);
			}
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, null);
		}
		return 0;
	}

	/**
	 * 查询操作
	 * 
	 * @param <T>     查询的JavaBean的类型
	 * @param sql     需要执行的sql语句
	 * @param rsh     顶级接口 IResultSetHander 的接口实现
	 * @param objects 不定长参数数组 用来填充预编译语句的?
	 * @return 返回一个结果集
	 */
	public static <T> T query(String sql, IResultSetHander<T> rsh, Object... objects) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = JDBCUtil.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			System.out.println(sql);
			for (int index = 0; index < objects.length; index++) {
				// 匹配到 like
				if (sql.contains("like") && index == 0) {
					Object obj = "%" + objects[index] + "%";
					stmt.setObject(index + 1, obj);
				} else {
					stmt.setObject(index + 1, objects[index]);
				}
			}
			rs = stmt.executeQuery();
			return rsh.hander(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, rs);
		}
		throw new RuntimeException("查询有异常");
	}

	public static <T> T query(String sql, IResultSetHander<T> rsh, Long... userIds) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = JDBCUtil.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			for (int index = 0; index < userIds.length; index++) {
				stmt.setObject(index + 1, userIds[index]);
			}
			rs = stmt.executeQuery();
			return rsh.hander(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, rs);
		}
		throw new RuntimeException("查询有异常");
	}

}
