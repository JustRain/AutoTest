package com.ipinyou.webpage.strategy;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.strategy.AlgorithmPlanInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class AlgorithmPlanPage {

	public static void search(WebDriver driver,AlgorithmPlanInfo pinfo) throws InterruptedException{
		 List<String> list = new ArrayList<String>();
		 list.add(pinfo.getAdname());
		 list.add(pinfo.getOrname());
		 PubHandle.search(driver, list,PubHandle.titlelist());
	}
	public static void inplan(WebDriver driver) throws InterruptedException{
			driver.findElement(By.className("ico-add")).click();
			boolean flag = Check.usualexist(driver, "创建广告计划", 2);
			if(!flag){
				ScreenshotandAssert.screenandasserttitle(driver, "没有进入创建计划页", "创建广告计划");
			
		}
	}
	//*[@id="limit.dailyBudget"]
	public static void createplan(WebDriver driver,AlgorithmPlanInfo pinfo){
		driver.findElement(By.id("name")).sendKeys(pinfo.getPlname());
		driver.findElement(By.id("beginDate-endDate")).click();
		driver.findElement(By.xpath("//*[@class='calendar first']//*[@title='"+pinfo.getBegindate()+"']")).click();
		driver.findElement(By.xpath("//*[@class='calendar']//*[@title='"+pinfo.getEnddate()+"']")).click();
		driver.findElement(By.xpath("/html/body/div[7]/div[2]/input[1]")).click();
		driver.findElement(By.id("limit.totalBudget")).sendKeys(pinfo.getTotalbudget());
		driver.findElement(By.name("submitForm")).click();
	}
	public static void check(WebDriver driver){
		boolean flag = Check.usualexist(driver, "创建投放策略", 5);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "计划创建失败", "创建投放策略");
		}
	}
}
