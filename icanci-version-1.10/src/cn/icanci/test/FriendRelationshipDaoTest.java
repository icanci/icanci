package cn.icanci.test;

import java.util.List;

import org.junit.jupiter.api.Test;

import cn.icanci.dao.IFriendRelationshipDao;
import cn.icanci.dao.impl.FriendRelationshipDaoImpl;
import cn.icanci.domain.FriendRelationship;

class FriendRelationshipDaoTest {

	IFriendRelationshipDao friendRelationshipDao = new FriendRelationshipDaoImpl();
	FriendRelationship friendRalationship = new FriendRelationship();
	
	
	@Test
	void testSave() {
		friendRalationship.setUserId1(7L);
		friendRalationship.setUserId2(22L);
		System.out.println(friendRalationship);
		friendRelationshipDao.save(friendRalationship);
	}

	@Test
	void testDelete() {
		friendRelationshipDao.delete(2L);
	}

	@Test
	void testListAll() {
		List<FriendRelationship> list = friendRelationshipDao.listAll(7L);
		for (FriendRelationship f : list) {
			System.out.println(f);
		}
	}

}
