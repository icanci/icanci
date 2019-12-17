package cn.icanci.dao;

import java.util.List;

import cn.icanci.domain.User;
import cn.icanci.domain.UserSpace;
import cn.icanci.page.PageResult;

/**
 * 用户发送的在小璨空间的顶级接口 用来执行 增删改查的功能
 * 
 * @author CC
 *
 */
public interface IUserSpaceDao {

	/**
	 * 保存 user 的一个 小璨小璨
	 * 
	 * @param userSpace UserSpace 对象
	 */
	void save(UserSpace userSpace);

	/**
	 * 根据 userSpace 对象得唯一主键 id 删除此条
	 * 
	 * @param id   传入 对象得唯一主键 id 
	 */
	void delete(Long id);

	/**
	 * 根据匹配 模糊查询 包含的内容
	 * 
	 * @param matching 需要匹配的字符串
	 * @return 返回 UserSpace 对象 如果有就返回 UserSpace对象 集合 没有就返回 null
	 */
	List<UserSpace> get(String matching);

	/**
	 * 查询UserSpace所有的结果 封装为一个集合
	 * 
	 * @return 返回一个 UserSpace类型的集合
	 */
	List<UserSpace> listAll();

	/**
	 * 根据 user 的id 来查找当前用户的所有的小璨
	 * 
	 * @param userId 当前用户id
	 * @return 返回当前用户自己的 小璨 list 集合
	 */
	List<UserSpace> listAll(Long userId);

	/**
	 * 所有查询操作
	 * @param userId  传入	userId
	 * @param databasetable	传入数据库名字
	 * @return	返回 泛型为  UserSpace 的List集合
	 */
	List<UserSpace> listAll(Long userId, String databasetable);

	/**
	 * 分页查询
	 * 
	 * @param currentPage 用户传入:当前页需要走跳转到那一页
	 * @param pageSize    用户传入:当前页最多显示多少
	 * @param id		当时用户的id
	 * @return 返回分页的结果集
	 */
	PageResult query(Integer currentPage, Integer pageSize, Long id);

	/**
	 * 条件查询
	 * @param currentPage	用户传入:当前页需要走跳转到那一页
	 * @param pageSize		用户传入:当前页最多显示多少
	 * @param id		当时用户的id
	 * @param matching	需要匹配的 字符串
	 * @return	返回分页的结果集
	 */
	PageResult query(Integer currentPage, Integer pageSize, Long id, String matching);
	/**
	 * 判断 user 是否有 发表 如果没有
	 * 
	 * @param u 当前登陆的用户
	 * @return 返回当前用户 User 的对象 或者 null
	 */
	User isRoot(User u);
}