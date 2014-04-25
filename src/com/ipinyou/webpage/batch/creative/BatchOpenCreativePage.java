package com.ipinyou.webpage.batch.creative;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.batch.creative.BatchOpenCreativeInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchOpenCreativePage {
	
	public static void search(WebDriver driver,BatchOpenCreativeInfo bcinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(bcinfo.getAdname());
		list.add(bcinfo.getOrname());
		list.add(bcinfo.getPlname());
		list.add(bcinfo.getStrname());
		PubHandle.search(driver, list, PubHandle.titlelist());	
	}
	public static void batchopencreative(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[4]/div[2]/a/span[1]")).click();
		boolean flag = Check.elementexist(driver, By.linkText("批量开启"), 5, "批量开启");
		if(flag){
			driver.findElement(By.xpath("/html/body/div[3]/div/div/div[4]/div[2]/ul/li[1]/a")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有找到批量开启连接",  "批量开启", By.linkText("批量开启"));
		}
	}
	
	public static void check(WebDriver driver){
		PubHandle.isdisplay(driver, By.className("btn-oval-open"), 5, "开启失败",true);
	}

}
