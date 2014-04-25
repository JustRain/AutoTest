package com.ipinyou.testcase.batch.Mobile;

import org.testng.annotations.BeforeClass;

public class BatchCopyStrategyMobApp extends BatchCopyStrategyMob {
	
	@BeforeClass
	public void beforClass(){
		mobiletype = "app";
	}

}
