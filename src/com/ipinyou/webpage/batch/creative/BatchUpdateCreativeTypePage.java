package com.ipinyou.webpage.batch.creative;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.ipinyou.entity.batch.creative.BatchUpdateCreativeTypeInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchUpdateCreativeTypePage {
	public static void search(WebDriver driver,BatchUpdateCreativeTypeInfo btinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(btinfo.getAdname());
		list.add(btinfo.getOrname());
		list.add(btinfo.getPlname());
		list.add(btinfo.getStrname());
		PubHandle.search(driver, list, PubHandle.titlelist());	
	}
	public static void batchupdatecreativetheme(WebDriver driver,BatchUpdateCreativeTypeInfo btinfo) throws InterruptedException{
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[4]/div[2]/a/span[1]")).click();
		boolean flag = Check.elementexist(driver, By.linkText("批量修改类目"), 5, "批量修改类目");
		if(flag){
			driver.findElement(By.linkText("批量修改类目")).click();
			PubHandle.select(driver, By.name("creative_tag_parent_0"), btinfo.getParenttext());
			PubHandle.select(driver, By.xpath("/html/body/div[10]/div[2]/div/div[1]/select[2]"), btinfo.getChildtext());
			driver.findElement(By.linkText("确  定")).click();
			Thread.sleep(500);
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有找到批量修改类目", "批量修改类目", By.linkText("批量修改类目"));
		}
	}
	public static void check(WebDriver driver,BatchUpdateCreativeTypeInfo btinfo) throws InterruptedException{
		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='creative_list']/tr/td[7]"), 5, btinfo.getParenttext()+"\n"+btinfo.getChildtext());
		if(!flag){
			ScreenshotandAssert.screenandasserttext(driver, "类目修改失败", btinfo.getParenttext()+"\n"+btinfo.getChildtext(), By.xpath("//*[@id='creative_list']/tr/td[7]"));
		}
	}

}
