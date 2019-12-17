package cn.icanci.hander;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.BeanInfo;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.icanci.dao.IResultSetHander;

public class ListBeanHander<T>implements IResultSetHander<List<T>> {

	private Class<T> classType;
	public ListBeanHander(Class<T> classType){
		this.classType = classType;
	}
	@Override
	public List<T> hander(ResultSet rs) throws Exception {
		List<T> list = new ArrayList<>();
		while(rs.next()) {
			T obj = classType.newInstance();
			list.add(obj);
			BeanInfo beanInfo = Introspector.getBeanInfo(classType, Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : pds) {
				String colunmnName = propertyDescriptor.getName();
				System.out.println(propertyDescriptor);
				System.out.println(colunmnName);
				Object val = rs.getObject(colunmnName);
				propertyDescriptor.getWriteMethod().invoke(obj,val);
			}
		}
		return list;
	}

}
