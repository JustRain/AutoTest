package com.ipinyou.webpage.batch.creative;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.batch.creative.BatchUpdateCreativeThemeInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchUpdateCreativeThemePage {
	public static void search(WebDriver driver,BatchUpdateCreativeThemeInfo btinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(btinfo.getAdname());
		list.add(btinfo.getOrname());
		list.add(btinfo.getPlname());
		list.add(btinfo.getStrname());
		PubHandle.search(driver, list, PubHandle.titlelist());	
	}
	public static void batchupdatecreativetheme(WebDriver driver,BatchUpdateCreativeThemeInfo btinfo) throws InterruptedException{
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[4]/div[2]/a/span[1]")).click();
		boolean flag = Check.elementexist(driver, By.linkText("�����޸�����"), 5, "�����޸�����");
		if(flag){
			driver.findElement(By.linkText("�����޸�����")).click();
			driver.findElement(By.className("js-input")).sendKeys(btinfo.getCreativetheme());
			driver.findElement(By.linkText("ȷ  ��")).click();
			Thread.sleep(500);
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "û���ҵ������޸�����",  "�����޸�����", By.linkText("�����޸�����"));
		}
	}

	public static void check(WebDriver driver,BatchUpdateCreativeThemeInfo btinfo) throws InterruptedException{
		boolean flag = Check.elementexist(driver, By.className("creative-name"), 5, btinfo.getCreativetheme());
		if(!flag){
			ScreenshotandAssert.screenandasserttext(driver, "�޸�ʧ��",  btinfo.getCreativetheme(), By.className("creative-name"));
		}
	}
	
	public static void recover(WebDriver driver,BatchUpdateCreativeThemeInfo btinfo) throws InterruptedException{
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[4]/div[2]/a/span[1]")).click();
		boolean flag = Check.elementexist(driver, By.linkText("�����޸�����"), 5, "�����޸�����");
		if(flag){
			driver.findElement(By.linkText("�����޸�����")).click();
			driver.findElement(By.className("js-input")).sendKeys(btinfo.getCreativethemerecover());
			driver.findElement(By.linkText("ȷ  ��")).click();
			Thread.sleep(500);
			boolean flag1 = Check.elementexist(driver, By.className("creative-name"), 5, btinfo.getCreativetheme());
			if(!flag1){
				ScreenshotandAssert.screenandasserttext(driver, "�ָ�ʧ��",  btinfo.getCreativethemerecover(), By.className("creative-name"));
			}
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "û���ҵ������޸�����",  "�����޸�����", By.linkText("�����޸�����"));
		}
	}
}
