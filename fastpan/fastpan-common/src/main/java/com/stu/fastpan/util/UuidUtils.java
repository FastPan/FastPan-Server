package com.stu.fastpan.util;

import java.util.UUID;

public class UuidUtils {
	
	/**
	 * 生成UUID，删除-
	 * @return String
	 */
	public static String getUuid(){
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}

}
