package com.stu.fastpan.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.ObjectMapper;


public final class ReflectUtils {
	private static final Log logger = LogFactory.getLog(ReflectUtils.class);
	
	/**
	 * 将Map形式的键值对中的值转换为泛型形参给出的类中的属性值
	 * 
	 * @param clazz 要转换成pojo的类名
	 * @param source 源数据
	 */
	public static <T extends Object> List<T> MapToListObject(Class<T> clazz, List<Map<String, Object>> source) {
		if(source == null || source.size() == 0)
			return null;
		
		List<T> items = new ArrayList<T>();
		
		for (Map<String, Object> params : source) {
			try {
				T item = clazz.newInstance();
				Field[] fields = item.getClass().getDeclaredFields();
				for(int i = 0 ; i< fields.length;i++){
					String name = fields[i].getName(); // 获取属性的名字
					
					Object value = params.get(name);
					if(value != null && !"".equals(value)){
						//注意下面这句，不设置true的话，不能修改private类型变量的值
						fields[i].setAccessible(true);
						fields[i].set(item, value);
					}
				}
				items.add(item);
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		return items;
	}	
	/*public static void main(String[] args) {
		LinkedHashMap<String, Object>  source1 = new LinkedHashMap<>();
		source1.put("id", 1001);
		source1.put("name", "zsf");
		LinkedHashMap<String, Object>  source2 = new LinkedHashMap<>();
		source2.put("id", 1002);
		source2.put("name", "hzs");
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(source1);
		list.add(source2);
		List<MyTest> object = MapToListObject(MyTest.class,list);
		System.out.println(list);
		System.out.println(object);
	}*/
	
	
}
