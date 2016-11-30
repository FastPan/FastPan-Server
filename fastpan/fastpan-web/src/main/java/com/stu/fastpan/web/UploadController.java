package com.stu.fastpan.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.stu.fastpan.dao.pojo.user.User;
import com.stu.fastpan.message.ResponseMessage;
import com.stu.fastpan.service.file.FileService;
import com.stu.fastpan.util.MD5Utils;

/**
 * 文件上传
 * 
 * 
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController {

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/uploadFile", method = { RequestMethod.POST })
	@ResponseBody
	public void upload(Long chunks, Long chunk, String fileMd5, String name, String fileSavePath, Long lastModifiedDate,
			@RequestParam(value = "file") MultipartFile[] files, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			// 登录失效
			return;
		}
		String userId = user.getUserId().toString();
		if (chunks == null) {
			chunks = 1L;
			chunk=0L;
		}

		if (fileMd5 == null || fileMd5.equals("") || userId == null || userId.equals("")) {
			return;
		}
		Date getDate = new Date(lastModifiedDate);
		String fileName = fileMd5;
		try {
			for (MultipartFile mf : files) {
				if (!mf.isEmpty()) {
					// 临时目录用来存放所有分片文件
					String tempFileDir = getTempFilePath(getDate, fileMd5, userId) + File.separator + "temp";
					File parentFileDir = new File(tempFileDir);
					if (!parentFileDir.exists()) {
						parentFileDir.mkdirs();
					}
					// 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台(默认每片为5M)
					File tempPartFile = new File(parentFileDir, fileName + "_" + chunk + ".part");
					InputStream inputStream = null;
					try {
						if (mf != null) {
							inputStream = mf.getInputStream();
							FileUtils.copyInputStreamToFile(inputStream, tempPartFile);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
						return;
					} finally {
						if (inputStream != null) {
							try {
								inputStream.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					// 是否全部上传完成
					// 所有分片都存在才说明整个文件上传完成
					// 所有分片文件都上传完成
					// 将所有分片文件合并到一个文件中
					if (parentFileDir.listFiles().length == chunks) {
						System.out.println("上传完毕");
						File destTempFile = new File(getTempFilePath(getDate, fileMd5, userId), fileName);
						for (int i = 0; i < chunks; i++) {
							File partFile = new File(parentFileDir, fileName + "_" + i + ".part");
							FileOutputStream destTempfos = null;
							try {
								destTempfos = new FileOutputStream(destTempFile, true);

								FileUtils.copyFile(partFile, destTempfos);

							} catch (Exception e) {
								e.printStackTrace();
								return;
							} finally {
								if (destTempfos != null) {
									try {
										destTempfos.close();
									} catch (Exception e) {
										e.printStackTrace();

									}
								}
							}
						}
						// 删除临时目录中的分片文件
						try {
							FileUtils.deleteDirectory(parentFileDir);
						} catch (IOException e) {
							e.printStackTrace();
							return;
						}

						if (MD5Utils.getFileMD5(destTempFile.getAbsolutePath()).equals(fileMd5)) {
							// 如果数据表中没有这个文件，则添加。
							com.stu.fastpan.dao.pojo.file.File checkMD5FileExist = fileService
									.checkMD5FileExist(fileMd5);
							if (checkMD5FileExist == null) {
								byte temp=0;
								com.stu.fastpan.dao.pojo.file.File file=new com.stu.fastpan.dao.pojo.file.File(destTempFile.getAbsolutePath(),temp ,temp, fileMd5);
								fileService.insert(file);
								//如果出错了，要清理文件
								System.out.println(file);
							}else{
								// 否则删除自己
								try {
									FileUtils.deleteDirectory(destTempFile);
								} catch (IOException e) {
									e.printStackTrace();
									return;
								}
							}
							
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/checkMD5", method = { RequestMethod.POST })
	@ResponseBody
	public ResponseMessage checkMD5(String MD5, String savePath, String fileName, HttpSession session) {
		ResponseMessage rm = null;
		User user = (User) session.getAttribute("user");
		if (user == null) {
			rm = new ResponseMessage(9999, "登录失效", null, false);
		}
		String userId = user.getUserId().toString();

		com.stu.fastpan.dao.pojo.file.File checkMD5File = fileService.checkMD5FileExist(MD5);
		if (checkMD5File != null) {
			// 文件存在
			System.out.println("直接秒传");
			rm = new ResponseMessage(1000, "文件秒传", null, true);
		} else {
			rm = new ResponseMessage(0, "文件不存在，请上传", null, true);
			System.out.println("文件不存在，请上传");
		}
		return rm;
	}

	private String getTempFilePath(Date date, String fileMd5, String userId) {
		SimpleDateFormat df = new SimpleDateFormat("\\yyyy\\MM\\dd\\HH\\mm\\ss");
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		return "f:\\filetemp" + df.format(date) + File.separator + fileMd5 + File.separator + userId;
	}
}
