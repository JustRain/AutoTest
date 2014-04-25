package com.ipinyou.webpage.ownmediawebpage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.ipinyou.entity.ownmedia.OwnMediaCreativeInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class OwnMediaCreativePage {
	public static void search(WebDriver driver,OwnMediaCreativeInfo ocinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>(); 
		list.add(ocinfo.getAdname());
		list.add(ocinfo.getOrname());
		list.add(ocinfo.getPlname());
	    list.add(ocinfo.getStrategyname());
		PubHandle.search(driver, list, PubHandle.titlelist());
	}
	public static void increative(WebDriver driver,String title,String reminder){
		driver.findElement(By.className("ico-add")).click();
		boolean flag = Check.usualexist(driver, title, 5);
		if(flag){
			;
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, reminder, title);
		}
	}
	public static void selcreative(WebDriver driver,String element) throws InterruptedException, AWTException{
		Actions action = new Actions(driver);
	    action.click(driver.findElement(By.id("SWFUpload_0"))).perform();
		Thread.sleep(2000);
		OwnMediaCreativePage.keyBoardDemo();
	    boolean flag = Check.elementexist(driver, By.className("upload-result"), 10, element);
	    if(flag){
	    	Thread.sleep(1000);
	    	driver.findElement(By.linkText("确定")).click();
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "上传创意失败", element, By.className("upload-result"));
	    }
		
	}
	public static void completecreative(WebDriver driver,OwnMediaCreativeInfo ocinfo){
		PubHandle.select(driver, By.cssSelector(".js-creative-tag-parent"), "美食");
		PubHandle.select(driver, By.cssSelector(".js-creative-tag-child"), "美食");
		WebElement arraive = driver.findElement(By.cssSelector(".js-targetUrl-input"));
		arraive.clear();
		arraive.sendKeys(ocinfo.getArriveadress());
		driver.findElement(By.name("strtgyCrtvs[0].creativeTheme")).sendKeys(ocinfo.getOwnmediatheme());
		driver.findElement(By.name("submitForm")).click();
	}
	public static void check(WebDriver driver,String reminder1,String reminder2,OwnMediaCreativeInfo ocinfo) throws InterruptedException{
		boolean flag = Check.elementexist(driver, By.className("creative-name"), 5, ocinfo.getOwnmediatheme());
		if(!flag){
			ScreenshotandAssert.screenandasserttext(driver, reminder1, ocinfo.getOwnmediatheme(), By.className("creative-name"));
		}
		boolean flag1 = Check.elementexist(driver, By.cssSelector(".js-targetUrl"), 5, ocinfo.getArriveadress());
		if(!flag1){
			ScreenshotandAssert.screenandasserttext(driver, reminder2, ocinfo.getArriveadress(), By.cssSelector(".js-targetUrl"));
		}
	}
	
	public static void keyBoardDemo() throws AWTException { 
		 Robot robot = new Robot();	
	     robot.keyPress(KeyEvent.VK_CONTROL); 
	     robot.keyPress(KeyEvent.VK_V); 
	     robot.keyRelease(KeyEvent.VK_CONTROL); 
	     robot.keyRelease(KeyEvent.VK_V); 
	     robot.keyPress(KeyEvent.VK_ENTER);
	     robot.keyRelease(KeyEvent.VK_ENTER);
	    }

}
