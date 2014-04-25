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
    	
    	//高级设置
    	//DAAT人群定向
    	driver.findElement(By.id("toggleAdvancedSetting")).click();
    	driver.findElement(By.id("btn-audiences")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"), "src", "customize#audiences");
    	boolean flag = Check.elementexist(driver, By.id("By"), 10, "人口属性");
    	if(flag){
    		driver.findElement(By.id("trigger_10109")).click();
    		boolean flag1 = Check.elementexist(driver, By.id("node_val_10110"), 10, "男性");
    		if(flag1){
    			PubHandle.drag(driver, By.id("node_val_10110"), By.cssSelector(".ui-droppable"));
    			driver.findElement(By.id("confirm")).click();
    		}else{
    			ScreenshotandAssert.screenandasserttext(driver, "没有打开性别标签", "男性", By.id("node_val_10110"));
    		}
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "没有跳转到人群设定frame", "人口属性", By.id("By"));
    	}
    	driver.switchTo().defaultContent();
    	//设置人群关注度
    	PubHandle.select(driver, By.id("audienceWeight"), "不限");
    	//平台位置设定
    	
    	driver.findElement(By.id("btn-exchanges")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"), "src", "exchanges;adLocations");
    	boolean exchangeflag = Check.elementexist(driver, By.xpath("//*[@id='platformSetting']/div[1]/p[1]/label"), 10, "谷歌移动");
    	if(exchangeflag){
    		driver.findElement(By.id("pltGoogleAmx")).click();
    		driver.findElement(By.id("confirm")).click();
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "没有跳转到平台位置设定frame", "谷歌移动", By.xpath("//*[@id='platformSetting']/div[1]/p[1]/label"));
    	}
    	driver.switchTo().defaultContent();
    	
    	//操作系统设定
    	driver.findElement(By.id("btn-mobileAttrosTypes")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"),  "src", "mobileAttrosVersions");
    	boolean osflag = Check.elementexist(driver, By.xpath("//*[@id='osSetting']/div[1]/p/label"), 10, "安卓");
    	if(osflag){
    		driver.findElement(By.id("android")).click();
    		driver.findElement(By.id("ios")).click();
    	    driver.findElement(By.id("WindowsPhone")).click();
    	    driver.findElement(By.id("confirm")).click();
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "没有跳转到操作系统设定frame",  "安卓", By.xpath("//*[@id='osSetting']/div[1]/p/label"));
    	}
    	driver.switchTo().defaultContent();
    	
    	//移动设备设定
    	driver.findElement(By.id("btn-mobileAttrmobileSets")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"), "src", "mobileAttrmobileSetBrands");
    	boolean mobflag = Check.elementexist(driver, By.xpath("//*[@id='mobilesetSetting']/div[1]/p/label"), 10, "手机");
    	if(mobflag){
    		driver.findElement(By.id("cellphone")).click();
    		driver.findElement(By.id("pad")).click();
    		driver.findElement(By.id("confirm")).click();
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "没有进入移动设备设定frame", "手机", By.xpath("//*[@id='mobilesetSetting']/div[1]/p/label"));
    	}
    	driver.switchTo().defaultContent();
    	//APP类型设定
    	driver.findElement(By.id("btn-mobileAttrappCategories")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"), "src", "mobileAttrappCategories");
    	boolean appflag = Check.elementexist(driver, By.xpath("//*[@id='appCatSetting']/div/p/label"), 10, "全选");
    	if(appflag){
    		driver.findElement(By.id("allapp")).click();
    		driver.findElement(By.id("confirm")).click();
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "没有跳转到app类型设定frame", "全选", By.xpath("//*[@id='appCatSetting']/div/p/label"));	
    	}
    	driver.switchTo().defaultContent();
    }
}
