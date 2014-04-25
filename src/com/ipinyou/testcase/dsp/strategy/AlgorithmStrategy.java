package com.ipinyou.testcase.dsp.strategy;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.strategy.AlgorithmStrategyInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.webpage.strategy.AlgorithmStrategyPage;

public class AlgorithmStrategy {
	WebDriver driver;
	PubHandle p  = new PubHandle();
	AlgorithmStrategyInfo asinfo = new AlgorithmStrategyInfo();
  @Parameters({"loginname","password"})
  @Test
  public void login(String loginname,String password){
	  LoginInfo login = new LoginInfo(loginname,password);
	  p.loginswitch(login, driver);
    }
  @Parameters({"adname","orname","alorplname"})
  @Test
  public void search(String adname,String orname,String alorplname) throws InterruptedException{
	asinfo.setAdname(adname);
	asinfo.setOrname(orname);
	asinfo.setAlorplname(alorplname);
	AlgorithmStrategyPage.search(driver, asinfo);
  }
  
  @Test
  public void instrategy(){
	  AlgorithmStrategyPage.instrategy(driver);
  }
  @Parameters({"algostrategyname","algorithm","pricegoal","unitprice"})
  @Test
  public void createstrtegy(String algostrategyname,String algorithm,String pricegoal,String unitprice){
	  asinfo.setAlgostrategyname(algostrategyname);
	  asinfo.setAlgorithm(algorithm);
	  asinfo.setPricegoal(pricegoal);
	  asinfo.setUnitprice(unitprice);
	  AlgorithmStrategyPage.createstrategy(driver, asinfo);
  }
  @Test
  public void firstcheck(){
	  AlgorithmStrategyPage.firstcheck(driver, asinfo);
  }
  @Parameters({"editalgorithm","editpricegoal","editunitprice","cpcunit"})
  @Test
  public void firstedit(String editalgorithm,String editpricegoal,String editunitprice,String cpcunit){
	  asinfo.setEditalgorithm(editalgorithm);
	  asinfo.setEditpricegoal(editpricegoal);
	  asinfo.setEditunitprice(editunitprice);
	  asinfo.setCpcunit(cpcunit);
	  AlgorithmStrategyPage.firsteditstr(driver, asinfo);
  }
  
  @Test
  public void firsteditcheck() throws InterruptedException{
	  AlgorithmStrategyPage.firsteditcheck(driver, asinfo);
  }
  @Parameters({"sechighprice","secalgorithm","secunitprice","secunitpricevalue"})
  @Test
  public void secondeditstr(String sechighprice,String secalgorithm,String secunitprice,String secunitpricevalue){
	  asinfo.setSechighprice(sechighprice);
	  asinfo.setSecalgorithm(secalgorithm);
	  asinfo.setSecunitprice(secunitprice);
	  asinfo.setSecunitpricevalue(secunitpricevalue);
	  AlgorithmStrategyPage.secondeditstr(driver, asinfo);
  }
  @Test
  public void secondeditcheck() throws InterruptedException{
	  AlgorithmStrategyPage.seceditcheck(driver, asinfo);
  }
  
  @Test
  public void openstatus(){
	  AlgorithmStrategyPage.openstatus(driver, asinfo);
  }
  @Test
  public void openedit(){
	  AlgorithmStrategyPage.openedit(driver, asinfo);
  }
  @Test
  public void secondcheck(){
	  AlgorithmStrategyPage.secondcheck(driver);
  }
  
  @Parameters({"copystrategyname","plname"})
  @Test
  public void copystr(String copystrategyname,String plname) throws InterruptedException{
	  asinfo.setCopystrategyname(copystrategyname);
	  asinfo.setPlname(plname);
	  AlgorithmStrategyPage.copystr(driver, asinfo);
  }
  @Parameters({"copyalgorithm"})
  @Test
  public void editcopystr(String copyalgorithm){
	  asinfo.setCopyalgorithm(copyalgorithm);
	  AlgorithmStrategyPage.editcopy(driver, asinfo);
  }
  @Parameters({"copystrategyname"})
  @Test
  public void editcopycheck(String copystrategyname){
	  asinfo.setCopystrategyname(copystrategyname);
	  AlgorithmStrategyPage.editcopycheck(driver, asinfo);
  }
  
  @Parameters({"othercopyalgorithm"})
  @Test
  public void othereditcopystr(String othercopyalgorithm){
	  asinfo.setOthercopyalgorithm(othercopyalgorithm);
	  AlgorithmStrategyPage.othereditcopystr(driver, asinfo);
  }
  @Test
  public void othereditcopycheck(){
	  AlgorithmStrategyPage.othereditcopycheck(driver, asinfo);
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
