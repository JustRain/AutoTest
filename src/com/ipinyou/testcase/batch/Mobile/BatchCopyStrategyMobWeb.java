package com.ipinyou.testcase.batch.Mobile;

import org.testng.annotations.BeforeClass;

public class BatchCopyStrategyMobWeb extends BatchCopyStrategyMob {
	@BeforeClass
	public void beforClass(){
		mobiletype = "web";
	}
}
