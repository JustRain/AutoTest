package com.ipinyou.testcase.dsp;

import org.testng.annotations.BeforeClass;

public class BatchMobPlan extends Plan {
	@BeforeClass
	public void beforeClass(){
		plantype = "batchmob";
	}
}
