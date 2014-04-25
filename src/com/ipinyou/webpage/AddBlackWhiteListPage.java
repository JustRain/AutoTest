package com.ipinyou.webpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.AddBlackWhiteListInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.ScreenshotandAssert;

public class AddBlackWhiteListPage {
	public static void switchlist(WebDriver driver,AddBlackWhiteListInfo abinfo) throws InterruptedException{
		driver.findElement(By.linkText("媒体管理")).click();
		driver.findElement(By.linkText("黑白名单管理")).click();
		driver.findElement(By.id("agency")).sendKeys(abinfo.getAgentname());
	    driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div[2]/span")).click();
	    boolean flag = Check.exist(driver,By.id("advertiser"), 5);
	    if(flag){
	    	driver.findElement(By.id("advertiser")).sendKeys(abinfo.getAdvertisername());
	  		driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/div[2]/span")).click();
	    }
	}
	
	public static void createblacklist(WebDriver driver,AddBlackWhiteListInfo abinfo) throws InterruptedException{
		boolean flagc = Check.elementexist(driver, By.xpath("/html/body/div[3]/div/div[5]/div[1]/a/span[2]"), 5, "创建黑名单");
		if(flagc){
			driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/div[1]/a/span[2]")).click();
			boolean flag = Check.elementexist(driver, By.xpath("//*[@id='form']/dl/dt/span"), 5, "请在此输入域名列表");
			if(flag){
					driver.findElement(By.id("name")).sendKeys(abinfo.getBlackname());
					driver.findElement(By.id("urlList")).sendKeys(abinfo.getBlackvalue());
					driver.findElement(By.cssSelector(".btn-success")).click();
				}else{
					ScreenshotandAssert.screenandasserttext(driver, "没有进入新增黑名单弹层", "请在此输入域名列表", By.xpath("//*[@id='form']/dl/dt/span"));
			}		
		}
	}
	public static void blackcheck(WebDriver driver,AddBlackWhiteListInfo abinfo) throws InterruptedException{
		check(driver, abinfo.getBlackname());
	}
	
	public static void check(WebDriver driver,String list) throws InterruptedException{
		Thread.sleep(2000);
	    boolean flag = Check.elementexist(driver, By.xpath("//*[@id='form']/div/table/tbody/tr[1]/td[3]"), 5,list);
	    if(!flag){
	    	ScreenshotandAssert.screenandasserttext(driver, list+"创建失败",list, By.xpath("//*[@id='form']/div/table/tbody/tr[1]/td[3]"));
	    }
		
	}
	public static void createwhitelist(WebDriver driver,AddBlackWhiteListInfo abinfo) throws InterruptedException{
		driver.findElement(By.xpath("/html/body/div[3]/div/div[5]/div[2]/a/span[2]")).click();
		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='form']/dl/dt/span"), 5, "请在此输入域名列表");
		if(flag){
			driver.findElement(By.id("name")).sendKeys(abinfo.getWhitename());
			driver.findElement(By.id("urlList")).sendKeys(abinfo.getWhitevalue());
			driver.findElement(By.cssSelector(".btn-success")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有进入创建黑白名单弹层", "请在此输入域名列表", By.xpath("//*[@id='form']/dl/dt/span"));
		}
	}
	public static void whitecheck(WebDriver driver,AddBlackWhiteListInfo abinfo) throws InterruptedException{
		check(driver, abinfo.getWhitename());
	}

}
