package com.ipinyou.webpage.batch.mobile;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ipinyou.entity.batch.mobile.BatchUploadMobileCreativeInfo;
import com.ipinyou.entity.batch.strategy.BatchUploadCreativeInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchUploadMobileCreativePage {
//	public static BatchUploadMobileCreativeInfo buinfo = new BatchUploadMobileCreativeInfo();
	public static void search(WebDriver driver,BatchUploadMobileCreativeInfo buinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(buinfo.getAdname());
		list.add(buinfo.getOrname());
		list.add(buinfo.getPlname());
		PubHandle.search(driver, list, PubHandle.titlelist());
	}
    public static void batchupload(WebDriver driver,BatchUploadMobileCreativeInfo buinfo) throws AWTException, InterruptedException{
    	driver.findElement(By.id("appendedInputButton")).clear();
    	if(!"".equals(buinfo.getMobiletype()) && "app".equals(buinfo.getMobiletype())){
    		driver.findElement(By.id("appendedInputButton")).sendKeys(buinfo.getAppstrname());	
    	}else if(!"".equals(buinfo.getMobiletype()) && "web".equals(buinfo.getMobiletype())){
    		driver.findElement(By.id("appendedInputButton")).sendKeys(buinfo.getWebstrname());
    	}
		
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[5]/div[5]/a/span[2]")).click();
		boolean flag = Check.usualexist(driver, "上传创意", 5);
		if(flag){
			driver.findElement(By.id("SWFUpload_0")).click();
			keyBoardDemo();
			Thread.sleep(2000);
			creativecheck(driver);
			if(!"".equals(buinfo.getMobiletype()) && "app".equals(buinfo.getMobiletype())){
				driver.findElement(By.className("js-name-input")).sendKeys(buinfo.getAppcreaname());
			}else if(!"".equals(buinfo.getMobiletype()) && "web".equals(buinfo.getMobiletype())){
				driver.findElement(By.className("js-name-input")).sendKeys(buinfo.getWebcreaname());
			}
			PubHandle.select(driver, By.cssSelector(".js-creative-tag-parent"), "奢侈品");
			PubHandle.select(driver, By.cssSelector(".js-creative-tag-child"), "奢侈品");
			WebElement arraive = driver.findElement(By.cssSelector(".js-targetUrl-input"));
			arraive.clear();
			arraive.sendKeys(buinfo.getArriveadress());
			driver.findElement(By.className("js-add-new-click")).click();
			driver.findElement(By.className("js-url-input")).sendKeys(buinfo.getMonitoraddress());
			driver.findElement(By.xpath("/html/body/div[10]/div[2]/div/div[2]/a[1]")).click();
			
			driver.findElement(By.className("js-add-new-tracking")).click();
			driver.findElement(By.className("js-url-input")).sendKeys(buinfo.getExposureaddress());
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
    
    public static void creativecheck(WebDriver driver) throws InterruptedException{
		boolean flag = Check.elementexist(driver, By.className("upload-result"), 10, "本次上传创意，成功1，失败0");
		if(flag){
			driver.findElement(By.xpath("/html/body/div[10]/div[2]/div[2]/a")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "上传失败", "本次上传创意，成功1，失败0", By.className("upload-result"));
		}
	}
    public static void check(WebDriver driver,BatchUploadMobileCreativeInfo buinfo) throws InterruptedException{
		driver.findElement(By.id("appendedInputButton")).clear();
		if(!"".equals(buinfo.getMobiletype()) && "app".equals(buinfo.getMobiletype())){
    		driver.findElement(By.id("appendedInputButton")).sendKeys(buinfo.getAppstrname());
    		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
    		driver.findElement(By.linkText(buinfo.getAppstrname())).click();
    		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='creative_list']/tr/td[4]/span"), 5, buinfo.getAppcreaname());
    		if(!flag){
    			ScreenshotandAssert.screenandasserttext(driver, "检查创意标题不对",buinfo.getAppcreaname(), By.xpath("//*[@id='creative_list']/tr/td[4]/span"));
    		}
    	}else if(!"".equals(buinfo.getMobiletype()) && "web".equals(buinfo.getMobiletype())){
    		driver.findElement(By.id("appendedInputButton")).sendKeys(buinfo.getWebstrname());
    		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
    		driver.findElement(By.linkText(buinfo.getWebstrname())).click();
    		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='creative_list']/tr/td[4]/span"), 5, buinfo.getWebcreaname());
    		if(!flag){
    			ScreenshotandAssert.screenandasserttext(driver, "检查创意标题不对",buinfo.getWebcreaname(), By.xpath("//*[@id='creative_list']/tr/td[4]/span"));
    		}
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
