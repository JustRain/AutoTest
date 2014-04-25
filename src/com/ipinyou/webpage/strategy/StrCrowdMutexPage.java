package com.ipinyou.webpage.strategy;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.strategy.StrCrowdMutexInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class StrCrowdMutexPage {

	public static void search(WebDriver driver,StrCrowdMutexInfo scminfo){
		List<String> list = new ArrayList<String>(); 
		list.add(scminfo.getAdname());
		list.add(scminfo.getOrname());
		list.add(scminfo.getPlname());
		try {
			PubHandle.search(driver, list,PubHandle.titlelist());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void instrategy(WebDriver driver){		
			driver.findElement(By.className("ico-add")).click();
			boolean flag1 = Check.usualexist(driver, "创建投放策略", 2);
			if(!flag1){
				ScreenshotandAssert.screenandasserttitle(driver, "没有进入创建投放策略页", "创建投放策略");
			}
    }
	public static void crowdmutex(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("toggleAdvancedSetting")).click();
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
		    StrCrowdMutexPage.checkcrowd(driver, "daat");
		    driver.navigate().refresh();
		    //new Actions(driver).keyDown(Keys.CONTROL).keyDown(Keys.F5).keyUp(Keys.CONTROL).keyUp(Keys.F5).perform();  
		    driver.findElement(By.id("toggleAdvancedSetting")).click();
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
			StrCrowdMutexPage.checkcrowd(driver, "baidu");
		}
	public static void visitorcrowd(WebDriver driver,StrCrowdMutexInfo scminfo) throws InterruptedException{
		//访客找回
		driver.findElement(By.id("btn-visitorRetarget")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "retargetConversions");
	    boolean vistorflag = Check.elementexist(driver, By.xpath("//*[@id='retargetRange']"),4 , "访客投放期：");
	    if(vistorflag){
	    	driver.findElement(By.xpath("//*[@title='"+scminfo.getRetargetingcode1()+"']")).click();
		    driver.findElement(By.xpath("//*[@title='"+scminfo.getConversioncode1()+"']")).click(); 
			driver.findElement(By.id("btn-retargetProductId")).click();
			driver.findElement(By.id("carouselModeBbAa")).click();
		    driver.findElement(By.id("categorySameHot")).click();
			driver.findElement(By.id("btn-retargetProductId")).click();    
			driver.findElement(By.id("confirm")).click();
			driver.switchTo().defaultContent();   
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "没有跳转到访客找回设定frame", "访客投放期：", By.xpath("//*[@id='retargetRange']"));
	    }
		              
	    //访客排除
	    driver.findElement(By.id("btn-visitorExclude")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "excludeConversions");
	    boolean excludeflag = Check.elementexist(driver, By.xpath("//*[@id='retargetCategoryDiv']/p/label"), 4, "分类访客");
	    if(excludeflag){
	    	driver.findElement(By.xpath("//*[@title='"+scminfo.getRetargetingcode1()+"']")).click();
	 	    driver.findElement(By.xpath("//*[@title='"+scminfo.getConversioncode1()+"']")).click();
	 	    driver.findElement(By.id("confirm")).click();
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "没有跳转到访客排除frame", "分类访客", By.xpath("//*[@id='retargetCategoryDiv']/p/label"));
	    }
 	    driver.switchTo().defaultContent();
 	    driver.findElement(By.name("submitForm")).click();
	}
	//访客排除检查
	public static void visitorcheck(WebDriver driver,StrCrowdMutexInfo scminfo) throws InterruptedException{
		driver.findElement(By.id("toggleAdvancedSetting")).click();
		PubHandle.usualelementcheck(driver, By.xpath("//*[@id='strategyFM']/div[2]/div/table/tbody/tr[5]/td[3]/span[2]"), 5, "访客找回提示不正确", "访客找回分类［"+scminfo.getRetargetingcode1()+"]与访客排除分类互斥。访客找回转化［"+scminfo.getConversioncode1()+"]与访客排除转化互斥。");
		System.out.println("访客找回分类［"+scminfo.getRetargetingcode1()+"]与访客排除分类互斥。访客找回转化［"+scminfo.getConversioncode1()+"]与访客排除转化互斥。");
		PubHandle.usualelementcheck(driver, By.xpath("//*[@id='strategyFM']/div[2]/div/table/tbody/tr[6]/td[3]/span[2]"), 5, "访客排除提示不正确", "访客排除分类［"+scminfo.getRetargetingcode1()+"]与访客找回分类互斥。访客排除转化［"+scminfo.getConversioncode1()+"]与访客找回转化互斥。");
		System.out.println("访客排除分类［"+scminfo.getRetargetingcode1()+"]与访客找回分类互斥。访客排除转化［"+scminfo.getConversioncode1()+"]与访客找回转化互斥。");
	}
	//人群定向检查
	public static void checkcrowd(WebDriver driver,String checkelement) throws InterruptedException{
		if(checkelement.equals("daat")){
			boolean daatflag = Check.elementvalue(driver, By.id("btn-audiences"), 5, "true", "data-disable");
			boolean daatexcludeflag = Check.elementvalue(driver, By.id("btn-audiencesExclude"), 5, "true", "data-disable");
			if(!daatflag){
				ScreenshotandAssert.screenandassert(driver, "daat人群设定扔可以点击", daatflag, true);
			}else{
				System.out.println("daat 检查通过");
			}
			if(!daatexcludeflag){
				ScreenshotandAssert.screenandassert(driver, "daat人群排出设定扔可以点击", daatexcludeflag, true);
			}else{
				System.out.println("daatexclude 检查通过");
			}
		}else if (checkelement.equals("baidu")){
			boolean baiduflag = Check.elementvalue(driver, By.id("btn-baiduAudiences"),5 , "true", "data-disable");
			if(!baiduflag){
				ScreenshotandAssert.screenandassert(driver, "百度人群设定扔可以点击", baiduflag, true);
			}else{
				System.out.println("百度人群 检查通过");
			}
		}
	}
	
	//访客人群更新
	public static void visitorupdate(WebDriver driver,StrCrowdMutexInfo scminfo) throws InterruptedException{
		 //访客排除
	    driver.findElement(By.id("btn-visitorExclude")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "excludeConversions");
	    boolean excludeflag = Check.elementexist(driver, By.xpath("//*[@id='retargetCategoryDiv']/p/label"), 4, "分类访客");
	    if(excludeflag){
	    	driver.findElement(By.xpath("//*[@title='"+scminfo.getRetargetingcode1()+"']")).click();
	 	    driver.findElement(By.xpath("//*[@title='"+scminfo.getConversioncode1()+"']")).click();
	    	driver.findElement(By.xpath("//*[@title='"+scminfo.getRetargetingcode2()+"']")).click();
	 	    driver.findElement(By.xpath("//*[@title='"+scminfo.getConversioncode2()+"']")).click();
	 	    driver.findElement(By.id("confirm")).click();
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "没有跳转到访客排除frame", "分类访客", By.xpath("//*[@id='retargetCategoryDiv']/p/label"));
	    }
 	    driver.switchTo().defaultContent();
 	    System.out.println("===========================人群更新完成");
	}
	//均匀投放设置与检查
	public static void blanceputin(WebDriver driver,StrCrowdMutexInfo scminfo) throws InterruptedException{
		PubHandle.select(driver, By.id("dayPacing"), scminfo.getBudgetblance());
		driver.findElement(By.name("submitForm")).click();
		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[11]/td[3]/span[2]"), 5, scminfo.getBudgetblance());
		if(!flag){
			ScreenshotandAssert.screenandasserttext(driver, "每日均匀预算提示不正确", scminfo.getBudgetreminder(), By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[11]/td[3]/span[2]"));
		}else{ 
			System.out.println(scminfo.getBudgetreminder()+"检查通过");
		}
		driver.findElement(By.id("limit.dailyBudget")).sendKeys("50");
		driver.findElement(By.name("submitForm")).click();
		boolean flag1 = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[11]/td[3]/span[2]"), 5, "每日预算为空时，每日投放量无上限");
		if(!flag1){
			ScreenshotandAssert.screenandasserttext(driver, "每日均匀预算提示不正确", "每日预算为空时，每日投放量无上限",  By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[11]/td[3]/span[2]"));
		}else{
			System.out.println("=====正常检查通过");
		}
		driver.findElement(By.id("toggleAdvancedSetting")).click();
		PubHandle.select(driver, By.id("dayPacing"), scminfo.getExposureblance());
		driver.findElement(By.name("submitForm")).click();
		
		boolean flag3 = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[14]/td[3]/span[2]"), 5, scminfo.getExposurereminder());
		if(!flag3){
			ScreenshotandAssert.screenandasserttext(driver, "每日曝光预算提示不正确", scminfo.getExposurereminder(), By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[14]/td[3]/span[2]"));
		}else{
			System.out.println(scminfo.getExposurereminder()+"检查通过");
		}
		driver.findElement(By.id("limit.impDailyLimit")).sendKeys("50");
		driver.findElement(By.name("submitForm")).click();
		boolean flag4 = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[14]/td[3]/span[2]"), 5, "每天，广告最多被曝光多少千次");
		if(!flag4){
			ScreenshotandAssert.screenandasserttext(driver, "每日曝光预算提示不正确", "每天，广告最多被曝光多少千次", By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[14]/td[3]/span[2]"));
		}else{
			System.out.println("第二次检查通过");
		}
	}
	
	//策略起止日期检查
	public static void datecheck(WebDriver driver,StrCrowdMutexInfo scminfo) throws InterruptedException{
		driver.findElement(By.id("beginDate-endDate")).click();
		System.out.println("begingdate is "+scminfo.getBegindate()+"\n"+"enddate is "+scminfo.getEnddate());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$('#beginDate').attr('value','"+scminfo.getBegindate()+"')",driver.findElement(By.id("beginDate")));
		js.executeScript("$('#endDate').attr('value','"+scminfo.getEnddate()+"')", driver.findElement(By.id("endDate")));
		driver.findElement(By.name("submitForm")).click();
		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[6]/td[3]/span[2]"), 5, "策略投放开始日期不能早于计划开始日期("+scminfo.getBegindate()+")");
		if(!flag){
			ScreenshotandAssert.screenandasserttext(driver, "日期提示不正确", "策略投放开始日期不能早于计划开始日期("+scminfo.getBegindate()+")",  By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[6]/td[3]/span[2]"));
		}else{
			System.out.println();
		}
		driver.findElement(By.id("beginDate-endDate")).click();

		js.executeScript("$('#beginDate').attr('value','"+scminfo.getBegindate1()+"')",driver.findElement(By.id("beginDate")));
		js.executeScript("$('#endDate').attr('value','"+scminfo.getEnddate1()+"')", driver.findElement(By.id("endDate")));
		driver.findElement(By.name("submitForm")).click();
		boolean flag1 = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[6]/td[3]/span[2]"), 5, "策略投放结束日期不能晚于计划结束日期("+scminfo.getOldenddate()+")");
		if(!flag1){
			ScreenshotandAssert.screenandasserttext(driver, "日期提示不正确", "策略投放结束日期不能晚于计划结束日期("+scminfo.getOldenddate()+")",  By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[6]/td[3]/span[2]"));
		}
		driver.findElement(By.id("beginDate-endDate")).click();

		js.executeScript("$('#beginDate').attr('value','"+scminfo.getBegindate()+"')",driver.findElement(By.id("beginDate")));
		js.executeScript("$('#endDate').attr('value','"+scminfo.getEnddate1()+"')", driver.findElement(By.id("endDate")));
		driver.findElement(By.name("submitForm")).click();
		boolean flag2 = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[6]/td[3]/span[2]"), 5, "策略投放开始日期不能早于计划开始日期("+scminfo.getBegindate()+")");
		if(!flag2){
			ScreenshotandAssert.screenandasserttext(driver, "日期提示不正确", "策略投放开始日期不能早于计划开始日期("+scminfo.getBegindate()+")",  By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[6]/td[3]/span[2]"));
		}
		/*
		boolean startflag = Check.isenbled(driver, By.xpath("//*[@title='2014/3/16 该日期超出限制,不可选']"), 5);
		System.out.println("======================flag  =="+startflag);
		if(startflag){
			ScreenshotandAssert.screenandassert(driver, "选择日期在计划外，不应可选", startflag, false);
		}
		boolean endflag = Check.isenbled(driver, By.xpath("//*[@title='2014/3/31 该日期超出限制,不可选']"), 5);
		System.out.println("======================flag  =="+endflag);
		if(endflag){
			ScreenshotandAssert.screenandassert(driver, "选择日期在计划外，不应可选", endflag, false);
		}*/
	}
}
