package cn.icanci.test;


import org.junit.jupiter.api.Test;

import cn.icanci.dao.IAdminDao;
import cn.icanci.dao.impl.AdminDaoImpl;
import cn.icanci.domain.Admin;

class AdminDaoTest {

	@Test
	void testLoginByUsername() {
		String username = "icanci";
		IAdminDao adminDao = new AdminDaoImpl();
		Admin a =  adminDao.LoginByUsername(username);
		System.out.println(a);
	}

}
