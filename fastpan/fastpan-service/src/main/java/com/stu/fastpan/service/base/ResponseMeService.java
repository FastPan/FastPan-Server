package com.stu.fastpan.service.base;

import java.io.Serializable;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.stu.fastpan.message.ResponseMessage;


public abstract class ResponseMeService<T, PK extends Serializable> {

	protected ResponseMessage SUCCESS() {
		return new ResponseMessage();
	}

	protected ResponseMessage SUCCESS(Object result) {
		return new ResponseMessage(result);
	}
	
    protected ResponseMessage FAIL(int code,String message){
        return new ResponseMessage(code,message,null,false);
    }


	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

}
