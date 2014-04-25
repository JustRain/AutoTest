package com.ipinyou.testcase.ownmedia;


import java.awt.AWTException;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.ownmedia.OwnMediaCreativeInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenShot;
import com.ipinyou.webpage.ownmediawebpage.OwnMediaCreativePage;

public class OwnMediaCreative {
	WebDriver driver;
	ScreenShot s = new ScreenShot();
	PubHandle p  = new PubHandle();
	OwnMediaCreativeInfo ocinfo = new OwnMediaCreativeInfo();
	@Parameters({"loginname","password"})
    @Test()
    public void login(String loginname,String password) throws NoSuchElementException{
		LoginInfo login = new LoginInfo(loginname,password);
	    p.loginswitch(login, driver);
    }
	
	@Parameters({"adname","orname","plname","strategyname"})
	@Test()
	public void search(String adname,String orname,String plname,String strategyname) throws NoSuchElementException{
		
		ocinfo.setAdname(adname);
		ocinfo.setOrname(orname);
		ocinfo.setPlname(plname);
		ocinfo.setStrategyname(strategyname);
		try {
			OwnMediaCreativePage.search(driver, ocinfo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
  @Test
  public void increative() throws NoSuchElementException{
	  OwnMediaCreativePage.increative(driver, "�ϴ�����", "û�н����ϴ�����ҳ");
  }
  @Test
  public void selcreative() throws AWTException,NoSuchElementException, InterruptedException{
	  OwnMediaCreativePage.selcreative(driver, "�����ϴ����⣬�ɹ�1��ʧ��0");
  }
  @Parameters({"arriveadress","ownmediatheme"})
  @Test
  public void completecreative(String arriveadress,String ownmediatheme){
	  ocinfo.setArriveadress(arriveadress);
	  ocinfo.setOwnmediatheme(ownmediatheme);
	  OwnMediaCreativePage.completecreative(driver,ocinfo);
	  try {
		OwnMediaCreativePage.check(driver, "�������ⲻ��ȷ", "�����ַ����ȷ",ocinfo);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
  }
  @Parameters({"browser","ownpath"})
  @BeforeTest
  public void beforeTest(String browser,String ownpath) {
	  driver = p.before(browser, driver);
	  PubHandle.setClipboardData(ownpath);
  }

  @AfterTest
  public void afterTest() {
	  p.logout(driver);
	  driver.quit();
}
}
