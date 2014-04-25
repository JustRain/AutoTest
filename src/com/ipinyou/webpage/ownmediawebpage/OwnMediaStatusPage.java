package com.ipinyou.webpage.ownmediawebpage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ipinyou.entity.ownmedia.OwnMediaStatusInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class OwnMediaStatusPage {
	
	public static void search(WebDriver driver,OwnMediaStatusInfo osinfo){
		List<String> list = new ArrayList<String>();
		list.add(osinfo.getAdname());
		list.add(osinfo.getOrname());
		try {
			PubHandle.search(driver, list, PubHandle.titlelist());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	   
	}
	public static void click(WebDriver driver,OwnMediaStatusInfo osinfo) throws InterruptedException{
	 driver.findElement(By.id("appendedInputButton")).sendKeys(osinfo.getStrategyname());
     driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
	 boolean flag = driver.findElement(By.className("btn-oval-close")).isDisplayed();
	 if(flag){
		 driver.findElement(By.className("btn-oval-close")).click();
		 Thread.sleep(1000);
		 boolean flag1 = driver.findElement(By.className("btn-oval-open")).isDisplayed();
		 if(!flag1){
			 ScreenshotandAssert.screenandassert(driver, "û�п�������״̬", flag1, true);
		 }
	 }else{
		 System.out.println("�����ѿ���");
	 }
  	  
	}
	public static void ownmediastatus(WebDriver driver,OwnMediaStatusInfo osinfo) throws InterruptedException{
		driver.findElement(By.id("appendedInputButton")).sendKeys(osinfo.getPlname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		WebElement plstatus = driver.findElement(By.className("btn-oval-close"));
		if(plstatus.isDisplayed()){
			driver.findElement(By.className("btn-oval-close")).click();
			Thread.sleep(1000);
			boolean flag = driver.findElement(By.className("btn-oval-open")).isDisplayed();
			if(flag){
				driver.findElement(By.linkText(osinfo.getPlname())).click();
				click(driver, osinfo);
			}else{
				ScreenshotandAssert.screenandassert(driver, "�ƻ�״̬û�д�", flag, true);
			}
		}else{
			System.out.println("�ƻ��ѿ���");
			driver.findElement(By.linkText(osinfo.getPlname())).click();
			click(driver, osinfo);
		}
	} 
}
