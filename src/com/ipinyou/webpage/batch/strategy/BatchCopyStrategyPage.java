package com.ipinyou.webpage.batch.strategy;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.batch.strategy.BatchCopyStrategyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchCopyStrategyPage {
	public static void search(WebDriver driver,BatchCopyStrategyInfo bcinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(bcinfo.getAdname());
		list.add(bcinfo.getOrname());
		list.add(bcinfo.getPlname());
		PubHandle.search(driver, list, PubHandle.titlelist());	
	}
	public static void batchcopy(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[5]/div[2]/a")).click();
		boolean flag = Check.elementexist(driver, By.linkText("批量复制策略"),5, "批量复制策略");
		if(flag){
			driver.findElement(By.linkText("批量复制策略")).click();
			PubHandle.switchframe(driver, By.className("myiframe"), "src", "campaignlist?id");
			boolean frameflag = Check.elementexist(driver, By.xpath("//*[@id='campaignlist']/table/thead/tr/th[2]"), 10, "计划名称");
			if(frameflag){
				driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr[2]/td[1]/input")).click();
				driver.findElement(By.id("confirm")).click();
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "没有进入复制frame", "计划名称", By.xpath("//*[@id='campaignlist']/table/thead/tr/th[2]"));
			}
			driver.switchTo().defaultContent();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有找到批量复制策略", "批量复制策略",  By.linkText("批量复制策略"));
		}
	}
	
	public static void check(WebDriver driver,BatchCopyStrategyInfo bcinfo){
		driver.findElement(By.linkText("AutoOrder")).click();
		driver.findElement(By.id("appendedInputButton")).sendKeys(bcinfo.getPlname1());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.linkText(bcinfo.getPlname1())).click();
		boolean flag = Check.exist(driver, By.linkText(bcinfo.getCopystr1()), 5);
		pubcheck(driver, "没有找到"+bcinfo.getCopystr1(), flag);
		boolean flag2 = Check.exist(driver, By.linkText(bcinfo.getCopystr2()), 5);
		pubcheck(driver, "没有找到"+bcinfo.getCopystr2(), flag2);
	}
	
	public static void pubcheck(WebDriver driver,String reminder,boolean flag){
		if(!flag){
			ScreenshotandAssert.screenandassert(driver, reminder, flag, true);
		}
	}

}
