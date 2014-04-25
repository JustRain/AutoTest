package com.ipinyou.webpage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.MobileStrategyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class MobileStrategyPage {
	public static void search(WebDriver driver,MobileStrategyInfo minfo,String title,String reminder) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(minfo.getAdname());
		list.add(minfo.getOrname());
		list.add(minfo.getPlname());
		PubHandle.search(driver, list,PubHandle.titlelist());
		boolean flag = Check.usualexist(driver, title,2);
		if(flag){
			;
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, reminder, title);
		}
	}
	public static void instrategy(WebDriver driver,String title,String reminder){
		driver.findElement(By.className("ico-add")).click();
		boolean flag = Check.usualexist(driver, title, 2);
		if(flag){
			;
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, reminder, title);
		}
	}
    public static void mobilestrategy(WebDriver driver,MobileStrategyInfo minfo) throws InterruptedException{
    	driver.findElement(By.id("name")).sendKeys(minfo.getStrategyname());
    	driver.findElement(By.id("advertisingMode1")).click();
    	PubHandle.select(driver, By.id("priority"), minfo.getLevel());
    	driver.findElement(By.id("limit.totalBudget")).sendKeys(minfo.getTbudget());
    	driver.findElement(By.id("limit.dailyBudget")).sendKeys(minfo.getDbudget());
    	driver.findElement(By.id("limit.impTotalLimit")).sendKeys(minfo.getImptlimit());
    	driver.findElement(By.id("limit.clickTotalLimit")).sendKeys(minfo.getCtclick());
    	driver.findElement(By.id("limit.impDailyLimit")).sendKeys(minfo.getImpdlimit());
    	driver.findElement(By.id("limit.clickDailyLimit")).sendKeys(minfo.getCdlimit());
    	driver.findElement(By.id("indvdLimitimpLimit")).sendKeys(minfo.getIndlimit());
    	driver.findElement(By.id("indvdLimitclickLimit")).sendKeys(minfo.getIndvdcllimit());
    	
    	//�߼�����
    	//DAAT��Ⱥ����
    	driver.findElement(By.id("toggleAdvancedSetting")).click();
    	driver.findElement(By.id("btn-audiences")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"), "src", "customize#audiences");
    	boolean flag = Check.elementexist(driver, By.id("By"), 10, "�˿�����");
    	if(flag){
    		driver.findElement(By.id("trigger_10109")).click();
    		boolean flag1 = Check.elementexist(driver, By.id("node_val_10110"), 10, "����");
    		if(flag1){
    			PubHandle.drag(driver, By.id("node_val_10110"), By.cssSelector(".ui-droppable"));
    			driver.findElement(By.id("confirm")).click();
    		}else{
    			ScreenshotandAssert.screenandasserttext(driver, "û�д��Ա��ǩ", "����", By.id("node_val_10110"));
    		}
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "û����ת����Ⱥ�趨frame", "�˿�����", By.id("By"));
    	}
    	driver.switchTo().defaultContent();
    	//������Ⱥ��ע��
    	PubHandle.select(driver, By.id("audienceWeight"), "����");
    	//ƽ̨λ���趨
    	
    	driver.findElement(By.id("btn-exchanges")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"), "src", "exchanges;adLocations");
    	boolean exchangeflag = Check.elementexist(driver, By.xpath("//*[@id='platformSetting']/div[1]/p[1]/label"), 10, "�ȸ��ƶ�");
    	if(exchangeflag){
    		driver.findElement(By.id("pltGoogleAmx")).click();
    		driver.findElement(By.id("confirm")).click();
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "û����ת��ƽ̨λ���趨frame", "�ȸ��ƶ�", By.xpath("//*[@id='platformSetting']/div[1]/p[1]/label"));
    	}
    	driver.switchTo().defaultContent();
    	
    	//����ϵͳ�趨
    	driver.findElement(By.id("btn-mobileAttrosTypes")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"),  "src", "mobileAttrosVersions");
    	boolean osflag = Check.elementexist(driver, By.xpath("//*[@id='osSetting']/div[1]/p/label"), 10, "��׿");
    	if(osflag){
    		driver.findElement(By.id("android")).click();
    		driver.findElement(By.id("ios")).click();
    	    driver.findElement(By.id("WindowsPhone")).click();
    	    driver.findElement(By.id("confirm")).click();
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "û����ת������ϵͳ�趨frame",  "��׿", By.xpath("//*[@id='osSetting']/div[1]/p/label"));
    	}
    	driver.switchTo().defaultContent();
    	
    	//�ƶ��豸�趨
    	driver.findElement(By.id("btn-mobileAttrmobileSets")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"), "src", "mobileAttrmobileSetBrands");
    	boolean mobflag = Check.elementexist(driver, By.xpath("//*[@id='mobilesetSetting']/div[1]/p/label"), 10, "�ֻ�");
    	if(mobflag){
    		driver.findElement(By.id("cellphone")).click();
    		driver.findElement(By.id("pad")).click();
    		driver.findElement(By.id("confirm")).click();
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "û�н����ƶ��豸�趨frame", "�ֻ�", By.xpath("//*[@id='mobilesetSetting']/div[1]/p/label"));
    	}
    	driver.switchTo().defaultContent();
    	//APP�����趨
    	driver.findElement(By.id("btn-mobileAttrappCategories")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"), "src", "mobileAttrappCategories");
    	boolean appflag = Check.elementexist(driver, By.xpath("//*[@id='appCatSetting']/div/p/label"), 10, "ȫѡ");
    	if(appflag){
    		driver.findElement(By.id("allapp")).click();
    		driver.findElement(By.id("confirm")).click();
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "û����ת��app�����趨frame", "ȫѡ", By.xpath("//*[@id='appCatSetting']/div/p/label"));	
    	}
    	driver.switchTo().defaultContent();
    }
}
