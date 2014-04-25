package com.ipinyou.webpage.batch.strategy;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ipinyou.entity.batch.strategy.BatchUploadCreativeInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchUploadCreativeswfPage {
	public static void search(WebDriver driver,BatchUploadCreativeInfo bcinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(bcinfo.getAdname());
		list.add(bcinfo.getOrname());
		list.add(bcinfo.getPlname());
		PubHandle.search(driver, list, PubHandle.titlelist());
	}
	
	public static void batchuploadcreative(WebDriver driver,BatchUploadCreativeInfo bcinfo) throws AWTException, InterruptedException{
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(bcinfo.getStr1());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[5]/div[5]/a/span[2]")).click();
		boolean flag = Check.usualexist(driver, "上传创意", 5);
		if(flag){
			driver.findElement(By.id("SWFUpload_3")).click();
			keyBoardDemo();
			Thread.sleep(2000);
			creativecheck(driver);
			driver.findElement(By.className("js-name-input")).sendKeys(bcinfo.getCreaname());
			PubHandle.select(driver, By.cssSelector(".js-creative-tag-parent"), "奢侈品");
			PubHandle.select(driver, By.cssSelector(".js-creative-tag-child"), "奢侈品");
			WebElement arraive = driver.findElement(By.cssSelector(".js-targetUrl-input"));
			arraive.clear();
			arraive.sendKeys(bcinfo.getArriveadress());
			driver.findElement(By.className("js-add-new-click")).click();
			driver.findElement(By.className("js-url-input")).sendKeys(bcinfo.getMonitoraddress());
			driver.findElement(By.xpath("/html/body/div[10]/div[2]/div/div[2]/a[1]")).click();
			
			driver.findElement(By.className("js-add-new-tracking")).click();
			driver.findElement(By.className("js-url-input")).sendKeys(bcinfo.getExposureaddress());
			driver.findElement(By.xpath("/html/body/div[10]/div[2]/div/div[3]/a[1]")).click();
			driver.findElement(By.name("submitForm")).click();
			
			boolean flag1 = Check.usualexist(driver, "策略列表", 5);
			if(!flag1){
				ScreenshotandAssert.screenandasserttitle(driver, "创意完成失败", "策略列表");
			}
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入上床创意页", "上传创意");
		}
	}	
	public static void check(WebDriver driver,BatchUploadCreativeInfo bcinfo) throws InterruptedException{
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(bcinfo.getStr1());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.linkText(bcinfo.getStr1())).click();
		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='creative_list']/tr/td[4]/span"), 5, bcinfo.getCreaname());
		if(!flag){
			ScreenshotandAssert.screenandasserttext(driver, "检查创意标题不对",bcinfo.getCreaname(), By.xpath("//*[@id='creative_list']/tr/td[4]/span"));
		}
	}
	public static void creativecheck(WebDriver driver) throws InterruptedException{
		boolean flag = Check.elementexist(driver, By.className("upload-result"), 10, "本次上传创意，成功1，失败0");
		if(flag){
			driver.findElement(By.xpath("/html/body/div[10]/div[2]/div[2]/a")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "上传失败", "本次上传创意，成功1，失败0", By.className("upload-result"));
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
