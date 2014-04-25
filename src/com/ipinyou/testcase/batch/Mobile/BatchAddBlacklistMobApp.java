package com.ipinyou.testcase.batch.Mobile;

import org.testng.annotations.BeforeClass;

public class BatchAddBlacklistMobApp extends BatchAddBlacklistMob {

	
	@BeforeClass
	public void beforeClass(){
		mobiletype="app";
	}
}
