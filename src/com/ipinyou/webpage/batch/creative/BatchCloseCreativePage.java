package com.ipinyou.webpage.batch.creative;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.batch.creative.BatchCloseCreativeInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchCloseCreativePage {
	public static void search(WebDriver driver,BatchCloseCreativeInfo bcinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(bcinfo.getAdname());
		list.add(bcinfo.getOrname());
		list.add(bcinfo.getPlname());
		list.add(bcinfo.getStrname());
		PubHandle.search(driver, list, PubHandle.titlelist());
	}
	
	public static void batchclosecreative(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[4]/div[2]/a/span[1]")).click();
		boolean flag = Check.elementexist(driver, By.linkText("�����ر�"), 5, "�����ر�");
		if(flag){
			driver.findElement(By.linkText("�����ر�")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "û���ҵ������ر�", "�����ر�", By.linkText("�����ر�"));
		}
	}
	
	public static void check(WebDriver driver){
		PubHandle.isdisplay(driver, By.className("btn-oval-close"), 5, "�ر�ʧ��", true);
	}

}
