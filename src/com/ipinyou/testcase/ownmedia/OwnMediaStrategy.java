package com.ipinyou.testcase.ownmedia;


import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.ownmedia.OwnMediaStrategyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.pub.ScreenshotandAssert;
import com.ipinyou.webpage.ownmediawebpage.OwnMediaStrategyPage;

public class OwnMediaStrategy {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	OwnMediaStrategyInfo ominfo = new OwnMediaStrategyInfo();
	
	@Parameters({"loginname","password"})
    @Test()
    public void login(String loginname,String password) throws NoSuchElementException{
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitch(login, driver);
    }
	
	@Parameters({"adname","orname","plname"})
	@Test()
	public void search(String adname,String orname,String plname) throws NoSuchElementException{
		ominfo.setAdname(adname);
		ominfo.setOrname(orname);
		ominfo.setPlname(plname);
		OwnMediaStrategyPage.search(driver, ominfo);
	}
	
	@Test()
	public void instrategy() throws NoSuchElementException{
		
		boolean flag = OwnMediaStrategyPage.instrategy(driver, "创建投放策略") ;
		if(flag){
			;
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入新建策略页", "创建投放策略");
		}
	}
	
    @Parameters({"strategyname","advertiserposition"})
	@Test
  public void Createownstrategy(String strategyname,String advertiserposition) throws InterruptedException {
    	ominfo.setStrategyname(strategyname);
    	ominfo.setAdvertiserposition(advertiserposition);
    	OwnMediaStrategyPage.Createownstrategy(driver, ominfo);
    	OwnMediaStrategyPage.check(driver, "上传创意", "策略保存失败");
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
