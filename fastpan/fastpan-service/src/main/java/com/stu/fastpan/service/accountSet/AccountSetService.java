package com.stu.fastpan.service.accountSet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;

import com.stu.fastpan.dao.mapper.user.UserMapper;
import com.stu.fastpan.dao.pojo.user.Password;
import com.stu.fastpan.dao.pojo.user.UpdateEmail;
import com.stu.fastpan.dao.pojo.user.User;
import com.stu.fastpan.message.ResponseMessage;
import com.stu.fastpan.service.base.ResponseMeService;
import com.stu.fastpan.service.sendPicCode.SendPicCodeService;
import com.stu.fastpan.util.FileName;
import com.stu.fastpan.util.MD5Utils;


@Service
public class AccountSetService extends ResponseMeService<User, String>
		implements AccountSetFacade {

	private static Logger log = LoggerFactory
			.getLogger(AccountSetService.class);

	@Autowired
	private UserMapper usermapper;

	@Autowired
	private SendPicCodeService sendPicCodeService;

	/**
	 * 登录之后获得个人信息
	 */

	@Override
	public ResponseMessage selectByPrimaryKey(HttpSession session) {
		User user2;

		User user = (User) session.getAttribute("user");

		if (user == null) {
			return FAIL(1006, "session失效");
		}
		try {
			user2 = usermapper.selectByPrimaryKey(user.getUserId());
			user2.setPassword(null);
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}
		log.info("查询成功");
		return SUCCESS(user2);
	}

	/**
	 * 登录后修改邮箱接口
	 */

	@Override
	public ResponseMessage updateEmail(UpdateEmail updateEmail,
			HttpSession session) {

		String oldEmail = updateEmail.getEmail();
		String oldCode = updateEmail.getCode();

		User user = (User) session.getAttribute("user");

		if (user == null) {
			log.info("session失效了");
			return FAIL(1006, "session失效");
		}

		User user2 = usermapper.selectByPrimaryKey(user.getUserId());

		if (!oldEmail.equals(user2.getEmail())) {
			log.info("邮箱错误");
			return FAIL(1008, "邮箱错误");
		} else {
			if (sendPicCodeService.testEmailCode(oldCode, session).isSuccess()) {
				user2.setEmail(updateEmail.getNewEmail());
				session.setAttribute("user", user2);
				log.info("调用成功");
				return SUCCESS();
			}

		}
		return FAIL(1005, "验证码错误");
	}

	/**
	 * 进行修改修改邮箱操作
	 */

	@Override
	public ResponseMessage updateEmail2(String newCode, HttpSession session) {
		int obj;

		User user = (User) session.getAttribute("user");

		if (user == null) {
			return FAIL(1006, "session失效");
		}

		try {
			if (sendPicCodeService.testEmailCode(newCode, session).isSuccess()) {
				Date date = new Date();
				user.setUpdateTime(date);
				obj = usermapper.updateByPrimaryKeySelective(user);
			} else {
				log.info("验证码错误");
				return FAIL(1005, "验证码错误");
			}
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}

		if (obj == 1) {
			session.setAttribute("user", user);
			log.info("更新邮箱成功");
			return SUCCESS("更新邮箱成功");
		}
		log.info("更新失败");
		return FAIL(1007, "更新失败");
	}

	/**
	 * 密码修改
	 */

	@Override
	public Object updatePassword(Password password, HttpSession session) {

		int obj;
		User user = (User) session.getAttribute("user");

		if (user == null) {
			log.info("session失效了");
			return FAIL(1006, "session失效");
		}

		// 用户输入的旧密码
		String oldPassword = MD5Utils.getMD5(password.getPassword().getBytes());
		if (user.getPassword().equals(oldPassword)) {
			// 用户输入的新密码
			String newPassword = MD5Utils.getMD5(password.getNewPassword()
					.getBytes());
			try {
				Date date = new Date();
				user.setUpdateTime(date);
				user.setPassword(newPassword);
				obj = usermapper.updateByPrimaryKeySelective(user);
			} catch (Exception e) {
				log.info("数据库语句问题");
				System.out.println(e.getMessage());
				return FAIL(1003, "业务参数错误");
			}
			if (obj == 1) {
				session.setAttribute("user", user);
				log.info("更新成功");
				return SUCCESS("更新密码成功");
			}
		} else {
			log.info("密码错误");
			return FAIL(1002, "密码错误");
		}
		log.info("更新失败");
		return FAIL(1007, "更新失败");

	}

	/**
	 * 修改昵称
	 */

	@Override
	public Object updateNickName(String nickName, HttpSession session) {
		int obj;
		User user2;
		User user = (User) session.getAttribute("user");

		if (user == null) {
			log.info("session失效了");
			return FAIL(1006, "session失效");
		}
		try {
			user2 = new User();
			Date date = new Date();
			user2.setUserId(user.getUserId());
			user2.setNickName(nickName);
			user2.setUpdateTime(date);
			obj = usermapper.updateByPrimaryKeySelective(user2);
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}

		if (obj == 1) {
			log.info("更新成功");
			session.setAttribute("user", user2);
			return SUCCESS("更新昵称成功");
		}
		log.info("更新失败了");
		return FAIL(1007, "更新失败");
	}
	
	/**
	 * 修改邮箱和昵称
	 */

	@Override
	public Object updateNameEm(String nickName,String email, HttpSession session) {
		int obj;
		User user2;
		User user = (User) session.getAttribute("user");

		if (user == null) {
			log.info("session失效了");
			return FAIL(1006, "session失效");
		}
		try {
			user2 = new User();
			Date date = new Date();
			user2.setUserId(user.getUserId());
			user2.setNickName(nickName);
			user2.setEmail(email);
			user2.setUpdateTime(date);
			obj = usermapper.updateByPrimaryKeySelective(user2);
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}

		if (obj == 1) {
			log.info("更新成功");
			session.setAttribute("user", user2);
			return SUCCESS("更新昵称成功");
		}
		log.info("更新失败了");
		return FAIL(1007, "更新失败");
	}

//	/**
//	 * 修改头像
//	 */
//
//	@Override
//	public ResponseMessage updateImage(String image, HttpSession session) {
//		User user2=new User();
//		int obj;
//		
//		//项目目录下
//		String path3 = "D:/fastPan/FastPan-Server/fastpan/fastpan-web/src/main/webapp/images";
//		
//		User user = (User) session.getAttribute("user");
//		if (user == null) {
////			return FAIL(1006, "session失效");
//			return FAIL(1006, "请重新登录");
//		}
//		try {
//			//将头像移动到项目目录下
//			File file = new File(image);
//			file.renameTo(new File(path3+file.getName()));
//			
//			Date date = new Date();
//			user2.setUserId(user.getUserId());
//			user2.setImage("../images/"+file.getName());
//			user2.setUpdateTime(date);
//			obj = usermapper.updateByPrimaryKeySelective(user2);
//		} catch (Exception e) {
//			log.info("数据库语句问题");
//			System.out.println(e.getMessage());
//			return FAIL(1003, "业务参数错误");
//		}
//
//		if (obj == 1) {
//			session.setAttribute("user", user2);
//			log.info("更新成功");
//			return SUCCESS("更新头像成功");
//		}
//		log.info("更新失败");
//		return FAIL(1007, "更新失败");
//	}
	
//	/**
//	 * 上传头像
//	 * 无限上传不确定。。。
//	 */
//	
//	@Override
//	public Object uploadImg(CommonsMultipartFile file,HttpSession session) {
//    
//		String fileName = FileName.makeFileName(file.getOriginalFilename());
//	    System.out.println(fileName);
//	    
//      String path = this.getClass().getClassLoader().getResource("/").getPath();
//      String path2 = path.substring(1, (path.length()-17))+"/img";
//        
//      String fileImge = FileName.makePath(fileName, path2);
//		if (!file.isEmpty()) {
//			try {
//				FileOutputStream os = new FileOutputStream(new File(fileImge));
//				InputStream in = file.getInputStream();
//				byte[] arr=new byte[400];
//				int b = 0;
//				while ((b = in.read(arr)) != -1) { // 读取文件
//					os.write(arr, 0, b);
//				}
//				os.flush(); // 关闭流
//				in.close();
//				os.close();
//				
////				//项目目录下
////				String path3 = "D:/fastPan/FastPan-Server/fastpan/fastpan-web/src/main/webapp/images";
////				File file3 = new File(fileImge);
////				file3.renameTo(new File(path3+file.getName()));
//				return fileImge;
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return FAIL(0,"文件不存在，请上传");
//	}
	
	/**
	 * 修改头像
	 */

	@Override
	public ResponseMessage updateImage(HttpSession session) {
		User user2=new User();
		int obj;
		
		//项目目录下
		String path3 = "D:/fastPan/FastPan-Server/fastpan/fastpan-web/src/main/webapp/images/";
		
		User user = (User) session.getAttribute("user");
		if (user == null) {
//			return FAIL(1006, "session失效");
			return FAIL(1006, "请重新登录");
		}
		try {
			//将头像移动到项目目录下
			
			String oldImage = (String) session.getAttribute("imageFile");
			File file = new File(oldImage);
			System.out.println(file.getName());
			file.renameTo(new File(path3+file.getName()));
			
			Date date = new Date();
			user2.setUserId(user.getUserId());
			user2.setImage("../images/"+file.getName());
			user2.setUpdateTime(date);
			obj = usermapper.updateByPrimaryKeySelective(user2);
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}

		if (obj == 1) {
			session.setAttribute("user", user2);
			log.info("更新成功");
			return SUCCESS("更新头像成功");
		}
		log.info("更新失败");
		return FAIL(1007, "更新失败");
	}	
	
	
	@Override
	public Object uploadImg(String strImg,HttpSession session) {
		
		BASE64Decoder decoder = new BASE64Decoder();
		String fileName = FileName.makeFileName("abc.jpg");
        String path = this.getClass().getClassLoader().getResource("/").getPath();
        String path2 = path.substring(1, (path.length()-17))+"/img";
        String fileImge = FileName.makePath(fileName, path2);
        
		if(strImg != null && !strImg.equals("")){	
	    String str = strImg.split(",")[1];	  
	            // Base64解码
	            try {
	            	
	            	byte[] bytes = decoder.decodeBuffer(str);
	                for (int i = 0; i < bytes.length; i++) {
	                    if (bytes[i] < 0) {// 调整异常数据
	                    	bytes[i] += 256;
	                    }
	                }
	                // 生成jpeg图片
	                OutputStream out = new FileOutputStream(fileImge);
	                out.write(bytes);
	                out.flush();
	                out.close();
	                session.setAttribute("imageFile", fileImge);
	                return SUCCESS("上传成功");
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
		}
		return FAIL(0,"文件不存在，请上传");
	}
	
}
