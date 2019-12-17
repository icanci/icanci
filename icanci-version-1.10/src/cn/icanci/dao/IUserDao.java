package cn.icanci.dao;

import java.util.List;

import cn.icanci.domain.User;
import cn.icanci.page.PageResult;

/**
 *  操作 user 的顶级接口
 * @author CC
 *
 */
public interface IUserDao {

	/**
	 * 增加 增加一个stu的信息
	 * @param user  一个stu 对象
	 */
	void save(User user);

	/**
	 * 删除一个user 根据id
	 * 
	 * @param id 需要删除的user的id
	 */
	void delete(Long id);

	/**
	 * 更新user的信息
	 * 
	 * @param id  需要更新的user的信息 id
	 * @param user 需要更新的user的对象
	 */
	void update(Long id, User user);

	/**
	 * 查找某个user的信息
	 * 
	 * @param id 需要查询的user的id
	 * @return 返回一个封装user信息的user对象
	 */
	User get(Long id);

	/**
	 * 查询所有user的信息
	 * 
	 * @return 返回所有user的信息的List集合
	 */
	List<User> listAll();

	/**
	 * 根据 用户的 email查询用户是否存在
	 * @param email 用户的email值
	 * @return	返回一个user对象 或者 null
	 */
	User getUserByEmail(String email);
	
	/**
	 * 根据用户的用户名检查此用户名是不是已经被注册
	 * @param username 	需要检测的用户名
	 * @return	返回user对象或者 null
	 */
	User getUserByUsername(String username);
	
	/**
	 * 分页查询
	 * 
	 * @param currentPage 用户传入:当前页需要走跳转到那一页
	 * @param pageSize    用户传入:当前页最多显示多少
	 * @return 返回 PageResult
	 */
	PageResult query(Integer currentPage, Integer pageSize);

	/**
	 * 判断是不是 User 用户
	 * 
	 * @param u 传入User 对象
	 * @return 返回 User对象
	 */
	User isRoot(User u);
}
