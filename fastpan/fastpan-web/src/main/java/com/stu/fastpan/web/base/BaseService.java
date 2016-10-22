package com.stu.fastpan.web.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.stu.fastpan.dao.pojo.base.BaseMapper;
import com.stu.fastpan.message.ResponseMessage;
import com.stu.fastpan.page.PageInfo;
import com.stu.fastpan.page.PageObject;


public abstract class BaseService<T, PK extends Serializable> {

	private Integer maxCount = 200; // 分页每页查询的最大条数

	/**
	 * 获取 泛型实际类型
	 * 
	 * @param index
	 * @return
	 */
	public Class getGenericType(int index) {
		Type genType = getClass().getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			throw new RuntimeException("Index outof bounds");
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

	protected ResponseMessage SUCCESS() {
		return new ResponseMessage();
	}

	protected ResponseMessage SUCCESS(Object result) {
		return new ResponseMessage(result);
	}
	
    protected ResponseMessage FAIL(int code,String message){
        return new ResponseMessage(code,message,null,false);
    }

	/**
	 * 子类实现此抽象方法，获得业务处理子类的sql映射接口
	 * 
	 * @return
	 */
	protected abstract BaseMapper<T, PK> getMapper();

	/**
	 * 子类实现批抽象方法，获得业务处理子类的sql映射文件namespace
	 * 
	 * @return
	 */
	protected abstract String getMapperNameSpace();

	/**
	 * 子类实现批抽象方法，获得业务层默认操作数据库名称，主要是针对于insert,update,delete，主从同步问题
	 * 
	 * @return
	 */
	protected abstract String getDefalultDatabase();

	@Autowired
	private SqlSessionFactory sqlSessionFactory;


	/**
	 * 根据主键查找实体对象
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public T get(PK id) {
		return getMapper().get(id);
	}

	/**
	 * 增加实体对象
	 * 
	 * @param entity
	 *            T 泛型类，既实际应用中的pojo
	 */
	public void insert(T entity) {
		getMapper().insert(entity);
	}

	/**
	 * 增加实体对象
	 * 
	 * @param entity
	 *            T 泛型类，既实际应用中的pojo
	 */
	public void insertSel(T entity) {
		getMapper().insertSel(entity);
	}

	/**
	 * 修改实体对象
	 * 
	 * @param entity
	 *            T 泛型类，既实际应用中的pojo
	 */
	public void update(T entity) {
		getMapper().update(entity);
	}

	/**
	 * 修改实体对象
	 * 
	 * @param entity
	 */
	public void updateByPKSel(T entity) {
		getMapper().updateByPKSel(entity);
	}

	/**
	 * 删除实体对象
	 * 
	 * @param id
	 *            主键
	 */
	public void delete(PK id) {
		getMapper().delete(id);
	}

	/**
	 * 批量删除数据
	 * 
	 * @param ids
	 *            主键之间以 , 隔开
	 */
	public void deleteByIds(String ids) {
		getMapper().deleteByIds(ids);
	}

	/**
	 * 批量删除数据
	 * 
	 * @param ids
	 *            主键数组
	 */
	public void deleteByIds(PK[] ids) {
		getMapper().deleteByIds(ids);
	}

	/**
	 * 查询对象集合 查询条件由sql映射文件根据传入的entity值处理。
	 * 
	 * @param formBean
	 *            T 泛型类，既实际应用中的FormBean
	 * @return 返回数据列表
	 */
	public List<T> queryList(T formBean) {
		return getMapper().queryList(formBean);
	}

	/**
	 * 查询对象集合 查询条件由sql映射文件根据传入的entity值处理。
	 * 
	 * @param formBean
	 *            T 泛型类，既实际应用中的FormBean
	 * @return 返回数据记录条数
	 */
	public Integer queryListCount(T formBean) {
		return getMapper().queryListCount(formBean);
	}

	/**
	 * 查询分页数据列表 查询条件由sql映射文件根据传入的entity值处理。
	 * 
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示记录数
	 * @param formBean
	 *            T 泛型类，既实际应用中的FormBean
	 * @return 返回数据列表
	 * @throws BusinessException
	 */
	public List<T> queryPageList(int currentPage, int pageSize, T formBean)
			throws Exception {
		if (currentPage < 1) {
			currentPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 1;
		}
		if (pageSize > maxCount) {// 分页查询每页最大条数
			pageSize = maxCount;
		}
		List<T> list = null;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			RowBounds rowBounds = new RowBounds((currentPage - 1) * pageSize,
					pageSize);
			list = session.selectList(getMapperNameSpace() + ".queryList",
					formBean, rowBounds);
		} catch (Exception e) {
			throw e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;

	}

	/**
	 * 查询对象集合 查询条件由sql映射文件根据传入的entity值处理。
	 * 
	 * @param selectSQL
	 *            选择查询语句
	 * @param formBean
	 *            T 泛型类，既实际应用中的FormBean
	 * @return 返回数据列表
	 * @throws BusinessException
	 */
	public List<T> queryListByForm(String selectSQL, T formBean)
			throws Exception {
		List<T> list = null;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(getMapperNameSpace() + "." + selectSQL,
					formBean);
		} catch (Exception e) {
			throw  e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	/**
	 * 查询分页数据列表 查询条件由sql映射文件根据传入的entity值处理。
	 * 
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示记录数
	 * @param selectSQL
	 *            选择查询语句
	 * @param formBean
	 *            T 泛型类，既实际应用中的FormBean
	 * @return 返回数据列表
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryPageListByForm(int currentPage, int pageSize,
			String selectSQL, T formBean) throws Exception {
		if (currentPage < 1) {
			currentPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 1;
		}
		if (pageSize > maxCount) {// 分页查询每页最大条数
			pageSize = maxCount;
		}
		List<T> list = null;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			RowBounds rowBounds = new RowBounds((currentPage - 1) * pageSize,
					pageSize);
			list = session.selectList(getMapperNameSpace() + "." + selectSQL,
					formBean, rowBounds);
		} catch (Exception e) {
			throw  e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;

	}

	/**
	 * 统计查询记录条数，一般与queryList配合使用
	 * 
	 * @param selectSqlCount
	 *            选择统计查询语句
	 * @param formBean
	 *            T 泛型类，既实际应用中的FormBean
	 * @return 记录条数
	 * @throws BusinessException
	 */
	public Integer queryListCountByForm(String selectSqlCount, T formBean)
			throws Exception {
		SqlSession session = null;
		Integer totalCount = 0;
		try {
			session = getSqlSessionFactory().openSession();
			totalCount = (Integer) session.selectOne(getMapperNameSpace() + "."
					+ selectSqlCount, formBean);
		} catch (Exception e) {
			throw  e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return totalCount;
	}

	/**
	 * 查询分页数据列表 查询条件由sql映射文件根据传入的entity值处理。
	 * 
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示记录数
	 * @param selectSQL
	 *            选择查询语句
	 * @param params
	 *            查询条件
	 * @return 返回数据列表
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryPageListByMap(int currentPage, int pageSize,
			String selectSQL, Map params) throws Exception {
		if (currentPage < 1) {
			currentPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 1;
		}
		if (pageSize > maxCount) {// 分页查询每页最大条数
			pageSize = maxCount;
		}
		List<T> list = null;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			RowBounds rowBounds = new RowBounds((currentPage - 1) * pageSize,
					pageSize);
			list = session.selectList(getMapperNameSpace() + "." + selectSQL,
					params, rowBounds);
		} catch (Exception e) {
			throw  e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;

	}

	/**
	 * 统计查询记录条数，一般与queryList配合使用
	 * 
	 * @param selectSqlCount
	 *            选择统计查询语句
	 * @param params
	 *            查询条件
	 * @return 记录条数
	 * @throws BusinessException
	 */
	public Integer queryListCountByMap(String selectSqlCount, Map params)
			throws Exception {
		SqlSession session = null;
		Integer totalCount = 0;
		try {
			session = getSqlSessionFactory().openSession();
			totalCount = (Integer) session.selectOne(getMapperNameSpace() + "."
					+ selectSqlCount, params);
		} catch (Exception e) {
			throw  e;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return totalCount;
	}

	/**
	 * 查询对象集合
	 * 
	 * @param params
	 *            参数Map
	 * @return 返加数据map集合
	 */
	public List<Map> queryListMap(Map params) {
		return getMapper().queryListMap(params);
	}

	/**
	 * 使用默认选择查询语句[queryListMap], 查询 [rows] 条记录数据, 返回的每行记录数据保存在Map中，
	 * 
	 * @param currentPage
	 *            从第几条记录开始
	 * @param pageSize
	 *            取多少条记录
	 * @param params
	 *            查询条件
	 * @return
	 * @throws BusinessException
	 */
	public List<Map> queryPageListMap(int currentPage, int pageSize, Map params)
			throws Exception {
		return queryPageListMapByMap(currentPage, pageSize, "queryListMap",
				params);
	}

	/**
	 * 查询 [rows] 条记录数据, 返回的每行记录数据保存在Map中
	 * 
	 * @param currentPage
	 *            从第几页开始
	 * @param pageSize
	 *            取多少条记录
	 * @param selectSQL
	 *            选择查询语句
	 * @param params
	 *            查询条件
	 * @return
	 * @throws BusinessException
	 */
	public List<Map> queryPageListMapByMap(int currentPage, int pageSize,
			String selectSQL, Map params) throws Exception {
		if (currentPage < 1) {
			currentPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 1;
		}
		if (pageSize > maxCount) {// 分页查询每页最大条数
			pageSize = maxCount;
		}
		List<Map> list = null;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			RowBounds rowBounds = new RowBounds((currentPage - 1) * pageSize,
					pageSize);
			list = session.selectList(getMapperNameSpace() + "." + selectSQL,
					params, rowBounds);
		} catch (Exception ex) {
			throw  ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}

	/**
	 * 查询数据, 返回的每行记录数据保存在Map中
	 * 
	 * @param selectSQL
	 *            选择查询语句
	 * @param formBean
	 *            T 泛型类，既实际应用中的FormBean
	 * @return
	 * @throws BusinessException
	 */
	public List<Map> queryListMapByForm(String selectSQL, T formBean)
			throws Exception {
		List<Map> list = null;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			list = session.selectList(getMapperNameSpace() + "." + selectSQL,
					formBean);
		} catch (Exception ex) {
			throw  ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}

	/**
	 * 查询 [rows] 条记录数据, 返回的每行记录数据保存在Map中
	 * 
	 * @param currentPage
	 *            从第几页开始
	 * @param pageSize
	 *            取多少条记录
	 * @param selectSQL
	 *            选择查询语句
	 * @param formBean
	 *            T 泛型类，既实际应用中的FormBean
	 * @return
	 * @throws BusinessException
	 */
	public List<Map> queryPageListMapByForm(int currentPage, int pageSize,
			String selectSQL, T formBean) throws Exception {
		if (currentPage < 1) {
			currentPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 1;
		}
		if (pageSize > maxCount) {// 分页查询每页最大条数
			pageSize = maxCount;
		}
		List<Map> list = null;
		SqlSession session = null;
		try {
			session = getSqlSessionFactory().openSession();
			RowBounds rowBounds = new RowBounds((currentPage - 1) * pageSize,
					pageSize);
			list = session.selectList(getMapperNameSpace() + "." + selectSQL,
					formBean, rowBounds);
		} catch (Exception ex) {
			throw  ex;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;
	}

	/**
	 * 查询分页数据对象 查询条件由sql映射文件根据传入的entity值处理。
	 * 
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示记录数
	 * @param entity
	 *            T 泛型类，既实际应用中的FormBean
	 * @return 返回分页数据对象
	 * @throws BusinessException
	 */
	public PageObject<T> queryPageObjectList(int currentPage, int pageSize,
			T formBean) throws Exception {
		if (currentPage < 1) {
			currentPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 1;
		}
		if (pageSize > maxCount) {// 分页查询每页最大条数
			pageSize = maxCount;
		}
		PageObject<T> pageObject = null;
		SqlSession session = null;
		try {
			pageObject = new PageObject<T>();
			PageInfo pageInfo = new PageInfo();
			session = getSqlSessionFactory().openSession();
			Integer totalCount = (Integer) session.selectOne(
					getMapperNameSpace() + ".queryListCount", formBean);
			// Integer totalCount = getMapper().queryListCount(entity);
			pageInfo.setTotalCount(totalCount);
			pageInfo.setCurrentPage(currentPage);
			pageInfo.setPageSize(pageSize);
			RowBounds rowBounds = new RowBounds((currentPage - 1) * pageSize,
					pageSize);
			List<T> list = session.selectList(getMapperNameSpace()
					+ ".queryList", formBean, rowBounds);
			pageObject.setData(list);
			pageObject.setPageInfo(pageInfo);
		} catch (Exception e) {
			throw  e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return pageObject;

	}

	/**
	 * 查询分页数据对象 查询条件由sql映射文件根据传入的entity值处理。
	 * 
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示记录数
	 * @param selectSql
	 *            选择查询语句,注意：查询统计语句id=查询语句id+“Count”
	 * @param entity
	 *            T 泛型类，既实际应用中的FormBean
	 * @return 返回分页数据对象
	 * @throws BusinessException
	 */
	public PageObject<T> queryPageObjectList(int currentPage, int pageSize,
			String selectSql, T formBean) throws Exception {
		if (currentPage < 1) {
			currentPage = 1;
		}
		if (pageSize < 1) {
			pageSize = 1;
		}
		if (pageSize > maxCount) {// 分页查询每页最大条数
			pageSize = maxCount;
		}
		PageObject<T> pageObject = null;
		SqlSession session = null;
		try {
			pageObject = new PageObject<T>();
			PageInfo pageInfo = new PageInfo();
			session = getSqlSessionFactory().openSession();
			Integer totalCount = (Integer) session.selectOne(
					getMapperNameSpace() + "." + selectSql + "Count", formBean);
			// Integer totalCount = getMapper().queryListCount(entity);
			pageInfo.setTotalCount(totalCount);
			pageInfo.setCurrentPage(currentPage);
			pageInfo.setPageSize(pageSize);
			RowBounds rowBounds = new RowBounds((currentPage - 1) * pageSize,
					pageSize);
			List<T> list = session.selectList(getMapperNameSpace() + "."
					+ selectSql, formBean, rowBounds);
			pageObject.setData(list);
			pageObject.setPageInfo(pageInfo);
		} catch (Exception e) {
			throw  e;
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return pageObject;

	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

}
