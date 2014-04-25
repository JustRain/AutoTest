package com.ipinyou.webpage.batch.strategy;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.batch.strategy.BatchEditStrategyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchEditStrategyPage {
	public static void search(WebDriver driver,BatchEditStrategyInfo bsinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(bsinfo.getAdname());
		list.add(bsinfo.getOrname());
		list.add(bsinfo.getPlname());
		PubHandle.search(driver, list, PubHandle.titlelist());
		driver.findElement(By.id("appendedInputButton")).sendKeys(bsinfo.getStrname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
	}
	public static void batchedit(WebDriver driver,BatchEditStrategyInfo bsinfo) throws InterruptedException{
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[5]/div[2]/a")).click();
		driver.findElement(By.linkText("批量修改策略")).click();
		boolean flag = Check.usualexist(driver, "批量修改投放策略", 10);
		if(flag){
			driver.findElement(By.id("allAdvancedAttr")).click();
			driver.findElement(By.id("allBidAttr")).click();
			daat(driver);
			attention(driver);
			retarget(driver);
			exclude(driver);
			clickfind(driver);
			classfyset(driver);
			cookie(driver, bsinfo.getCookietext());
			area(driver);
			date(driver);
			plate(driver);
			os(driver);
			broswers(driver);
			blacklist(driver,bsinfo.getBlacklists(),bsinfo.getBatchblack());
			position(driver, bsinfo.getPositonblack(), bsinfo.getPositionwhite());
			bidstrategy(driver, bsinfo);
			driver.findElement(By.name("submitForm")).click();
			boolean failflag = Check.elementexist(driver, By.xpath("//*[@id='tr_dayPacing']/td[3]/span[2]"), 5, bsinfo.getDailyreminder());
			if(failflag){
				driver.findElement(By.id("allBasicAttr")).click();
				driver.findElement(By.id("limit.dailyBudget")).sendKeys(bsinfo.getDailybudget());
				PubHandle.select(driver, By.id("dayPacing"), bsinfo.getEditblanceputin());
				driver.findElement(By.name("submitForm")).click();
				boolean flag1 = Check.usualexist(driver, "策略列表", 4);
				if(!flag1){
					ScreenshotandAssert.screenandasserttitle(driver, "批量修改失败", "策略列表");
				}
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "提示不正确或者没有提示", bsinfo.getDailyreminder(), By.xpath("//*[@id='tr_dayPacing']/td[3]/span[2]"));
			}
			
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入批量修改策略页", "批量修改投放策略");
			}
	}
	
	public static void check(WebDriver driver,BatchEditStrategyInfo bsinfo) throws InterruptedException{
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(bsinfo.getStrname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.className("alter-icon")).click();
		boolean flag = Check.usualexist(driver, "修改投放策略", 5);
		if(flag){
			driver.findElement(By.id("toggleAdvancedSetting")).click();
			pubcheck(driver, By.xpath("//*[@id='audience-selected']/table/tbody/tr/td[2]"), bsinfo.getPeopleproperty());
			pubcheck(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[2]/td[2]"), bsinfo.getClassfytext());
			pubcheck(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[3]/td[2]"), bsinfo.getContext());
			pubcheck(driver, By.xpath("//*[@id='exclude-selected']/table/tbody/tr[1]/td[2]"), bsinfo.getClassifyexclude());
			pubcheck(driver, By.xpath("//*[@id='exclude-selected']/table/tbody/tr[2]/td[2]"), bsinfo.getConexclude());
			pubcheck(driver, By.id("clickCookie-selected"), "当前已选点击找回类型："+"\n"+bsinfo.getClicksele());
			//pubcheck(driver, By.xpath("//*[@id='pyidcategory-selected']/table/tbody/tr/td"), bsinfo.getPeopclassfy());
			pubcheck(driver, By.id("pyids"), bsinfo.getCookietext());
			pubcheck(driver, By.className("geo-all"), bsinfo.getAreatext());
			//pubcheck(driver, By.id("exchanges"), bsinfo.getPlatetext());
			PubHandle.checkvalue(driver, By.id("exchanges"), bsinfo.getPlatetext(), "value");
			PubHandle.checkvalue(driver, By.id("adLocations"), bsinfo.getPlateposition(), "value");
			pubcheck(driver, By.id("osform-all"), bsinfo.getOstext());
			pubcheck(driver, By.xpath("//*[@id='browser-selected']/table/tbody/tr/td"), bsinfo.getBrowsertext());
			pubcheck(driver, By.id("count-blackWhiteUrlblackUrlIds"), bsinfo.getBlackcount());
			pubcheck(driver, By.id("adUnit.blackIdsTextArea"), bsinfo.getPositonblack());
			pubcheck(driver, By.id("adUnit.whiteIdsTextArea"), bsinfo.getPositionwhite());
			PubHandle.selcheck(driver, By.id("dayPacing"), bsinfo.getEditblanceputin());
			PubHandle.checkvalue(driver, By.id("preferDeal"), bsinfo.getEditpreferdeal(), "value");
			PubHandle.checkvalue(driver, By.id("cpmBidPrice"), bsinfo.getEdithighprice(), "value");
			
			PubHandle.selcheck(driver, By.id("expectedGoal"), bsinfo.getEditpricegoal());
			PubHandle.checkvalue(driver, By.id("expectedParams"), bsinfo.getEditreachprice(), "value");
			PubHandle.selcheck(driver, By.id("effectGoal"), bsinfo.getEditeffectgoal());
			PubHandle.checkvalue(driver, By.id("effectGoalRate"), bsinfo.getEditrate(), "value");
			pubcheck(driver, By.id("scriptCode"), bsinfo.getEditscriptcode());
			pubcheck(driver, By.id("remark"), bsinfo.getEditremark());
			
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "咩有进入修改策略页", "修改投放策略");
			driver.findElement(By.id("toggleAdvancedSetting")).click();
		}
	}
	
	//DAAT人群定向
	public static void daat(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("btn-audiences")).click();
		Thread.sleep(1000);
		PubHandle.switchframe(driver, By.className("myiframe"),"src","audiences");
		boolean flag = Check.elementexist(driver, By.id("node_content_10108"), 10, "人口属性");
		if(flag){
			driver.findElement(By.id("trigger_10109")).click();
			boolean flag1 = Check.elementexist(driver, By.id("node_val_10111"), 10, "女性");
			if(flag1){
				PubHandle.drag(driver, By.id("node_val_10111"), By.cssSelector(".ui-droppable"));
				driver.findElement(By.id("confirm")).click();
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "没有打开性别项", "女性", By.id("node_val_10111"));
			}
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有进入DAAT frame", "人口属性", By.id("node_content_10108"));
		}
		driver.switchTo().defaultContent();
	}
	
	//人群关注度
	public static void attention(WebDriver driver){
		PubHandle.select(driver, By.id("audienceWeight"), "不限");
	}
	
	//访客找回
	public static void retarget(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("btn-visitorRetarget")).click();
		PubHandle.switchframe(driver, By.className("myiframe"),"src" , "retargetConversions");
		boolean flag = Check.elementexist(driver, By.id("retargetRange"), 10, "访客投放期：");
		if(flag){
			driver.findElement(By.xpath("//*[@title='retargetingcode1']")).click();
			driver.findElement(By.xpath("//*[@title='conversioncode1']")).click();
			driver.findElement(By.id("confirm")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有进入访客找回frame", "访客投放期：", By.xpath("//*[@id='retargetSetting']/div[1]/div[1]"));
		}
		driver.switchTo().defaultContent();
	}
	//访客排除
	public static void exclude(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("btn-visitorExclude")).click();
		PubHandle.switchframe(driver, By.className("myiframe"), "src", "excludeConversions");
		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='retargetCategoryDiv']/p/label"), 10, "分类访客");
		if(flag){
			driver.findElement(By.xpath("//*[@title='retargetingcode2']")).click();
			driver.findElement(By.xpath("//*[@title='conversioncode2']")).click();
			driver.findElement(By.id("confirm")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有进入访客排除frame", "分类访客", By.xpath("//*[@id='retargetCategoryDiv']/p/label"));
		}
		driver.switchTo().defaultContent();
	}
	
    //点击找回
	public static void clickfind(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("btn-clickCookieType")).click();
		PubHandle.switchframe(driver, By.className("myiframe"), "src", "clickCookieType");
		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='clickCookieSetting']/div/table/tbody/tr/td[2]/label"), 5, "点击过本广告主广告");
		if(flag){
			driver.findElement(By.id("clkcookie_AdvClick")).click();
			driver.findElement(By.id("confirm")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有进入点击找回frame", "点击过本广告主广告", By.xpath("//*[@id='clickCookieSetting']/div/table/tbody/tr/td[2]/label"));
		}
		driver.switchTo().defaultContent();
	}
	
	//人群分类设定
	public static void classfyset(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("btn-pyidCategories")).click();
		PubHandle.switchframe(driver, By.className("myiframe"), "src", "pyidCategories");
		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='audienceClassifySetting']/table/thead/tr/th[3]"), 5, "编码");
		if(flag){
			driver.findElement(By.id("select_all")).click();
			driver.findElement(By.id("confirm")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有进入人群分类frame", "编码", By.xpath("//*[@id='audienceClassifySetting']/table/thead/tr/th[3]"));
		}
		driver.switchTo().defaultContent();
	}
	
	//cookie定向
	public static void cookie(WebDriver driver,String cookie){
		driver.findElement(By.id("pyids")).sendKeys(cookie);
	}
	
	//地域设定
	public static void area(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("btn-areas")).click();
		PubHandle.switchframe(driver, By.className("myiframe"), "src", "customize#areas");
		boolean flag = Check.elementexist(driver, By.className("fc-gray"), 5, "请搜索城市：");
		if(flag){
			driver.findElement(By.id("confirm")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有进入地域设定frame", "请搜索城市：", By.className("fc-gray"));
		}
		driver.switchTo().defaultContent();
	}
	//时段设定
	public static void date(WebDriver driver) throws InterruptedException{		
		driver.findElement(By.id("btn-dayParting")).click();
		PubHandle.switchframe(driver, By.className("myiframe"), "src", "day_parting/customize#dayParting");
		boolean flag = Check.elementexist(driver, By.xpath("/html/body/table/tbody/tr[1]/th[2]"), 5, "日期");
		if(flag){
			driver.findElement(By.id("selectAll")).click();
			driver.findElement(By.id("confirm")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有进入日期设定frame", "日期", By.xpath("/html/body/table/tbody/tr[1]/th[2]"));
		}
		driver.switchTo().defaultContent();
	}
	//平台位置设定
	public static void plate(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("btn-exchanges")).click();
		PubHandle.switchframe(driver, By.className("myiframe"), "src", "exchanges;adLocations");
		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='platformSetting']/div[4]/p[1]/label"), 5, "腾讯");
		if(flag){
			driver.findElement(By.id("pltTaobao")).click();
			driver.findElement(By.id("pltGoogle")).click();
			driver.findElement(By.id("pltSina")).click();
			driver.findElement(By.id("pltTencent")).click();
			driver.findElement(By.id("pltBaidu")).click();
			driver.findElement(By.id("showYoukuOption")).click();
			driver.findElement(By.id("youku")).click();
			driver.findElement(By.id("tudou")).click();
			driver.findElement(By.id("pltMiaozhen")).click();
			driver.findElement(By.id("pltBaoFeng")).click();
			driver.findElement(By.id("pltIqiyi")).click();
			driver.findElement(By.id("pltShanda")).click();			
			driver.findElement(By.id("confirm")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有进入平台设定frame", "腾讯", By.xpath("//*[@id='platformSetting']/div[4]/p[1]/label"));
		}
		driver.switchTo().defaultContent();
	}
	
	//操作系统设定
	public static void os(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("btn-osTypes")).click();
		PubHandle.switchframe(driver, By.className("myiframe"), "src", "osTypes;osVersions");
		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='osSetting']/div[1]/p/label"), 5,"PC操作系统");
		if(flag){
			driver.findElement(By.id("confirm")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有进入系统设定frame", "PC操作系统",  By.xpath("//*[@id='osSetting']/div[1]/p/label"));
		}
		driver.switchTo().defaultContent();
	}
	//浏览器设定
	public static void broswers(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("btn-browsers")).click();
		PubHandle.switchframe(driver, By.className("myiframe"), "src", "customize#browsers");
		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='agentProductSetting']/div/p/label"), 5, "全选");
		if(flag){
			driver.findElement(By.id("select-all")).click();
			driver.findElement(By.id("confirm")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有进入浏览器设定frame", "全选", By.xpath("//*[@id='agentProductSetting']/div/p/label"));
		}
		driver.switchTo().defaultContent();
	}
	//黑名单设定
	public static void blacklist(WebDriver driver,String list,String batchblack) throws InterruptedException{
		driver.findElement(By.id("btn-blackWhiteUrlblackUrlIds")).click();
		PubHandle.switchframe(driver, By.className("myiframe"), "src", "blackWhiteUrlcustomBlackUrls");
		boolean flag = Check.elementexist(driver, By.id("listBtn"), 10, "维护黑白名单列表");
		if(flag){
			driver.findElement(By.id("set-name")).sendKeys(batchblack);
			driver.findElement(By.id("set-domains")).sendKeys(list);
			driver.findElement(By.id("confirm")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有进入黑名单设置frame", "维护黑白名单列表", By.id("listBtn"));
		}
		driver.switchTo().defaultContent();
	}
	//广告位黑白名单
	public static void position(WebDriver driver,String positionblack,String positionwhite){
		driver.findElement(By.id("enableAdUnitBlackList")).click();
		driver.findElement(By.id("enableAdUnitWhiteList")).click();
		driver.findElement(By.id("adUnit.blackIds")).sendKeys(positionblack);
		driver.findElement(By.id("adUnit.whiteIds")).sendKeys(positionwhite);
	}
	//出价策略设置
	public static void bidstrategy(WebDriver driver,BatchEditStrategyInfo bsinfo){
		PubHandle.select(driver, By.id("dayPacing"), bsinfo.getEditblanceputin());
//		driver.findElement(By.id("preferDeal")).sendKeys(bsinfo.getEditpreferdeal());
		driver.findElement(By.id("cpmBidPrice")).sendKeys(bsinfo.getEdithighprice());
		//PubHandle.select(driver, By.id("algorithmCode"), bsinfo.getEditalgorithmCode());
		PubHandle.select(driver, By.id("expectedGoal"),bsinfo.getEditpricegoal());
		driver.findElement(By.id("reach")).sendKeys(bsinfo.getUnitcpc());
		PubHandle.select(driver, By.id("effectGoal"), bsinfo.getEditeffectgoal());
		driver.findElement(By.id("effectGoalRate")).sendKeys(bsinfo.getEditrate());
		driver.findElement(By.id("scriptCode")).sendKeys(bsinfo.getEditscriptcode());
		driver.findElement(By.id("remark")).sendKeys(bsinfo.getEditremark());
	}
	
	//公共检查
	public static  void pubcheck(WebDriver driver,By by,String gettext){
		String text = driver.findElement(by).getText();
		if(!text.equals(gettext)){
			ScreenshotandAssert.screenandasserttext(driver, gettext+"修改失败", gettext, by);
		}else{
			System.out.println(gettext+"检查通过");
		}
	}
}

