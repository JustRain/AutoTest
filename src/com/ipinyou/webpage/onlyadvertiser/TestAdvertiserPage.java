package com.ipinyou.webpage.onlyadvertiser;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.onlyadvertiser.TestAdvertiserInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class TestAdvertiserPage {
	
	public static void switchurl(TestAdvertiserInfo tainfo) throws AWTException{
		keyBoardDemoF();
		PubHandle.setClipboardData(tainfo.getAdvertiserurl());
		keyBoardDemoE();
	}
	
	public static void check(WebDriver driver){
		boolean flag = Check.usualexist(driver, "没有访问该页面的权限", 5);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "广告主权限不对", "没有访问该页面的权限");
		}
	}
	public static void keyBoardDemoF() throws AWTException{
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_F6);
		robot.keyRelease(KeyEvent.VK_F6);
	}
	public static void keyBoardDemoE()throws AWTException{
		 Robot robot = new Robot();	
	     robot.keyPress(KeyEvent.VK_CONTROL);     
	     robot.keyPress(KeyEvent.VK_V); 
	     robot.keyRelease(KeyEvent.VK_CONTROL); 	     
	     robot.keyRelease(KeyEvent.VK_V); 
	     robot.keyPress(KeyEvent.VK_ENTER);
	     robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public static void aftertest(WebDriver driver){
		driver.findElement(By.linkText("返回首页")).click();
		boolean flag = Check.usualexist(driver, "统计报表-投放效果分析报表", 5);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "返回首页失败", "统计报表-投放效果分析报表");
		}
	}

}
