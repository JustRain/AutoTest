package com.ipinyou.webpage.agency;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.agency.AgencyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class AgencyPage {
	
	public static void backmannage(WebDriver driver){
		driver.findElement(By.linkText("后台管理")).click();
		boolean flag = Check.usualexist(driver, "代理商管理", 5);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "没有跳转到代理商管理页", "代理商管理");
		}
	}
	
	
	public static void createagecy(WebDriver driver,AgencyInfo ainfo){
		driver.findElement(By.className("ico-add")).click();
		boolean flag = Check.usualexist(driver, "创建代理商", 5);
		if(flag){
			driver.findElement(By.id("pool.name")).sendKeys(ainfo.getAgencyname());
			driver.findElement(By.id("pool.sysId")).sendKeys(ainfo.getSystemid());
			driver.findElement(By.name("type")).click();
			driver.findElement(By.id("pool.quota")).sendKeys(ainfo.getAmount());
			driver.findElement(By.id("pool.serviceFeeRate")).clear();
			driver.findElement(By.id("pool.serviceFeeRate")).sendKeys(ainfo.getServicerate());
			driver.findElement(By.id("pool.email")).sendKeys(ainfo.getAgencyemail());
			driver.findElement(By.id("role_110")).click();
			driver.findElement(By.id("role_477")).click();
            driver.findElement(By.xpath("//*[@id='pool_form']/div/div/div[5]/input[1]")).click();
		}else{
			
		}
	}

	public static void check(WebDriver driver){
		boolean flag = Check.usualexist(driver, "代理商管理", 5);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "代理商创建失败",  "代理商管理");
		}
	}
	
	public static void datadcheck(WebDriver driver,AgencyInfo ainfo) throws InterruptedException{
		driver.findElement(By.linkText(ainfo.getAgencyname())).click();
		boolean flag = Check.usualexist(driver, "修改代理商", 5);
		if(flag){
			PubHandle.checkvalue(driver, By.id("pool.name"), ainfo.getAgencyname(), "value");
			PubHandle.checkvalue(driver, By.id("pool.sysId"), ainfo.getSystemid(), "value");
			PubHandle.checkvalue(driver, By.id("pool.quota"),ainfo.getAmount(), "value");
			PubHandle.checkvalue(driver,By.id("pool.serviceFeeRate"),  ainfo.getServicerate(), "value");
			PubHandle.usualelementcheck(driver, By.xpath("//*[@id='pool_form']/div/div/div[2]/table/tbody/tr[10]/td[2]/span[1]"),5 , "邮箱错误", ainfo.getAgencyemail());
			PubHandle.isenbled(driver, By.id("role_110"), "角色没有被选中", true);
			PubHandle.isenbled(driver, By.id("role_477"), "角色没有被选中", true);
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入修改代理商页",  "修改代理商");
		}
	}
}
