package com.stu.fastpan.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;



public class FileName {

	public static String makeFileName(String filename) { // 2.jpg
		// 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		// return UUID.randomUUID().toString() + "_" + filename;
		Date date = new Date();
		return date.getTime() + filename;
	}

	public static String makePath(String filename, String savePath) {

		SimpleDateFormat simp = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String str = simp.format(date);

		String dir1 = str.substring(0, 4);
		String dir2 = str.substring(4, 6);
		String dir3 = str.substring(6, 8);

		// 构造新的保存目录
		String dir = savePath + "/" + dir1 + "/" + dir2 + "/" + dir3;
		File file = new File(dir);
		// 如果目录不存在
		if (!file.exists()) {
			// 创建目录
			file.mkdirs();
		}
		return dir + "/" + filename;
	}

	//获取服务器地址
	public static String getPath() {
		return "https://ss0.bdstatic.com/img";
	}
}
