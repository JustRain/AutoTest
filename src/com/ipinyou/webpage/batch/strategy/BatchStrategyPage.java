package com.ipinyou.webpage.batch.strategy;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.ipinyou.entity.batch.strategy.BatchStrategyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchStrategyPage {
	
	public static void search(WebDriver driver,BatchStrategyInfo bsinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(bsinfo.getAdname());
		list.add(bsinfo.getOrname());
		list.add(bsinfo.getPlname());
		PubHandle.search(driver, list, PubHandle.titlelist());
	}
	public static void instrategy(WebDriver driver){
		driver.findElement(By.className("ico-add")).click();
		boolean flag = Check.usualexist(driver, "创建投放策略", 5);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "没有跳转到创建投放策略页", "创建投放策略");
		}
	}
	public static void createstrategy(WebDriver driver,BatchStrategyInfo bsinfo){
		driver.findElement(By.id("name")).sendKeys(bsinfo.getStrategyname());
		driver.findElement(By.id("advertisingMode0")).click();
		PubHandle.select(driver, By.id("priority"), "15");
		PubHandle.select(driver, By.id("algorithmCode"), "哈迪斯");
		driver.findElement(By.id("cpc")).sendKeys("5");
		driver.findElement(By.name("submitForm")).click();
		boolean flag = Check.usualexist(driver, "上传创意", 5);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "策略新建失败", "上传创意");
		}
	}
}
