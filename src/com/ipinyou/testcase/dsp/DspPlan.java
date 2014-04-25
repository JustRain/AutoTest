package com.ipinyou.testcase.dsp;

import org.testng.annotations.BeforeClass;

public class DspPlan extends Plan {

	@BeforeClass
	public void beforeClass(){
		plantype = "dsp";
	}
}
