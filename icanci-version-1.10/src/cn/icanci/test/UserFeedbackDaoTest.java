package cn.icanci.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import cn.icanci.dao.IUserFeedbackDao;
import cn.icanci.dao.impl.UserFeedbackImpl;
import cn.icanci.domain.UserFeedback;

class UserFeedbackDaoTest {
	IUserFeedbackDao userFeedbackDao = new UserFeedbackImpl();
	UserFeedback u = new UserFeedback();

	@Test
	void testSave() {
		u.setFeedbackTheme("有人完危险");
		u.setFeedbackContent("在11月22日晚上11点,某某发送xxx在竞速大厦打架");
		u.setFeedbackTime(System.currentTimeMillis());
		u.setOutPrint("2029-11-30 12:23:32");
		userFeedbackDao.save(u);
	}

	@Test
	void testDelete() {
		userFeedbackDao.delete(10L);
	}

	@Test
	void TestList() {
		List<UserFeedback> list = new ArrayList<>();
		list = userFeedbackDao.listAll();
		for (UserFeedback userFeedback : list) {
			System.out.println(userFeedback);
		}
	}

	@Test
	void testGet() {
		
	}

	@Test
	void testQueryIntegerInteger() {
	}
	

	@Test
	void testQueryIntegerIntegerString() {
	}

}
