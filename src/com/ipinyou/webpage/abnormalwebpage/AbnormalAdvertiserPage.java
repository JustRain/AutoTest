package com.ipinyou.webpage.abnormalwebpage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ipinyou.entity.abnormaltestcase.AbnormalAdvertiserInfo;
import com.ipinyou.entity.abnormaltestcase.AbnormalAdvertiserInfo1;
import com.ipinyou.entity.abnormaltestcase.AbnormalAdvertiserNameInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.ScreenshotandAssert;

public class AbnormalAdvertiserPage {

	public  WebDriver driver;
	static Check c = new Check();
	public static void create(WebDriver driver,AbnormalAdvertiserInfo adinfo,int a)throws NoSuchElementException{
		
		driver.findElement(By.className("resource-create-btn")).click();
		driver.findElement(By.id("advertiser.name")).sendKeys(adinfo.adname);
		driver.findElement(By.id("advertiser.registerName")).sendKeys(adinfo.registername);
		if(a==1){
			driver.findElement(By.id("advertiser.serviceFeeRate")).sendKeys(adinfo.servicefeerate);
		}
//		driver.findElement(By.id("advertiser.selfService1")).click();
//		driver.findElement(By.id("advertiser.selfService0")).click();
		Select industry = new Select(driver.findElement(By.name("advertiser.verticalTagId")));
		industry.selectByVisibleText(adinfo.industrytext);
		WebElement websuite = driver.findElement(By.id("advertiser.website"));
		websuite.clear();
		websuite.sendKeys(adinfo.advertiserwebsite);
		driver.findElement(By.id("advertiser.cellphone")).sendKeys(adinfo.cellphone);
		driver.findElement(By.id("advertiser.contactName")).sendKeys(adinfo.contactname);
		driver.findElement(By.id("advertiser.email")).sendKeys(adinfo.email);
		driver.findElement(By.id("advertiser.showLogo1")).click();
		driver.findElement(By.id("advertiser.showLogo0")).click();
		WebElement addaptitude =driver.findElement(By.id("addQualificationFiles"));
		addaptitude.click();
		if(a == 2){
			addaptitude.click();	
		}else{
			;
		}
		//上传ICP证
		Select aptitudeicp = new Select(driver.findElement(By.name("types")));
		aptitudeicp.selectByValue("ICP");
		driver.findElement(By.name("qualifications")).sendKeys(adinfo.path);
		addaptitude.click();
		//上传企业营业执照
		Select aptitudelicense = new Select(driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[12]/td[2]/select")));
		aptitudelicense.selectByValue("License");
		WebElement chooselicense = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[12]/td[2]/input"));
		chooselicense.sendKeys(adinfo.path);
		addaptitude.click();
		//上传法人代表身份证
		Select aptitudelegalid = new Select(driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[13]/td[2]/select")));
		aptitudelegalid.selectByValue("LegalId");
		WebElement chooselegalid = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[13]/td[2]/input"));
		chooselegalid.sendKeys(adinfo.path);
		
		addaptitude.click();
		//上传产品资质
		Select aptitudeproduct = new Select(driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[14]/td[2]/select")));
		aptitudeproduct.selectByValue("Product");
		WebElement chooseproduct = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[14]/td[2]/input"));
		chooseproduct.sendKeys(adinfo.path);
		
		addaptitude.click();
        //上传其他资质
		Select aptitudeother = new Select(driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[15]/td[2]/select")));
		aptitudeother.selectByValue("Other");
		WebElement chooseother = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[15]/td[2]/input"));
		chooseother.sendKeys(adinfo.path);
		
		
		WebElement submit =  driver.findElement(By.name("submitForm"));
		submit.click();	    
	}
	
	public static void check(WebDriver driver,AbnormalAdvertiserInfo1 aainfo){
		String title = driver.getTitle();
		if(!title.equals("创建广告主")){
			ScreenshotandAssert.screenandasserttitle(driver, "跳转页失败", "创建广告主");			
		}else{
			String adname = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[1]/td[3]/span[2]")).getText();
			if(!adname.equals(aainfo.getAdnamereminder())){
				ScreenshotandAssert.screenandasserttext(driver, "广告主提示不正确", aainfo.getAdnamereminder(), By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[1]/td[3]/span[2]"));
			}
			String comname =driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[2]/td[3]/span[2]")).getText();
			if(!comname.equals(aainfo.getAdnamereminder())){
				ScreenshotandAssert.screenandasserttext(driver, "公司名提示不正确", aainfo.getAdnamereminder(), By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[2]/td[3]/span[2]"));
			}
			String url = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[5]/td[3]/span[2]")).getText();
			String urlex = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[5]/td[3]/span[4]")).getText();
			if(!url.equals(aainfo.getUrlremainder())){
				ScreenshotandAssert.screenandasserttext(driver, "网址提示信息不正确", aainfo.getUrlremainder(), By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[5]/td[3]/span[2]"));
			}
			if(!urlex.equals(aainfo.getUrlexample())){
				ScreenshotandAssert.screenandasserttext(driver, "示例不正确", aainfo.getUrlexample(), By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[5]/td[3]/span[4]"));
			}
			String contact = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[7]/td[3]/span[2]")).getText();
			if(!contact.equals(aainfo.getContactremainder())){
				ScreenshotandAssert.screenandasserttext(driver, "联系人提示不正确", aainfo.getContactremainder(), By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[7]/td[3]/span[2]"));
			}
			String email = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[8]/td[3]/span[2]")).getText();
			if(!email.equals(aainfo.getEmailremainder())){
				ScreenshotandAssert.screenandasserttext(driver, "邮件提示不正确", aainfo.getEmailremainder(), By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[8]/td[3]/span[2]"));
			}
			String aptitude = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[10]/td[3]/span[2]")).getText();
			if(!aptitude.equals(aainfo.getAptituderemainder())){
				ScreenshotandAssert.screenandasserttext(driver, "资质提示不正确", aainfo.getAptituderemainder(), By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[10]/td[3]/span[2]"));
			}
		}
	}
	public static int getuser(WebDriver driver){
		String user = driver.findElement(By.xpath("//*[@id='welcome']/div[2]/a[1]")).getText();
		if(user.equals("徐冲")){
			return 1;
		}else{
			return 2;
		}
	}

}
