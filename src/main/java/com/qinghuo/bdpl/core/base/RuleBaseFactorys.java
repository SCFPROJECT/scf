package com.qinghuo.bdpl.core.base;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;

public class RuleBaseFactorys {
	private static RuleBase ruleBase;

	public static RuleBase getRuleBase() {
		return null != ruleBase ? ruleBase : RuleBaseFactory.newRuleBase();
	}
}
