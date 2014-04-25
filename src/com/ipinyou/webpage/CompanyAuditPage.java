package com.ipinyou.webpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.pub.Check;
import com.ipinyou.pub.ScreenshotandAssert;

public class CompanyAuditPage {
	public static WebDriver driver;
	public  static Check c = new Check();
	public static boolean switchpage(WebDriver driver,String element){
		driver.findElement(By.linkText("后台管理")).click();
		try {
		  boolean flag = Check.elementexist(driver, By.xpath("/html/body/div[3]/h5"), 2, element);
		  if(flag){
				return true;
			}else{
				return false;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean auditpage(WebDriver driver,String title){
		driver.findElement(By.id("menu_advertiser_audit")).click();
		boolean flag = Check.usualexist(driver, title, 2);
		if(flag){
			return true;
		}else{
			return false;
		}
	}
	
	
	public static void companyaudit(WebDriver driver) throws InterruptedException{
		driver.findElement(By.xpath("//*[@id='listForm']/div/table/tbody/tr[1]/td[11]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.name("submitForm")).click();
		Thread.sleep(2000);
	}
	
	public static boolean check(WebDriver driver,String element){
		boolean flag = Check.indexexist(driver, element, By.xpath("//*[@id='listForm']/div/table/tbody/tr[1]/td[11]"), 2);
		if(flag){
			return true;
		}else{
			return false;
		}
	}
	public static void checkscreen(WebDriver driver,String reminder,String element){
		ScreenshotandAssert.screenandasserttext(driver, reminder, element,By.xpath("//*[@id='listForm']/div/table/tbody/tr[1]/td[11]"));
	}
}
