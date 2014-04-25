package com.ipinyou.webpage.batch.plan;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.ipinyou.entity.batch.plan.*;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchPlanPage {
	
	public static void search(WebDriver driver,BatchPlanInfo pinfo) throws InterruptedException{
		 List<String> list = new ArrayList<String>();
		 list.add(pinfo.getAdname());
		 list.add(pinfo.getOrname());
		 PubHandle.search(driver, list,PubHandle.titlelist());
	}
	public static void inplan(WebDriver driver,BatchPlanInfo pinfo,String title,String reminder){
		boolean flag = Check.usualexist(driver, title,5);
		if(flag){
			driver.findElement(By.className("ico-add")).click();
			boolean cflag = Check.usualexist(driver, "创建广告计划", 5);
			if(!cflag){
				ScreenshotandAssert.screenandasserttitle(driver, "没有进入创建计划页", "创建广告计划");
			}	
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, reminder, title);
		}
	}
	public static void createplan(WebDriver driver,BatchPlanInfo pinfo){
		driver.findElement(By.id("name")).sendKeys(pinfo.getPlname1());
		driver.findElement(By.id("beginDate-endDate")).click();
		//选择起止日期
		driver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/table/tbody/tr[5]/td[1]")).click();
		driver.findElement(By.xpath("/html/body/div[7]/div[1]/div[2]/table/tbody/tr[5]/td[7]")).click();
		driver.findElement(By.xpath("/html/body/div[7]/div[2]/input[1]")).click();
		driver.findElement(By.id("limit.totalBudget")).sendKeys(pinfo.getTotalbudget());
		driver.findElement(By.id("limit.dailyBudget")).sendKeys(pinfo.getDailybudget());
		driver.findElement(By.id("limit.impTotalLimit")).sendKeys(pinfo.getImptotallimit());
		driver.findElement(By.id("limit.clickTotalLimit")).sendKeys(pinfo.getClickTotalLimit());
		driver.findElement(By.id("limit.impDailyLimit")).sendKeys(pinfo.getImdailylimit());
		driver.findElement(By.id("limit.clickDailyLimit")).sendKeys(pinfo.getClickdailylimit());
		driver.findElement(By.id("campaignIndvdLimitimpLimit")).sendKeys(pinfo.getCampaignindvdlimitimplimit());
		driver.findElement(By.id("campaignIndvdLimitclickLimit")).sendKeys(pinfo.getCampaignindvdlimitclicklimit());
		driver.findElement(By.id("creativeIndvdLimitimpLimit")).sendKeys(pinfo.getCreativeindvdlimitimplimit());
		driver.findElement(By.id("creativeIndvdLimitclickLimit")).sendKeys(pinfo.getCreativeindvdlimitclicklimit());
	}
	public static void check(WebDriver driver){
		driver.findElement(By.name("submitForm")).click();
		boolean flag = Check.usualexist(driver, "创建投放策略", 5);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "创建计划失败", "创建投放策略");
		}
	}
}