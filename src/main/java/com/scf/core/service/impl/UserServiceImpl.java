package com.scf.core.service.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scf.core.conditionbean.ParamUser;
import com.scf.core.dao.UserMapper;
import com.scf.core.domain.User;
import com.scf.core.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {

	private UserMapper userMapper;
	@Autowired
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	@Override
	public void insertUser(User user) throws Exception {
		userMapper.insertUser(user);
	}
	@Override
	public void updateUser(User user) throws Exception {
		userMapper.updateUser(user);
	}
	@Override
	public void deleteUser(String id) throws Exception {
		userMapper.deleteUser(id);
	}
	@Override
	public User findUserById(String id) throws Exception {
		User user = userMapper.findUserById(id);
		return user;
	}
	@Override
	public List<User> findAllUser() throws Exception {
		List<User> users = userMapper.findAllUser();
		return users;
	}
	@Override
	public List<User> findUserByParaMap(ParamUser pu)
			throws Exception {
		List<User> users = userMapper.findUserByParaMap(pu);
		return users;
	}
	@Override
	public List<User> findUsersNoParam() throws Exception {
		List<User> nUsers = userMapper.findUsersNoParam();
		return nUsers;
	}
	@Override
	public List<User> findUserByParaUser(User user) throws Exception {
		List<User> nUsers = userMapper.findUserByParaUser(user);
		return nUsers;
	}
	@Override
	public List<User> findUserByMap(Map<String,Object> map) throws Exception {
		List<User> nUsers = userMapper.findUserByMap(map);
		return nUsers;
	}
	
}
