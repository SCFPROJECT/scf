package com.qinghuo.bdpl.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scf.core.conditionbean.ParamUser;
import com.scf.core.domain.User;
import com.scf.core.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class UserServiceTest {
	private static final String ID = "16055F55A63B4FA69162092DB26F421B";
	private static final Logger logger = Logger.getLogger(UserServiceTest.class);
	private UserService userService;
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Test
	public void testInsertUser() throws Exception {
		logger.info("*********testInsertUser()********");
		User user = new User();
		user.setName("李四");
		user.setEmail("lisi@163.com");
		user.setTelephone("13989097809");
		user.setAge(30);
		userService.insertUser(user);
	}
	//@Test
	public void testFindUserById() throws Exception {
		logger.info("*********testFindUserById()********");
		User user = userService.findUserById(ID);
		Assert.assertEquals("lisi", user.getName());
		Assert.assertEquals("lisi@163.com", user.getEmail());
		Assert.assertEquals("13989097809", user.getTelephone());
		Assert.assertEquals(30, user.getAge());
	}
	//@Test
	public void testUpdateUser() throws Exception {
		logger.info("*********testUpdateUser()********");
		User user = userService.findUserById(ID);
		user.setName("lisisi");
		userService.updateUser(user);
		Assert.assertEquals("lisisi", user.getName());
	}
	//@Test
	public void testFindAllUser() throws Exception {
		logger.info("*********testFindAllUser()********");
		List<User> users = userService.findAllUser();
		Assert.assertEquals(3,users.size());
	}
	
	public void testFindUserByParaMap() throws Exception {
		logger.info("*********testFindUserByParaMap()********");
//		Map<String,Object> paraMap =  new HashMap<String,Object>();
//		//paraMap.put("uname", "zhangsan");
////		paraMap.put("uemail", "zhangsan@163.com");
//		paraMap.put("uage", 25);
//		List<User> users = userService.findUserByParaMap(paraMap);
//		System.out.println("Total : " + users.size());
		
		ParamUser pu = new ParamUser();
		pu.setAge(25);
		//pu.setName("zhangsan");
		//pu.setEmail("zhangsan@163.com");
		List<User> users = userService.findUserByParaMap(pu);
		System.out.println("**size** = " + users.size());
	}
	@Test
	public void testFindUsersByMap() throws Exception {
		Map<String,Object> map =  new HashMap<String,Object>();
		map.put("age", 25);
		List<User> users = userService.findUserByMap(map);
		System.out.println("***FindMap size***" + users.size());
	}
	//@Test
	public void testDeleteUser() throws Exception {
		logger.info("*********testDeleteUser()********");
		
	}
	//@Test
	public void testFindUsersNoParam() throws Exception {
		logger.info("*********testFindUsersNoParam()********");
		List<User> users = userService.findUsersNoParam();
		Assert.assertEquals(3,users.size());
	}
	
	public void testFindUsersByUser() throws Exception {
		User user = new User();
		user.setAge(30);
		user.setName("李四");
		List<User> users = userService.findUserByParaUser(user);
		System.out.println("size = " + users.size());
	}
	
}
