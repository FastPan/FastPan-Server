package com.stu.fastpan.message;

public class ResponseMessage {
    /**
     * 状态码
     */
    private int code=0;
    /**
     * 消息
     */
    private String message="";

    /**
     * 是否成功
     */
    private  boolean success =true;
    /**
     * 结果集
     */
    private Object result;


    public ResponseMessage(int code, String message, Object result,boolean success) {
        this.code = code;
        this.message = message;
        this.result = result;
        this.success = success;
    }

    public ResponseMessage() {

    }

    public ResponseMessage(Object result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

	@Override
	public String toString() {
		return "ResponseMessage [code=" + code + ", message=" + message + ", success=" + success + ", result=" + result
				+ "]";
	}


    
}
