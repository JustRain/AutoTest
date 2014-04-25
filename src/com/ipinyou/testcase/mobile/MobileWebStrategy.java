package com.ipinyou.testcase.mobile;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ipinyou.entity.LoginInfo;
import com.ipinyou.entity.mobile.MobileAppStrategyInfo;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.webpage.mobile.MobileStrategyPage;

public class MobileWebStrategy {
	WebDriver driver;
	MobileAppStrategyInfo masinfo = new MobileAppStrategyInfo();
	PubHandle p = new PubHandle();
	public String mobiletype = "web";
	@Parameters({"loginname","password"})
	 @Test
	  public void login(String loginname,String password) {
		  LoginInfo login = new LoginInfo(loginname, password);
		  p.loginswitch(login, driver);
	  }
	 
	  @Parameters({"adname","orname","plname"})
	  @Test
		public void search(String adname,String orname,String plname) throws NoSuchElementException{
			
		  masinfo.setAdname(adname);
		  masinfo.setOrname(orname);
		  masinfo.setPlname(plname);
			try {
				MobileStrategyPage.search(driver, masinfo, "策略列表","搜索失败");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		@Test
		public void instrategy() throws NoSuchElementException{
			MobileStrategyPage.instrategy(driver, "创建投放策略", "没有进入创建策略页");
		}
		@Parameters({"mobwebstrategyname","level","tbudget","dbudget","ctclick","impdlimit","cdlimit","indlimit","indvdcllimit","imptlimit","blackname","whitename","blacklist","whitelist","posblack","poswhite","directed","retargetingcode1","retargetingcode2","conversioncode1","conversioncode2","preferdeal","highprice","arithmetic","pricegoal","unitprice","effectgoal","effectrate","scriptcode","remark"})
		@Test
		public void mobilestrategy(String mobwebstrategyname,String level,String tbudget,String dbudget,String ctclick,String impdlimit,String cdlimit,String indlimit,
				String indvdcllimit,String imptlimit,String blackname,String whitename,String blacklist,String whitelist,String posblack,String poswhite
				,String directed,String retargetingcode1,String retargetingcode2,String conversioncode1,String conversioncode2,String preferdeal,String highprice,
				String arithmetic,String pricegoal,String unitprice,String effectgoal,String effectrate,String scriptcode,String remark){
			masinfo.setMobwebstrategyname(mobwebstrategyname);
			masinfo.setLevel(level);
			masinfo.setTbudget(tbudget);
			masinfo.setDbudget(dbudget);
			masinfo.setCtclick(ctclick);
			masinfo.setImpdlimit(impdlimit);
			masinfo.setCdlimit(cdlimit);
			masinfo.setIndlimit(indlimit);
			masinfo.setIndvdcllimit(indvdcllimit);
			masinfo.setImptlimit(imptlimit);
			masinfo.setBlackName(blackname);
			masinfo.setWhiteName(whitename);
			masinfo.setBlacklist(blacklist);
			masinfo.setWhitelist(whitelist);
			masinfo.setPosblack(posblack);
			masinfo.setPoswhite(poswhite);
			masinfo.setMobiletype(mobiletype);
			masinfo.setRetargetingcode1(retargetingcode1);
			masinfo.setRetargetingcode2(retargetingcode2);
			masinfo.setConversioncode1(conversioncode1);
			masinfo.setConversioncode2(conversioncode2);
			masinfo.setPreferdeal(preferdeal);
			masinfo.setHighprice(highprice);
			masinfo.setArithmetic(arithmetic);
			masinfo.setPricegoal(pricegoal);
			masinfo.setUnitprice(unitprice);
			masinfo.setEffectgoal(effectgoal);
			masinfo.setEffectrate(effectrate);
			masinfo.setScriptcode(scriptcode);
			masinfo.setRemark(remark);
			try {
				MobileStrategyPage.mobilestrategy(driver, masinfo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		@Parameters({"daatselect","attention","daatexclude","retargetclassify","retargetconversion","retargetexclude","conversionexclude","clickcookie","directed","area","time","plate","os",
			"device","apptype","mobilenet","uncheat","putin","flow","preferdeal","highprice","arithmetic","pricegoal","unitprice","effectgoal","effectrate","scriptcode","remark","plname",
			"checkunitprive","posblack","poswhite","blacklistcount","whitelistcount"})
		@Test
		public void check(String daatselect,String attention,String daatexclude,String retargetclassify,String retargetconversion,String retargetexclude,String conversionexclude,String clickcookie,
				String directed,String area,String time,String plate,String os,String device,String apptype,String mobilenet,
				String uncheat,String putin,String flow,String preferdeal,String highprice,String arithmetic,
				String pricegoal,String unitprice,String effectgoal,String effectrate,String scriptcode,String remark,String plname,String checkunitprive,
				String posblack,String poswhite,String blacklistcount,String whitelistcount) throws InterruptedException{
			masinfo.setPlname(plname);
			masinfo.setDaatselect(daatselect);
			masinfo.setAttention(attention);
			masinfo.setDaatexclude(daatexclude);
			masinfo.setRetargetclassify(retargetclassify);
			masinfo.setRetargetconversion(retargetconversion);
			masinfo.setRetargetexclude(retargetexclude);
			masinfo.setConversionexclude(conversionexclude);
			masinfo.setClickcookie(clickcookie);
			masinfo.setDirected(directed);
			masinfo.setArea(area);
			masinfo.setTime(time);
			masinfo.setPlate(plate);
			masinfo.setOs(os);
			masinfo.setDevice(device);
			masinfo.setApptype(apptype);
			masinfo.setMobilenet(mobilenet);
			masinfo.setUncheat(uncheat);
			masinfo.setPutin(putin);
			masinfo.setFlow(flow);
			masinfo.setPricegoal(pricegoal);
			masinfo.setHighprice(highprice);
			masinfo.setArithmetic(arithmetic);
			masinfo.setPricegoal(pricegoal);
			masinfo.setUnitprice(unitprice);
			masinfo.setEffectgoal(effectgoal);
			masinfo.setEffectrate(effectrate);
			masinfo.setScriptcode(scriptcode);
			masinfo.setRemark(remark);
			masinfo.setPreferdeal(preferdeal);
			masinfo.setCheckunitprive(checkunitprive);
			masinfo.setBlacklistcount(blacklistcount);
			masinfo.setWhitelistcount(whitelistcount);
			MobileStrategyPage.datacheck(driver, masinfo);
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
