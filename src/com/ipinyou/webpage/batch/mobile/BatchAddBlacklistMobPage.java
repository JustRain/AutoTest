package com.ipinyou.webpage.batch.mobile;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.batch.mobile.BatchAddBlacklistMobInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchAddBlacklistMobPage {
	public static void search(WebDriver driver,BatchAddBlacklistMobInfo bainfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(bainfo.getAdname());
		list.add(bainfo.getOrname());
		list.add(bainfo.getPlname());
		PubHandle.search(driver, list, PubHandle.titlelist());
		driver.findElement(By.id("appendedInputButton")).clear();
		if(bainfo.getMobiletype().equals("web")){
			driver.findElement(By.id("appendedInputButton")).sendKeys(bainfo.getWebstrname());
		}else if(bainfo.getMobiletype().equals("app")){
			driver.findElement(By.id("appendedInputButton")).sendKeys(bainfo.getAppstrname());
		}
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
	}
	public static void batchaddblacklist(WebDriver driver,BatchAddBlacklistMobInfo bainfo) throws InterruptedException{
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[5]/div[2]/a")).click();
		boolean flag = Check.elementexist(driver, By.linkText("�������Ӻڰ�����"), 5, "�������Ӻڰ�����");
		if(flag){
			driver.findElement(By.linkText("�������Ӻڰ�����")).click();
			boolean flag1 = Check.usualexist(driver, "�������Ӻڰ�����", 5);
			if(flag1){
				if(!"".equals(bainfo.getMobiletype())&&"app".equals(bainfo.getMobiletype())){
					driver.findElement(By.id("enableAppBlackList")).click();
					driver.findElement(By.id("enableAppWhiteList")).click();
					driver.findElement(By.id("blackAppIds")).sendKeys(bainfo.getBatchappblacklist());
					driver.findElement(By.id("whiteAppIds")).sendKeys(bainfo.getBatchappwhitelist());
				}else if(!"".equals(bainfo.getMobiletype())&&"web".equals(bainfo.getMobiletype())){
					driver.findElement(By.id("btn-blackUrlIds")).click();
					PubHandle.switchframe(driver, By.className("myiframe"), "src", "customBlackUrls");
					boolean flag2 = Check.elementexist(driver, By.id("listBtn"), 5, "ά���ڰ������б�");
					if(flag2){
						driver.findElement(By.id("set-name")).sendKeys(bainfo.getBatchwebblackname());
						driver.findElement(By.id("set-domains")).sendKeys(bainfo.getBatchwebblackurl());
						driver.findElement(By.id("confirm")).click();
						
					}else{
						ScreenshotandAssert.screenandasserttext(driver, "û�н������������iframe", "ά���ڰ������б�",  By.id("listBtn"));
					}
					driver.switchTo().defaultContent();
					driver.findElement(By.id("btn-whiteUrlIds")).click();
					PubHandle.switchframe(driver,  By.className("myiframe"), "src", "customWhiteUrls");
					boolean flag3 = Check.elementexist(driver, By.id("listBtn"), 5, "ά���ڰ������б�");
					if(flag3){
						driver.findElement(By.id("set-name")).sendKeys(bainfo.getBatchwebwhitename());
						driver.findElement(By.id("set-domains")).sendKeys(bainfo.getBatchwebwhiteurl());
						driver.findElement(By.id("confirm")).click();
					}else{
						ScreenshotandAssert.screenandasserttext(driver, "û�н������������iframe", "ά���ڰ������б�",  By.id("listBtn"));
					}
					driver.switchTo().defaultContent();
				}
				driver.findElement(By.name("submitForm")).click();
				
			}else{
				ScreenshotandAssert.screenandasserttitle(driver, "û�н����������Ӻڰ�����ҳ", "�������Ӻڰ�����");
			}
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "û���ҵ��������Ӻڰ���������", "�������Ӻڰ�����");
		}
	}
	public static void check(WebDriver driver,BatchAddBlacklistMobInfo bainfo) throws InterruptedException{
		driver.findElement(By.id("appendedInputButton")).clear();
		if(bainfo.getMobiletype().equals("web")){
			driver.findElement(By.id("appendedInputButton")).sendKeys(bainfo.getWebstrname());
		}else if(bainfo.getMobiletype().equals("app")){
			driver.findElement(By.id("appendedInputButton")).sendKeys(bainfo.getAppstrname());
		}		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.className("alter-icon")).click();
		boolean flag = Check.usualexist(driver, "�޸�Ͷ�Ų���", 5);
		if(flag){
			driver.findElement(By.id("toggleAdvancedSetting")).click();
			if(!"".equals(bainfo.getMobiletype())&&"app".equals(bainfo.getMobiletype())){
				boolean appblackflag = Check.elementexist(driver, By.id("blackWhiteAppblackAppIds"), 5, bainfo.getBatchappblacklist()+"\n"+bainfo.getBatchappblacklistold());
				if(!appblackflag){
					ScreenshotandAssert.screenandasserttext(driver, "�������޸�ʧ��", bainfo.getBatchappblacklist()+"\n"+bainfo.getBatchappblacklistold(),  By.id("blackWhiteAppblackAppIds"));
				}else{
					System.out.println(bainfo.getBatchappblacklist()+"\n"+bainfo.getBatchappblacklistold()+"  Successed");
				}
				boolean appwhiteflag = Check.elementexist(driver, By.id("blackWhiteAppwhiteAppIds"), 5, bainfo.getBatchappwhitelistold()+"\n"+bainfo.getBatchappwhitelist());
				if(!appwhiteflag){
					ScreenshotandAssert.screenandasserttext(driver, "�������޸�ʧ��", bainfo.getBatchappwhitelistold()+"\n"+bainfo.getBatchappwhitelist(),  By.id("blackWhiteAppwhiteAppIds"));
				}else{
					System.out.println(bainfo.getBatchappwhitelistold()+"\n"+bainfo.getBatchappwhitelist()+"  Successed");
				}
				
			}else if(!"".equals(bainfo.getMobiletype())&&"web".equals(bainfo.getMobiletype())){

				System.out.println("black =========="+driver.findElement(By.id("count-blackWhiteUrlblackUrlIds")).getText());
				System.out.println("white =========="+driver.findElement(By.id("count-blackWhiteUrlwhiteUrlIds")).getText());
				System.out.println("white =========="+driver.findElement(By.className("black-results")).getText());
				System.out.println("���������� =========="+driver.findElement(By.xpath("//*[@id='strategyFM']/div[2]/div/table/tbody/tr[21]/td[1]/span[2]")).getText());
				//System.out.println(driver.getPageSource());
				boolean blackflag = Check.elementexist(driver, By.id("count-blackWhiteUrlblackUrlIds"), 5,"2");
				if(!blackflag){
					ScreenshotandAssert.screenandasserttext(driver, "�������޸�ʧ��", "2", By.id("count-blackWhiteUrlblackUrlIds"));
				}
				boolean whiteflag = Check.elementexist(driver, By.id("count-blackWhiteUrlwhiteUrlIds"), 5, "2");
				if(!whiteflag){
					ScreenshotandAssert.screenandasserttext(driver, "�������޸�ʧ��", "2", By.id("count-blackWhiteUrlwhiteUrlIds"));
				}
			}
			
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "û�н����޸Ĳ���ҳ",  "�޸�Ͷ�Ų���");
		}
	}
	
}
