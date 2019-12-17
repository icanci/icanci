package cn.icanci.dao.impl;

import java.util.List;

import cn.icanci.dao.IFriendRelationshipDao;
import cn.icanci.domain.FriendRelationship;
import cn.icanci.hander.ListBeanHander;
import cn.icanci.template.JDBCTemplate;

public class FriendRelationshipDaoImpl implements IFriendRelationshipDao{

	@Override
	public void save(FriendRelationship friendRelationship) {
		//编写sql语句
		String sql = "insert into friendrelationship (userId1,userId2) values (?,?)";
		JDBCTemplate.update(sql, friendRelationship.getUserId1(),friendRelationship.getUserId2());
	}

	@Override
	public void delete(Long id) {
		//编写 sql 语句
		String sql = "delete from friendrelationship where id = ?";
		JDBCTemplate.update(sql, id);
	}

	@Override
	public List<FriendRelationship> listAll(Long userID) {
		//编写sql语句
		String sql = "select * from friendrelationship where friendrelationship.userId1=?";
		return JDBCTemplate.query(sql, new ListBeanHander<>(FriendRelationship.class), userID);
	}

}
