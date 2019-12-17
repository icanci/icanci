package cn.icanci.page;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

//分页的结果对象
@SuppressWarnings("all")
@Getter
public class PageResult {
	private List<?> listData; // 结果集数据,通过SQL查询
	private Integer totalCount; // 结果总数,通过SQL查询

	private Integer currentPage = 1; // 当前页 用户传入
	private Integer pageSize = 5; // 每一页条数 用户传入

	private Integer beginPage = 1;// 首页
	private Integer prevPage;// 上页 计算
	private Integer nextPage;// 下页 计算
	private Integer totalPage;// 末页 计算

	public PageResult(List<?> listData, Integer totalCount, Integer currentPage, Integer pageSize) {
		super();
		this.listData = listData;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		this.prevPage = currentPage - 1 >= 1 ? currentPage - 1 : 1;
		this.nextPage = currentPage + 1 <= totalPage ? currentPage + 1 : totalPage;
		
	}
	
	
}
