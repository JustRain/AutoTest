package com.ipinyou.webpage.batch.strategy;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.batch.strategy.BatchAddBlacklistInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchAddBlacklistPage {
	public static void search(WebDriver driver,BatchAddBlacklistInfo bainfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(bainfo.getAdname());
		list.add(bainfo.getOrname());
		list.add(bainfo.getPlname());
		PubHandle.search(driver, list, PubHandle.titlelist());
	}
	public static void batchaddblacklist(WebDriver driver,BatchAddBlacklistInfo bainfo) throws InterruptedException{
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[5]/div[2]/a")).click();
		boolean flag = Check.elementexist(driver, By.linkText("�������Ӻڰ�����"), 5, "�������Ӻڰ�����");
		if(flag){
			driver.findElement(By.linkText("�������Ӻڰ�����")).click();
			boolean flag1 = Check.usualexist(driver, "�������Ӻڰ�����", 5);
			if(flag1){
				driver.findElement(By.id("btn-blackUrlIds")).click();
				PubHandle.switchframe(driver, By.className("myiframe"), "src", "customBlackUrls");
				boolean flag2 = Check.elementexist(driver, By.id("listBtn"), 5, "ά���ڰ������б�");
				if(flag2){
					driver.findElement(By.id("select_all")).click();
					driver.findElement(By.id("confirm")).click();
				}else{
					ScreenshotandAssert.screenandasserttext(driver, "û�н������������iframe", "ά���ڰ������б�",  By.id("listBtn"));
				}
			}else{
				ScreenshotandAssert.screenandasserttitle(driver, "û�н����������Ӻڰ�����ҳ", "�������Ӻڰ�����");
			}
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "û���ҵ��������Ӻڰ���������", "�������Ӻڰ�����");
		}
		driver.switchTo().defaultContent();
		driver.findElement(By.name("submitForm")).click();
	}
	public static void check(WebDriver driver,BatchAddBlacklistInfo bainfo) throws InterruptedException{
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(bainfo.getStrname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.className("alter-icon")).click();
		boolean flag = Check.usualexist(driver, "�޸�Ͷ�Ų���", 5);
		if(flag){
			driver.findElement(By.id("toggleAdvancedSetting")).click();
			boolean blackflag = Check.elementexist(driver, By.id("count-blackWhiteUrlblackUrlIds"), 5,"2");
			if(!blackflag){
				ScreenshotandAssert.screenandasserttext(driver, "�������޸�ʧ��", "2", By.id("count-blackWhiteUrlblackUrlIds"));
			}
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "û�н����޸Ĳ���ҳ",  "�޸�Ͷ�Ų���");
		}
	}
	
}
