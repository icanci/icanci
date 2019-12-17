package cn.icanci.test;

import java.util.List;

import org.junit.jupiter.api.Test;

import cn.icanci.dao.IUserSpaceDao;
import cn.icanci.dao.impl.UserSpaceDaoImpl;
import cn.icanci.domain.UserSpace;

class UserSpaceDaoTest {

	private IUserSpaceDao userSpaceDao = new UserSpaceDaoImpl();
	private UserSpace userSpace = new UserSpace();

	@Test
	void testSave() {
		userSpace.setUserId(7L);
		userSpace.setUserMessages("郑强是真的帅,帅呆了,酷毙了!!!!");
		userSpace.setUserImage("/upload/images.jsp");
		userSpace.setOutputTime(System.currentTimeMillis());
		System.out.println(userSpace);
		userSpaceDao.save(userSpace);
	}

	@Test
	void testDelete() {
		userSpaceDao.delete(2L);
	}

	@Test
	void testGet() {
		String sql = "呆";
		List<UserSpace> u = userSpaceDao.get(sql);
		for (UserSpace userSpace : u) {
			System.out.println(userSpace);
		}
	}

	@Test
	void testListAll() {
		List<UserSpace> list = userSpaceDao.listAll();
		for (UserSpace user : list) {
			System.out.println(user);
		}
	}


	@Test
	void testListAllLong() {
		List<UserSpace> userSelfList = userSpaceDao.listAll(7L);
		for (UserSpace u : userSelfList) {
			System.out.println(u);
		}
	}
	

	/**
	 *	下面的数据有异常 数据库名字传递获取之后会有异常
	 */
	
	
	@Test
	void testListAllLongLongArray() {
		List<UserSpace> userSelfList = userSpaceDao.listAll(7L,"user");
		System.out.println(userSelfList);
		for (UserSpace u : userSelfList) {
			System.out.println(u);
		}
	}

	@Test
	void testQuery() {
	}

	@Test
	void testIsRoot() {
	}

}
