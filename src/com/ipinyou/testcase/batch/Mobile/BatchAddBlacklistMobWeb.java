package com.ipinyou.testcase.batch.Mobile;

import org.testng.annotations.BeforeClass;

public class BatchAddBlacklistMobWeb extends BatchAddBlacklistMob {

	@BeforeClass
	public void beforeClass(){
		mobiletype="web";
	}
}
