package com.qinghuo.bdpl.core.dao;

import java.util.List;
import java.util.Map;

import com.qinghuo.bdpl.core.conditionbean.ParamUser;
import com.qinghuo.bdpl.core.domain.User;

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
