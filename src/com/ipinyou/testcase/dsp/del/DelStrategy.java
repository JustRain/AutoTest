package com.ipinyou.testcase.dsp.del;

import java.awt.AWTException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.StrategyInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.webpage.del.DelStrategyPage;

public class DelStrategy {
	WebDriver driver;
	PubHandle p  = new PubHandle();
	StrategyInfo sinfo = new StrategyInfo();
	@Parameters({"loginname","password"})
	@Test
    public void login(String loginname,String password) throws NoSuchElementException{
		  LoginInfo login = new LoginInfo(loginname,password);
	      p.loginswitch(login, driver);
    }
	@Parameters({"adname","orname","plname"})
	@Test
	public void search(String adname,String orname,String plname) throws NoSuchElementException{
		
		sinfo.setAdname(adname);
		sinfo.setOrname(orname);
		sinfo.setPlname(plname);
		DelStrategyPage.search(driver, sinfo);
	}
	@Test
	public void instrategy() throws NoSuchElementException{
		DelStrategyPage.instrategy(driver);
	}
	@Parameters({"delstrtegy"})
	@Test
	public void createStr(String delstrtegy){
		sinfo.setStrategyname(delstrtegy);
		DelStrategyPage.createstrategy(driver, sinfo);
	}
	
	@Parameters({"delpath","delcreativetheme","delurl"})
	@Test
	public void uploadcreative(String delpath,String delcreativetheme,String delurl) throws AWTException, InterruptedException{
		sinfo.setDelpath(delpath);
		sinfo.setDelcreativetheme(delcreativetheme);
		sinfo.setDelurl(delurl);
		DelStrategyPage.uploadcreative(driver, sinfo);
	}
	@Test
	public void delcreative(){
		DelStrategyPage.delcreative(driver, sinfo);
	}
	
  @Test
  public void delstr(){
	  DelStrategyPage.delStr(driver, sinfo);
  }
  @Test
  public void credatacheck() throws SQLException{
	  DelStrategyPage.creadatacheck(sinfo);
  }
  @Test
  public void strdatacheck() throws SQLException{
	  DelStrategyPage.strdatacheck(sinfo);
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
