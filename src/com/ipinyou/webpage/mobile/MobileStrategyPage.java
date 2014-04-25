package com.ipinyou.webpage.mobile;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ipinyou.entity.MobileStrategyInfo;
import com.ipinyou.entity.mobile.MobileAppStrategyInfo;
import com.ipinyou.pub.Check;
import com.ipinyou.pub.PubHandle;
import com.ipinyou.pub.ScreenshotandAssert;

public class MobileStrategyPage {
	public static void search(WebDriver driver,MobileAppStrategyInfo masinfo,String title,String reminder) throws InterruptedException{
		List<String> list = new ArrayList<String>();
		list.add(masinfo.getAdname());
		list.add(masinfo.getOrname());
		list.add(masinfo.getPlname());
		PubHandle.search(driver, list,PubHandle.titlelist());
		boolean flag = Check.usualexist(driver, title,2);
		if(flag){
			;
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, reminder, title);
		}
	}
	public static void instrategy(WebDriver driver,String title,String reminder){
		driver.findElement(By.className("ico-add")).click();
		boolean flag = Check.usualexist(driver, title, 2);
		if(flag){
			;
		}else{
			ScreenshotandAssert.screenandasserttitle(driver, reminder, title);
		}
	}
    public static void mobilestrategy(WebDriver driver,MobileAppStrategyInfo masinfo) throws InterruptedException{
    	if(!"".equals(masinfo.getMobiletype()) && "app".equals(masinfo.getMobiletype())){
        	driver.findElement(By.id("name")).sendKeys(masinfo.getMobappstrategyname());

    	}else if(!"".equals(masinfo.getMobiletype()) && "web".equals(masinfo.getMobiletype())){
        	driver.findElement(By.id("name")).sendKeys(masinfo.getMobwebstrategyname());

    	}
    	driver.findElement(By.id("advertisingMode1")).click();
    	if(masinfo.getMobiletype().equals("app")){
    		driver.findElement(By.id("mobileAttr.mobileType0")).click();
    	}else if(masinfo.getMobiletype().equals("web")){
    		driver.findElement(By.id("mobileAttr.mobileType1")).click();
    	}
    	PubHandle.select(driver, By.id("priority"), masinfo.getLevel());
    	driver.findElement(By.id("limit.totalBudget")).sendKeys(masinfo.getTbudget());
    	driver.findElement(By.id("limit.dailyBudget")).sendKeys(masinfo.getDbudget());
    	driver.findElement(By.id("limit.impTotalLimit")).sendKeys(masinfo.getImptlimit());
    	driver.findElement(By.id("limit.clickTotalLimit")).sendKeys(masinfo.getCtclick());
    	driver.findElement(By.id("limit.impDailyLimit")).sendKeys(masinfo.getImpdlimit());
    	driver.findElement(By.id("limit.clickDailyLimit")).sendKeys(masinfo.getCdlimit());
    	driver.findElement(By.id("indvdLimitimpLimit")).sendKeys(masinfo.getIndlimit());
    	driver.findElement(By.id("indvdLimitclickLimit")).sendKeys(masinfo.getIndvdcllimit());
    	
    	//�߼�����
    	//DAAT��Ⱥ����
    	driver.findElement(By.id("toggleAdvancedSetting")).click();
    	driver.findElement(By.id("btn-audiences")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"), "src", "customize#audiences");
    	boolean flag = Check.elementexist(driver, By.id("node_content_10108"), 10, "�˿�����");
    	if(flag){
    		driver.findElement(By.id("trigger_10109")).click();
    		boolean flag1 = Check.elementexist(driver, By.id("node_val_10110"), 10, "����");
    		if(flag1){
    			PubHandle.drag(driver, By.id("node_val_10110"), By.cssSelector(".ui-droppable"));
    			driver.findElement(By.id("confirm")).click();
    		}else{
    			ScreenshotandAssert.screenandasserttext(driver, "û�д��Ա��ǩ", "����", By.id("node_val_10110"));
    		}
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "û����ת����Ⱥ�趨frame", "�˿�����", By.id("node_content_10108"));
    	}
    	driver.switchTo().defaultContent();
    	//������Ⱥ��ע��
    	PubHandle.select(driver, By.id("audienceWeight"), "��");
    	
    	//DAAT��Ⱥ�ų�����
    	driver.findElement(By.id("btn-audiencesExclude")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"), "src", "audiencesExclude");
    	boolean excludeflag = Check.elementexist(driver, By.id("node_content_10108"), 10, "�˿�����");
    	if(excludeflag){
    		boolean valflag = Check.elementexist(driver, By.id("node_val_10144"), 10, "�ؼ������׶�");
    		if(valflag){
    			driver.findElement(By.id("trigger_10144")).click();
    			boolean stuflag = Check.elementexist(driver, By.id("node_val_10145"), 10, "ѧ��ʱ��");
    			if(stuflag){
    				PubHandle.drag(driver, By.id("node_val_10145"), By.cssSelector(".ui-droppable"));
        			driver.findElement(By.id("confirm")).click();
    			}else{
    				ScreenshotandAssert.screenandasserttext(driver, "û���ҵ�ѧ��ʱ��", "ѧ��ʱ��", By.id("node_val_10145"));
    			}
    		}else{
    			ScreenshotandAssert.screenandasserttext(driver, "û���ҵ��ؼ������׶�", "�ؼ������׶�", By.id("node_val_10144"));
    		}
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "������ת��DAAT��Ⱥ�ų�iframe", "�˿�����", By.id("node_content_10108"));
    	}
    	driver.switchTo().defaultContent();
    	
        driver.findElement(By.id("btn-visitorRetarget")).click();
  	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "retargetConversions");
  	    boolean vistorflag = Check.elementexist(driver, By.xpath("//*[@id='retargetRange']"),4 , "�ÿ�Ͷ���ڣ�");
  	    if(vistorflag){
  	    	//driver.findElement(By.id("cat_01")).click();
  	    	driver.findElement(By.xpath("//*[@title='"+masinfo.getRetargetingcode2()+"']")).click();
  		    driver.findElement(By.xpath("//*[@title='"+masinfo.getConversioncode2()+"']")).click(); 
  			driver.findElement(By.id("btn-retargetProductId")).click();
  			driver.findElement(By.id("carouselModeBbAa")).click();
  		    driver.findElement(By.id("categorySameHot")).click();
  			driver.findElement(By.id("btn-retargetProductId")).click();    
  			driver.findElement(By.id("confirm")).click();
  			driver.switchTo().defaultContent();   
//  			boolean checkflag2 = Check.elementexist(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[2]/td[2]"), 2, masinfo.getRetargetclassify());
//  			if(!checkflag2){
//  				ScreenshotandAssert.screenandasserttext(driver, "����ÿ��һز���", masinfo.getRetargetclassify(), By.xpath("//*[@id='retarget-selected']/table/tbody/tr[2]/td[2]"));
//  			}
//  			boolean checkflag3 = Check.elementexist(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[3]/td[2]"), 2, masinfo.getRetargetconversion());
//  			if(!checkflag3){
//  				ScreenshotandAssert.screenandasserttext(driver, "ת��Ŀ���һز���", masinfo.getRetargetconversion(), By.xpath("//*[@id='retarget-selected']/table/tbody/tr[3]/td[2]"));
//  			}
  			
  	    }else{
  	    	ScreenshotandAssert.screenandasserttext(driver, "û����ת���ÿ��һ��趨frame", "�ÿ�Ͷ���ڣ�", By.xpath("//*[@id='retargetRange']"));
  	    }
  		              
  	    //�ÿ��ų�
  	    driver.findElement(By.id("btn-visitorExclude")).click();
  	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "excludeConversions");
  	    boolean excludeflag1 = Check.elementexist(driver, By.xpath("//*[@id='retargetCategoryDiv']/p/label"), 4, "����ÿ�");
  	    if(excludeflag1){
  	    	driver.findElement(By.xpath("//*[@title='"+masinfo.getRetargetingcode1()+"']")).click();
  	 	    driver.findElement(By.xpath("//*[@title='"+masinfo.getConversioncode1()+"']")).click();
  	 	    driver.findElement(By.id("confirm")).click();
  	    }else{
  	    	ScreenshotandAssert.screenandasserttext(driver, "û����ת���ÿ��ų�frame", "����ÿ�", By.xpath("//*[@id='retargetCategoryDiv']/p/label"));
  	    }
   	    driver.switchTo().defaultContent();

  	    //����ÿ��һ�
  	    driver.findElement(By.id("btn-clickCookieType")).click();
  	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "clickCookieType");
  	    flag(driver,By.xpath("//*[@id='clickCookieSetting']/div/table/tbody/tr/td[1]/label"),"δ����","δ��ת������һ�frame");
  	    driver.findElement(By.id("clkcookie_Na")).click();
  	    PubHandle.isenbled(driver, By.id("clkcookie_Na"), "δ���ð�ť������", true);
  	    driver.findElement(By.id("clkcookie_AdvClick")).click();
  	    PubHandle.isenbled(driver, By.id("clkcookie_AdvClick"), "��������������ť������", true);
  	    driver.findElement(By.id("clkcookie_PinyouClick")).click();
  	    PubHandle.isenbled(driver, By.id("clkcookie_PinyouClick"), "�������水ť������", true);
  	    driver.findElement(By.id("confirm")).click();
  	    driver.switchTo().defaultContent();
  	    
  	  //��Ⱥ���ඨ��
  	    driver.findElement(By.id("btn-pyidCategories")).click();
  	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "pyidCategories");
  	    flag(driver,By.xpath("//*[@id='audienceClassifySetting']/table/thead/tr/th[3]"),"����","δ��ת����Ⱥ���ඨ��frame");
  	    driver.findElement(By.id("select_all")).click();
  	    driver.findElement(By.id("confirm")).click();
  	    driver.switchTo().defaultContent();
  	    
  	    //driver.findElement(By.id("pyids")).sendKeys(sinfo.getPyid());
  	    
  	    //�����趨
  	    driver.findElement(By.id("btn-areas")).click();
  	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "customize#areas");
  	    flag(driver,By.xpath("//*[@id='area_1']/div/span[2]"),"����","δ��ת�������趨frame");
  	    driver.findElement(By.id("select_all")).click();
  	    driver.findElement(By.id("confirm")).click();
  	    driver.switchTo().defaultContent();
  	    
  	    //ʱ���趨
  	    driver.findElement(By.id("btn-dayParting")).click();
  	    PubHandle.switchframe(driver, By.className("myiframe"), "src", "customize#dayParting");
  	    flag(driver,By.xpath("/html/body/table/tbody/tr[1]/th[2]"),"����","δ��ת��ʱ���趨frame");
  	    driver.findElement(By.id("selectAll_2")).click();
  	    driver.findElement(By.id("selectAfternoon")).click();
  	    driver.findElement(By.id("confirm")).click();
  	    driver.switchTo().defaultContent();
        
    	
    	//ƽ̨λ���趨
    	driver.findElement(By.id("btn-exchanges")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"), "src", "exchanges;adLocations");
    	boolean exchangeflag = Check.elementexist(driver, By.xpath("//*[@id='platformSetting']/div[1]/p[1]/label"), 10, "�ȸ��ƶ�");
    	if(exchangeflag){
    		driver.findElement(By.id("pltGoogleAmx")).click();
    		driver.findElement(By.id("pltTaobao")).click();
    		driver.findElement(By.id("pltSmaato")).click();
    		driver.findElement(By.id("pltInmobi")).click();
    		driver.findElement(By.id("pltMogo")).click();
    		driver.findElement(By.id("pltYouku")).click();
    		driver.findElement(By.id("confirm")).click();
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "û����ת��ƽ̨λ���趨frame", "�ȸ��ƶ�", By.xpath("//*[@id='platformSetting']/div[1]/p[1]/label"));
    	}
    	driver.switchTo().defaultContent();
    	
    	//����ϵͳ�趨
    	driver.findElement(By.id("btn-osTypes")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"),  "src", "Mobile#osTypes;osVersions");
    	boolean osflag = Check.elementexist(driver, By.xpath("//*[@id='osSetting']/div[1]/p/label"), 10, "��׿");
    	if(osflag){
    		driver.findElement(By.id("android")).click();
    		driver.findElement(By.id("ios")).click();
    	    driver.findElement(By.id("WindowsPhone")).click();
    	    driver.findElement(By.id("OtherOS")).click();
    	    driver.findElement(By.id("confirm")).click();
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "û����ת������ϵͳ�趨frame",  "��׿", By.xpath("//*[@id='osSetting']/div[1]/p/label"));
    	}
    	driver.switchTo().defaultContent();
    	
    	//�ƶ��豸�趨
    	driver.findElement(By.id("btn-mobileAttrmobileSets")).click();
    	PubHandle.switchframe(driver, By.className("myiframe"), "src", "mobileAttrmobileSetBrands");
    	boolean mobflag = Check.elementexist(driver, By.xpath("//*[@id='mobilesetSetting']/div[1]/p/label"), 10, "�ֻ�");
    	if(mobflag){
    		driver.findElement(By.id("cellphone")).click();
    		driver.findElement(By.id("pad")).click();
    		driver.findElement(By.id("OtherSet")).click();
    		driver.findElement(By.id("confirm")).click();
    	}else{
    		ScreenshotandAssert.screenandasserttext(driver, "û�н����ƶ��豸�趨frame", "�ֻ�", By.xpath("//*[@id='mobilesetSetting']/div[1]/p/label"));
    	}
    	driver.switchTo().defaultContent();
    	//APP�����趨
    	if(!"".equals(masinfo.getMobiletype())&&"app".equals(masinfo.getMobiletype())){
    		driver.findElement(By.id("btn-mobileAttrappCategories")).click();
        	PubHandle.switchframe(driver, By.className("myiframe"), "src", "customize#mobileAttrappCategories");
        	boolean appflag = Check.elementexist(driver, By.id("select_all"), 10, "ȫѡ");
        	if(appflag){
        		driver.findElement(By.id("select_all")).click();
        		driver.findElement(By.id("confirm")).click();
        	}else{
        		ScreenshotandAssert.screenandasserttext(driver, "û����ת��app�����趨frame", "ȫѡ", By.xpath("//*[@id='appCatSetting']/div/p/label"));	
        	}
        	driver.switchTo().defaultContent();
    	}
    	
    	//�ƶ����綨��
    	driver.findElement(By.id("mobileAttr.mobileNetworks0")).click();
    	driver.findElement(By.id("mobileAttr.mobileNetworks1")).click();
    	driver.findElement(By.id("mobileAttr.mobileNetworks2")).click();
    	driver.findElement(By.id("mobileAttr.mobileNetworks3")).click();
    	driver.findElement(By.id("mobileAttr.mobileNetworks4")).click();
    	
    	if(""!=masinfo.getMobiletype() && "web".equals(masinfo.getMobiletype())){
    		//����������
        	driver.findElement(By.id("btn-blackWhiteUrlblackUrlIds")).click();
        	PubHandle.switchframe(driver, By.className("myiframe"), "src","blackWhiteUrlcustomBlackUrls");
        	boolean blackflag = Check.elementexist(driver, By.className("fc-gray"), 10, "�����������������ѡ�����еĺ������б�Ҳ�������Ҳ�����ֱ�ӱ༭�������µ�������");
        	if(blackflag){
        		driver.findElement(By.id("set-name")).sendKeys(masinfo.getBlackName());
        		driver.findElement(By.id("set-domains")).sendKeys(masinfo.getBlacklist());
        		driver.findElement(By.id("confirm")).click();
        	}else{
        		ScreenshotandAssert.screenandasserttext(driver, "û�н������������iframe", "�����������������ѡ�����еĺ������б�Ҳ�������Ҳ�����ֱ�ӱ༭�������µ�������",  By.className("fc-gray"));
        	}
        	driver.switchTo().defaultContent();
        	
        	//����������
        	
        	driver.findElement(By.id("btn-blackWhiteUrlwhiteUrlIds")).click();
        	PubHandle.switchframe(driver, By.className("myiframe"), "src", "blackWhiteUrlcustomWhiteUrls");
        	boolean whiteflag = Check.elementexist(driver, By.className("fc-gray"),10, "�����������������ѡ�����еİ������б�Ҳ�������Ҳ�����ֱ�ӱ༭�������µ�������");
        	if(whiteflag){
        		driver.findElement(By.id("set-name")).sendKeys(masinfo.getWhiteName());
        		driver.findElement(By.id("set-domains")).sendKeys(masinfo.getWhitelist());
        		driver.findElement(By.id("confirm")).click();
        	}else{
        		ScreenshotandAssert.screenandasserttext(driver, "û�н��������iframe", "�����������������ѡ�����еİ������б�Ҳ�������Ҳ�����ֱ�ӱ༭�������µ�������",  By.className("fc-gray"));
        	}
        	driver.switchTo().defaultContent();
        	//���λ�ڰ�����
            driver.findElement(By.id("enableAdUnitBlackList")).click();
            driver.findElement(By.id("enableAdUnitWhiteList")).click();
            driver.findElement(By.id("adUnit.blackIdsTextArea")).sendKeys(masinfo.getPosblack());
            driver.findElement(By.id("adUnit.whiteIdsTextArea")).sendKeys(masinfo.getPoswhite());
    	}
    	//APP�ڰ������趨
    	if(!"".equals(masinfo.getMobiletype())&&"app".equals(masinfo.getMobiletype())){
    		driver.findElement(By.id("enableAppBlackList")).click();
            driver.findElement(By.id("enableAppWhiteList")).click();
            driver.findElement(By.id("blackWhiteAppblackAppIds")).sendKeys(masinfo.getAppblack());
            driver.findElement(By.id("blackWhiteAppwhiteAppIds")).sendKeys(masinfo.getAppwhite());	//�ƶ��û�����
        	driver.findElement(By.id("mobileAttr.idfas")).sendKeys(masinfo.getDirected());
    	}
    	
    	
        //������
    	driver.findElement(By.id("btn-antiCheating")).click();
    
        //����Ͷ��
        PubHandle.select(driver, By.id("dayPacing"), "ÿ���ع����");
        //�������
        driver.findElement(By.id("btn-backing")).click();
        //preferdeal
        System.out.println("====================-0--------------"+masinfo.getPreferdeal());
//        driver.findElement(By.id("preferDeal")).sendKeys(masinfo.getPreferdeal());
        //��߳���
        driver.findElement(By.id("cpmBidPrice")).clear();
        driver.findElement(By.id("cpmBidPrice")).sendKeys(masinfo.getHighprice());
        //ѡ���㷨
        PubHandle.select(driver, By.id("algorithmCode"),masinfo.getArithmetic());
        //�۸�Ŀ��
        PubHandle.select(driver, By.id("expectedGoal"), masinfo.getPricegoal());
        //ת������
        driver.findElement(By.id("convert")).sendKeys(masinfo.getUnitprice());
        
        //Ч��Ŀ��
        PubHandle.select(driver, By.id("effectGoal"), masinfo.getEffectgoal());
        //������
        driver.findElement(By.id("effectGoalRate")).sendKeys(masinfo.getEffectrate());
        //�ڲ�����
        driver.findElement(By.id("scriptCode")).sendKeys(masinfo.getScriptcode());
        
        //��ע
        driver.findElement(By.id("remark")).sendKeys(masinfo.getRemark());
        driver.findElement(By.name("submitForm")).click();
        
    }
    public static void check(WebDriver driver){
    	PubHandle.usualcheck(driver, "�ƶ����Դ���ʧ��", "�ϴ�����");
    }
    public static void datacheck(WebDriver driver,MobileAppStrategyInfo masinfo) throws InterruptedException{
    	driver.findElement(By.linkText(masinfo.getPlname())).click();
    	if(!"".equals(masinfo.getMobiletype()) && "app".equals(masinfo.getMobiletype())){
        	driver.findElement(By.id("appendedInputButton")).sendKeys(masinfo.getMobappstrategyname());

    	}else if(!"".equals(masinfo.getMobiletype()) && "web".equals(masinfo.getMobiletype())){
        	driver.findElement(By.id("appendedInputButton")).sendKeys(masinfo.getMobwebstrategyname());
    	}
	    driver.findElement(By.xpath("//*[@id='queryForm']/button")).click();
		driver.findElement(By.className("relative")).click();
		boolean webflag = Check.usualexist(driver, "�޸�Ͷ�Ų���", 5);
		if(webflag){
    	if(""!=masinfo.getMobiletype() && "app".equals(masinfo.getMobiletype())){
			    generalcheck(driver, masinfo);
    		    dacheck(driver, By.id("mobileAttr.idfas"), masinfo.getDirected());
    			dacheck(driver, By.id("blackWhiteAppblackAppIds"),masinfo.getAppblack() );
    			dacheck(driver, By.id("blackWhiteAppwhiteAppIds"), masinfo.getAppwhite());
    			isenble(driver, By.id("enableAppBlackList"));
    			isenble(driver, By.id("enableAppWhiteList"));
    	}else if(!"".equals(masinfo.getMobiletype())&&"web".equals(masinfo.getMobiletype())){
    		   generalcheck(driver, masinfo);
    		   isenble(driver, By.id("enableAdUnitBlackList"));
    		   isenble(driver, By.id("enableAdUnitWhiteList"));
    		   dacheck(driver, By.id("count-blackWhiteUrlblackUrlIds"),masinfo.getBlacklistcount());
    		   dacheck(driver, By.id("count-blackWhiteUrlwhiteUrlIds"), masinfo.getWhitelistcount());
    		   dacheck(driver, By.id("adUnit.blackIdsTextArea"), masinfo.getPosblack());
    		   dacheck(driver, By.id("adUnit.whiteIdsTextArea"), masinfo.getPoswhite());
    	}
	 }else{
    			ScreenshotandAssert.screenandasserttitle(driver, "û�н����޸�Ͷ�Ų���ҳ", "�޸�Ͷ�Ų���");
    		}
    }
    public static void dacheck(WebDriver driver,By by,String checktext){
    	String  text = driver.findElement(by).getText();
    	if("".equals(text) || !text.equals(checktext)){
    		ScreenshotandAssert.screenandasserttext(driver, checktext+"���ʧ��", checktext, by);
    	}else{
    		System.out.println(checktext+"���ͨ��");
    	}
    	
    }
    public static void dacheck1(WebDriver driver,String text,String checktext){
    	if("".equals(text) || !text.equals(checktext)){
    		ScreenshotandAssert.screenandassertseletext(driver,checktext+"���ʧ��", checktext, text);
    	}else{
    		System.out.println(checktext+"���ͨ��");
    	}
    	
    }
    public static void flag(WebDriver driver,By by,String element,String reminder){
		try {
			boolean flag = Check.elementexist(driver, by, 4, element);
			if(flag){
				;
			}else{
				ScreenshotandAssert.screenandasserttext(driver, reminder, element, by);
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
    public static String  gettext(WebDriver driver,By by){
    	String text = driver.findElement(by).getText();
    	return text;
    }
    public static void isenble(WebDriver driver,By by){
    	boolean flag = Check.isenbled(driver, by, 5);
    	if(!flag){
    		ScreenshotandAssert.screenandassert(driver, "û�б�ѡ��", flag, true);
    	}
    }
    
    public static void generalcheck(WebDriver driver,MobileAppStrategyInfo masinfo) throws InterruptedException{
    	driver.findElement(By.id("toggleAdvancedSetting")).click();
		dacheck(driver, By.xpath("//*[@id='audience-selected']/table/tbody/tr/td[2]"), masinfo.getDaatselect());
		PubHandle.selcheck(driver, By.id("audienceWeight"), masinfo.getAttention());
		dacheck(driver, By.xpath("//*[@id='audience-exclude-selected']/table/tbody/tr/td[2]"), masinfo.getDaatexclude());
		dacheck(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[2]/td[2]"), masinfo.getRetargetclassify());
		dacheck(driver, By.xpath("//*[@id='retarget-selected']/table/tbody/tr[3]/td[2]"), masinfo.getRetargetconversion());
		dacheck(driver, By.xpath("//*[@id='exclude-selected']/table/tbody/tr[1]/td[2]"), masinfo.getRetargetexclude());
		dacheck(driver, By.xpath("//*[@id='exclude-selected']/table/tbody/tr[2]/td[2]"), masinfo.getConversionexclude());
		dacheck(driver, By.id("clickCookie-selected"), "��ǰ��ѡ����һ����ͣ�"+"\n"+masinfo.getClickcookie());
		System.out.println();
		dacheck(driver, By.id("count-areas"),masinfo.getArea());
		
		String timetext = gettext(driver, By.xpath("//*[@id='day-parting-selected']/table/tbody/tr[1]/td[2]"))+" "+gettext(driver, By.xpath("//*[@id='day-parting-selected']/table/tbody/tr[7]/td[2]"));
		System.out.println(timetext);
		dacheck1(driver, timetext, masinfo.getTime());
		PubHandle.checkvalue(driver, By.id("exchanges"), masinfo.getPlate(), "value");
		System.out.println("plate=========="+driver.findElement(By.id("exchanges")).getAttribute("value"));
		PubHandle.checkvalue(driver, By.id("osTypes"),masinfo.getOs(), "value");
		System.out.println("os=========="+driver.findElement(By.id("osTypes")).getAttribute("value"));
		PubHandle.checkvalue(driver, By.id("mobileAttrmobileSets"), masinfo.getDevice(), "value");
		System.out.println("device=========="+driver.findElement(By.id("mobileAttrmobileSets")).getAttribute("value"));

		
		isenble(driver, By.id("mobileAttr.mobileNetworks0"));
		isenble(driver, By.id("mobileAttr.mobileNetworks1"));
		isenble(driver, By.id("mobileAttr.mobileNetworks2"));
		isenble(driver, By.id("mobileAttr.mobileNetworks3"));
		isenble(driver, By.id("mobileAttr.mobileNetworks4"));
		
		
		//���Ҳ������ü��
		PubHandle.selcheck(driver, By.id("dayPacing"), masinfo.getPutin());
//		PubHandle.checkvalue(driver, By.id("preferDeal"), masinfo.getPreferdeal(), "value");
		PubHandle.checkvalue(driver, By.id("cpmBidPrice"), masinfo.getHighprice(), "value");
		PubHandle.selcheck(driver, By.id("algorithmCode"),masinfo.getArithmetic());
		PubHandle.selcheck(driver, By.id("expectedGoal"), masinfo.getPricegoal());
		PubHandle.checkvalue(driver, By.id("expectedParams"), masinfo.getCheckunitprive(), "value");
		PubHandle.selcheck(driver, By.id("effectGoal"), masinfo.getEffectgoal());
		PubHandle.checkvalue(driver, By.id("effectGoalRate"), masinfo.getEffectrate(), "value");
		dacheck(driver, By.id("scriptCode"),masinfo.getScriptcode());
		dacheck(driver, By.id("remark"), masinfo.getRemark());
    }
}
