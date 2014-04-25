package com.ipinyou.webpage.batch.mobile;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.batch.mobile.BatchEditStrategyMobInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class BatchEditStrategyMobPage {
	public static void search(WebDriver driver,BatchEditStrategyMobInfo bsinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(bsinfo.getAdname());
		list.add(bsinfo.getOrname());
		list.add(bsinfo.getPlname());
		PubHandle.search(driver, list, PubHandle.titlelist());
		if(bsinfo.getMobiletype().equals("app")){
			driver.findElement(By.id("appendedInputButton")).sendKeys(bsinfo.getAppstrname());
		}else if(bsinfo.getMobiletype().equals("web")){
			driver.findElement(By.id("appendedInputButton")).sendKeys(bsinfo.getWebstrname());
		}
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		
	}
	public static void batchedit(WebDriver driver,BatchEditStrategyMobInfo bsinfo) throws InterruptedException{
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[5]/div[2]/a")).click();
		driver.findElement(By.linkText("批量修改策略")).click();
		boolean flag = Check.usualexist(driver, "批量修改投放策略", 10);
		if(flag){
			driver.findElement(By.id("allAdvancedAttr")).click();
			driver.findElement(By.id("allBidAttr")).click();
			daat(driver);
			attention(driver);
			daatexclude(driver);
			retarget(driver);
			exclude(driver);
			clickfind(driver);
			area(driver);
			date(driver);
			//plate(driver);
			//os(driver);
			device(driver);
			bidstrategy(driver, bsinfo);
			if(!"".equals(bsinfo.getMobiletype())&&"app".equals(bsinfo.getMobiletype())){
				apptype(driver);
				appwhiteblack(driver, bsinfo.getWhiteapp(), bsinfo.getBlackapp());
				mobiledirected(driver, bsinfo.getBatchdirected());
			}else if(!"".equals(bsinfo.getMobiletype())&&"web".equals(bsinfo.getMobiletype())){
				blacklist(driver,bsinfo.getBlacklists(),bsinfo.getBatchblack());	
				whitelist(driver, bsinfo.getBatchwhite(), bsinfo.getWhitelists());
			}
			driver.findElement(By.name("submitForm")).click();
			boolean flag1 = Check.usualexist(driver, "策略列表", 4);
			if(!flag1){
				ScreenshotandAssert.screenandasserttitle(driver, "批量修改失败", "策略列表");
			}
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "没有进入批量修改策略页", "批量修改投放策略");
			}
	}
	
	public static void check(WebDriver driver,BatchEditStrategyMobInfo bsinfo) throws InterruptedException{
		driver.findElement(By.id("appendedInputButton")).clear();
		if(bsinfo.getMobiletype().equals("app")){
			driver.findElement(By.id("appendedInputButton")).sendKeys(bsinfo.getAppstrname());
		}else if(bsinfo.getMobiletype().equals("web")){
			driver.findElement(By.id("appendedInputButton")).sendKeys(bsinfo.getWebstrname());
		}		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.className("alter-icon")).click();
		boolean flag = Check.usualexist(driver, "修改投放策略", 5);
		if(flag){
			driver.findElement(By.id("toggleAdvancedSetting")).click();
			pubcheck(driver, By.xpath("//*[@id='audience-selected']/table/tbody/tr/td[2]"), bsinfo.getPeopleproperty());
			pubcheck(driver, By.xpath("//*[@id='audience-exclude-selected']/table/tbody/tr/td[2]"), bsinfo.getDaatexclude());
			pubcheck(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[2]/td[2]"), bsinfo.getClassfytext());
			pubcheck(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[3]/td[2]"), bsinfo.getContext());
			pubcheck(driver, By.xpath("//*[@id='exclude-selected']/table/tbody/tr[1]/td[2]"), bsinfo.getClassifyexclude());
			pubcheck(driver, By.xpath("//*[@id='exclude-selected']/table/tbody/tr[2]/td[2]"), bsinfo.getConexclude());
			pubcheck(driver, By.id("clickCookie-selected"), "当前已选点击找回类型："+"\n"+bsinfo.getClicksele());
			//pubcheck(driver, By.xpath("//*[@id='pyidcategory-selected']/table/tbody/tr/td"), bsinfo.getPeopclassfy());
			pubcheck(driver, By.className("geo-all"), bsinfo.getAreatext());
			//pubcheck(driver, By.xpath("//*[@id='platform-selected']/table/tbody/tr/td[1]/strong"), bsinfo.getPlatetext());
			//pubcheck(driver, By.xpath("//*[@id='osform-selected']/table/tbody/tr/td[2]"), bsinfo.getOstext());
			pubcheck(driver, By.xpath("//*[@id='mobileset-selected']/table/tbody/tr/td[2]"), bsinfo.getMobiledevice());
			PubHandle.selcheck(driver, By.id("dayPacing"), bsinfo.getEditblanceputin());
//			PubHandle.checkvalue(driver, By.id("preferDeal"), bsinfo.getEditpreferdeal(), "value");
			PubHandle.checkvalue(driver, By.id("cpmBidPrice"), bsinfo.getEdithighprice(), "value");
			PubHandle.selcheck(driver, By.id("algorithmCode"), bsinfo.getEditalgorithmCode());
			PubHandle.selcheck(driver, By.id("expectedGoal"), bsinfo.getEditpricegoal());
			PubHandle.checkvalue(driver, By.id("expectedParams"), bsinfo.getEditreachprice(), "value");
			PubHandle.selcheck(driver, By.id("effectGoal"), bsinfo.getEditeffectgoal());
			PubHandle.checkvalue(driver, By.id("effectGoalRate"), bsinfo.getEditrate(), "value");
			pubcheck(driver, By.id("scriptCode"), bsinfo.getEditscriptcode());
			pubcheck(driver, By.id("remark"), bsinfo.getEditremark());
			if(!"".equals(bsinfo.getMobiletype())&&"app".equals(bsinfo.getMobiletype())){
				pubcheck(driver, By.id("mobileAttr.idfas"), bsinfo.getBatchdirected());
				pubcheck(driver, By.id("count-mobileAttrappCategories"), bsinfo.getApptype());
				pubcheck(driver, By.id("blackWhiteAppblackAppIds"),bsinfo.getBlackapp());
				pubcheck(driver, By.id("blackWhiteAppwhiteAppIds"), bsinfo.getWhiteapp());
			}else if(!"".equals(bsinfo.getMobiletype())&&"web".equals(bsinfo.getMobiletype())){
//				pubcheck(driver, By.id("count-blackWhiteUrlblackUrlIds"), bsinfo.getMediumblack());
				pubcheck(driver, By.id("count-blackWhiteUrlblackUrlIds"), "1");
				pubcheck(driver, By.id("count-blackWhiteUrlwhiteUrlIds"), "1");
//				pubcheck(driver, By.id("adUnit.blackIdsTextArea"), bsinfo.getPositonblack());
//				pubcheck(driver, By.id("adUnit.whiteIdsTextArea"), bsinfo.getPositionwhite());
			}
			
			//pubcheck(driver, By.id("blackWhiteUrl.blackUrls"), bsinfo.getBlacklists());
			
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
				Thread.sleep(500);
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
	//DAAT人群排除定向
	public static void daatexclude(WebDriver driver)throws InterruptedException{
		driver.findElement(By.id("btn-audiencesExclude")).click();
		PubHandle.switchframe(driver, By.className("myiframe"), "src", "audiencesExclude");
		boolean excludeflag = Check.elementexist(driver, By.id("node_content_10108"), 10, "人口属性");
		if(excludeflag){
			boolean valflag = Check.elementexist(driver, By.id("node_val_10144"), 10, "关键人生阶段");
			if(valflag){
				driver.findElement(By.id("trigger_10144")).click();
				boolean stuflag = Check.elementexist(driver, By.id("node_val_10146"), 10, "求职时期");
				if(stuflag){
					PubHandle.drag(driver, By.id("node_val_10146"), By.cssSelector(".ui-droppable"));
	    			driver.findElement(By.id("confirm")).click();
				}else{
					ScreenshotandAssert.screenandasserttext(driver, "没有找到求职时期", "求职时期", By.id("node_val_10145"));
				}
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "没有找到关键人生阶段", "关键人生阶段", By.id("node_val_10144"));
			}
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "咩有跳转到DAAT人群排除iframe", "人口属性", By.id("node_content_10108"));
		}
		driver.switchTo().defaultContent();
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
	/*public static void classfyset(WebDriver driver) throws InterruptedException{
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
	}*/
	

	
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
		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='platformSetting']/div[1]/p[1]/label"), 5, "谷歌移动");
		if(flag){
			driver.findElement(By.xpath("//*[@id='platformSetting']/div[1]/p[1]/label")).click();
			driver.findElement(By.id("confirm")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有进入平台设定frame", "谷歌移动", By.xpath("//*[@id='platformSetting']/div[1]/p[1]/label"));
		}
		driver.switchTo().defaultContent();
	}
	
	//操作系统设定
	public static void os(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("btn-osTypes")).click();
		PubHandle.switchframe(driver, By.className("myiframe"), "src", "osTypes;osVersions");
		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='osSetting']/div[1]/p/label"), 5,"安卓");
		if(flag){
			driver.findElement(By.id("android")).click();
			driver.findElement(By.id("confirm")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有进入系统设定frame", "安卓",  By.xpath("//*[@id='osSetting']/div[1]/p/label"));
		}
		driver.switchTo().defaultContent();
	}
	//移动设备设定
	public static void device(WebDriver driver)throws InterruptedException{
		driver.findElement(By.id("btn-mobileAttrmobileSets")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"), "src", "mobileAttrmobileSetBrands");
    	boolean mobflag = Check.elementexist(driver, By.xpath("//*[@id='mobilesetSetting']/div[1]/p/label"), 10, "手机");
    	if(mobflag){
    		driver.findElement(By.id("cellphone")).click();
    		driver.findElement(By.id("confirm")).click();
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "没有进入移动设备设定frame", "手机", By.xpath("//*[@id='mobilesetSetting']/div[1]/p/label"));
    	}
    	driver.switchTo().defaultContent();
	}
	//APP类型设定
	public static void apptype(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("btn-mobileAttrappCategories")).click();
		PubHandle.switchframe(driver, By.className("myiframe"), "src", "customize#mobileAttrappCategories");
    	boolean appflag = Check.elementexist(driver, By.id("select_all"), 10, "全选");
    	if(appflag){
    		driver.findElement(By.id("select_1")).click();
    		driver.findElement(By.id("confirm")).click();
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "没有跳转到app类型设定frame", "全选", By.xpath("//*[@id='appCatSetting']/div/p/label"));	
    	}
    	driver.switchTo().defaultContent();
	}
	
	//APP黑白名单设定
	public static void appwhiteblack(WebDriver driver,String black,String white){
		driver.findElement(By.id("enableAppBlackList")).click();
        driver.findElement(By.id("enableAppWhiteList")).click();
        driver.findElement(By.id("blackWhiteAppblackAppIds")).sendKeys(white);
        driver.findElement(By.id("blackWhiteAppwhiteAppIds")).sendKeys(black);
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
	public static void whitelist(WebDriver driver,String list,String batchwhite) throws InterruptedException{
		driver.findElement(By.id("btn-blackWhiteUrlwhiteUrlIds")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"), "src", "blackWhiteUrlcustomWhiteUrls");
    	boolean whiteflag = Check.elementexist(driver, By.className("fc-gray"),10, "您可以在左侧区域中选择已有的白名单列表，也可以在右侧区域直接编辑或输入新的域名。");
    	if(whiteflag){
    		driver.findElement(By.id("set-name")).sendKeys(list);
    		driver.findElement(By.id("set-domains")).sendKeys(batchwhite);
    		driver.findElement(By.id("confirm")).click();
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "没有进入白名单iframe", "您可以在左侧区域中选择已有的白名单列表，也可以在右侧区域直接编辑或输入新的域名。",  By.className("fc-gray"));
    	}
    	driver.switchTo().defaultContent();
	}
	//移动用户定向
	public static void mobiledirected(WebDriver driver,String batchdirected)throws InterruptedException{
		driver.findElement(By.id("mobileAttr.idfas")).sendKeys(batchdirected);
	}
	//出价策略设置
	public static void bidstrategy(WebDriver driver,BatchEditStrategyMobInfo bsinfo){
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
			System.out.println(gettext+"修改通过");
		}
		
	}
}

