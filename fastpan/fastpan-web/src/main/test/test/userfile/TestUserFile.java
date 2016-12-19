package test.userfile;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.stu.fastpan.dao.pojo.user.User;
import com.stu.fastpan.service.userfile.UserFileService;
import com.stu.fastpan.web.UserFileController;

import test.BaseSpringTestCase;

public class TestUserFile extends BaseSpringTestCase {

	@Autowired
	UserFileService userFileService;
	@Autowired
	UserFileController userFileController;
	@Before
	public void before() {
	}

	@Test
	public void selectUserFile() {
		byte state=0;
		System.out.println(userFileService.selectByUserIdPathState("619457c6a49942b790eb014552d8c6f3","/",state));
	}
	
	@Test
	public void getFileList(){
		String path="/";
		Byte state=0;
		HttpSession session=new HttpSession() {
			
			@Override
			public void setMaxInactiveInterval(int interval) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setAttribute(String name, Object value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeValue(String name) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void removeAttribute(String name) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void putValue(String name, Object value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean isNew() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void invalidate() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String[] getValueNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getValue(String name) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public HttpSessionContext getSessionContext() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ServletContext getServletContext() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getMaxInactiveInterval() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public long getLastAccessedTime() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getId() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public long getCreationTime() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Enumeration getAttributeNames() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getAttribute(String name) {
				// TODO Auto-generated method stub
				User user = new User();
				user.setUserId(user.createConstraint());
				return user;
			}
		};
		System.out.println(userFileController.getFileList(session, path, state));
	}
}
