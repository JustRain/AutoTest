package com.ipinyou.testcase.selfadvertiser;


import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.selfadvertiser.PlanInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.pub.ScreenshotandAssert;
import com.ipinyou.webpage.selfadvertiserpage.PlanPage;

public class Plan {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	PlanInfo pinfo = new PlanInfo();
	@Parameters({"loginname","password"})
    @Test
    public void login(String loginname,String password){
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitcho(login, driver);
    }
	
	@Parameters({"adname","orname"})
	@Test
	public void search(String adname,String orname){
		PlanInfo pinfo = new PlanInfo();
		pinfo.setAdname(adname);
		pinfo.setOrname(orname);
		
		try {
			PlanPage.search(driver, pinfo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void inplan() {
		try {
			boolean flag = PlanPage.inplan(driver, "创建广告计划", "创建投放计划");
			if(flag){
				;
			}else{
				ScreenshotandAssert.screenandasserttitle(driver, "没有进入创建计划页", "创建广告计划");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	@Parameters({"plname","totalbudget","dailybudget","imptotallimit","clickTotalLimit","imdailylimit","clickdailylimit","campaignindvdlimitimplimit",
	"campaignindvdlimitclicklimit","creativeindvdlimitimplimit","creativeindvdlimitclicklimit","statecoefficient"})
	@Test
	public void createplan(String plname,String totalbudget,String dailybudget,String imptotallimit,String clickTotalLimit,String imdailylimit,
			String clickdailylimit,String campaignindvdlimitimplimit,String campaignindvdlimitclicklimit,String creativeindvdlimitimplimit,String creativeindvdlimitclicklimit,String statecoefficient)
					throws NoSuchElementException, InterruptedException{
		
		pinfo.setPlname(plname);
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
		
		PlanPage.createplan(driver, pinfo);
		boolean flag = PlanPage.submit(driver, "创建投放策略");
		if(flag){
			;
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, pinfo.getPlname()+"新建失败", "创建投放策略");
		}
	}
	
	@Parameters({"orname","plname","totalbudget","dailybudget"})
	@Test
	public void check(String orname,String plname,String totalbudget,String dailybudget){
		try {
			pinfo.setPlname(plname);
			pinfo.setTotalbudget(totalbudget);
			pinfo.setDailybudget(dailybudget);
			pinfo.setOrname(orname);
			PlanPage.check(driver, pinfo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
