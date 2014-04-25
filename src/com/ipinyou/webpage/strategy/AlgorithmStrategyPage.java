package com.ipinyou.webpage.strategy;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ipinyou.entity.strategy.AlgorithmStrategyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class AlgorithmStrategyPage {
	public static void search(WebDriver driver,AlgorithmStrategyInfo asinfo) throws InterruptedException{
		List<String> list = new ArrayList<String>(); 
		list.add(asinfo.getAdname());
		list.add(asinfo.getOrname());
		list.add(asinfo.getAlorplname());
		PubHandle.search(driver, list,PubHandle.titlelist());
	}
	
	public static void instrategy(WebDriver driver){
		driver.findElement(By.className("ico-add")).click();
		boolean flag1 = Check.usualexist(driver, "����Ͷ�Ų���", 2);
		if(!flag1){
			ScreenshotandAssert.screenandasserttitle(driver, "û�н��봴��Ͷ�Ų���ҳ", "����Ͷ�Ų���");
		}
	}
	
	public static void createstrategy(WebDriver driver,AlgorithmStrategyInfo asinfo){
		System.out.println(asinfo.getAlgostrategyname());
		driver.findElement(By.id("name")).sendKeys(asinfo.getAlgostrategyname());
		PubHandle.select(driver, By.id("algorithmCode"),asinfo.getAlgorithm());
		PubHandle.select(driver, By.id("expectedGoal"), asinfo.getPricegoal());
		driver.findElement(By.id("cpc")).sendKeys(asinfo.getUnitprice());
		driver.findElement(By.name("submitForm")).click();
		boolean flag = Check.usualexist(driver, "�ϴ�����", 5);
		if(!flag){
			ScreenshotandAssert.screenandasserttitle(driver, "��������ʧ��", "�ϴ�����");
		}else{
			System.out.println(asinfo.getAlgostrategyname()+"Success");
		}
	}
	public static void firstcheck(WebDriver driver,AlgorithmStrategyInfo asinfo){
		driver.findElement(By.linkText(asinfo.getAlorplname())).click();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.xpath("//*[@title='�޸Ĳ���']")).click();
		boolean flag = Check.usualexist(driver, "�޸�Ͷ�Ų���", 5);
		if(flag){
			PubHandle.selcheck(driver, By.id("algorithmCode"), asinfo.getAlgorithm());
			PubHandle.selcheck(driver, By.id("expectedGoal"), asinfo.getPricegoal());
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "û�н�������޸�Ҳ", "�޸�Ͷ�Ų���");
		}
	}
	public static void firsteditstr(WebDriver driver,AlgorithmStrategyInfo asinfo){
		driver.findElement(By.linkText(asinfo.getAlorplname())).click();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.xpath("//*[@title='�޸Ĳ���']")).click();
		boolean flag = Check.usualexist(driver, "�޸�Ͷ�Ų���", 5);
		if(flag){
			PubHandle.select(driver, By.id("algorithmCode"), asinfo.getEditalgorithm());
			driver.findElement(By.name("submitForm")).click();
			boolean editflag = Check.usualexist(driver, "�����б�", 5);
			if(!editflag){
				ScreenshotandAssert.screenandasserttitle(driver, "�޸�ʧ��", "�����б�");
			}else{
				System.out.println("�ͼ��㷨�޸ĳɹ�");
			}
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "û�н�������޸�Ҳ", "�޸�Ͷ�Ų���");
		}
	}
	public static void firsteditcheck(WebDriver driver,AlgorithmStrategyInfo asinfo) throws InterruptedException{
		    driver.findElement(By.id("appendedInputButton")).clear();
			driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
			driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
			driver.findElement(By.xpath("//*[@title='�޸Ĳ���']")).click();
			boolean flag = Check.usualexist(driver, "�޸�Ͷ�Ų���", 5);
			if(flag){
				PubHandle.selcheck(driver,By.id("algorithmCode"),asinfo.getEditalgorithm());
				PubHandle.checkvalue(driver, By.id("expectedParams"), asinfo.getCpcunit(), "value");
			}else{
				ScreenshotandAssert.screenandasserttitle(driver, "û�н�������޸�Ҳ", "�޸�Ͷ�Ų���");
			}
		}
	public static void secondeditstr(WebDriver driver,AlgorithmStrategyInfo asinfo){
		driver.findElement(By.linkText(asinfo.getAlorplname())).click();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.xpath("//*[@title='�޸Ĳ���']")).click();
		boolean flag = Check.usualexist(driver, "�޸�Ͷ�Ų���", 5);
		if(flag){
			PubHandle.select(driver, By.id("algorithmCode"), asinfo.getSecalgorithm());
            driver.findElement(By.id("cpmBidPrice")).sendKeys(asinfo.getSechighprice());
            driver.findElement(By.id("cpc")).sendKeys(asinfo.getSecunitprice());
			driver.findElement(By.name("submitForm")).click();
			boolean editflag = Check.usualexist(driver, "�����б�", 5);
			if(!editflag){
				ScreenshotandAssert.screenandasserttitle(driver, "�޸�ʧ��", "�����б�");
			}else{
				System.out.println("�ڶ����޸Ĳ���ͨ��");
			}
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "û�н�������޸�Ҳ", "�޸�Ͷ�Ų���");
		}
	}
	
	public static void seceditcheck(WebDriver driver,AlgorithmStrategyInfo asinfo) throws InterruptedException{
		    driver.findElement(By.id("appendedInputButton")).clear();
			driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
			driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
			driver.findElement(By.xpath("//*[@title='�޸Ĳ���']")).click();
			boolean flag = Check.usualexist(driver, "�޸�Ͷ�Ų���", 5);
			if(flag){
				PubHandle.checkvalue(driver, By.id("cpmBidPrice"), asinfo.getSechighprice(), "value");
				PubHandle.selcheck(driver,By.id("algorithmCode"),asinfo.getSecalgorithm());
				PubHandle.checkvalue(driver, By.id("expectedParams"), asinfo.getSecunitpricevalue(), "value");
			}else{
				ScreenshotandAssert.screenandasserttitle(driver, "û�н�������޸�Ҳ", "�޸�Ͷ�Ų���");
			}
	}
	
	
	public static void openstatus(WebDriver driver,AlgorithmStrategyInfo asinfo){
		driver.findElement(By.linkText(asinfo.getAlorplname())).click();
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		WebElement strstatus = driver.findElement(By.className("btn-oval-close"));
	    if(strstatus.isDisplayed()){
			  strstatus.click();
		  }
	}
	
	public static void openedit(WebDriver driver,AlgorithmStrategyInfo asinfo){		
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.xpath("//*[@title='�޸Ĳ���']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$('#algorithmCode').get(0).selectedIndex=3;", driver.findElement(By.id("algorithmCode")));
		driver.findElement(By.name("submitForm")).click();
	}
	public static void  secondcheck(WebDriver driver){
		driver.findElement(By.xpath("//*[@title='�޸Ĳ���']")).click();
		boolean flag = Check.isenbled(driver, By.id("algorithmCode"), 5);
		if(flag){
			ScreenshotandAssert.screenandassert(driver, "�㷨û�йر�", flag, false);
		}else{
			System.out.println("״̬�������㷨�Ѳ����޸�");
		}
	}
	
	public static void copystr(WebDriver driver,AlgorithmStrategyInfo asinfo) throws InterruptedException{
		driver.findElement(By.linkText(asinfo.getAlorplname())).click();
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getAlgostrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.id("select_all")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[5]/div[2]/a")).click();
		boolean flag = Check.elementexist(driver, By.linkText("�������Ʋ���"),5, "�������Ʋ���");
		if(flag){
			driver.findElement(By.linkText("�������Ʋ���")).click();
			PubHandle.switchframe(driver, By.className("myiframe"), "src", "campaignlist?id");
			boolean frameflag = Check.elementexist(driver, By.xpath("//*[@id='campaignlist']/table/thead/tr/th[2]"), 10, "�ƻ�����");
			if(frameflag){
				driver.findElement(By.id("select_all")).click();
				driver.findElement(By.id("confirm")).click();
			}else{
				ScreenshotandAssert.screenandasserttext(driver, "û�н��븴��frame", "�ƻ�����", By.xpath("//*[@id='campaignlist']/table/thead/tr/th[2]"));
			}
			driver.switchTo().defaultContent();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "û���ҵ��������Ʋ���", "�������Ʋ���",  By.linkText("�������Ʋ���"));
		}
		boolean copyflag = Check.elementexist(driver, By.linkText(asinfo.getCopystrategyname()), 5, asinfo.getCopystrategyname());
		if(!copyflag){
			ScreenshotandAssert.screenandasserttext(driver, asinfo.getAlgostrategyname()+"����ʧ��", asinfo.getCopystrategyname(), By.linkText(asinfo.getCopystrategyname()));
		}else{
			System.out.println(asinfo.getAlgostrategyname()+"����success");
		}
	}
	
	public static void  editcopy(WebDriver driver,AlgorithmStrategyInfo asinfo){
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getCopystrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.xpath("//*[@title='�޸Ĳ���']")).click();
		boolean flag = Check.usualexist(driver, "�޸�Ͷ�Ų���", 5);
		if(flag){
			PubHandle.select(driver, By.id("algorithmCode"), asinfo.getCopyalgorithm());
			driver.findElement(By.name("submitForm")).click();
			System.out.println("ͬ�ƻ��ڸ��Ʋ����޸ĳɹ�");
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "û�н�������޸�Ҳ", "�޸�Ͷ�Ų���");
		}
	}
	
	public static void editcopycheck(WebDriver driver,AlgorithmStrategyInfo asinfo){
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(asinfo.getCopystrategyname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.xpath("//*[@title='�޸Ĳ���']")).click();
		boolean flag = Check.usualexist(driver, "�޸�Ͷ�Ų���", 5);
		if(flag){
			PubHandle.selcheck(driver, By.id("algorithmCode"), asinfo.getCopyalgorithm());
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "û�н�������޸�Ҳ", "�޸�Ͷ�Ų���");
		}
	}
	
	public static void othereditcopystr(WebDriver driver,AlgorithmStrategyInfo asinfo){
		driver.findElement(By.linkText(asinfo.getOrname())).click();
		pubseatch(driver, asinfo.getPlname());
		driver.findElement(By.linkText(asinfo.getPlname())).click();
		pubseatch(driver, asinfo.getCopystrategyname());
		driver.findElement(By.xpath("//*[@title='�޸Ĳ���']")).click();
		boolean flag = Check.usualexist(driver, "�޸�Ͷ�Ų���", 5);
		if(flag){
			PubHandle.select(driver, By.id("algorithmCode"), asinfo.getOthercopyalgorithm());
			driver.findElement(By.name("submitForm")).click();
			System.out.println("��ƻ����Ʋ����޸ĳɹ�");
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "û�н�������޸�Ҳ", "�޸�Ͷ�Ų���");
		}
	}
	public static void othereditcopycheck(WebDriver driver,AlgorithmStrategyInfo asinfo){
		pubseatch(driver, asinfo.getCopystrategyname());
		driver.findElement(By.xpath("//*[@title='�޸Ĳ���']")).click();
		boolean flag = Check.usualexist(driver, "�޸�Ͷ�Ų���", 5);
		if(flag){
			PubHandle.selcheck(driver, By.id("algorithmCode"), asinfo.getOthercopyalgorithm());
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "û�н�������޸�Ҳ", "�޸�Ͷ�Ų���");
		}
	}
	
	
	public static void pubcheck(WebDriver driver,By by,String checkelemet){
		String text = driver.findElement(by).getText();
		if(!text.equals(checkelemet)){
			ScreenshotandAssert.screenandasserttext(driver, checkelemet+"���ʧ��", checkelemet, by);
		}else{
			System.out.println(checkelemet+"���pass");
		}
	}
	public static void pubseatch(WebDriver driver,String searchname){
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(searchname);
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
	}
}
