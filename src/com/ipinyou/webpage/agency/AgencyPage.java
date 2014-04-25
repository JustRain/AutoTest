package com.ipinyou.webpage.agency;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.agency.AgencyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class AgencyPage {
	
	public static void backmannage(WebDriver driver){
		driver.findElement(By.linkText("��̨����")).click();
		boolean flag = Check.usualexist(driver, "�����̹���", 5);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "û����ת�������̹���ҳ", "�����̹���");
		}
	}
	
	
	public static void createagecy(WebDriver driver,AgencyInfo ainfo){
		driver.findElement(By.className("ico-add")).click();
		boolean flag = Check.usualexist(driver, "����������", 5);
		if(flag){
			driver.findElement(By.id("pool.name")).sendKeys(ainfo.getAgencyname());
			driver.findElement(By.id("pool.sysId")).sendKeys(ainfo.getSystemid());
			driver.findElement(By.name("type")).click();
			driver.findElement(By.id("pool.quota")).sendKeys(ainfo.getAmount());
			driver.findElement(By.id("pool.serviceFeeRate")).clear();
			driver.findElement(By.id("pool.serviceFeeRate")).sendKeys(ainfo.getServicerate());
			driver.findElement(By.id("pool.email")).sendKeys(ainfo.getAgencyemail());
			driver.findElement(By.id("role_110")).click();
			driver.findElement(By.id("role_477")).click();
            driver.findElement(By.xpath("//*[@id='pool_form']/div/div/div[5]/input[1]")).click();
		}else{
			
		}
	}

	public static void check(WebDriver driver){
		boolean flag = Check.usualexist(driver, "�����̹���", 5);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "�����̴���ʧ��",  "�����̹���");
		}
	}
	
	public static void datadcheck(WebDriver driver,AgencyInfo ainfo) throws InterruptedException{
		driver.findElement(By.linkText(ainfo.getAgencyname())).click();
		boolean flag = Check.usualexist(driver, "�޸Ĵ�����", 5);
		if(flag){
			PubHandle.checkvalue(driver, By.id("pool.name"), ainfo.getAgencyname(), "value");
			PubHandle.checkvalue(driver, By.id("pool.sysId"), ainfo.getSystemid(), "value");
			PubHandle.checkvalue(driver, By.id("pool.quota"),ainfo.getAmount(), "value");
			PubHandle.checkvalue(driver,By.id("pool.serviceFeeRate"),  ainfo.getServicerate(), "value");
			PubHandle.usualelementcheck(driver, By.xpath("//*[@id='pool_form']/div/div/div[2]/table/tbody/tr[10]/td[2]/span[1]"),5 , "�������", ainfo.getAgencyemail());
			PubHandle.isenbled(driver, By.id("role_110"), "��ɫû�б�ѡ��", true);
			PubHandle.isenbled(driver, By.id("role_477"), "��ɫû�б�ѡ��", true);
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "û�н����޸Ĵ�����ҳ",  "�޸Ĵ�����");
		}
	}
}
