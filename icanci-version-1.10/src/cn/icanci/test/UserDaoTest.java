package cn.icanci.test;

import java.util.List;

import org.junit.jupiter.api.Test;

import cn.icanci.dao.IUserDao;
import cn.icanci.dao.impl.UserDaoImpl;
import cn.icanci.domain.User;
import cn.icanci.util.MD5Util;

class UserDaoTest {
	private IUserDao userDao = new UserDaoImpl();

	@Test
	void testSave() {
		User user = new User();
		user.setUsername("啊瑞");
		user.setPassword(MD5Util.getMD5String("daimengdaimeng"));
		user.setEmail("daimenngorui@163.com");
		user.setJointime(System.currentTimeMillis());
		user.setSex("男");
		user.setHeadimage("/upload/xu.img");
		System.out.println(user);
		userDao.save(user);
	}

	@Test
	void testDelete() {
		userDao.delete(1L);
	}

	@Test
	void testUpdate() {
		User user = new User();
		user.setUsername("徐xiao瑞");
		user.setPassword(MD5Util.getMD5String("daimengdaimeng"));
		user.setEmail("saorui@163.com");
		user.setJointime(System.currentTimeMillis());
		user.setSex("男");
		user.setHeadimage("/upload/xu.img");
		System.out.println(user);
		userDao.update(8L, user);
	}

	@Test
	void testGet() {
		User u = userDao.get(8L);
		System.out.println(u);
	}

	@Test
	void testListAll() {
		List<User> list = userDao.listAll();
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	void testGetUserByUsername() {
		User u = userDao.getUserByUsername("zq1234");
		System.out.println(u);
	}
	

	@Test
	void testEmail() {
		String email = "1845666903@qq.com";
		User user = userDao.getUserByEmail(email);
		System.out.println(user);
	}
	
	@Test
	void testQuery() {
	}

	@Test
	void testIsRoot() {
	}

	@Test
	void testLong() {
		System.out.println("a1677b48b9f5a06b500cd1974d89f3d4".length());
	}
	
	@Test
	void testTime() {
		
	}
}
