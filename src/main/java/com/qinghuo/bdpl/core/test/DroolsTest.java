package com.qinghuo.bdpl.core.test;

import java.io.IOException;

import com.qinghuo.bdpl.core.service.PointRuleEngine;
import com.qinghuo.bdpl.core.service.impl.PointRuleEngineImpl;
import com.qinghuo.bdpl.core.test.domain.PointDomain;

public class DroolsTest {

	public static void main(String[] args) throws IOException {
		PointRuleEngine pointEngine = new PointRuleEngineImpl();
		System.out.println(pointEngine.toString());

		System.out.println("初始化规则引擎...");
		pointEngine.initEngine();
		System.out.println("初始化规则引擎结束.");

		final PointDomain pointDomain = new PointDomain();

		pointDomain.setUserName("yuanzhao");
		pointDomain.setBackMoney(100);
		pointDomain.setBackNums(1);
		pointDomain.setBillThisMonth(5);
		pointDomain.setBirthDay(true);
		pointDomain.setBuyMoney(500);
		pointDomain.setBuyNums(5);
		pointDomain.setPoint(new Long(100));

		pointEngine.excuteRuleEngine(pointDomain);

		System.out.println("执行完毕BillThisMonth：" + pointDomain.getBillThisMonth());
		System.out.println("执行完毕BuyMoney：" + pointDomain.getBuyMoney());
		System.out.println("执行完毕BuyNums：" + pointDomain.getBuyNums());

		System.out.println("执行完毕规则引擎决定发送积分：" + pointDomain.getPoint());

	}
}
