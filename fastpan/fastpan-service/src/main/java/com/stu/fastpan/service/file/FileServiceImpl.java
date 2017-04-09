package com.stu.fastpan.service.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.stu.fastpan.dao.mapper.file.FileMapper;
import com.stu.fastpan.dao.pojo.file.File;
import com.stu.fastpan.dao.pojo.file.FileUpload;
import com.stu.fastpan.dao.pojo.userfile.UserFile;
import com.stu.fastpan.message.ResponseMessage;
import com.stu.fastpan.service.base.BaseService;
import com.stu.fastpan.service.userfile.UserFileService;
import com.stu.fastpan.util.MD5Utils;

@Service
public class FileServiceImpl extends BaseService implements FileService {

	@Autowired
	private FileMapper fileMapper;
	// @Autowired
	// private UserFileMapper userFileMapper;
	@Autowired
	private UserFileService userFileService;

	public File checkMD5FileExist(String MD5) {
		return fileMapper.selectByMD5(MD5);
	}

	@Override
	public boolean insert(File file) {
		try {
			if (fileMapper.insert(file) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public ResponseMessage fileUpload(String userId, FileUpload fileUpload, MultipartFile[] files) {
		ResponseMessage rm = null;
		if (userId == null) {
			// 登录失效
			rm = FAIL(9999, "登录失效");
		}
		if (fileUpload.getChunks() == null) {
			fileUpload.setChunks(1L);
			fileUpload.setChunk(0L);
		}

		if (fileUpload.getFileMd5() == null || fileUpload.getFileMd5().equals("")) {
			rm = FAIL(9999, "md5失效");
		}
		Date getDate = new Date(fileUpload.getLastModifiedDate());
		String split =fileUpload.getName().indexOf(".")!=-1?fileUpload.getName().substring(fileUpload.getName().lastIndexOf(".")+1):"";
		String fileName = fileUpload.getFileMd5()+"."+split;
		try {
			for (MultipartFile mf : files) {
				if (!mf.isEmpty()) {
					// 临时目录用来存放所有分片文件
					String tempFileDir = getTempFilePath(getDate, fileUpload.getFileMd5(), userId)
							+ java.io.File.separator + "temp";
					java.io.File parentFileDir = new java.io.File(tempFileDir);
					if (!parentFileDir.exists()) {
						parentFileDir.mkdirs();
					}
					// 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台(默认每片为5M)
					java.io.File tempPartFile = new java.io.File(parentFileDir,
							fileName + "_" + fileUpload.getChunk() + ".part");
					InputStream inputStream = null;
					try {
						if (mf != null) {
							inputStream = mf.getInputStream();
							FileUtils.copyInputStreamToFile(inputStream, tempPartFile);

						}
					} catch (IOException e1) {
						e1.printStackTrace();
						rm = FAIL(9999, "文件上传异常");
					} finally {
						if (inputStream != null) {
							try {
								inputStream.close();
							} catch (IOException e) {
								e.printStackTrace();
								rm = FAIL(9999, "文件上传异常");
							}
						}
					}
					// 是否全部上传完成
					// 所有分片都存在才说明整个文件上传完成
					// 所有分片文件都上传完成
					// 将所有分片文件合并到一个文件中
					if (parentFileDir.listFiles().length == fileUpload.getChunks()) {
						java.io.File destTempFile = new java.io.File(
								getTempFilePath(getDate, fileUpload.getFileMd5(), userId), fileName);
						for (int i = 0; i < fileUpload.getChunks(); i++) {
							java.io.File partFile = new java.io.File(parentFileDir, fileName + "_" + i + ".part");
							FileOutputStream destTempfos = null;
							try {
								destTempfos = new FileOutputStream(destTempFile, true);

								FileUtils.copyFile(partFile, destTempfos);

							} catch (Exception e) {
								e.printStackTrace();
								rm = FAIL(9999, "文件合并异常");
							} finally {
								if (destTempfos != null) {
									try {
										destTempfos.close();
									} catch (Exception e) {
										e.printStackTrace();
										rm = FAIL(9999, "文件合并异常");
									}
								}
							}
						}
						// 删除临时目录中的分片文件
						try {
							FileUtils.deleteDirectory(parentFileDir);
						} catch (IOException e) {
							e.printStackTrace();
							rm = FAIL(9999, "删除临时文件异常");
						}

						if (MD5Utils.getFileMD5(destTempFile.getAbsolutePath()).equals(fileUpload.getFileMd5())) {
							// 如果数据表中没有这个文件，则添加。
							com.stu.fastpan.dao.pojo.file.File file = checkMD5FileExist(fileUpload.getFileMd5());
							if (file == null) {
								byte temp = 0;
								file = new com.stu.fastpan.dao.pojo.file.File(destTempFile.getAbsolutePath(), temp,
										temp, fileUpload.getSize(), fileUpload.getFileMd5());
								insert(file);
								// 如果出错了，要清理文件
								System.out.println(file);
							} else {
								// 否则删除自己
								try {
									FileUtils.deleteDirectory(destTempFile);
								} catch (IOException e) {
									e.printStackTrace();
									rm = FAIL(9999, "删除临时文件异常");
								}
							}
							System.out.println(fileUpload.getFileSavePath());
							// 关联至用户文件表
							byte state = 0;
							rm = userFileService.insert(new UserFile(UserFile.createUUID(), fileUpload.getName(),
									fileUpload.getFileSavePath(), userId, file.getFileId(), state));
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rm;
	}

	private String getTempFilePath(Date date, String fileMd5, String userId) {
		SimpleDateFormat df = new SimpleDateFormat("\\yyyy\\MM\\dd\\HH\\mm\\ss");
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
		
//		return "f:\\filetemp" + df.format(date) + java.io.File.separator + fileMd5 + java.io.File.separator + userId;
		return "e:\\fastPanFile" + df.format(date) + java.io.File.separator + fileMd5 + java.io.File.separator + userId;
	}

	@Override
	public ResponseMessage checkMD5(String userId, String MD5, String savePath, String fileName) {
		ResponseMessage rm = null;
		if (userId == null) {
			rm = new ResponseMessage(9999, "登录失效", null, false);
		}

		com.stu.fastpan.dao.pojo.file.File checkMD5File = checkMD5FileExist(MD5);
		if (checkMD5File != null) {
			// 文件存在
			System.out.println("直接秒传");
			// 关联至用户文件表
			byte state = 0;
			rm = userFileService.insert(new UserFile(UserFile.createUUID(), fileName,
					savePath, userId, checkMD5File.getFileId(), state));
			rm = new ResponseMessage(1000, "文件秒传", null, true);
		} else {
			rm = new ResponseMessage(0, "文件不存在，请上传", null, true);
			System.out.println("文件不存在，请上传");
		}
		return rm;
	}
}
