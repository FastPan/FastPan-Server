package com.stu.fastpan.message;

import java.io.Serializable;


public class RequestMessage<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 请求上下文 */
	//@NotNull
	private T requestContext;


	public T getRequestContext() {
		return requestContext;
	}

	public void setRequestContext(T requestContext) {
		this.requestContext = requestContext;
	}

}
