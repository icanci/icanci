package cn.icanci.dao;

import java.sql.ResultSet;

/**
 * 结果集处理器
 * 作用:用来规范处结果集
 * @author CC
 * 对应泛型
 *  泛型为 T
 */
public interface IResultSetHander<T> {
	/**
	 * 处理结果集的顶级接口 
	 * @param rs	传入 Set 集合
	 * @return	返回 T 类型对象 T是泛型
	 * @throws Exception	或抛出空指针等其他的异常
	 */
	T hander(ResultSet rs) throws Exception;
}
