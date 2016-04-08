package com.scf.core.domain;

public class User {
	private String id;
	private String name;
	private String email;
	private String telephone;
	private int age;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
//	@Override
//	public String toString() {
//		StringBuffer sb = new StringBuffer();
//		sb.append("[id = " + id);
//		sb.append(",name = " + name);
//		sb.append(",email = " + email);
//		sb.append(",telephone = " + telephone);
//		sb.append(",age = " + age + "]");
//		return sb.toString();
//	}
	
}
