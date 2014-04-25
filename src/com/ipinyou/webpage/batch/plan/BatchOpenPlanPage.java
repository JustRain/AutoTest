package com.ipinyou.webpage.batch.plan;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.batch.plan.BatchOpenPlanInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchOpenPlanPage {
	
	public static void search(WebDriver driver,BatchOpenPlanInfo bpinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(bpinfo.getAdname());
		list.add(bpinfo.getOrname());
		PubHandle.search(driver, list, PubHandle.titlelist());	
	}
	public static void batchopen(WebDriver driver){
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[3]/div[2]")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/div[2]/ul/li[1]/a")).click();
	}
	public static void check1(WebDriver driver,BatchOpenPlanInfo bpinfo) throws InterruptedException{
		pubsearch(driver, bpinfo.getPlanname1());
		Thread.sleep(1000);
		boolean flag = driver.findElement(By.className("btn-oval-open")).isDisplayed();
		if(!flag){
			ScreenshotandAssert.screenandassert(driver, bpinfo.getPlanname1()+"没有开启成功", flag, true);
		}
	}
	public static void check2(WebDriver driver,BatchOpenPlanInfo bpinfo) throws InterruptedException{
		pubsearch(driver, bpinfo.getPlanname2());
		Thread.sleep(1000);
		boolean flag = driver.findElement(By.className("btn-oval-open")).isDisplayed();
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
