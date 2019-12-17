package cn.icanci.dao.impl;

import cn.icanci.dao.IAdminDao;
import cn.icanci.domain.Admin;
import cn.icanci.hander.BeanHander;
import cn.icanci.template.JDBCTemplate;

public class AdminDaoImpl implements IAdminDao {

	@Override
	public Admin LoginByUsername(String username) {
		//编写sql语句
		return JDBCTemplate.query("select * from admin where username = ?",new BeanHander<>(Admin.class), username);
	}

}
