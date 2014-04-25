package com.ipinyou.testcase.selfadvertiser;


import java.util.NoSuchElementException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.selfadvertiser.StrategyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.pub.ScreenshotandAssert;
import com.ipinyou.webpage.selfadvertiserpage.StrategyPage;

@Test
public class Strategy {
	
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	StrategyInfo sinfo = new StrategyInfo();
	Check c = new Check();
	@Parameters({"loginname","password"})
    public void login(String loginname,String password) throws NoSuchElementException{
		  LoginInfo login = new LoginInfo(loginname,password);
	      p.loginswitcho(login, driver);
    }
	
	@Parameters({"adname","orname","plname"})
	public void search(String adname,String orname,String plname) throws NoSuchElementException{
		
		sinfo.setAdname(adname);
		sinfo.setOrname(orname);
		sinfo.setPlname(plname);
		StrategyPage.search(driver, sinfo);
	}
	
	public void instrategy() throws NoSuchElementException{
		boolean flag = StrategyPage.instrategy(driver, "����Ͷ�Ų���");
		if(flag){
			;
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, "û����ת������Ͷ�Ų���ҳ", "����Ͷ�Ų���");
		}
	}
	
  @Parameters({"strategyname","totlebudget","dailybudget","imptotallimit","clicktotallimit","imdailylimit","clickdailylimit","indvdlimitimplimit","indvdlimitclicklimit"
	  ,"pyidcategories","pyid","blacklists","price","cpmprice","dailyimps","scriptcode","remarkcode","cpcprice","blackdefinedname","whitedefinedname","whitelists","retargetingcode1","conversioncode1","retargetingcode2","conversioncode2"})
  public void createstrategy(String strategyname,String totlebudget,String dailybudget,String imptotallimit,String clicktotallimit,
		  String imdailylimit,String clickdailylimit,String indvdlimitimplimit,String indvdlimitclicklimit,String pyidcategories,String pyid
		  ,String blacklists,String price,String cpmprice,String dailyimps,String scriptcode,String remarkcode,String cpcprice,String blackdefinedname,String whitedefinedname,String whitelists,
		  String retargetingcode1,String conversioncode1,String retargetingcode2,String conversioncode2)throws InterruptedException,NoSuchElementException,AssertionError,ElementNotVisibleException {
	  
	  sinfo.setStrategyname(strategyname);
	  sinfo.setTotlebudget(totlebudget);
	  sinfo.setDailybudget(dailybudget);
	  sinfo.setImptotallimit(imptotallimit);
	  sinfo.setClicktotallimit(clicktotallimit);
	  sinfo.setImdailylimit(imdailylimit);
	  sinfo.setClickdailylimit(clickdailylimit);
	  sinfo.setIndvdlimitimplimit(indvdlimitimplimit);
	  sinfo.setIndvdlimitclicklimit(indvdlimitclicklimit);
	  sinfo.setPyidcategories(pyidcategories);
	  sinfo.setPyid(pyid);
	  sinfo.setBlacklists(blacklists);
	  sinfo.setPrice(cpmprice);
	  sinfo.setCpmprice(cpmprice);
	  sinfo.setDailyimps(dailyimps);
	  sinfo.setScriptcode(scriptcode);
	  sinfo.setRemarkcode(remarkcode);  
	  sinfo.setCpcprice(cpcprice);
	  sinfo.setBlackdefinedname(blackdefinedname);
	  sinfo.setWhitedefinedname(whitedefinedname);
	  sinfo.setWhitelists(whitelists);
	  sinfo.setRetargetingcode1(retargetingcode1);
	  sinfo.setRetargetingcode2(retargetingcode2);
	  sinfo.setConversioncode1(conversioncode1);
	  sinfo.setConversioncode2(conversioncode2);
	  
	  StrategyPage.createstrategy(driver, sinfo);
	  
	  boolean flag = StrategyPage.submit(driver, "�ϴ�����");
	  if(flag){
		  ;
	  }else{
		  ScreenshotandAssert.screenandasserttitle(driver, "��������ʧ��", "�ϴ�����");
	  }
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
