package com.ipinyou.webpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.AllotInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class AllotPage {
	public static  WebDriver driver;
	public static Check c = new Check();
	public static PubHandle p = new PubHandle();
	public static void allot(WebDriver driver,String title,String reminder,AllotInfo alinfo) {
		driver.findElement(By.id("finaceAdminMenu")).click();
		boolean flag = Check.usualexist(driver, title, 2);
		if(flag){
			driver.findElement(By.linkText("资金管理")).click();
			driver.findElement(By.id("advertiser")).sendKeys(alinfo.adname);
			driver.findElement(By.id("submit")).click();
			driver.findElement(By.linkText("资金分配")).click();
			driver.findElement(By.id("tradeAmount")).sendKeys(alinfo.acount);
			driver.findElement(By.id("remark")).sendKeys(alinfo.remark);
			driver.findElement(By.id("payPassword")).sendKeys(alinfo.paypassword);
			driver.findElement(By.id("form_confirm")).click();
			driver.findElement(By.className("confirm")).click();
			try {
				PubHandle.usualelementcheck(driver, By.linkText("资金分配"), 2,"充值失败", "资金分配");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, reminder, title);
		}
	}	
}
