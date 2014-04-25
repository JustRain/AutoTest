package com.ipinyou.testcase.dsp;
import java.awt.AWTException;
import java.awt.Robot;

import java.awt.event.KeyEvent;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.CreativeInfo;
import com.ipinyou.entity.LoginInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.pub.ScreenshotandAssert;
import com.ipinyou.webpage.CreativePage;

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
	    p.loginswitch(login, driver);
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
  

  //JPG
  @Parameters({"jpgtheme","dspjpgpath","arriveadress","monitoraddress","exposureaddress","reminder"})
  @Test
  public void slecomcreativejpg(String jpgtheme,String dspjpgpath,String arriveadress,String monitoraddress,String exposureaddress,String reminder) throws InterruptedException, AWTException{
	  cinfo.setJpgtheme(jpgtheme);
	  cinfo.setDspjpgpath(dspjpgpath);
	  cinfo.setArriveadress(arriveadress);
	  cinfo.setMonitoraddress(monitoraddress);
	  cinfo.setExposureaddress(exposureaddress);
	  cinfo.setReminder(reminder);
	  CreativePage.selcreativejpg(driver, cinfo);
	  CreativePage.completecreativejpg(driver, cinfo);
  }
  //FLV
  @Parameters({"flvtheme","dspflvpath"})
  @Test
  public void slecomcreativeflv(String flvtheme,String dspflvpath) throws InterruptedException, AWTException{
	  cinfo.setFlvtheme(flvtheme);
	  cinfo.setDspflvpath(dspflvpath);
//	  cinfo.setArriveadress(arriveadress);
//	  cinfo.setMonitoraddress(monitoraddress);
//	  cinfo.setExposureaddress(exposureaddress);
//	  cinfo.setReminder(reminder);
	  CreativePage.selcreativeflv(driver, cinfo);
	  CreativePage.completecreativeflv(driver, cinfo);
  }
  //SWF
  @Parameters({"swftheme","dspswfpath"})
  @Test
  public void selcomcreativeswf(String swftheme,String dspswfpath) throws InterruptedException, AWTException{
	  cinfo.setSwftheme(swftheme);
	  cinfo.setDspswfpath(dspswfpath);
//	  cinfo.setArriveadress(arriveadress);
//	  cinfo.setMonitoraddress(monitoraddress);
//	  cinfo.setExposureaddress(exposureaddress);
//	  cinfo.setReminder(reminder);
	  CreativePage.selcreativswf(driver, cinfo);
	  CreativePage.completecreativeswf(driver, cinfo);
  }
  //PNG
  @Parameters({"pngtheme","dsppngpath"})
  @Test
  public void selcomcreativepng(String pngtheme,String dsppngpath) throws InterruptedException, AWTException{
	  cinfo.setPngtheme(pngtheme);
	  cinfo.setDsppngpath(dsppngpath);
//	  cinfo.setArriveadress(arriveadress);
//	  cinfo.setMonitoraddress(monitoraddress);
//	  cinfo.setExposureaddress(exposureaddress);
//	  cinfo.setReminder(reminder);
	  CreativePage.selcreativepng(driver, cinfo);
	  CreativePage.completecreativepng(driver, cinfo);
  }
  
  //GIF
  @Parameters({"giftheme","dspgifpath"})
  @Test
  public void selcomcreativegif(String giftheme,String dspgifpath) throws InterruptedException, AWTException{
	  cinfo.setGiftheme(giftheme);
	  cinfo.setDspgifpath(dspgifpath);
//	  cinfo.setArriveadress(arriveadress);
//	  cinfo.setMonitoraddress(monitoraddress);
//	  cinfo.setExposureaddress(exposureaddress);
//	  cinfo.setReminder(reminder);
	  CreativePage.selcreativegif(driver, cinfo);
	  CreativePage.completecreativegif(driver, cinfo);
  }
  
  @Test
  public void check(){
	  try {
		CreativePage.checkcreative(driver, cinfo);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
  }
  @Parameters({"browser"})
  @BeforeTest
  public void beforeTest(String browser1) {
	  driver = p.before(browser1, driver);
	//  PubHandle.setClipboardData(dsppath);
  }

  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
  }
}
