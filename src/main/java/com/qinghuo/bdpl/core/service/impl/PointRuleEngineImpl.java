package com.qinghuo.bdpl.core.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.drools.RuleBase;
import org.drools.StatefulSession;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.spi.Activation;

import com.qinghuo.bdpl.core.base.RuleBaseFactorys;
import com.qinghuo.bdpl.core.service.PointRuleEngine;
import com.qinghuo.bdpl.core.test.domain.PointDomain;

public class PointRuleEngineImpl implements PointRuleEngine {

	private RuleBase ruleBase;

	@Override
	public void initEngine() {
		// TODO Auto-generated method stub
		// 设置时间格式
		System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm:ss");
		ruleBase = RuleBaseFactorys.getRuleBase();

		try {
			PackageBuilder pb = getPackageBuilderFromDrlFile();
			ruleBase.addPackage(pb.getPackage());
		} catch (DroolsParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void refreshEnginRule() {
		// TODO Auto-generated method stub
		ruleBase = RuleBaseFactorys.getRuleBase();

		org.drools.rule.Package[] packages = ruleBase.getPackages();

		for (org.drools.rule.Package pg : packages) {
			ruleBase.removePackage(pg.getName());
		}

		initEngine();
	}

	@Override
	public void excuteRuleEngine(final PointDomain pointDomain) {
		// TODO Auto-generated method stub
		if (null == ruleBase.getPackages() || 0 == ruleBase.getPackages().length) {
			return;
		}

		StatefulSession ss = ruleBase.newStatefulSession();
		ss.insert(pointDomain);

		ss.fireAllRules(new org.drools.spi.AgendaFilter() {
			public boolean accept(Activation activation) {
				return !activation.getRule().getName().contains("_test");
			}
		});

		ss.dispose();
	}

	/**
	 * 从Drl规则文件中读取规则
	 * 
	 * @return
	 * @throws Exception
	 */
	private PackageBuilder getPackageBuilderFromDrlFile() throws Exception {
		// 获取测试脚本文件
		List<String> drlFilePath = getTestDrlFile();
		// 装载测试脚本文件
		List<Reader> readers = readRuleFromDrlFile(drlFilePath);

		PackageBuilder backageBuilder = new PackageBuilder();
		for (Reader r : readers) {
			backageBuilder.addPackageFromDrl(r);
		}

		if (backageBuilder.hasErrors()) {
			throw new Exception(backageBuilder.getErrors().toString());
		}

		return backageBuilder;
	}

	/**
	 * @param drlFilePath 脚本文件路径
	 * @return
	 * @throws FileNotFoundException
	 */
	private List<Reader> readRuleFromDrlFile(List<String> drlFilePath) throws FileNotFoundException {
		if (null == drlFilePath || 0 == drlFilePath.size()) {
			return null;
		}

		List<Reader> readers = new ArrayList<Reader>();

		for (String ruleFilePath : drlFilePath) {
			readers.add(new FileReader(new File(ruleFilePath)));
		}

		return readers;

	}

	/**
	 * 获取测试规则文件
	 * 
	 * @return
	 */
	private List<String> getTestDrlFile() {
		List<String> drlFilePath = new ArrayList<String>();
		drlFilePath.add("D:/bdpl-core/src/main/resources/drl/addpoint.drl");
		drlFilePath.add("D:/bdpl-core/src/main/resources/drl/subpoint.drl");
		return drlFilePath;
	}
}
