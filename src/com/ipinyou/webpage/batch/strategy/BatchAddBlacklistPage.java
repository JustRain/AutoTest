package com.ipinyou.webpage.batch.strategy;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.batch.strategy.BatchAddBlacklistInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchAddBlacklistPage {
	public static void search(WebDriver driver,BatchAddBlacklistInfo bainfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(bainfo.getAdname());
		list.add(bainfo.getOrname());
		list.add(bainfo.getPlname());
		PubHandle.search(driver, list, PubHandle.titlelist());
	}
	public static void batchaddblacklist(WebDriver driver,BatchAddBlacklistInfo bainfo) throws InterruptedException{
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[5]/div[2]/a")).click();
		boolean flag = Check.elementexist(driver, By.linkText("批量增加黑白名单"), 5, "批量增加黑白名单");
		if(flag){
			driver.findElement(By.linkText("批量增加黑白名单")).click();
			boolean flag1 = Check.usualexist(driver, "批量增加黑白名单", 5);
			if(flag1){
				driver.findElement(By.id("btn-blackUrlIds")).click();
				PubHandle.switchframe(driver, By.className("myiframe"), "src", "customBlackUrls");
				boolean flag2 = Check.elementexist(driver, By.id("listBtn"), 5, "维护黑白名单列表");
				if(flag2){
					driver.findElement(By.id("select_all")).click();
					driver.findElement(By.id("confirm")).click();
				}else{
					ScreenshotandAssert.screenandasserttext(driver, "没有进入黑名单设置iframe", "维护黑白名单列表",  By.id("listBtn"));
				}
			}else{
				ScreenshotandAssert.screenandasserttitle(driver, "没有进入批量增加黑白名单页", "批量增加黑白名单");
			}
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有找到批量增加黑白名单连接", "批量增加黑白名单");
		}
		driver.switchTo().defaultContent();
		driver.findElement(By.name("submitForm")).click();
	}
	public static void check(WebDriver driver,BatchAddBlacklistInfo bainfo) throws InterruptedException{
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(bainfo.getStrname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.className("alter-icon")).click();
		boolean flag = Check.usualexist(driver, "修改投放策略", 5);
		if(flag){
			driver.findElement(By.id("toggleAdvancedSetting")).click();
			boolean blackflag = Check.elementexist(driver, By.id("count-blackWhiteUrlblackUrlIds"), 5,"2");
			if(!blackflag){
				ScreenshotandAssert.screenandasserttext(driver, "黑名单修改失败", "2", By.id("count-blackWhiteUrlblackUrlIds"));
			}
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入修改策略页",  "修改投放策略");
		}
	}
	
}
