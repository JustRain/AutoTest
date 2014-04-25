package com.ipinyou.webpage.batch.creative;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.batch.creative.BatchUpdataCreativeUrlInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchUpdataCreativeUrlPage {
	public static void search(WebDriver driver,BatchUpdataCreativeUrlInfo buinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(buinfo.getAdname());
		list.add(buinfo.getOrname());
		list.add(buinfo.getPlname());
		list.add(buinfo.getStrname());
		PubHandle.search(driver, list, PubHandle.titlelist());
	}
	
	public static void batchupdateurl(WebDriver driver,BatchUpdataCreativeUrlInfo buinfo,int i) throws InterruptedException{
		if(i == 1){
			serveice(driver, By.linkText("批量修改到达地址"),By.xpath("//*[@id='creative_list']/tr/td[9]/div[1]/span[2]"), buinfo.getArriveurl(), "批量修改到达地址","没有找到批量修改到达地址", "到达地址修改失败");
		}else if(i==2){
			serveice(driver, By.linkText("批量修改点击地址"), By.xpath("//*[@id='creative_list']/tr/td[9]/div[2]/span[2]"), buinfo.getClickurl(), "批量修改点击地址", "没有找到批量修改点击地址", "点击地址修改失败");
			
		}else if(i==3){
			serveice(driver, By.linkText("批量修改曝光监测地址"), By.xpath("//*[@id='creative_list']/tr/td[9]/div[3]/div/span[2]"), buinfo.getTrackurl(), "批量修改曝光监测地址", "没有找到批量修改曝光地址", "曝光地址修改失败");
		}
		
	}
	
	public static void serveice(WebDriver driver,By by,By by1,String url,String element,String reminder,String reminder1) throws InterruptedException{		
		batchclick(driver);
		boolean flag = Check.elementexist(driver, by, 5, element);
		if(flag){
			driver.findElement(by).click();
			driver.findElement(By.className("js-input")).sendKeys(url);
			driver.findElement(By.linkText("确  定")).click();
			check(driver, by1, reminder1, url);
		}else{
			ScreenshotandAssert.screenandasserttext(driver, reminder, element, by);
		}
	}
	public static void check(WebDriver driver,By by,String reminder,String checkelement) throws InterruptedException{
		PubHandle.usualelementcheck(driver, by, 5, reminder, checkelement);
		Thread.sleep(1000);
	}
	public static void batchclick(WebDriver driver){
		boolean flag = Check.isenbled(driver, By.id("select_all"), 5);
		if(!flag){
			driver.findElement(By.id("select_all")).click();
		}
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[4]/div[2]/a/span[1]")).click();
	}
}
