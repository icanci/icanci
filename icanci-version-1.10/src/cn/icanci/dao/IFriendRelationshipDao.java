package cn.icanci.dao;

import java.util.List;
import cn.icanci.domain.FriendRelationship;

public interface IFriendRelationshipDao {

	/**
	 * 保存一个好友关系
	 * 
	 * @param friendRelationship 一个好友关系对象
	 */
	void save(FriendRelationship friendRelationship);

	/**
	 * 删除一个关系
	 * 
	 * @param id 需要删除的关系id编号
	 */
	void delete(Long id);

	/**
	 * 显示好友列表
	 * @param userID  当前用户的 id
	 * @return   返回当前用户的所有的好友
	 */
	List<FriendRelationship> listAll(Long userID);
}
