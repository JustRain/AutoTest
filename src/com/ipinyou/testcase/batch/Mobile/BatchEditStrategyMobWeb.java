package com.ipinyou.testcase.batch.Mobile;

import org.testng.annotations.BeforeClass;

public class BatchEditStrategyMobWeb extends BatchEditStrategyMob {
	@BeforeClass
	public void beforeClass(){
		mobiletype="web";
	}
}
