package com.ipinyou.webpage.ownmediawebpage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.ownmedia.OwnMediaStrategyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class OwnMediaStrategyPage {
	
	public static void search(WebDriver driver,OwnMediaStrategyInfo ominfo){
		List<String> list = new ArrayList<String>(); 
		list.add(ominfo.getAdname());
		list.add(ominfo.getOrname());
		list.add(ominfo.getPlname());
		try {
			PubHandle.search(driver, list, PubHandle.titlelist());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static boolean instrategy(WebDriver driver,String title){
		driver.findElement(By.className("ico-add")).click();
		return Check.usualexist(driver, title, 2);	 
	}
	public static void Createownstrategy(WebDriver driver,OwnMediaStrategyInfo ominfo) throws InterruptedException{
		driver.findElement(By.id("name")).sendKeys(ominfo.getStrategyname());
		driver.findElement(By.id("advertisingMode2")).click();
		driver.findElement(By.id("adUnitTypes0")).click();
		driver.findElement(By.id("adUnitTypes1")).click();
		PubHandle.select(driver, By.id("priority"), "15");
		driver.findElement(By.id("toggleAdvancedSetting")).click();
		driver.findElement(By.id("btn-adUnit.whiteIds")).click();
		PubHandle.switchframe(driver, By.className("myiframe"), "src", "whiteIdsInput");
		boolean flag = Check.elementexist(driver, By.id("select_all"), 10, "全选");
		if(flag){
			driver.findElement(By.id("j-search")).sendKeys(ominfo.getAdvertiserposition());
			driver.findElement(By.className("icon-search")).click();
			boolean flag1 = Check.elementexist(driver, By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[14]/div[1]/span[3]"), 10, "autowebsite");
			if(flag1){
				Thread.sleep(1000);
				driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[14]/div[1]/span[2]")).click();
				driver.findElement(By.id("confirm")).click();
				/*driver.findElement(By.xpath("//*[@id='j-adsiteList']/div[14]/div[2]/span[1]")).click();
				boolean flag2 = Check.elementexist(driver, By.xpath("//*[@id='j-adsiteList']/div[3]/div[2]/div[1]/div[1]/span[3]"), 10,"autopart");
				System.out.println(driver.findElement(By.xpath("//*[@id='j-adsiteList']/div[3]/div[2]/div[1]/div[1]/span[3]")).getText());
				if(flag2){
					Thread.sleep(1000);
					driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[3]/div[2]/div[1]/div[1]/span[1]")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[3]/div[2]/div/div[2]/div/div/span[2]")).click();
					driver.findElement(By.id("confirm")).click();
				}else{
					ScreenshotandAssert.screenandasserttext(driver, "没有找到autopart", "autopart", By.xpath("//*[@id='j-adsiteList']/div[3]/div[2]/div[1]/div[1]/span[3]"));
				}*/
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "没有找到"+ominfo.getAdvertiserposition(), "autowebsite", By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[3]/div[1]/span[3]"));
			}
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有跳转到广告位设定frame", "全选", By.id("select_all"));
		}
		driver.switchTo().defaultContent();
		
		boolean flag3 = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[2]/div/table/tbody/tr[12]/td[2]/div/div[1]/span[2]"), 5, "您已经设定了2个广告位条件");
		if(flag3){
			driver.findElement(By.name("submitForm")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有选择上广告位", "您已经设定了2个广告位条件", By.xpath("//*[@id='strategyFM']/div[2]/div/table/tbody/tr[12]/td[2]/div/div[1]/span[2]"));
		}
		
	}
	public static void check(WebDriver driver,String title,String reminder){
		boolean flag = Check.usualexist(driver, title, 5);
		if(flag){
			;
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, reminder, title);
		}
	}
}
