package com.ipinyou.webpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.ScreenshotandAssert;

public class AdvinteriorauditPage {
	static Check c = new Check();
	public static boolean switchpage(WebDriver driver,String element) throws InterruptedException{
		driver.findElement(By.linkText("后台管理")).click();
		boolean flag = Check.elementexist(driver, By.xpath("/html/body/div[3]/h5"), 2, element);
		if(flag){
			return true;
		}else{
			return false;
		}
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
	
	public static void interioraudit(WebDriver driver){
		driver.findElement(By.xpath("//*[@id='listForm']/div/table/tbody/tr[1]/td[12]/a")).click();
		driver.findElement(By.name("submitForm")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean check(WebDriver driver,String element){
		boolean flag = Check.indexexist(driver, element, By.xpath("//*[@id='listForm']/div/table/tbody/tr[1]/td[12]"), 2);
		if(flag){
			return true;
		}else{
			return false;
		}
	}
	public static void checkscreen(WebDriver driver,String reminder,String element){
		ScreenshotandAssert.screenandasserttext(driver, reminder, element,By.xpath("//*[@id='listForm']/div/table/tbody/tr[1]/td[12]"));
	}
	public static void screen(WebDriver driver,String reminder,String element){
		ScreenshotandAssert.screenandasserttext(driver, reminder, element, By.xpath("/html/body/div[3]/h5"));
	} 
}
