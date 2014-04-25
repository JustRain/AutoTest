package com.ipinyou.testcase.dsp;

import static org.testng.AssertJUnit.assertEquals;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.MobileCreativeInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.pub.ScreenshotandAssert;
import com.ipinyou.webpage.MobileCreativePage;

public class MobileCreative {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
    MobileCreativeInfo minfo = new MobileCreativeInfo();

	public  void setClipboardData(String string) {
		StringSelection stsel = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stsel, stsel);

	}
	
  @Parameters({"loginname","password"})
	@Test
  public void login(String loginname,String password) {
	  LoginInfo login = new LoginInfo(loginname,password);
      p.loginswitch(login, driver);
  }
  @Parameters({"adname","orname","plname","strategyname"})
	@Test()
	public void search(String adname,String orname,String plname,String strategyname) throws NoSuchElementException{
	  minfo.setAdname(adname);
	  minfo.setOrname(orname);
	  minfo.setPlname(plname);
	  minfo.setStrategyname(strategyname);
	  MobileCreativePage.search(driver, minfo);
	}
  @Test
  public void increative() throws NoSuchElementException{
	  
	  boolean flag = MobileCreativePage.increative(driver, "上传创意");
	  if(flag){
		  ;
	  }else{
		  ScreenshotandAssert.screenandasserttitle(driver, "没有跳转到策略新建页", "上传创意");
	  }
  }
  @Test
  public void selcreative() throws AWTException,NoSuchElementException, InterruptedException{
	  
	  
	  Actions action = new Actions(driver);
	  action.click(driver.findElement(By.id("SWFUpload_0"))).perform();
	  Thread.sleep(3000);
	  Creative.keyBoardDemo();
	  Thread.sleep(3000);
	  WebElement uploadresult = driver.findElement(By.className("upload-result"));
	  String result = uploadresult.getText();
	  assertEquals("上传成功","本次上传创意，成功1，失败0", result);
      
	  WebElement createconfirm = driver.findElement(By.xpath("/html/body/div[9]/div[2]/div[2]/a"));
	  createconfirm.click();
  }
  
  @Parameters({"arriveadress","monitoraddress","exposureaddress","mobiletheme"})
  @Test
  public void completecreative(String arriveadress,String monitoraddress,String exposureaddress,String mobiletheme){
	  minfo.setArriveadress(arriveadress);
	  minfo.setMonitoraddress(monitoraddress);
	  minfo.setExposureaddress(exposureaddress);
	  minfo.setMobiletheme(mobiletheme);
  }
  @Parameters({"browser","mobilepath"})
  @BeforeTest
  public void beforeTest(String browser,String mobilepath) {
	  driver = p.before(browser, driver);
	  setClipboardData(mobilepath);
  }

  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
  }
}
