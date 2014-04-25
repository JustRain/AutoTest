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
		boolean flag = Check.exist(driver, By.linkText("�����Ż�����"), 2);
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
		//��������
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
		//�߼�����
		driver.findElement(By.id("toggleAdvancedSetting")).click();
		boolean crowdflag = driver.findElement(By.id("btn-baiduAudiences")).isDisplayed();
		if(crowdflag){
			//�ٶ���Ⱥ�趨
		    driver.findElement(By.id("btn-baiduAudiences")).click();
			PubHandle.switchframe(driver, By.className("myiframe"), "src", "baiduAudiences");
			boolean flag = Check.elementexist(driver, By.id("node_content_50008"), 10, "��Ȥ��");
			if(flag){
				boolean flag1 = Check.elementexist(driver, By.id("node_val_50025"), 10, "Ӱ��");
				if(flag1){
					driver.findElement(By.id("trigger_50025")).click();
					boolean flag2 = Check.elementexist(driver, By.id("node_val_50026"), 10, "Ժ�ߵ�Ӱ");
					if(flag2){
						driver.findElement(By.id("node_val_50026")).click();
						PubHandle.drag(driver, By.id("node_val_50026"), By.cssSelector(".ui-droppable"));
						Thread.sleep(500);
						driver.findElement(By.id("confirm")).click();					
					}else{
						ScreenshotandAssert.screenandasserttext(driver, "��קʧ��", "Ժ�ߵ�Ӱ", By.id("node_val_50026"));
					}
				}else{
					ScreenshotandAssert.screenandasserttext(driver, "Ӱ����û�д�", "Ӱ��", By.id("trigger_50025"));
				}
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "û������frameҳ", "��Ȥ��", By.id("node_content_50008"));
			}		
		    driver.switchTo().defaultContent();
		  boolean checkflag1 =  Check.elementexist(driver, By.xpath("//*[@id='baidu-audience-selected']/table/tbody/tr/td[2]/span"), 2, "��Ȥ�� / Ӱ�� / Ժ�ߵ�Ӱ");
		  if(!checkflag1){
			  ScreenshotandAssert.screenandasserttext(driver, "�ٶ���Ⱥ�趨����ȷ", "��Ȥ�� / Ӱ�� / Ժ�ߵ�Ӱ", By.xpath("//*[@id='baidu-audience-selected']/table/tbody/tr/td[2]/span"));
		  }
		    
		    //�趨��Ⱥ��ע��
		    PubHandle.select(driver, By.id("audienceWeight"), "����");
		}else{
			//DAAT��Ⱥ�趨
			driver.findElement(By.id("btn-audiences")).click();
			PubHandle.switchframe(driver, By.className("myiframe"), "src", "audiences");
			boolean daatflag = Check.elementexist(driver, By.id("node_content_10108"), 10, "�˿�����");
			if(daatflag){
				driver.findElement(By.id("trigger_10109")).click();
				boolean firstlevle = Check.elementexist(driver, By.id("node_val_10110"), 10, "����");
				if(firstlevle){
					driver.findElement(By.id("node_val_10110")).click();
					PubHandle.drag(driver, By.id("node_val_10110"), By.cssSelector(".ui-droppable"));
					Thread.sleep(500);
					driver.findElement(By.id("confirm")).click();
				}else{
					ScreenshotandAssert.screenandasserttext(driver, "û�д��Ա��ǩ", "����", By.id("node_val_10110"));
				}
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "û�н���DAAT��ǩframe", "�˿�����", By.id("node_content_10108"));
			}
			
			driver.switchTo().defaultContent();
		}
		
	    //�ÿ��һ��趨(3.3��ǰ�汾)
//	    driver.findElement(By.id("btn-visitorRetarget")).click();
//	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "retargetConversions");
//	    boolean vistorflag = Check.elementexist(driver, By.xpath("//*[@id='retargetSetting']/div[1]/div[1]"),4 , "�ÿ�Ͷ���ڣ�");
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
//				ScreenshotandAssert.screenandasserttext(driver, "����ÿ��һز���", "retargetingcode1", By.xpath("//*[@id='retarget-selected']/table/tbody/tr[2]/td[2]"));
//			}
//			boolean checkflag3 = Check.elementexist(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[3]/td[2]"), 2, "conversioncode1");
//			if(!checkflag3){
//				ScreenshotandAssert.screenandasserttext(driver, "ת��Ŀ���һز���", "conversioncode1", By.xpath("//*[@id='retarget-selected']/table/tbody/tr[3]/td[2]"));
//			}
//			
//	    }else{
//	    	ScreenshotandAssert.screenandasserttext(driver, "û����ת���ÿ��һ��趨frame", "�ÿ�Ͷ���ڣ�", By.xpath("//*[@id='retargetSetting']/div[1]/span[1]"));
//	    }
	    
	    //�ÿ��һ��趨(3.4�汾)
	    
	    driver.findElement(By.id("btn-visitorRetarget")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "retargetConversions");
	    boolean vistorflag = Check.elementexist(driver, By.xpath("//*[@id='retargetRange']"),4 , "�ÿ�Ͷ���ڣ�");
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
				ScreenshotandAssert.screenandasserttext(driver, "����ÿ��һز���", sinfo.getRetargetingcode1(), By.xpath("//*[@id='retarget-selected']/table/tbody/tr[2]/td[2]"));
			}
			boolean checkflag3 = Check.elementexist(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[3]/td[2]"), 2, sinfo.getConversioncode1());
			if(!checkflag3){
				ScreenshotandAssert.screenandasserttext(driver, "ת��Ŀ���һز���", sinfo.getConversioncode1(), By.xpath("//*[@id='retarget-selected']/table/tbody/tr[3]/td[2]"));
			}
			
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "û����ת���ÿ��һ��趨frame", "�ÿ�Ͷ���ڣ�", By.xpath("//*[@id='retargetRange']"));
	    }
		              
	    //�ÿ��ų�
	    driver.findElement(By.id("btn-visitorExclude")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "excludeConversions");
	    boolean excludeflag = Check.elementexist(driver, By.xpath("//*[@id='retargetCategoryDiv']/p/label"), 4, "����ÿ�");
	    if(excludeflag){
	    	driver.findElement(By.xpath("//*[@title='"+sinfo.getRetargetingcode2()+"']")).click();
	 	    driver.findElement(By.xpath("//*[@title='"+sinfo.getConversioncode2()+"']")).click();
	 	    driver.findElement(By.id("confirm")).click();
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "û����ת���ÿ��ų�frame", "����ÿ�", By.xpath("//*[@id='retargetCategoryDiv']/p/label"));
	    }
 	    driver.switchTo().defaultContent();

	    //����ÿ��һ�
	    driver.findElement(By.id("btn-clickCookieType")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "clickCookieType");
	    flag(driver,By.xpath("//*[@id='clickCookieSetting']/div/table/tbody/tr/td[1]/label"),"δ����","δ��ת������һ�frame");
	    driver.findElement(By.id("clkcookie_Na")).click();
	    PubHandle.isenbled(driver, By.id("clkcookie_Na"), "δ���ð�ť������", true);
	    driver.findElement(By.id("clkcookie_AdvClick")).click();
	    PubHandle.isenbled(driver, By.id("clkcookie_AdvClick"), "��������������ť������", true);
	    driver.findElement(By.id("clkcookie_PinyouClick")).click();
	    PubHandle.isenbled(driver, By.id("clkcookie_PinyouClick"), "�������水ť������", true);
	    driver.findElement(By.id("confirm")).click();
	    driver.switchTo().defaultContent();
	    
	  //��Ⱥ���ඨ��
	    driver.findElement(By.id("btn-pyidCategories")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "pyidCategories");
	    flag(driver,By.xpath("//*[@id='audienceClassifySetting']/table/thead/tr/th[3]"),"����","δ��ת����Ⱥ���ඨ��frame");
	    driver.findElement(By.id("select_all")).click();
	    driver.findElement(By.id("confirm")).click();
	    driver.switchTo().defaultContent();
	    
	    driver.findElement(By.id("pyids")).sendKeys(sinfo.getPyid());
	    
	    //�����趨
	    driver.findElement(By.id("btn-areas")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "customize#areas");
	    flag(driver,By.xpath("//*[@id='area_1']/div/span[2]"),"����","δ��ת�������趨frame");
	    driver.findElement(By.id("select_1")).click();
	    driver.findElement(By.id("confirm")).click();
	    driver.switchTo().defaultContent();
	    
	    //ʱ���趨
	    driver.findElement(By.id("btn-dayParting")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "customize#dayParting");
	    flag(driver,By.xpath("/html/body/table/tbody/tr[1]/th[2]"),"����","δ��ת��ʱ���趨frame");
	    driver.findElement(By.id("selectAll_2")).click();
	    driver.findElement(By.id("selectAfternoon")).click();
	    driver.findElement(By.id("confirm")).click();
	    driver.switchTo().defaultContent();
	    
	    driver.findElement(By.id("adUnitTypes0")).click();
	    driver.findElement(By.id("adUnitTypes1")).click();
	    
	    //ƽ̨�趨
	    driver.findElement(By.id("btn-exchanges")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "exchanges;adLocations");
	    flag(driver,By.xpath("//*[@id='platformSetting']/div[4]/p[1]/label"),"��Ѷ","δ��ת��ƽ̨�趨frame");
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
	    // 4.0����ʢ��ƽ̨
//	    driver.findElement(By.id("pltShanda")).click();
	    driver.findElement(By.xpath("//*[@id='confirm']")).click();
	    driver.switchTo().defaultContent();
	    
	    // ����ϵͳ�趨
	    driver.findElement(By.id("btn-osTypes")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "osTypes;osVersions");
	    flag(driver,By.xpath("//*[@id='osSetting']/div[1]/p/label"),"PC����ϵͳ","δ��ת������ϵͳframe");
	    driver.findElement(By.id("alldesktopos")).click();
	    driver.findElement(By.id("confirm")).click();
	    driver.switchTo().defaultContent();
	    
	    //������趨
	    driver.findElement(By.id("btn-browsers")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "browsers");
	    flag(driver,By.xpath("//*[@id='agentProductSetting']/div/p/label"),"ȫѡ","δ��ת�������frame");
	    driver.findElement(By.id("select-all")).click();
	    driver.findElement(By.id("confirm")).click();
	    driver.switchTo().defaultContent();
	    
	    //�ڰ�������(old)
//	    driver.findElement(By.id("enableBlackList")).click();
//	    driver.findElement(By.id("blackWhiteUrl.blackUrls")).sendKeys(sinfo.getBlacklists());
	    
	    //�ڰ������趨
	    driver.findElement(By.id("btn-blackWhiteUrlblackUrlIds")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "blackWhiteUrlcustomBlackUrls");
	    boolean blackflag = Check.elementexist(driver, By.className("fc-gray"), 10, "�����������������ѡ�����еĺ������б�Ҳ�������Ҳ�����ֱ�ӱ༭�������µ�������");
	    if(blackflag){
	    	driver.findElement(By.id("select_all")).click();
	    	driver.findElement(By.id("set-name")).sendKeys(sinfo.getBlackdefinedname());
	    	driver.findElement(By.id("set-domains")).sendKeys(sinfo.getBlacklists());
	    	driver.findElement(By.id("confirm")).click();
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "û�н������������frame", "�����������������ѡ�����еĺ������б�Ҳ�������Ҳ�����ֱ�ӱ༭�������µ�������",  By.className("fc-gray"));
	    }
	    driver.switchTo().defaultContent();
	    
	    //����������
	    driver.findElement(By.id("btn-blackWhiteUrlwhiteUrlIds")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "blackWhiteUrlcustomWhiteUrls");
	    boolean whiteflag = Check.elementexist(driver, By.className("fc-gray"), 10, "�����������������ѡ�����еİ������б�Ҳ�������Ҳ�����ֱ�ӱ༭�������µ�������");
	    if(whiteflag){
	    	driver.findElement(By.id("select_all")).click();
	    	driver.findElement(By.id("set-name")).sendKeys(sinfo.getWhitedefinedname());
	    	driver.findElement(By.id("set-domains")).sendKeys(sinfo.getWhitelists());
	    	driver.findElement(By.id("confirm")).click();
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "û�н������������frame", "�����������������ѡ�����еİ������б�Ҳ�������Ҳ�����ֱ�ӱ༭�������µ�������", By.className("fc-gray"));
	    }
	    driver.switchTo().defaultContent();
	    
	    
	    PubHandle.select(driver, By.id("dayPacing"), "ÿ��Ԥ�����");
	    
	    WebElement highprice = driver.findElement(By.id("cpmBidPrice"));
		highprice.clear();
		highprice.sendKeys(sinfo.getPrice());
		
		if(getuseri(driver)==1){
			PubHandle.select(driver, By.id("algorithmCode"), "����˹");
			PubHandle.select(driver, By.id("expectedGoal"), "CPC����");
			driver.findElement(By.id("cpc")).sendKeys(sinfo.getCpcprice());
			PubHandle.select(driver, By.id("effectGoal"), "�����");
			driver.findElement(By.id("effectGoalRate")).sendKeys(sinfo.getEffectGoalRate());
		}else if(getuseri(driver)==2){
			PubHandle.select(driver, By.id("algorithmCode"), "Ĭ���㷨");
			PubHandle.select(driver, By.id("expectedGoal"), "CPC����");
			driver.findElement(By.id("cpc")).sendKeys(sinfo.getCpcprice());
			PubHandle.select(driver, By.id("effectGoal"), "�����");
			driver.findElement(By.id("effectGoalRate")).sendKeys(sinfo.getEffectGoalRate());
		}else if(getuseri(driver)==3){
			PubHandle.select(driver, By.id("expectedGoal"), "CPC����");
			driver.findElement(By.id("cpc")).sendKeys(sinfo.getCpcprice());
			PubHandle.select(driver, By.id("effectGoal"), "�����");
			driver.findElement(By.id("effectGoalRate")).sendKeys(sinfo.getEffectGoalRate());
		}else{
			ScreenshotandAssert.screenandassert(driver, "û���ҵ��㷨", PubHandle.doesWebElementExist(driver, By.id("algorithmCode")), true);
		}
		if(getuser(driver).equals("���") || getuser(driver).equals("medium")){
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
		if(username.equals("���")){
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
