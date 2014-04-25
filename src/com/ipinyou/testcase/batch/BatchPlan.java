package com.ipinyou.testcase.batch;


import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.batch.plan.BatchPlanInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.plan.BatchPlanPage;

public class BatchPlan {
	WebDriver driver;
	ScreenShot s =  new ScreenShot();
	PubHandle p  = new PubHandle();
	BatchPlanInfo pinfo = new BatchPlanInfo(); 
	@Parameters({"loginname","password"})
    @Test
    public void login(String loginname,String password){
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitch(login, driver);
    }
	
	@Parameters({"adname","orname"})
	@Test
	public void search(String adname,String orname) throws InterruptedException{
		pinfo.setAdname(adname);
		pinfo.setOrname(orname);
		BatchPlanPage.search(driver, pinfo);
	}
	
	@Test
	public void inplan() {
		BatchPlanPage.inplan(driver, pinfo, "计划列表", "没有进入计划列表页");
	}
	
	@Parameters({"plname1","totalbudget","dailybudget","imptotallimit","clickTotalLimit","imdailylimit","clickdailylimit","campaignindvdlimitimplimit",
	"campaignindvdlimitclicklimit","creativeindvdlimitimplimit","creativeindvdlimitclicklimit","statecoefficient"})
	@Test
	public void createplan(String plname1,String totalbudget,String dailybudget,String imptotallimit,String clickTotalLimit,String imdailylimit,
			String clickdailylimit,String campaignindvdlimitimplimit,String campaignindvdlimitclicklimit,String creativeindvdlimitimplimit,String creativeindvdlimitclicklimit,String statecoefficient)
					throws NoSuchElementException, InterruptedException{
		pinfo.setPlname1(plname1);
		pinfo.setTotalbudget(totalbudget);
		pinfo.setDailybudget(dailybudget);
		pinfo.setImptotallimit(imptotallimit);
		pinfo.setClickTotalLimit(clickTotalLimit);
		pinfo.setImdailylimit(imdailylimit);
		pinfo.setClickdailylimit(clickdailylimit);
		pinfo.setCampaignindvdlimitimplimit(campaignindvdlimitimplimit);
		pinfo.setCampaignindvdlimitclicklimit(campaignindvdlimitclicklimit);
		pinfo.setCreativeindvdlimitimplimit(creativeindvdlimitimplimit);
		pinfo.setCreativeindvdlimitclicklimit(creativeindvdlimitclicklimit);
		pinfo.setStatecoefficient(statecoefficient);
		BatchPlanPage.createplan(driver, pinfo);
		BatchPlanPage.check(driver);
	}
	@Parameters({"browser"})
  @BeforeTest
  public void beforeTest(String browser) {
		driver = p.before(browser, driver);
  }

  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
  }
}
