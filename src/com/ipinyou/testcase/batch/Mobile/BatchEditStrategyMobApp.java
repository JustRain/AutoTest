package com.ipinyou.testcase.batch.Mobile;

import org.testng.annotations.BeforeClass;

public class BatchEditStrategyMobApp extends BatchEditStrategyMob {
	@BeforeClass
	public void beforeClass(){
		mobiletype="app";
	}
}
