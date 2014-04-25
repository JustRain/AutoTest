package com.ipinyou.testcase.selfadvertiser;
import java.awt.AWTException;
import java.awt.Robot;

import java.awt.event.KeyEvent;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.selfadvertiser.CreativeInfo;
import com.ipinyou.entity.LoginInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.pub.ScreenshotandAssert;
import com.ipinyou.webpage.selfadvertiserpage.CreativePage;

public class Creative {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	CreativeInfo cinfo = new CreativeInfo();
	
	public static void keyBoardDemo() throws AWTException { 
	 Robot robot = new Robot();	
     robot.keyPress(KeyEvent.VK_CONTROL); 
     robot.keyPress(KeyEvent.VK_V); 
     robot.keyRelease(KeyEvent.VK_CONTROL); 
     robot.keyRelease(KeyEvent.VK_V); 
     robot.keyPress(KeyEvent.VK_ENTER);
     robot.keyRelease(KeyEvent.VK_ENTER);
    }
	@Parameters({"loginname","password"})
    @Test()
    public void login(String loginname,String password){
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitcho(login, driver);
    }
	
	@Parameters({"adname","orname","plname","strategyname"})
	@Test
	public void search(String adname,String orname,String plname,String strategyname) throws NoSuchElementException{
		
		cinfo.setAdname(adname);
		cinfo.setOrname(orname);
		cinfo.setPlname(plname);
		cinfo.setStrategyname(strategyname);
		try {
			CreativePage.search(driver, cinfo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
  @Test
  public void increative() throws NoSuchElementException{
	 boolean flag = CreativePage.increative(driver, "上传创意");
	 if(flag){
		 ;
	 }else{
		 ScreenshotandAssert.screenandasserttitle(driver, "未跳转至上传创意页", "上传创意");
	 }
  }
  

  @Test
  public void selcreative() throws AWTException,NoSuchElementException, InterruptedException{
	  CreativePage.selcreative(driver,"创意上传失败","本次上传创意，成功1，失败0");
  }
  @Parameters({"arriveadress","monitoraddress","exposureaddress","theme"})
  @Test
  public void completecreative(String arriveadress,String monitoraddress,String exposureaddress,String theme) throws InterruptedException{
	  
	    cinfo.setArriveadress(arriveadress);
	    cinfo.setMonitoraddress(monitoraddress);
	    cinfo.setExposureaddress(exposureaddress);
	    cinfo.setTheme(theme);
	    CreativePage.completecreative(driver, cinfo);
  }
  @Test
  public void check(){
	  try {
		CreativePage.checkcreative(driver, cinfo);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
  }
  @Parameters({"browser","dsppath"})
  @BeforeTest
  public void beforeTest(String browser1,String dsppath) {
	  driver = p.before(browser1, driver);
	  PubHandle.setClipboardData(dsppath);
  }

  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
  }
}
