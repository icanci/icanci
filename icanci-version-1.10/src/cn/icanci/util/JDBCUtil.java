package cn.icanci.util;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JDBCUtil {
	private static Properties p = new Properties();
	private static DataSource ds =null;
	//加载驱动 连接数据库 定义PreparedStatement 语句   执行sql语句 关闭连接
	static {
		try {
			//从本地的class路径寻找配置文件的资源
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("druid.properties"));
			ds = DruidDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取数据库连接 加载驱动在druid连接池就已经完成了
	 * @return  返回 连接对象
	 */
	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 把连接还给连接池
	 * @param conn	Connection 对象
	 * @param stmt	PreparedStatement 对象
	 * @param rs	ResultSet 对象
	 */
	public static void close(Connection conn,PreparedStatement stmt,ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
		}catch(Exception e) {
			
		}finally {
			try {
				if(stmt!=null) {
					stmt.close();
				}
			}catch(Exception e) {
				
			}finally {
				try {
					if(conn!=null) {
						conn.close();
					}
				}catch(Exception e) {
				}
			}
		}
	}
}
