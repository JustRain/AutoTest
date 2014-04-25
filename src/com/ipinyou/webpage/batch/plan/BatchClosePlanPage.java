package com.ipinyou.webpage.batch.plan;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.batch.plan.BatchClosePlanInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchClosePlanPage {
	public static void search(WebDriver driver,BatchClosePlanInfo bpinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(bpinfo.getAdname());
		list.add(bpinfo.getOrname());
		PubHandle.search(driver, list, PubHandle.titlelist());	
	}
	public static void batchclose(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/div[2]/a/span[1]")).click();
		boolean flag = Check.elementexist(driver, By.linkText("批量关闭"), 10, "批量关闭");
		if(flag){
			driver.findElement(By.linkText("批量关闭")).click();
			Thread.sleep(2000);
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有找到批量关闭", "批量关闭",  By.linkText("批量关闭"));
		}
	
	}
	public static void check1(WebDriver driver,BatchClosePlanInfo bpinfo){
		pubsearch(driver, bpinfo.getPlanname1());
		boolean flag = driver.findElement(By.className("btn-oval-close")).isDisplayed();
		if(!flag){
			ScreenshotandAssert.screenandassert(driver, bpinfo.getPlanname1()+"没有开启成功", flag, true);
		}
	}
	public static void check2(WebDriver driver,BatchClosePlanInfo bpinfo){
		pubsearch(driver, bpinfo.getPlanname2());
		boolean flag = driver.findElement(By.className("btn-oval-close")).isDisplayed();
		if(!flag){
			ScreenshotandAssert.screenandassert(driver, bpinfo.getPlanname2()+"没有开启成功", flag, true);
		}
	}

	
	public static void pubsearch(WebDriver driver,String searchname){
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(searchname);
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
	}
	
}
