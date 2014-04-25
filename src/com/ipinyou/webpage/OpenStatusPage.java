package com.ipinyou.webpage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ipinyou.entity.OpenStatusInfo;
import com.ipinyou.pub.PubHandle;

public class OpenStatusPage {
	public static void openstatus(WebDriver driver,OpenStatusInfo osi) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(osi.getAdname());
		list.add(osi.getOrname());
		PubHandle.search(driver, list, PubHandle.titlelist());
		WebElement plstatus = driver.findElement(By.className("btn-oval-close"));
	      if(plstatus.isDisplayed()){
	    	  plstatus.click();
	    	  WebElement pllink = driver.findElement(By.linkText(osi.getPlname()));
	    	  pllink.click();
	      }else{
	    	  WebElement pllink = driver.findElement(By.linkText(osi.getPlname()));
	    	  pllink.click();
	      }
		  
		 
		  WebElement seastr = driver.findElement(By.id("appendedInputButton"));
		  seastr.sendKeys(osi.getStrategyname());
		  WebElement doseastr = driver.findElement(By.xpath("//*[@id='queryForm']/button"));
		  doseastr.click();
		  WebElement strstatus = driver.findElement(By.className("btn-oval-close"));
		  
		  if(strstatus.isDisplayed()){
			  strstatus.click();
			  WebElement strlink = driver.findElement(By.linkText(osi.getStrategyname()));
			  strlink.click();
		  }else{
			  WebElement strlink = driver.findElement(By.linkText(osi.getStrategyname()));
			  strlink.click();
		  }
		
	}

}
