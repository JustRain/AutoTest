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
			boolean flag1 = Check.usualexist(driver, "����Ͷ�Ų���", 2);
			if(!flag1){
				ScreenshotandAssert.screenandasserttitle(driver, "û�н��봴��Ͷ�Ų���ҳ", "����Ͷ�Ų���");
			}
    }
	public static void crowdmutex(WebDriver driver) throws InterruptedException{
		driver.findElement(By.id("toggleAdvancedSetting")).click();
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
		    StrCrowdMutexPage.checkcrowd(driver, "daat");
		    driver.navigate().refresh();
		    //new Actions(driver).keyDown(Keys.CONTROL).keyDown(Keys.F5).keyUp(Keys.CONTROL).keyUp(Keys.F5).perform();  
		    driver.findElement(By.id("toggleAdvancedSetting")).click();
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
			StrCrowdMutexPage.checkcrowd(driver, "baidu");
		}
	public static void visitorcrowd(WebDriver driver,StrCrowdMutexInfo scminfo) throws InterruptedException{
		//�ÿ��һ�
		driver.findElement(By.id("btn-visitorRetarget")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "retargetConversions");
	    boolean vistorflag = Check.elementexist(driver, By.xpath("//*[@id='retargetRange']"),4 , "�ÿ�Ͷ���ڣ�");
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
	    	ScreenshotandAssert.screenandasserttext(driver, "û����ת���ÿ��һ��趨frame", "�ÿ�Ͷ���ڣ�", By.xpath("//*[@id='retargetRange']"));
	    }
		              
	    //�ÿ��ų�
	    driver.findElement(By.id("btn-visitorExclude")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "excludeConversions");
	    boolean excludeflag = Check.elementexist(driver, By.xpath("//*[@id='retargetCategoryDiv']/p/label"), 4, "����ÿ�");
	    if(excludeflag){
	    	driver.findElement(By.xpath("//*[@title='"+scminfo.getRetargetingcode1()+"']")).click();
	 	    driver.findElement(By.xpath("//*[@title='"+scminfo.getConversioncode1()+"']")).click();
	 	    driver.findElement(By.id("confirm")).click();
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "û����ת���ÿ��ų�frame", "����ÿ�", By.xpath("//*[@id='retargetCategoryDiv']/p/label"));
	    }
 	    driver.switchTo().defaultContent();
 	    driver.findElement(By.name("submitForm")).click();
	}
	//�ÿ��ų����
	public static void visitorcheck(WebDriver driver,StrCrowdMutexInfo scminfo) throws InterruptedException{
		driver.findElement(By.id("toggleAdvancedSetting")).click();
		PubHandle.usualelementcheck(driver, By.xpath("//*[@id='strategyFM']/div[2]/div/table/tbody/tr[5]/td[3]/span[2]"), 5, "�ÿ��һ���ʾ����ȷ", "�ÿ��һط����"+scminfo.getRetargetingcode1()+"]��ÿ��ų����໥�⡣�ÿ��һ�ת����"+scminfo.getConversioncode1()+"]��ÿ��ų�ת�����⡣");
		System.out.println("�ÿ��һط����"+scminfo.getRetargetingcode1()+"]��ÿ��ų����໥�⡣�ÿ��һ�ת����"+scminfo.getConversioncode1()+"]��ÿ��ų�ת�����⡣");
		PubHandle.usualelementcheck(driver, By.xpath("//*[@id='strategyFM']/div[2]/div/table/tbody/tr[6]/td[3]/span[2]"), 5, "�ÿ��ų���ʾ����ȷ", "�ÿ��ų������"+scminfo.getRetargetingcode1()+"]��ÿ��һط��໥�⡣�ÿ��ų�ת����"+scminfo.getConversioncode1()+"]��ÿ��һ�ת�����⡣");
		System.out.println("�ÿ��ų������"+scminfo.getRetargetingcode1()+"]��ÿ��һط��໥�⡣�ÿ��ų�ת����"+scminfo.getConversioncode1()+"]��ÿ��һ�ת�����⡣");
	}
	//��Ⱥ������
	public static void checkcrowd(WebDriver driver,String checkelement) throws InterruptedException{
		if(checkelement.equals("daat")){
			boolean daatflag = Check.elementvalue(driver, By.id("btn-audiences"), 5, "true", "data-disable");
			boolean daatexcludeflag = Check.elementvalue(driver, By.id("btn-audiencesExclude"), 5, "true", "data-disable");
			if(!daatflag){
				ScreenshotandAssert.screenandassert(driver, "daat��Ⱥ�趨�ӿ��Ե��", daatflag, true);
			}else{
				System.out.println("daat ���ͨ��");
			}
			if(!daatexcludeflag){
				ScreenshotandAssert.screenandassert(driver, "daat��Ⱥ�ų��趨�ӿ��Ե��", daatexcludeflag, true);
			}else{
				System.out.println("daatexclude ���ͨ��");
			}
		}else if (checkelement.equals("baidu")){
			boolean baiduflag = Check.elementvalue(driver, By.id("btn-baiduAudiences"),5 , "true", "data-disable");
			if(!baiduflag){
				ScreenshotandAssert.screenandassert(driver, "�ٶ���Ⱥ�趨�ӿ��Ե��", baiduflag, true);
			}else{
				System.out.println("�ٶ���Ⱥ ���ͨ��");
			}
		}
	}
	
	//�ÿ���Ⱥ����
	public static void visitorupdate(WebDriver driver,StrCrowdMutexInfo scminfo) throws InterruptedException{
		 //�ÿ��ų�
	    driver.findElement(By.id("btn-visitorExclude")).click();
	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "excludeConversions");
	    boolean excludeflag = Check.elementexist(driver, By.xpath("//*[@id='retargetCategoryDiv']/p/label"), 4, "����ÿ�");
	    if(excludeflag){
	    	driver.findElement(By.xpath("//*[@title='"+scminfo.getRetargetingcode1()+"']")).click();
	 	    driver.findElement(By.xpath("//*[@title='"+scminfo.getConversioncode1()+"']")).click();
	    	driver.findElement(By.xpath("//*[@title='"+scminfo.getRetargetingcode2()+"']")).click();
	 	    driver.findElement(By.xpath("//*[@title='"+scminfo.getConversioncode2()+"']")).click();
	 	    driver.findElement(By.id("confirm")).click();
	    }else{
	    	ScreenshotandAssert.screenandasserttext(driver, "û����ת���ÿ��ų�frame", "����ÿ�", By.xpath("//*[@id='retargetCategoryDiv']/p/label"));
	    }
 	    driver.switchTo().defaultContent();
 	    System.out.println("===========================��Ⱥ�������");
	}
	//����Ͷ����������
	public static void blanceputin(WebDriver driver,StrCrowdMutexInfo scminfo) throws InterruptedException{
		PubHandle.select(driver, By.id("dayPacing"), scminfo.getBudgetblance());
		driver.findElement(By.name("submitForm")).click();
		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[11]/td[3]/span[2]"), 5, scminfo.getBudgetblance());
		if(!flag){
			ScreenshotandAssert.screenandasserttext(driver, "ÿ�վ���Ԥ����ʾ����ȷ", scminfo.getBudgetreminder(), By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[11]/td[3]/span[2]"));
		}else{ 
			System.out.println(scminfo.getBudgetreminder()+"���ͨ��");
		}
		driver.findElement(By.id("limit.dailyBudget")).sendKeys("50");
		driver.findElement(By.name("submitForm")).click();
		boolean flag1 = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[11]/td[3]/span[2]"), 5, "ÿ��Ԥ��Ϊ��ʱ��ÿ��Ͷ����������");
		if(!flag1){
			ScreenshotandAssert.screenandasserttext(driver, "ÿ�վ���Ԥ����ʾ����ȷ", "ÿ��Ԥ��Ϊ��ʱ��ÿ��Ͷ����������",  By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[11]/td[3]/span[2]"));
		}else{
			System.out.println("=====�������ͨ��");
		}
		driver.findElement(By.id("toggleAdvancedSetting")).click();
		PubHandle.select(driver, By.id("dayPacing"), scminfo.getExposureblance());
		driver.findElement(By.name("submitForm")).click();
		
		boolean flag3 = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[14]/td[3]/span[2]"), 5, scminfo.getExposurereminder());
		if(!flag3){
			ScreenshotandAssert.screenandasserttext(driver, "ÿ���ع�Ԥ����ʾ����ȷ", scminfo.getExposurereminder(), By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[14]/td[3]/span[2]"));
		}else{
			System.out.println(scminfo.getExposurereminder()+"���ͨ��");
		}
		driver.findElement(By.id("limit.impDailyLimit")).sendKeys("50");
		driver.findElement(By.name("submitForm")).click();
		boolean flag4 = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[14]/td[3]/span[2]"), 5, "ÿ�죬�����౻�ع����ǧ��");
		if(!flag4){
			ScreenshotandAssert.screenandasserttext(driver, "ÿ���ع�Ԥ����ʾ����ȷ", "ÿ�죬�����౻�ع����ǧ��", By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[14]/td[3]/span[2]"));
		}else{
			System.out.println("�ڶ��μ��ͨ��");
		}
	}
	
	//������ֹ���ڼ��
	public static void datecheck(WebDriver driver,StrCrowdMutexInfo scminfo) throws InterruptedException{
		driver.findElement(By.id("beginDate-endDate")).click();
		System.out.println("begingdate is "+scminfo.getBegindate()+"\n"+"enddate is "+scminfo.getEnddate());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$('#beginDate').attr('value','"+scminfo.getBegindate()+"')",driver.findElement(By.id("beginDate")));
		js.executeScript("$('#endDate').attr('value','"+scminfo.getEnddate()+"')", driver.findElement(By.id("endDate")));
		driver.findElement(By.name("submitForm")).click();
		boolean flag = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[6]/td[3]/span[2]"), 5, "����Ͷ�ſ�ʼ���ڲ������ڼƻ���ʼ����("+scminfo.getBegindate()+")");
		if(!flag){
			ScreenshotandAssert.screenandasserttext(driver, "������ʾ����ȷ", "����Ͷ�ſ�ʼ���ڲ������ڼƻ���ʼ����("+scminfo.getBegindate()+")",  By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[6]/td[3]/span[2]"));
		}else{
			System.out.println();
		}
		driver.findElement(By.id("beginDate-endDate")).click();

		js.executeScript("$('#beginDate').attr('value','"+scminfo.getBegindate1()+"')",driver.findElement(By.id("beginDate")));
		js.executeScript("$('#endDate').attr('value','"+scminfo.getEnddate1()+"')", driver.findElement(By.id("endDate")));
		driver.findElement(By.name("submitForm")).click();
		boolean flag1 = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[6]/td[3]/span[2]"), 5, "����Ͷ�Ž������ڲ������ڼƻ���������("+scminfo.getOldenddate()+")");
		if(!flag1){
			ScreenshotandAssert.screenandasserttext(driver, "������ʾ����ȷ", "����Ͷ�Ž������ڲ������ڼƻ���������("+scminfo.getOldenddate()+")",  By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[6]/td[3]/span[2]"));
		}
		driver.findElement(By.id("beginDate-endDate")).click();

		js.executeScript("$('#beginDate').attr('value','"+scminfo.getBegindate()+"')",driver.findElement(By.id("beginDate")));
		js.executeScript("$('#endDate').attr('value','"+scminfo.getEnddate1()+"')", driver.findElement(By.id("endDate")));
		driver.findElement(By.name("submitForm")).click();
		boolean flag2 = Check.elementexist(driver, By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[6]/td[3]/span[2]"), 5, "����Ͷ�ſ�ʼ���ڲ������ڼƻ���ʼ����("+scminfo.getBegindate()+")");
		if(!flag2){
			ScreenshotandAssert.screenandasserttext(driver, "������ʾ����ȷ", "����Ͷ�ſ�ʼ���ڲ������ڼƻ���ʼ����("+scminfo.getBegindate()+")",  By.xpath("//*[@id='strategyFM']/div[1]/div/table/tbody/tr[6]/td[3]/span[2]"));
		}
		/*
		boolean startflag = Check.isenbled(driver, By.xpath("//*[@title='2014/3/16 �����ڳ�������,����ѡ']"), 5);
		System.out.println("======================flag  =="+startflag);
		if(startflag){
			ScreenshotandAssert.screenandassert(driver, "ѡ�������ڼƻ��⣬��Ӧ��ѡ", startflag, false);
		}
		boolean endflag = Check.isenbled(driver, By.xpath("//*[@title='2014/3/31 �����ڳ�������,����ѡ']"), 5);
		System.out.println("======================flag  =="+endflag);
		if(endflag){
			ScreenshotandAssert.screenandassert(driver, "ѡ�������ڼƻ��⣬��Ӧ��ѡ", endflag, false);
		}*/
	}
}
