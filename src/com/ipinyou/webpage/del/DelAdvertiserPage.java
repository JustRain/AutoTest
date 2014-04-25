package com.ipinyou.webpage.del;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.ipinyou.entity.AdvertiserInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.DBService;
import com.ipinyou.pub.ScreenshotandAssert;

public class DelAdvertiserPage {
    public  WebDriver driver;
	static Check c = new Check();
	public static void create(WebDriver driver,AdvertiserInfo adinfo,int a)throws NoSuchElementException{
		
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
		driver.findElement(By.id("advertiser.type0")).click();
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
		Select aptitudelicense = new Select(driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[14]/td[2]/select")));
		aptitudelicense.selectByValue("License");
		WebElement chooselicense = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[14]/td[2]/input"));
		chooselicense.sendKeys(adinfo.path);
		addaptitude.click();
		//上传法人代表身份证
		Select aptitudelegalid = new Select(driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[15]/td[2]/select")));
		aptitudelegalid.selectByValue("LegalId");
		WebElement chooselegalid = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[15]/td[2]/input"));
		chooselegalid.sendKeys(adinfo.path);
		
		addaptitude.click();
		//上传产品资质
		Select aptitudeproduct = new Select(driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[16]/td[2]/select")));
		aptitudeproduct.selectByValue("Product");
		WebElement chooseproduct = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[16]/td[2]/input"));
		chooseproduct.sendKeys(adinfo.path);
		
		addaptitude.click();
        //上传其他资质
		Select aptitudeother = new Select(driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[17]/td[2]/select")));
		aptitudeother.selectByValue("Other");
		WebElement chooseother = driver.findElement(By.xpath("//*[@id='advertiser_form']/div[1]/div[2]/table/tbody/tr[17]/td[2]/input"));
		chooseother.sendKeys(adinfo.path);
		
		
		WebElement submit =  driver.findElement(By.name("submitForm"));
		submit.click();	    
	}
	
	public static boolean check(WebDriver driver,String element){
		boolean flag = Check.usualexist(driver, element, 2);
		if(flag){
			return true;
		}else{
			return false;
		}
	}
	public static void contentcheck(WebDriver driver,AdvertiserInfo adinfo) throws InterruptedException{
		driver.findElement(By.id("appendedInputButton")).sendKeys(adinfo.adname);
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/table/tbody/tr/td[1]/span/a")).click();
		
		boolean advnameflag = Check.elementvalue(driver, By.id("advertiser.name"), 5, adinfo.adname, "value");
		if(!advnameflag){
			ScreenshotandAssert.screenandassertvalue(driver,  "广告主名称不对",  adinfo.adname, By.id("advertiser.name"), "value");
		}
		
		boolean comname = Check.elementvalue(driver, By.id("advertiser.registerName"), 5, adinfo.registername, "value");
		if(!comname){
           ScreenshotandAssert.screenandassertvalue(driver, "注册名不对", adinfo.registername,  By.id("advertiser.registerName"), "value");
		}
		boolean rateflag = Check.elementvalue(driver, By.id("advertiser.serviceFeeRate"), 5, adinfo.servicefeerate, "value");
		if(!rateflag){
			ScreenshotandAssert.screenandassertvalue(driver, "服务费率不对", adinfo.servicefeerate, By.id("advertiser.serviceFeeRate"), "value");
		}
		boolean urlflag = Check.elementvalue(driver, By.id("advertiser.website"), 5, adinfo.advertiserwebsite, "value");
		if(!urlflag){
			ScreenshotandAssert.screenandassertvalue(driver, "网址不对", adinfo.advertiserwebsite,  By.id("advertiser.website"), "value");
		}
		boolean phoneflag = Check.elementvalue(driver, By.id("advertiser.cellphone"), 5, adinfo.cellphone,"value");
		if(!phoneflag){
			ScreenshotandAssert.screenandassertvalue(driver, "电话不对", adinfo.cellphone,  By.id("advertiser.cellphone"), "value");
		}
		boolean contactflag = Check.elementvalue(driver, By.id("advertiser.contactName"), 5, adinfo.contactname, "value");
		if(!contactflag){
			ScreenshotandAssert.screenandassertvalue(driver, "联系人不对", adinfo.contactname, By.id("advertiser.contactName"), "value");
		}
		boolean emailflag = Check.elementvalue(driver, By.id("advertiser.email"), 5, adinfo.email, "value");
		if(!emailflag){
			ScreenshotandAssert.screenandassertvalue(driver, "邮件不对", adinfo.email,  By.id("advertiser.email"), "value");
		}
	}
	
//	执行删除
	public static void delAdvertiser(WebDriver driver,AdvertiserInfo adinfo) throws InterruptedException{
		driver.findElement(By.id("adAdminMenu")).click();
		driver.findElement(By.id("appendedInputButton")).clear();
		driver.findElement(By.id("appendedInputButton")).sendKeys(adinfo.getDeladname());
		driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		boolean flag = Check.elementexist(driver, By.linkText(adinfo.getDeladname()), 5, adinfo.getDeladname());
		if(flag){
			driver.findElement(By.className("dele-icon")).click();
			driver.findElement(By.className("confirm")).click();
		}else{
			ScreenshotandAssert.screenandasserttext(driver, "没有找到"+adinfo.getDeladname() , adinfo.getDeladname(), By.linkText(adinfo.getDeladname()));
		}
	}
	//数据库检查
	
	public static void datacheck(WebDriver driver,AdvertiserInfo adinfo) throws SQLException{
		String sql = "select removed from advertiser where name = '"+adinfo.getDeladname()+"'";
		DBService.databasecheck(sql,adinfo.getDeladname()+"删除失败");
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
