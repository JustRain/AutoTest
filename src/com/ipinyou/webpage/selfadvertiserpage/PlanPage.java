package com.ipinyou.webpage.selfadvertiserpage;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.selfadvertiser.PlanInfo;
import com.ipinyou.pub.Check;

import com.ipinyou.pub.ScreenshotandAssert;

public class PlanPage {
	
	public static void search(WebDriver driver,PlanInfo pinfo) throws InterruptedException{
		driver.findElement(By.id("appendedInputButton")).sendKeys(pinfo.getOrname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.linkText(pinfo.getOrname())).click();		
	}
	public static boolean inplan(WebDriver driver,String title,String element) throws InterruptedException{
		boolean flag1 = Check.elementexist(driver, By.className("ico-add"), 4, element);
		if(!flag1){
			driver.findElement(By.className("ico-add")).click();
			boolean flag = Check.usualexist(driver, title, 2);
			if(flag){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	public static void createplan(WebDriver driver,PlanInfo pinfo){
		driver.findElement(By.id("name")).sendKeys(pinfo.getPlname());
		driver.findElement(By.id("beginDate-endDate")).click();
		//计划起止日期
		driver.findElement(By.xpath("/html/body/div[7]/div[1]/div[1]/table/tbody/tr[7]/td[1]")).click();
		driver.findElement(By.xpath("/html/body/div[7]/div[1]/div[2]/table/tbody/tr[7]/td[7]")).click();
		
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
	public static boolean submit (WebDriver driver,String title){
		driver.findElement(By.name("submitForm")).click();
		boolean flag = Check.usualexist(driver, title, 2);
		if(flag){
			return true;
		}else{
			return false;
		}
	}
	
	public static void check(WebDriver driver,PlanInfo pinfo) throws InterruptedException,IllegalArgumentException{
		String value = "value";
		driver.findElement(By.linkText(pinfo.getOrname())).click();
		boolean pageflag = Check.usualexist(driver, "计划列表", 2);
		if(!pageflag){
			ScreenshotandAssert.screenandasserttitle(driver, "没有跳回计划列表页", "计划列表");
		}
		driver.findElement(By.className("alter-icon")).click();
		Thread.sleep(1000);
		boolean flag = Check.elementvalue(driver, By.id("name"), 2, pinfo.getPlname(), value);
		if(!flag){
			ScreenshotandAssert.screenandassertvalue(driver, "计划名称不对", pinfo.getPlname(), By.id("name"), value);
		}
		boolean flag1 = Check.elementvalue(driver, By.id("limit.totalBudget"), 2, pinfo.getTotalbudget()+".00", value);
		if(!flag1){
			ScreenshotandAssert.screenandassertvalue(driver, "总预算不对", pinfo.getTotalbudget()+".00", By.id("limit.totalBudget"), value);
		}
		boolean flag2 = Check.elementvalue(driver, By.id("limit.dailyBudget"), 2, pinfo.getDailybudget()+".00", value);
		if(!flag2){
			ScreenshotandAssert.screenandassertvalue(driver, "每日预算不对", pinfo.getDailybudget()+".00", By.id("limit.dailyBudget"), value);
		}
		
	}

}
