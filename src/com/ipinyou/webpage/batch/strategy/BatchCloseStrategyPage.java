package com.ipinyou.webpage.batch.strategy;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.batch.strategy.BatchCloseStrategyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchCloseStrategyPage {
	
	public static void search(WebDriver driver,BatchCloseStrategyInfo bsinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(bsinfo.getAdname());
		list.add(bsinfo.getOrname());
		list.add(bsinfo.getPlname());
		PubHandle.search(driver, list, PubHandle.titlelist());
	}
	public static void batchclose(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[5]/div[2]/a")).click();
		boolean flag = Check.elementexist(driver, By.linkText("批量关闭"), 10, "批量关闭");
		if(flag){
			driver.findElement(By.linkText("批量关闭")).click();
			Thread.sleep(2000);
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有点开批量下拉框", "批量关闭",  By.linkText("批量关闭"));
		}

	}
	public static void check(WebDriver driver,BatchCloseStrategyInfo bsinfo,String name) throws InterruptedException{
		if(name == bsinfo.getName1()){
			driver.findElement(By.id("appendedInputButton")).clear();
			driver.findElement(By.id("appendedInputButton")).sendKeys(bsinfo.getName1());
			driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
			boolean flag = Check.elementexist(driver, By.linkText(bsinfo.getName1()), 5, bsinfo.getName1());
			if(flag){
				boolean flag1 = Check.exist(driver, By.className("btn-oval-close"), 5);
				if(!flag1){
					ScreenshotandAssert.screenandassert(driver, "没有关闭成功", flag1, true);
				}
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "没有搜索到"+bsinfo.getName1(), bsinfo.getName1(), By.linkText(bsinfo.getName1()));
			}
		}else if(name == bsinfo.getName2()){
			driver.findElement(By.id("appendedInputButton")).clear();
			driver.findElement(By.id("appendedInputButton")).sendKeys(bsinfo.getName2());
			driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
			boolean flag = Check.elementexist(driver, By.linkText(bsinfo.getName2()), 5, bsinfo.getName2());
			if(flag){
				boolean flag1 = Check.exist(driver, By.className("btn-oval-close"), 5);
				if(!flag1){
					ScreenshotandAssert.screenandassert(driver, "没有关闭成功", flag1, true);
				}
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "没有搜索到"+bsinfo.getName2(), bsinfo.getName2(), By.linkText(bsinfo.getName2()));
			}
		}
	}
}
