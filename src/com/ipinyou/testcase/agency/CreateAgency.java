package com.ipinyou.testcase.agency;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.agency.AgencyInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.webpage.agency.AgencyPage;

public class CreateAgency {
	PubHandle p = new PubHandle();
	WebDriver driver;
	AgencyInfo ainfo = new AgencyInfo();
    @Parameters({"agencyloginname","agencypassword"})
  @Test
  public void login(String agencyloginname,String agencypassword) {
    	LoginInfo login = new LoginInfo(agencyloginname,agencypassword);
    	p.login(login, driver);
  }
    
    @Test
    public void backmannage(){
    	AgencyPage.backmannage(driver);
    }
  @Parameters({"agencyname","systemid","amount","servicerate","agencyemail"})
    @Test
    public void createagency(String agencyname,String systemid,String amount,String servicerate,String agencyemail){
    	ainfo.setAgencyname(agencyname);
    	ainfo.setSystemid(systemid);
    	ainfo.setAmount(amount);
    	ainfo.setServicerate(servicerate);
    	ainfo.setAgencyemail(agencyemail);
    	AgencyPage.createagecy(driver, ainfo);
    }
  
  @Test
  public void check() throws InterruptedException{
	  AgencyPage.check(driver);
	  AgencyPage.datadcheck(driver, ainfo);
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
