package com.ipinyou.webpage.ownmediawebpage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.ownmedia.OwnMediaStrategyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class ProprietaryMediaStrategyPage {
	public static void search(WebDriver driver,OwnMediaStrategyInfo sinfo){
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
	public static void instrategy(WebDriver driver){
		boolean flag = Check.exist(driver, By.linkText("创建优化策略"), 2);
		if(flag){
			driver.findElement(By.className("ico-add")).click();
			boolean flag1 = Check.usualexist(driver, "创建投放策略", 2);
			if(!flag1){
				ScreenshotandAssert.screenandasserttitle(driver, "没有进入创建策略页", "创建投放策略");
			}
		}
	}
	public static void createstrategy(WebDriver driver,OwnMediaStrategyInfo sinfo) throws InterruptedException{
		driver.findElement(By.id("name")).sendKeys(sinfo.getStrategyname());
		driver.findElement(By.id("advertisingMode2")).click();
		driver.findElement(By.id("adUnitTypes0")).click();
		driver.findElement(By.id("adUnitTypes1")).click();
		PubHandle.select(driver, By.id("priority"), "15");
		driver.findElement(By.id("limit.totalBudget")).sendKeys(sinfo.getTotlebudget());
		driver.findElement(By.id("limit.dailyBudget")).sendKeys(sinfo.getDailybudget());
		driver.findElement(By.id("limit.impTotalLimit")).sendKeys(sinfo.getImptotallimit());
		driver.findElement(By.id("limit.clickTotalLimit")).sendKeys(sinfo.getClicktotallimit());
		driver.findElement(By.id("limit.impDailyLimit")).sendKeys(sinfo.getImdailylimit());
		driver.findElement(By.id("limit.clickDailyLimit")).sendKeys(sinfo.getClickdailylimit());
		driver.findElement(By.id("indvdLimitimpLimit")).sendKeys(sinfo.getIndvdlimitimplimit());
		driver.findElement(By.id("indvdLimitclickLimit")).sendKeys(sinfo.getIndvdlimitclicklimit());
		Thread.sleep(1000);
		
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
		    
		    //广告位设定
		    
		    driver.findElement(By.id("btn-adUnit.whiteIds")).click();
			PubHandle.switchframe(driver, By.className("myiframe"), "src", "whiteIdsInput");
			boolean flag = Check.elementexist(driver, By.id("select_all"), 10, "全选");
			if(flag){
				Thread.sleep(1000);
				System.out.println(sinfo.getAdvertiserposition());
				driver.findElement(By.id("j-search")).sendKeys(sinfo.getAdvertiserposition());
				driver.findElement(By.className("icon-search")).click();
				boolean flag1 = Check.elementexist(driver, By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[15]/div[1]/span[3]"), 10, sinfo.getAdvertiserposition());
				if(flag1){
					driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[15]/div[1]/span[2]")).click();
					driver.findElement(By.id("confirm")).click();
		/*			driver.findElement(By.xpath("//*[@id='j-adsiteList']/div[15]/div[1]/span[1]")).click();
					boolean flag2 = Check.elementexist(driver, By.xpath("//*[@id='j-adsiteList']/div[15]/div[2]/div[1]/div[1]/span[2]"), 10,"autopart");
					//System.out.println(driver.findElement(By.xpath("//*[@id='j-adsiteList']/div[3]/div[2]/div[1]/div[1]/span[3]")).getText());
					if(flag2){
						Thread.sleep(1000);
						driver.findElement(By.xpath("//*[@id='j-adsiteList']/div[15]/div[2]/div[1]/div[1]/span[1]")).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[3]/div[2]/div/div[2]/div/div/span[2]")).click();
						driver.findElement(By.id("confirm")).click();
					}else{
						ScreenshotandAssert.screenandasserttext(driver, "没有找到autopart", "autopart", By.xpath("//*[@id='j-adsiteList']/div[3]/div[2]/div[1]/div[1]/span[3]"));
					}*/
				}else{
					ScreenshotandAssert.screenandasserttext(driver, "没有找到"+sinfo.getAdvertiserposition(), "autowebsite", By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[15]/div[1]/span[3]"));
				}
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "没有跳转到广告位设定frame", "全选", By.id("select_all"));
			}
			driver.switchTo().defaultContent();
			
			boolean flag3 = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[2]/div/table/tbody/tr[13]/td[2]/div/div[1]/span[2]"), 5, "您已经设定了2个广告位条件");
			if(flag3){
				;
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "没有选择上广告位", "您已经设定了2个广告位条件", By.xpath("//*[@id='strategyFM']/div[2]/div/table/tbody/tr[13]/td[2]/div/div[1]/span[2]"));
			}			
			 //时段设定
		    driver.findElement(By.id("btn-dayParting")).click();
		    PubHandle.switchframe(driver, By.className("myiframe"), "src", "customize#dayParting");
		    flag(driver,By.xpath("/html/body/table/tbody/tr[1]/th[2]"),"日期","未跳转至时段设定frame");
		    driver.findElement(By.id("selectAll_2")).click();
		    driver.findElement(By.id("selectAfternoon")).click();
		    driver.findElement(By.id("confirm")).click();
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
		    
		    //内部设施
			driver.findElement(By.id("remark")).sendKeys(sinfo.getRemarkcode());
            driver.findElement(By.id("scriptCode")).sendKeys(sinfo.getScriptcode());
	}
	
	
	
       public static void submit(WebDriver driver){
	       driver.findElement(By.name("submitForm")).click();
	      boolean flag = Check.usualexist(driver, "上传创意", 2);
           if(!flag){
    	   ScreenshotandAssert.screenandasserttitle(driver, "创建自由媒体策略失败", "上传创意");
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
}
