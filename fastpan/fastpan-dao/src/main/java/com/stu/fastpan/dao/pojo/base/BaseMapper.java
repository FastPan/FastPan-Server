package com.stu.fastpan.dao.pojo.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * Sql映射处理基接口
 * 
 * <p>
 * Sql映射处理基接口：实现单表CURD基本操作,继承此类的子类的sql映射文件必须定义相对应的sql语句
 * </p>
 */
public interface BaseMapper<T, PK extends Serializable> {

	/**
	 * 根据主键查找实体对象
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public T get(PK id);

	/**
	 * 增加实体对象
	 * 
	 * @param entity
	 *            T 泛型类，既实际应用中的pojo
	 */
	public void insert(T entity);

	/**
	 * 增加实体对象
	 * 
	 * @param entity
	 *            T 泛型类，既实际应用中的pojo
	 */
	public void insertSel(T entity);

	/**
	 * 修改实体对象
	 * 
	 * @param entity
	 *            T 泛型类，既实际应用中的pojo
	 */
	public void update(T entity);

	/**
	 * 修改实体对象,如果formBean对象属性是空值不做修改。
	 * 
	 * @param formBean
	 *            T 泛型类，既实际应用中的pojo
	 */
	public void updateByPKSel(T formBean);

	/**
	 * 删除实体对象
	 * 
	 * @param id
	 *            主键
	 */
	public void delete(PK id);

	/**
	 * 批量删除数据<br>
	 * 该方法主要使用in语句来实现
	 * 
	 * @param ids
	 *            主键之间以 , 隔开
	 */
	public void deleteByIds(String ids);

	/**
	 * 批量删除数据<br>
	 * 该方法主要使用in语句来实现
	 * 
	 * @param ids
	 *            主键数组
	 */
	public void deleteByIds(PK[] ids);

	/**
	 * 查询对象集合 查询条件由sql映射文件根据传入的entity值处理。 注意：查询条件与 queryListCount
	 * 查询条件一致，以便分页不出错。
	 * 
	 * @param entity
	 *            T 泛型类，既实际应用中的formBean
	 * @return 返加数据列表
	 */
	public List<T> queryList(T formBean);

	/**
	 * 查询记录总数 查询条件由sql映射文件根据传入的entity值处理。 注意：查询条件与 queryList 查询条件一致，以便分页不出错。
	 * 
	 * @param entity
	 *            T 泛型类，既实际应用中的formBean
	 * @return 返回记录数
	 */
	public Integer queryListCount(T formBean);

	/**
	 * 查询对象集合
	 * 
	 * @param params
	 *            参数Map
	 * @return 返加数据map集合
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> queryListMap(Map params);

}
