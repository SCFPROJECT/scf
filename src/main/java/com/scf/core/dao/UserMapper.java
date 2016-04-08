package com.scf.core.dao;

import java.util.List;
import java.util.Map;

import com.scf.core.conditionbean.ParamUser;
import com.scf.core.domain.User;

/**
 * 
 * @author hehuan
 *
 */
public interface UserMapper {

	void insertUser(User user);
	void deleteUser(String id);
	User findUserById(String id);
	List<User> findUserByParaMap(ParamUser paraMap);
	void updateUser(User user);
	List<User> findAllUser();
	List<User> findUsersNoParam();
	List<User> findUserByParaUser(User User);
	List<User> findUserByMap(Map<String,Object> map);
}
