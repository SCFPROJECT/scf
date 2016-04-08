package com.qinghuo.bdpl.core.test.domain;

public class PointDomain {

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isBirthDay() {
		return birthDay;
	}

	public void setBirthDay(boolean birthDay) {
		this.birthDay = birthDay;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public int getBuyNums() {
		return buyNums;
	}

	public void setBuyNums(int buyNums) {
		this.buyNums = buyNums;
	}

	public int getBackNums() {
		return backNums;
	}

	public void setBackNums(int backNums) {
		this.backNums = backNums;
	}

	public double getBuyMoney() {
		return buyMoney;
	}

	public void setBuyMoney(double buyMoney) {
		this.buyMoney = buyMoney;
	}

	public double getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(double backMoney) {
		this.backMoney = backMoney;
	}

	public int getBillThisMonth() {
		return billThisMonth;
	}

	public void setBillThisMonth(int billThisMonth) {
		this.billThisMonth = billThisMonth;
	}

	private boolean birthDay;

	private Long point;

	private int buyNums;

	private int backNums;

	private double buyMoney;

	private double backMoney;

	private int billThisMonth;

	/**
	 * 记录积分发送流水，防止重复发放
	 * 
	 * @param userName 用户名
	 * @param type 积分发放类型
	 */
	public void recordPointLog(String userName, String type) {
		System.out.println("增加对" + userName + "的类型为" + type + "的积分操作记录.");
	}

}
