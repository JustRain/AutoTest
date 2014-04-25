package com.ipinyou.testcase.batch.Mobile;


import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.batch.mobile.BatchCopyStrategyMobInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.batch.mobile.BatchCopyStrategyMobPage;

public class BatchCopyStrategyMob {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	BatchCopyStrategyMobInfo bcinfo = new BatchCopyStrategyMobInfo();
	public String mobiletype = null;
	@Parameters({"loginname","password"})
	@Test
    public void login(String loginname,String password) throws NoSuchElementException{
		  LoginInfo login = new LoginInfo(loginname,password);
	      p.loginswitch(login, driver);
  	 }
	
	@Parameters({"adname","orname","plname","appstrname","webstrname"})
	@Test
	public void search(String adname,String orname,String plname,String appstrname,String webstrname) throws NoSuchElementException, InterruptedException{
		bcinfo.setAdname(adname);
		bcinfo.setOrname(orname);
		bcinfo.setPlname(plname);
		bcinfo.setAppstrname(appstrname);
		bcinfo.setWebstrname(webstrname);
		bcinfo.setMobiletype(mobiletype);
		BatchCopyStrategyMobPage.search(driver, bcinfo);
	}
	@Parameters({"plname"})
	@Test
	public void batchcopy(String plname) throws InterruptedException{
		bcinfo.setPlname(plname);
		BatchCopyStrategyMobPage.batchcopy(driver,bcinfo);
		Thread.sleep(2000);
	
	}
	
	@Parameters({"copystr1","copystr2","positonblack","positionwhite","daatexclude","mobiledevice","apptype","mediumblack","mediumwhite",
		"batchdirected","blackapp","whiteapp","peopleproperty","classfytext","context","areatext","platetext","ostext","batchblack","batchwhite","classifyexclude",
		"conexclude","clicksele","batchappblacklistold","batchappwhitelistold","batchappwhitelist","batchappblacklist","plname1","orname","editreachprice",
		"edithighprice","editpricegoal","editeffectgoal","editrate","editscriptcode","editremark","editblanceputin","editpreferdeal","editalgorithmCode"})
	@Test
	public void check(String copystr1,String copystr2,String positonblack,String positionwhite,String daatexclude,String mobiledevice,String apptype,String mediumblack,String mediumwhite,
			String batchdirected,String blackapp,String whiteapp,String peopleproperty,String classfytext,String context,String areatext,String platetext,String ostext,String batchblack,String batchwhite
			,String classifyexclude,String conexclude,String clicksele,String batchappblacklistold,String batchappwhitelistold,String batchappwhitelist,String batchappblacklist,String plname1,
			String orname,String editreachprice,String edithighprice,String editpricegoal,String editeffectgoal,String editrate,String editscriptcode
  			,String editremark,String editblanceputin,String editpreferdeal,String editalgorithmCode) throws InterruptedException{
		
		bcinfo.setCopystr1(copystr1);
		bcinfo.setCopystr2(copystr2);
		bcinfo.setPositionwhite(positionwhite);
		bcinfo.setPositonblack(positonblack);
		bcinfo.setDaatexclude(daatexclude);
		bcinfo.setMobiledevice(mobiledevice);
		bcinfo.setApptype(apptype);
		bcinfo.setMediumblack(mediumblack);
		bcinfo.setMediumwhite(mediumwhite);
		bcinfo.setBatchdirected(batchdirected);
		bcinfo.setBlackapp(blackapp);
		bcinfo.setWhiteapp(whiteapp);
		bcinfo.setPeopleproperty(peopleproperty);
		bcinfo.setClassfytext(classfytext);
		bcinfo.setContext(context);
		bcinfo.setAreatext(areatext);
		bcinfo.setPlatetext(platetext);
		bcinfo.setOstext(ostext);
		bcinfo.setBatchblack(batchblack);
		bcinfo.setBatchwhite(batchwhite);
		bcinfo.setClassifyexclude(classifyexclude);
		bcinfo.setConexclude(conexclude);
		bcinfo.setClicksele(clicksele);
		bcinfo.setBatchappblacklist(batchappblacklist);
		bcinfo.setBatchappblacklistold(batchappblacklistold);
		bcinfo.setBatchappwhitelist(batchappwhitelist);
		bcinfo.setBatchappwhitelistold(batchappwhitelistold);
		bcinfo.setPlname1(plname1);
		bcinfo.setOrname(orname);
		bcinfo.setEditreachprice(editreachprice);
		bcinfo.setEdithighprice(edithighprice);
		bcinfo.setEditpricegoal(editpricegoal);
		bcinfo.setEditeffectgoal(editeffectgoal);
		bcinfo.setEditrate(editrate);
		bcinfo.setEditscriptcode(editscriptcode);
		bcinfo.setEditremark(editremark);
		bcinfo.setEditblanceputin(editblanceputin);
		bcinfo.setEditpreferdeal(editpreferdeal);
		bcinfo.setEditalgorithmCode(editalgorithmCode);
		
		BatchCopyStrategyMobPage.check(driver, bcinfo);
		BatchCopyStrategyMobPage.outcheck(driver, bcinfo);
	}
	@Parameters({"browser"})
  @BeforeTest
  public void beforeTest(String browser) {
		driver = p.before(browser, driver);
	}

  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
  }

}
