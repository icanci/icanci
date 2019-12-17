package cn.icanci.dao;

import java.util.List;

import cn.icanci.domain.UserFeedback;
import cn.icanci.page.PageResult;

/**
 * 用户反馈的顶级接口
 * @author CC
 *
 */
public interface IUserFeedbackDao {
	/**
	 * 保存游客或者用户的意见反馈 匿名反馈
	 * @param u 返回一个  UserFeedback 对象
	 */
	void save(UserFeedback u);
	
	/**
	 * 管理员查看用户提交的反馈建议 可以删除一些没有实质意义的反馈 以做到精准定位用户需求
	 * @param id 需要删除的 UserFeedback id
	 */
	void delete(Long id);
	
	/**
	 * 用来查询所有的反馈建议 
	 * @return  返回 List UserFeedback 集合
	 */
	List<UserFeedback> listAll();

	/**
	 * 根据匹配 模糊查询 包含的内容
	 * 
	 * @param matching 需要匹配的字符串
	 * @return 返回 UserSpace 对象 如果有就返回 UserSpace对象 集合 没有就返回 null
	 */
	List<UserFeedback> get(String matching);
	
	/**
	 * 分页查询
	 * 
	 * @param currentPage 管理员传入:当前页需要走跳转到那一页
	 * @param pageSize    管理员传入:当前页最多显示多少
	 * @return 返回 PageResult 集合
	 */
	PageResult query(Integer currentPage, Integer pageSize);
	
	/**
	 * 分页查询
	 * @param currentPage	管理员传入:当前页需要走跳转到那一页
	 * @param pageSize		管理员传入:当前页最多显示多少
	 * @param matching		管理员关键字查询
	 * @return				返回结果集对象
	 */
	PageResult query(Integer currentPage, Integer pageSize,String matching);
	

}
