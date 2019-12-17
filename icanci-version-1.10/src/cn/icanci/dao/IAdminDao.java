package cn.icanci.dao;

import cn.icanci.domain.Admin;

/**
 * 管理员顶级接口
 * @author CC
 *
 */

public interface IAdminDao {
	
	/**
	 * 管理员通过 管理员用户名登陆
	 * @param username 需要判断的用户登陆名
	 * @return 		返回一个Admin 对象
	 */
	Admin LoginByUsername(String username);
	
}
