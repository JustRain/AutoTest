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
		boolean flag = Check.exist(driver, By.linkText("�����Ż�����"), 2);
		if(flag){
			driver.findElement(By.className("ico-add")).click();
			boolean flag1 = Check.usualexist(driver, "����Ͷ�Ų���", 2);
			if(!flag1){
				ScreenshotandAssert.screenandasserttitle(driver, "û�н��봴������ҳ", "����Ͷ�Ų���");
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
		    
		    //���λ�趨
		    
		    driver.findElement(By.id("btn-adUnit.whiteIds")).click();
			PubHandle.switchframe(driver, By.className("myiframe"), "src", "whiteIdsInput");
			boolean flag = Check.elementexist(driver, By.id("select_all"), 10, "ȫѡ");
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
						ScreenshotandAssert.screenandasserttext(driver, "û���ҵ�autopart", "autopart", By.xpath("//*[@id='j-adsiteList']/div[3]/div[2]/div[1]/div[1]/span[3]"));
					}*/
				}else{
					ScreenshotandAssert.screenandasserttext(driver, "û���ҵ�"+sinfo.getAdvertiserposition(), "autowebsite", By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[15]/div[1]/span[3]"));
				}
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "û����ת�����λ�趨frame", "ȫѡ", By.id("select_all"));
			}
			driver.switchTo().defaultContent();
			
			boolean flag3 = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[2]/div/table/tbody/tr[13]/td[2]/div/div[1]/span[2]"), 5, "���Ѿ��趨��2�����λ����");
			if(flag3){
				;
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "û��ѡ���Ϲ��λ", "���Ѿ��趨��2�����λ����", By.xpath("//*[@id='strategyFM']/div[2]/div/table/tbody/tr[13]/td[2]/div/div[1]/span[2]"));
			}			
			 //ʱ���趨
		    driver.findElement(By.id("btn-dayParting")).click();
		    PubHandle.switchframe(driver, By.className("myiframe"), "src", "customize#dayParting");
		    flag(driver,By.xpath("/html/body/table/tbody/tr[1]/th[2]"),"����","δ��ת��ʱ���趨frame");
		    driver.findElement(By.id("selectAll_2")).click();
		    driver.findElement(By.id("selectAfternoon")).click();
		    driver.findElement(By.id("confirm")).click();
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
		    
		    //�ڲ���ʩ
			driver.findElement(By.id("remark")).sendKeys(sinfo.getRemarkcode());
            driver.findElement(By.id("scriptCode")).sendKeys(sinfo.getScriptcode());
	}
	
	
	
       public static void submit(WebDriver driver){
	       driver.findElement(By.name("submitForm")).click();
	      boolean flag = Check.usualexist(driver, "�ϴ�����", 2);
           if(!flag){
    	   ScreenshotandAssert.screenandasserttitle(driver, "��������ý�����ʧ��", "�ϴ�����");
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
