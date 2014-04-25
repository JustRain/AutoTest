package com.ipinyou.webpage.abnormalwebpage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.javascript.host.Screen;
import com.ipinyou.entity.abnormaltestcase.AbnormalAdvertiserInfo;
import com.ipinyou.entity.abnormaltestcase.AbnormalAdvertiserInfo1;
import com.ipinyou.entity.abnormaltestcase.AbnormalAdvertiserNameInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.ScreenshotandAssert;

public class AbnormalAdvertiserPageName {
	public  WebDriver driver;
	static Check c = new Check();
	public static void create(WebDriver driver,AbnormalAdvertiserNameInfo aainfo,int a)throws NoSuchElementException{
		
		driver.findElement(By.className("resource-create-btn")).click();
		driver.findElement(By.id("advertiser.name")).sendKeys(aainfo.getAdname());
		driver.findElement(By.id("advertiser.registerName")).sendKeys(aainfo.getRegistername());
		if(a==1){
			driver.findElement(By.id("advertiser.serviceFeeRate")).sendKeys(aainfo.getServicefeerate());
		}
//		driver.findElement(By.id("advertiser.selfService1")).click();
//		driver.findElement(By.id("advertiser.selfService0")).click();
		Select industry = new Select(driver.findElement(By.name("advertiser.verticalTagId")));
		industry.selectByVisibleText(aainfo.getIndustrytext());
		WebElement websuite = driver.findElement(By.id("advertiser.website"));
		websuite.clear();
		websuite.sendKeys(aainfo.getAdvertiserwebsite());
		driver.findElement(By.id("advertiser.cellphone")).sendKeys(aainfo.getCellphone());
		driver.findElement(By.id("advertiser.contactName")).sendKeys(aainfo.getContactname());
		driver.findElement(By.id("advertiser.email")).sendKeys(aainfo.getEmail());
		driver.findElement(By.id("advertiser.showLogo1")).click();
		driver.findElement(By.id("advertiser.showLogo0")).click();
		WebElement addaptitude =driver.findElement(By.id("addQualificationFiles"));
		addaptitude.click();
		if(a == 2){
			addaptitude.click();	
		}else{
			;
		}
		//�ϴ�ICP֤
		Select aptitudeicp = new Select(driver.findElement(By.name("types")));
		aptitudeicp.selectByValue("ICP");
		driver.findElement(By.name("qualifications")).sendKeys(aainfo.getPath());
		addaptitude.click();
		/*//�ϴ���ҵӪҵִ��
		Select aptitudelicense = new Select(driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[12]/td[2]/select")));
		aptitudelicense.selectByValue("License");
		WebElement chooselicense = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[12]/td[2]/input"));
//		chooselicense.sendKeys(aainfo.getPath());
		addaptitude.click();
		//�ϴ����˴������֤
		Select aptitudelegalid = new Select(driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[13]/td[2]/select")));
		aptitudelegalid.selectByValue("LegalId");
		WebElement chooselegalid = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[13]/td[2]/input"));
		chooselegalid.sendKeys(aainfo.getPath());
		
		addaptitude.click();
		//�ϴ���Ʒ����
		Select aptitudeproduct = new Select(driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[14]/td[2]/select")));
		aptitudeproduct.selectByValue("Product");
		WebElement chooseproduct = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[14]/td[2]/input"));
		chooseproduct.sendKeys(aainfo.getPath());
		
		addaptitude.click();
        //�ϴ���������
		Select aptitudeother = new Select(driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[15]/td[2]/select")));
		aptitudeother.selectByValue("Other");
		WebElement chooseother = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[15]/td[2]/input"));
		chooseother.sendKeys(aainfo.getPath());*/
		
		
		WebElement submit =  driver.findElement(By.name("submitForm"));
		submit.click();	    
	}
	
	public static void check(WebDriver driver,AbnormalAdvertiserNameInfo aainfo){
		String title = driver.getTitle();
		if(!title.equals("���������")){
			ScreenshotandAssert.screenandasserttitle(driver, "��תҳʧ��", "���������");			
		}else{
			String adname = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[1]/td[3]/span[2]")).getText();
			if(!adname.equals(aainfo.getAdnamereminder())){
				ScreenshotandAssert.screenandasserttext(driver, "�������ʾ����ȷ", aainfo.getAdnamereminder(), By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[1]/td[3]/span[2]"));
			}
			
			
			
			/*String comname =driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[2]/td[3]/span[2]")).getText();
			if(!comname.equals(aainfo.getAdnamereminder())){
				ScreenshotandAssert.screenandasserttext(driver, "��˾����ʾ����ȷ", aainfo.getAdnamereminder(), By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[2]/td[3]/span[2]"));
			}
			String url = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[5]/td[3]/span[2]")).getText();
			String urlex = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[5]/td[3]/span[4]")).getText();
			if(!url.equals(aainfo.getUrlremainder())){
				ScreenshotandAssert.screenandasserttext(driver, "��ַ��ʾ��Ϣ����ȷ", aainfo.getUrlremainder(), By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[5]/td[3]/span[2]"));
			}
			if(!urlex.equals(aainfo.getUrlexample())){
				ScreenshotandAssert.screenandasserttext(driver, "ʾ������ȷ", aainfo.getUrlexample(), By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[5]/td[3]/span[4]"));
			}
			String contact = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[7]/td[3]/span[2]")).getText();
			if(!contact.equals(aainfo.getContactremainder())){
				ScreenshotandAssert.screenandasserttext(driver, "��ϵ����ʾ����ȷ", aainfo.getContactremainder(), By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[7]/td[3]/span[2]"));
			}
			String email = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[8]/td[3]/span[2]")).getText();
			if(!email.equals(aainfo.getEmailremainder())){
				ScreenshotandAssert.screenandasserttext(driver, "�ʼ���ʾ����ȷ", aainfo.getEmailremainder(), By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[8]/td[3]/span[2]"));
			}
			String aptitude = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[10]/td[3]/span[2]")).getText();
			if(!aptitude.equals(aainfo.getAptituderemainder())){
				ScreenshotandAssert.screenandasserttext(driver, "������ʾ����ȷ", aainfo.getAptituderemainder(), By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[10]/td[3]/span[2]"));
			}*/
		}
	}
	public static void editname(WebDriver driver,AbnormalAdvertiserNameInfo aainfo){
		By by = By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[1]/td[3]/span[2]");
		pubdo(driver, aainfo.getAdname1(), aainfo.getAdnamereminder(),by);
		pubdo(driver, aainfo.getAdname2(), "���������",by);
		driver.findElement(By.id("advertiser.name")).clear();
		driver.findElement(By.id("advertiser.name")).sendKeys(aainfo.getAdname3());
		driver.findElement(By.id("advertiser.registerName")).clear();
		driver.findElement(By.id("advertiser.registerName")).sendKeys(aainfo.getRegistername1());
		driver.findElement(By.name("submitForm")).click();
		String adname = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[2]/td[3]/span[2]")).getText();
		if(!adname.equals("2��64���ַ�")){
			ScreenshotandAssert.screenandasserttext(driver, "�������ʾ����ȷ", "2��64���ַ�", By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[1]/td[3]/span[2]"));
		} 
	}
	
	public static void companynamecheck(WebDriver driver,AbnormalAdvertiserNameInfo aainfo){
		By by = By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[2]/td[3]/span[2]");
		pubdo(driver, aainfo.getRegistername2(), aainfo.getAdnamereminder(),by);
		pubdo(driver, aainfo.getRegistername3(), "���������",by);
		
	}
	
	public static void pubdo(WebDriver driver,String args,String argsreminder,By by){
		driver.findElement(By.id("advertiser.name")).clear();
		driver.findElement(By.id("advertiser.name")).sendKeys(args);
		driver.findElement(By.name("submitForm")).click();
		String adname = driver.findElement(by).getText();
		if(!adname.equals(argsreminder) && !"".equals(adname)){
			ScreenshotandAssert.screenandasserttext(driver, "�������ʾ����ȷ", argsreminder, by);
		} 
		else if("".equals(adname)){
			ScreenshotandAssert.screenandasserttext(driver, "�������ʾ����ȷ", "���������", by);
		}
	}
	public static int getuser(WebDriver driver){
		String user = driver.findElement(By.xpath("//*[@id='welcome']/div[2]/a[1]")).getText();
		if(user.equals("���")){
			return 1;
		}else{
			return 2;
		}
	}

}
