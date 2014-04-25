package com.ipinyou.webpage;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ipinyou.entity.StrategyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class StrategyPage {
	
	public static void search(WebDriver driver,StrategyInfo sinfo){
		List<String> list = new ArrayList<String>(); 
		list.add(sinfo.getAdname());
		list.add(sinfo.getOrname());
		list.add(sinfo.getPlname());
		try {
			PubHandle.search(driver, list,PubHandle.titlelist());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static boolean instrategy(WebDriver driver,String title){
		boolean flag = Check.exist(driver, By.linkText("创建优化策略"), 2);
		if(flag){
			driver.findElement(By.className("ico-add")).click();
			boolean flag1 = Check.usualexist(driver, title, 2);
			if(flag1){
				return true;
			}else{
				return false;
			}
			
		}else{
			return false;
		}
	}
	public static void createstrategy(WebDriver driver,StrategyInfo sinfo) throws InterruptedException,NoSuchElementException,AssertionError,ElementNotVisibleException{
		//基本设置
		driver.findElement(By.id("name")).sendKeys(sinfo.getStrategyname());
		driver.findElement(By.id("advertisingMode0")).click();
		Select level = new Select(driver.findElement(By.id("priority")));
		level.selectByValue("15");
		driver.findElement(By.id("limit.totalBudget")).sendKeys(sinfo.getTotlebudget());
		driver.findElement(By.id("limit.dailyBudget")).sendKeys(sinfo.getDailybudget());
		driver.findElement(By.id("limit.impTotalLimit")).sendKeys(sinfo.getImptotallimit());
		driver.findElement(By.id("limit.clickTotalLimit")).sendKeys(sinfo.getClicktotallimit());
		driver.findElement(By.id("limit.impDailyLimit")).sendKeys(sinfo.getImdailylimit());
		driver.findElement(By.id("limit.clickDailyLimit")).sendKeys(sinfo.getClickdailylimit());
		driver.findElement(By.id("indvdLimitimpLimit")).sendKeys(sinfo.getIndvdlimitimplimit());
		driver.findElement(By.id("indvdLimitclickLimit")).sendKeys(sinfo.getIndvdlimitclicklimit());
		Thread.sleep(1000);
		//高级设置
		driver.findElement(By.id("toggleAdvancedSetting")).click();
		boolean crowdflag = driver.findElement(By.id("btn-baiduAudiences")).isDisplayed();
		if(crowdflag){
			//百度人群设定
		    driver.findElement(By.id("btn-baiduAudiences")).click();
			PubHandle.switchframe(driver, By.className("myiframe"), "src", "baiduAudiences");
			boolean flag = Check.elementexist(driver, By.id("node_content_50008"), 10, "兴趣类");
			if(flag){
				boolean flag1 = Check.elementexist(driver, By.id("node_val_50025"), 10, "影视");
				if(flag1){
					driver.findElement(By.id("trigger_50025")).click();
					boolean flag2 = Check.elementexist(driver, By.id("node_val_50026"), 10, "院线电影");
					if(flag2){
						driver.findElement(By.id("node_val_50026")).click();
						PubHandle.drag(driver, By.id("node_val_50026"), By.cssSelector(".ui-droppable"));
						Thread.sleep(500);
						driver.findElement(By.id("confirm")).click();					
					}else{
						ScreenshotandAssert.screenandasserttext(driver, "拖拽失败", "院线电影", By.id("node_val_50026"));
					}
				}else{
					ScreenshotandAssert.screenandasserttext(driver, "影视栏没有打开", "影视", By.id("trigger_50025"));
				}
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "没有跳到frame页", "兴趣类", By.id("node_content_50008"));
			}		
		    driver.switchTo().defaultContent();
		  boolean checkflag1 =  Check.elementexist(driver, By.xpath("//*[@id='baidu-audience-selected']/table/tbody/tr/td[2]/span"), 2, "兴趣类 / 影视 / 院线电影");
		  if(!checkflag1){
			  ScreenshotandAssert.screenandasserttext(driver, "百度人群设定不正确", "兴趣类 / 影视 / 院线电影", By.xpath("//*[@id='baidu-audience-selected']/table/tbody/tr/td[2]/span"));
		  }
		    
		    //设定人群关注度
		    PubHandle.select(driver, By.id("audienceWeight"), "不限");
		}else{
			//DAAT人群设定
			driver.findElement(By.id("btn-audiences")).click();
			PubHandle.switchframe(driver, By.className("myiframe"), "src", "audiences");
			boolean daatflag = Check.elementexist(driver, By.id("node_content_10108"), 10, "人口属性");
			if(daatflag){
				driver.findElement(By.id("trigger_10109")).click();
				boolean firstlevle = Check.elementexist(driver, By.id("node_val_10110"), 10, "男性");
				if(firstlevle){
					driver.findElement(By.id("node_val_10110")).click();
					PubHandle.drag(driver, By.id("node_val_10110"), By.cssSelector(".ui-droppable"));
					Thread.sleep(500);
					driver.findElement(By.id("confirm")).click();
				}else{
					ScreenshotandAssert.screenandasserttext(driver, "没有打开性别标签", "男性", By.id("node_val_10110"));
				}
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "没有进入DAAT标签frame", "人口属性", By.id("node_content_10108"));
			}
			
			driver.switchTo().defaultContent();
		}
		
	    //访客找回设定(3.3以前版本)
//	    driver.findElement(By.id("btn-visitorRetarget")).click();
//	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "retargetConversions");
//	    boolean vistorflag = Check.elementexist(driver, By.xpath("//*[@id='retargetSetting']/div[1]/div[1]"),4 , "访客投放期：");
//	    if(vistorflag){
//	    	driver.findElement(By.id("cat_01")).click();
//		    driver.findElement(By.xpath("//*[@title='conversioncode1']")).click();
//			driver.findElement(By.id("btn-retargetProductId")).click();
//			Check.exist(driver, By.id("carouselModeBAba"), 2);
//			driver.findElement(By.id("carouselModeBbAa")).click();
//			Check.exist(driver, By.id("categoryRelated"), 2);
//		    driver.findElement(By.id("categorySameHot")).click();
//			driver.findElement(By.id("btn-retargetProductId")).click();    
//			driver.findElement(By.id("confirm")).click();
//			driver.switchTo().defaultContent();   
//			boolean checkflag2 = Check.elementexist(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[2]/td[2]"), 2, "retargetingcode1");
//			if(!checkflag2){
//				ScreenshotandAssert.screenandasserttext(driver, "分类访客找回不对", "retargetingcode1", By.xpath("//*[@id='retarget-selected']/table/tbody/tr[2]/td[2]"));
//			}
//			boolean checkflag3 = Check.elementexist(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[3]/td[2]"), 2, "conversioncode1");
//			if(!checkflag3){
//				ScreenshotandAssert.screenandasserttext(driver, "转化目标找回不对", "conversioncode1", By.xpath("//*[@id='retarget-selected']/table/tbody/tr[3]/td[2]"));
//			}
//			
//	    }else{
//	    	ScreenshotandAssert.screenandasserttext(driver, "没有跳转到访客找回设定frame", "访客投放期：", By.xpath("//*[@id='retargetSetting']/div[1]/span[1]"));
//	    }
	    
	    //访客找回设定(3.4版本)
	    
	    driver.findElement(By.id("btn-visitorRetarget")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "retargetConversions");
	    boolean vistorflag = Check.elementexist(driver, By.xpath("//*[@id='retargetRange']"),4 , "访客投放期：");
	    if(vistorflag){
	    	//driver.findElement(By.id("cat_01")).click();
	    	driver.findElement(By.xpath("//*[@title='"+sinfo.getRetargetingcode1()+"']")).click();
		    driver.findElement(By.xpath("//*[@title='"+sinfo.getConversioncode1()+"']")).click(); 
			driver.findElement(By.id("btn-retargetProductId")).click();
			driver.findElement(By.id("carouselModeBbAa")).click();
		    driver.findElement(By.id("categorySameHot")).click();
			driver.findElement(By.id("btn-retargetProductId")).click();    
			driver.findElement(By.id("confirm")).click();
			driver.switchTo().defaultContent();   
			boolean checkflag2 = Check.elementexist(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[2]/td[2]"), 2, sinfo.getRetargetingcode1());
			if(!checkflag2){
				ScreenshotandAssert.screenandasserttext(driver, "分类访客找回不对", sinfo.getRetargetingcode1(), By.xpath("//*[@id='retarget-selected']/table/tbody/tr[2]/td[2]"));
			}
			boolean checkflag3 = Check.elementexist(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[3]/td[2]"), 2, sinfo.getConversioncode1());
			if(!checkflag3){
				ScreenshotandAssert.screenandasserttext(driver, "转化目标找回不对", sinfo.getConversioncode1(), By.xpath("//*[@id='retarget-selected']/table/tbody/tr[3]/td[2]"));
			}
			
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "没有跳转到访客找回设定frame", "访客投放期：", By.xpath("//*[@id='retargetRange']"));
	    }
		              
	    //访客排除
	    driver.findElement(By.id("btn-visitorExclude")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "excludeConversions");
	    boolean excludeflag = Check.elementexist(driver, By.xpath("//*[@id='retargetCategoryDiv']/p/label"), 4, "分类访客");
	    if(excludeflag){
	    	driver.findElement(By.xpath("//*[@title='"+sinfo.getRetargetingcode2()+"']")).click();
	 	    driver.findElement(By.xpath("//*[@title='"+sinfo.getConversioncode2()+"']")).click();
	 	    driver.findElement(By.id("confirm")).click();
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "没有跳转到访客排除frame", "分类访客", By.xpath("//*[@id='retargetCategoryDiv']/p/label"));
	    }
 	    driver.switchTo().defaultContent();

	    //点击访客找回
	    driver.findElement(By.id("btn-clickCookieType")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "clickCookieType");
	    flag(driver,By.xpath("//*[@id='clickCookieSetting']/div/table/tbody/tr/td[1]/label"),"未设置","未跳转至点击找回frame");
	    driver.findElement(By.id("clkcookie_Na")).click();
	    PubHandle.isenbled(driver, By.id("clkcookie_Na"), "未设置按钮不可用", true);
	    driver.findElement(By.id("clkcookie_AdvClick")).click();
	    PubHandle.isenbled(driver, By.id("clkcookie_AdvClick"), "点击过本广告主按钮不可用", true);
	    driver.findElement(By.id("clkcookie_PinyouClick")).click();
	    PubHandle.isenbled(driver, By.id("clkcookie_PinyouClick"), "点击过广告按钮不可用", true);
	    driver.findElement(By.id("confirm")).click();
	    driver.switchTo().defaultContent();
	    
	  //人群分类定向
	    driver.findElement(By.id("btn-pyidCategories")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "pyidCategories");
	    flag(driver,By.xpath("//*[@id='audienceClassifySetting']/table/thead/tr/th[3]"),"编码","未跳转至人群分类定向frame");
	    driver.findElement(By.id("select_all")).click();
	    driver.findElement(By.id("confirm")).click();
	    driver.switchTo().defaultContent();
	    
	    driver.findElement(By.id("pyids")).sendKeys(sinfo.getPyid());
	    
	    //地域设定
	    driver.findElement(By.id("btn-areas")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "customize#areas");
	    flag(driver,By.xpath("//*[@id='area_1']/div/span[2]"),"北京","未跳转至地域设定frame");
	    driver.findElement(By.id("select_1")).click();
	    driver.findElement(By.id("confirm")).click();
	    driver.switchTo().defaultContent();
	    
	    //时段设定
	    driver.findElement(By.id("btn-dayParting")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "customize#dayParting");
	    flag(driver,By.xpath("/html/body/table/tbody/tr[1]/th[2]"),"日期","未跳转至时段设定frame");
	    driver.findElement(By.id("selectAll_2")).click();
	    driver.findElement(By.id("selectAfternoon")).click();
	    driver.findElement(By.id("confirm")).click();
	    driver.switchTo().defaultContent();
	    
	    driver.findElement(By.id("adUnitTypes0")).click();
	    driver.findElement(By.id("adUnitTypes1")).click();
	    
	    //平台设定
	    driver.findElement(By.id("btn-exchanges")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "exchanges;adLocations");
	    flag(driver,By.xpath("//*[@id='platformSetting']/div[4]/p[1]/label"),"腾讯","未跳转至平台设定frame");
	    driver.findElement(By.id("pltTaobao")).click();
	    driver.findElement(By.id("pltGoogle")).click();
	    driver.findElement(By.id("pltSina")).click();
	    driver.findElement(By.id("pltTencent")).click();
	    driver.findElement(By.id("pltBaidu")).click();
	    driver.findElement(By.id("pltYouku")).click();
	    driver.findElement(By.id("pltMiaozhen")).click();
	    driver.findElement(By.id("pltAllyes")).click();
	    driver.findElement(By.id("pltBaoFeng")).click();
	    driver.findElement(By.id("pltIqiyi")).click();
	    // 4.0新增盛大平台
//	    driver.findElement(By.id("pltShanda")).click();
	    driver.findElement(By.xpath("//*[@id='confirm']")).click();
	    driver.switchTo().defaultContent();
	    
	    // 操作系统设定
	    driver.findElement(By.id("btn-osTypes")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "osTypes;osVersions");
	    flag(driver,By.xpath("//*[@id='osSetting']/div[1]/p/label"),"PC操作系统","未跳转至操作系统frame");
	    driver.findElement(By.id("alldesktopos")).click();
	    driver.findElement(By.id("confirm")).click();
	    driver.switchTo().defaultContent();
	    
	    //浏览器设定
	    driver.findElement(By.id("btn-browsers")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "browsers");
	    flag(driver,By.xpath("//*[@id='agentProductSetting']/div/p/label"),"全选","未跳转至浏览器frame");
	    driver.findElement(By.id("select-all")).click();
	    driver.findElement(By.id("confirm")).click();
	    driver.switchTo().defaultContent();
	    
	    //黑白名单设(old)
//	    driver.findElement(By.id("enableBlackList")).click();
//	    driver.findElement(By.id("blackWhiteUrl.blackUrls")).sendKeys(sinfo.getBlacklists());
	    
	    //黑白名单设定
	    driver.findElement(By.id("btn-blackWhiteUrlblackUrlIds")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "blackWhiteUrlcustomBlackUrls");
	    boolean blackflag = Check.elementexist(driver, By.className("fc-gray"), 10, "您可以在左侧区域中选择已有的黑名单列表，也可以在右侧区域直接编辑或输入新的域名。");
	    if(blackflag){
	    	driver.findElement(By.id("select_all")).click();
	    	driver.findElement(By.id("set-name")).sendKeys(sinfo.getBlackdefinedname());
	    	driver.findElement(By.id("set-domains")).sendKeys(sinfo.getBlacklists());
	    	driver.findElement(By.id("confirm")).click();
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "没有进入黑名单设置frame", "您可以在左侧区域中选择已有的黑名单列表，也可以在右侧区域直接编辑或输入新的域名。",  By.className("fc-gray"));
	    }
	    driver.switchTo().defaultContent();
	    
	    //白名单设置
	    driver.findElement(By.id("btn-blackWhiteUrlwhiteUrlIds")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "blackWhiteUrlcustomWhiteUrls");
	    boolean whiteflag = Check.elementexist(driver, By.className("fc-gray"), 10, "您可以在左侧区域中选择已有的白名单列表，也可以在右侧区域直接编辑或输入新的域名。");
	    if(whiteflag){
	    	driver.findElement(By.id("select_all")).click();
	    	driver.findElement(By.id("set-name")).sendKeys(sinfo.getWhitedefinedname());
	    	driver.findElement(By.id("set-domains")).sendKeys(sinfo.getWhitelists());
	    	driver.findElement(By.id("confirm")).click();
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "没有进入白名单设置frame", "您可以在左侧区域中选择已有的白名单列表，也可以在右侧区域直接编辑或输入新的域名。", By.className("fc-gray"));
	    }
	    driver.switchTo().defaultContent();
	    
	    
	    PubHandle.select(driver, By.id("dayPacing"), "每日预算均匀");
	    
	    WebElement highprice = driver.findElement(By.id("cpmBidPrice"));
		highprice.clear();
		highprice.sendKeys(sinfo.getPrice());
		
		if(getuseri(driver)==1){
			PubHandle.select(driver, By.id("algorithmCode"), "哈迪斯");
			PubHandle.select(driver, By.id("expectedGoal"), "CPC单价");
			driver.findElement(By.id("cpc")).sendKeys(sinfo.getCpcprice());
			PubHandle.select(driver, By.id("effectGoal"), "点击率");
			driver.findElement(By.id("effectGoalRate")).sendKeys(sinfo.getEffectGoalRate());
		}else if(getuseri(driver)==2){
			PubHandle.select(driver, By.id("algorithmCode"), "默认算法");
			PubHandle.select(driver, By.id("expectedGoal"), "CPC单价");
			driver.findElement(By.id("cpc")).sendKeys(sinfo.getCpcprice());
			PubHandle.select(driver, By.id("effectGoal"), "点击率");
			driver.findElement(By.id("effectGoalRate")).sendKeys(sinfo.getEffectGoalRate());
		}else if(getuseri(driver)==3){
			PubHandle.select(driver, By.id("expectedGoal"), "CPC单价");
			driver.findElement(By.id("cpc")).sendKeys(sinfo.getCpcprice());
			PubHandle.select(driver, By.id("effectGoal"), "点击率");
			driver.findElement(By.id("effectGoalRate")).sendKeys(sinfo.getEffectGoalRate());
		}else{
			ScreenshotandAssert.screenandassert(driver, "没有找到算法", PubHandle.doesWebElementExist(driver, By.id("algorithmCode")), true);
		}
		if(getuser(driver).equals("徐冲") || getuser(driver).equals("medium")){
			driver.findElement(By.id("scriptCode")).sendKeys(sinfo.getScriptcode());
		}else{
			;
		}
		
		driver.findElement(By.id("remark")).sendKeys(sinfo.getRemarkcode());
	}
	
	public static boolean submit(WebDriver driver,String title){
		driver.findElement(By.name("submitForm")).click();
		boolean flag = Check.usualexist(driver, title, 2);
		if(flag){
			return true;
		}else{
			return false;
		}
	}
	
	
	public static void flag(WebDriver driver,By by,String element,String reminder){
		try {
			boolean flag = Check.elementexist(driver, by, 4, element);
			if(flag){
				;
			}else{
				ScreenshotandAssert.screenandasserttext(driver, reminder, element, by);
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static String getuser(WebDriver driver){
		return driver.findElement(By.xpath("//*[@id='welcome']/div[2]/a[1]")).getText();
	}
	
	public static int getuseri(WebDriver driver){
		String username = driver.findElement(By.xpath("//*[@id='welcome']/div[2]/a[1]")).getText();
		if(username.equals("徐冲")){
			return 1;
		}else if(username.equals("project") ||username.equals("medium")){
			return 2;
		}else if(username.equals("selfautotest")){
			return 3;
		}else{
			return 4;
		}
	}

}
