package cn.icanci.hander;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

public class BeanHander<T> implements cn.icanci.dao.IResultSetHander<T> {
	
	private Class<T> classType;//把结果集中的一行数据封装成一个对象
	public BeanHander(Class<T> classType) {
		this.classType = classType;
	}
	@Override
	public T hander(ResultSet rs) throws Exception {
		T obj = classType.newInstance();
		BeanInfo beanInfo = Introspector.getBeanInfo(classType,Object.class);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		if(rs.next()) {
			for (PropertyDescriptor propertyDescriptor : pds) {
				String columnName = propertyDescriptor.getName();
				Object val = rs.getObject(columnName);
				propertyDescriptor.getWriteMethod().invoke(obj, val);
			}
		}	
		return obj;
	}
}
