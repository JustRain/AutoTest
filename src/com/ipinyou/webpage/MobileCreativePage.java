package com.ipinyou.webpage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.ipinyou.entity.MobileCreativeInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class MobileCreativePage {
	public static void keyBoardDemo() throws AWTException { 
		 Robot robot = new Robot();	
	     robot.keyPress(KeyEvent.VK_CONTROL); 
	     robot.keyPress(KeyEvent.VK_V); 
	     robot.keyRelease(KeyEvent.VK_CONTROL); 
	     robot.keyRelease(KeyEvent.VK_V); 
	     robot.keyPress(KeyEvent.VK_ENTER);
	     robot.keyRelease(KeyEvent.VK_ENTER);
	    }
	
	public static void search(WebDriver driver,MobileCreativeInfo minfo){
		List<String> list = new ArrayList<String>();
		list.add(minfo.getAdname());
		list.add(minfo.getOrname());
		list.add(minfo.getPlname());
		list.add(minfo.getStrategyname());
		try {
			PubHandle.search(driver, list,PubHandle.titlelist());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean increative(WebDriver driver,String title){
		driver.findElement(By.className("ico-add")).click();
		boolean flag = Check.usualexist(driver, title, 2);
		if(flag){
			return true;
		}else{
			return false;
		}
	}
	public static void selcreative(WebDriver driver,String element) throws AWTException, InterruptedException{
		By by = By.xpath("/html/body/div[9]/div[2]/div[2]/a");
		Actions action = new Actions(driver);
	    action.click(driver.findElement(By.id("SWFUpload_0"))).perform();
		Thread.sleep(2000);
		keyBoardDemo();
	    boolean flag = Check.elementexist(driver, By.className("upload-result"), 10, element);
	    if(flag){
	    	driver.findElement(by).click();
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "上传创意失败", element, By.className("upload-result"));
	    }
	}
	public static void completecreative(WebDriver driver,MobileCreativeInfo minfo) throws InterruptedException{
		boolean comflag = Check.elementexist(driver, By.className("js-add-new-click"),5 , "添加点击监测地址");
		if(comflag){
			Select first = new Select(driver.findElement(By.cssSelector(".js-creative-tag-parent")));
			first.selectByVisibleText("奢侈品");
			Select second = new Select(driver.findElement(By.cssSelector(".js-creative-tag-child")));
			second.selectByVisibleText("奢侈品");
			Thread.sleep(1000);
			boolean urlflag = Check.elementexist(driver,By.className("js-add-new-click"), 4, "添加点击监测地址");
			if(urlflag){
				WebElement arraive =driver.findElement(By.cssSelector(".js-targetUrl-input"));
				arraive.clear();
				arraive.sendKeys(minfo.getArriveadress());
				driver.findElement(By.className("js-add-new-click")).click();
				driver.findElement(By.className("js-url-input")).sendKeys(minfo.getMonitoraddress());
				driver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div[2]/a[1]")).click();
				driver.findElement(By.className("js-add-new-tracking")).click();
				driver.findElement(By.className("js-url-input")).sendKeys(minfo.getExposureaddress());
				driver.findElement(By.xpath("/html/body/div[9]/div[2]/div/div[3]/a[1]")).click();
				driver.findElement(By.name("strtgyCrtvs[0].creativeTheme")).sendKeys(minfo.getMobiletheme());
				driver.findElement(By.name("submitForm")).click();
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "不能填写地址", "添加点击监测地址", By.className("js-add-new-click"));
			}
					
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "不能完善创意信息", "添加点击监测地址", By.className("js-add-new-click"));
		}
	}

}

