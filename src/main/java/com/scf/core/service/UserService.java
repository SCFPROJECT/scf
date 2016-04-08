package com.scf.core.service;

import java.util.List;
import java.util.Map;

import com.scf.core.conditionbean.ParamUser;
import com.scf.core.domain.User;

/**
 * 
 * @author hehuan
 *
 */
public interface UserService {

	public void insertUser(User user) throws Exception;
	public void updateUser(User user) throws Exception;
	public void deleteUser(String id) throws Exception;
	public User findUserById(String id) throws Exception;
	public List<User> findAllUser() throws Exception;
	public List<User> findUserByParaMap(ParamUser pu) throws Exception;
	public List<User> findUserByParaUser(User user) throws Exception;
	public List<User> findUsersNoParam() throws Exception;
	public List<User> findUserByMap(Map<String,Object> map) throws Exception;
}
