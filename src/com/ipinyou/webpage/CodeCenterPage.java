package com.ipinyou.webpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ipinyou.entity.CodeCenterInfo;
import com.ipinyou.pub.PubHandle;

public class CodeCenterPage {
	
	public static void codecenter(WebDriver driver,CodeCenterInfo ccinfo,int a) throws InterruptedException{
		  driver.findElement(By.id("codeCenterMenu")).click();
		  if(a==1){
			  driver.findElement(By.id("agency")).sendKeys(ccinfo.getAgentname());
		  	  driver.findElement(By.id("page_retargeting")).click();
		  	  Thread.sleep(1000);
		  	  WebElement seladvertiser = driver.findElement(By.id("advertiser"));
		  	  seladvertiser.click();
		  	  seladvertiser.sendKeys(ccinfo.getAdvertisername());
		  	  driver.findElement(By.id("page_retargeting")).click();
		  	  Thread.sleep(1000);
		  }else if(a==2){
			  WebElement seladvertiser = driver.findElement(By.id("advertiser"));
		  	  seladvertiser.click();
		  	  seladvertiser.sendKeys(ccinfo.getAdvname());
		  	  driver.findElement(By.className("query-options")).click();
		  	  Thread.sleep(1000);
		  }
	  	  
	}
	
	public static void createcategory(WebDriver driver,CodeCenterInfo ccinfo) throws InterruptedException{
		//driver.findElement(By.id("page_retargeting")).click();
		driver.findElement(By.id("page_category")).click();
		driver.findElement(By.className("ico-add")).click();
		driver.findElement(By.id("name")).sendKeys(ccinfo.getCatename());
		driver.findElement(By.id("paraValue")).clear();
		driver.findElement(By.id("paraValue")).sendKeys(ccinfo.getCatevalue());
		driver.findElement(By.xpath("//*[@id='retarget_form']/div/div[3]/a[1]")).click();
		Thread.sleep(1000);
		PubHandle.usualelementcheck(driver, By.xpath("//*[@id='main']/div/div[4]/div[2]/table/tbody/tr[1]/td[1]"), 2, ccinfo.getCatename()+"创建失败", ccinfo.getCatename());
	}
	
	public static void createpageconversion(WebDriver driver,CodeCenterInfo ccinfo) throws InterruptedException{
		 driver.findElement(By.id("page_conversion")).click();
		 driver.findElement(By.className("ico-add")).click();
		 driver.findElement(By.id("name")).sendKeys(ccinfo.getConname());
		 driver.findElement(By.xpath("//*[@id='conversion_form']/div/div[2]/a[1]")).click();
		 Thread.sleep(1000);
		 PubHandle.usualelementcheck(driver, By.xpath("//*[@id='main']/div[1]/div[4]/div/div[2]/table/tbody/tr[1]/td[1]"), 2, ccinfo.getConname()+"创建失败", ccinfo.getConname());
	}
	
	public static void createcategory1(WebDriver driver,CodeCenterInfo ccinfo) throws InterruptedException{
		//driver.findElement(By.xpath("//*[@id='main']/div[1]/div[3]/a[2]")).click();
		driver.findElement(By.linkText("分类访客找回")).click();
		driver.findElement(By.className("ico-add")).click();
		driver.findElement(By.id("name")).sendKeys(ccinfo.getCatename1());
		WebElement categoryvalue = driver.findElement(By.id("paraValue"));
		categoryvalue.clear();
		categoryvalue.sendKeys(ccinfo.getCatevalue1());
		driver.findElement(By.xpath("//*[@id='retarget_form']/div/div[3]/a[1]")).click();
		Thread.sleep(1000);
		PubHandle.usualelementcheck(driver, By.xpath("//*[@id='main']/div/div[4]/div[2]/table/tbody/tr[1]/td[1]"), 2, ccinfo.getCatename1()+"创建失败", ccinfo.getCatename1());
	}
	public static void createpageconversion1(WebDriver driver,CodeCenterInfo ccinfo) throws InterruptedException{
		//driver.findElement(By.id("page_conversion")).click();
		driver.findElement(By.linkText("转化代码")).click();
		driver.findElement(By.className("ico-add")).click();
		driver.findElement(By.id("name")).sendKeys(ccinfo.getConname1());
		driver.findElement(By.xpath("//*[@id='conversion_form']/div/div[2]/a[1]")).click();
		PubHandle.usualelementcheck(driver, By.xpath("//*[@id='main']/div[1]/div[4]/div/div[2]/table/tbody/tr[1]/td[1]"), 2, ccinfo.getConname1()+"创建失败", ccinfo.getConname1());
	}
	
	public static int getuser(WebDriver driver){
		String user = driver.findElement(By.xpath("//*[@id='welcome']/div[2]/a[1]")).getText();
		if(user.equals("徐冲")){
			return 1;
		}else{
			return 2;
		}
	}

}
