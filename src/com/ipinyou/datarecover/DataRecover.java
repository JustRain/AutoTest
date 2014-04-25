package com.ipinyou.datarecover;
                                                            
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;

import com.mysql.jdbc.Statement;




public class DataRecover {
	

	 public static void main(String[] args) {
	   		Connection con = null;
//			Statement stmt;
			Connection conn = null;
		    Statement stmt;
		    ResultSet res = null;
			 try
		     {
		       
		       Class.forName("com.mysql.jdbc.Driver").newInstance();
		       conn = DriverManager.getConnection("jdbc:mysql://10.1.1.62:3306/fulltest?characterEncoding=UTF-8","dispatch", "optimus");

		       stmt = (Statement) conn.createStatement();
		 
		       //删除创意
		       int res1 = stmt.executeUpdate("delete from strategy_creative_rel where creative_id in (select id from creative WHERE theme in ('delcreativetheme','batchcreativeswf','batchcreativeapp','batchcreativeweb','Uploadgif','Uploadpng','Uploadswf','Uploadflv','UploadCreative','batchcreative','googeladx','ownmediacreative','ProjectUploadCreative','MediumUploadCreative','SelfUploadCreative'))");
		       int res2 = stmt.executeUpdate("delete from creative_tag_rel where creative_id in (select id from creative WHERE theme in ('delcreativetheme','batchcreativeswf','batchcreativeapp','batchcreativeweb','Uploadgif','Uploadpng','Uploadswf','Uploadflv','UploadCreative','batchcreative','googeladx','ownmediacreative','ProjectUploadCreative','MediumUploadCreative','SelfUploadCreative'))");
		       int res3 = stmt.executeUpdate("delete from creative_audit where creative_id in (select id from creative WHERE theme in ('delcreativetheme','batchcreativeswf','batchcreativeapp','batchcreativeweb','Uploadgif','Uploadpng','Uploadswf','Uploadflv','UploadCreative','batchcreative','googeladx','ownmediacreative','ProjectUploadCreative','MediumUploadCreative','SelfUploadCreative'))");
		       int res4 = stmt.executeUpdate("delete from creative where theme in ('delcreativetheme','batchcreativeswf','batchcreativeapp','batchcreativeweb','Uploadgif','Uploadpng','Uploadswf','Uploadflv','UploadCreative','batchcreative','googeladx','ownmediacreative','ProjectUploadCreative','MediumUploadCreative','SelfUploadCreative')");
		       System.out.println("创意删除完成");
		       
		       //删除移动DSP创意
//		       int res1a = stmt.executeUpdate("delete from strategy_creative_rel where creative_id in (select id from creative WHERE name = 'googeladx')");
//		       int res2b= stmt.executeUpdate("delete from creative_tag_rel where creative_id in (select id from creative WHERE name = 'googeladx')");
//		       int res3c = stmt.executeUpdate("delete from creative_audit where creative_id in (select id from creative WHERE name = 'googeladx')");
//		       int res4d = stmt.executeUpdate("delete from creative where name = 'googeladx'");
//		       System.out.println("移动DSP创意删除完成");
		       //删除自有媒体创意
//		       int res11a = stmt.executeUpdate("delete from strategy_creative_rel where creative_id in (select id from creative WHERE name = 'ownmedia')");
//		       int res22a = stmt.executeUpdate("delete from creative_tag_rel where creative_id in (select id from creative WHERE name = 'ownmedia')");
//		       int res33 = stmt.executeUpdate("delete from creative_audit where creative_id in (select id from creative WHERE name = 'ownmedia')");
//		       int res44 = stmt.executeUpdate("delete from creative where name = 'ownmedia'");
//		       System.out.println("自有媒体创意删除完成");
		       
		       //删除复制策略
		       
		       
		       //删除策略
		       int res5 = stmt.executeUpdate("delete from strategy_stats where id in (select id from strategy where name in ('DelAutoStrategyDsp','ProprietaryMediaStrategy','AutoStrategyDsp-复本','BatchStrategyDsp-复本','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp'))");
		       int res6 = stmt.executeUpdate("delete from strategy_status where id in (select id from strategy where name in ('DelAutoStrategyDsp','ProprietaryMediaStrategy','AutoStrategyDsp-复本','BatchStrategyDsp-复本','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp'))");
		       int res7 = stmt.executeUpdate("delete from strategy_black_white_url where id in (select id from strategy where name in ('DelAutoStrategyDsp','ProprietaryMediaStrategy','AutoStrategyDsp-复本','BatchStrategyDsp-复本','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp'))");
		       int res71 = stmt.executeUpdate("delete from strategy_creative_rel where  strategy_id in (select id from strategy where name in ('DelAutoStrategyDsp','ProprietaryMediaStrategy','AutoStrategyDsp-复本','BatchStrategyDsp-复本','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp'))");
		       //int res81 = stmt.executeUpdate("delete from strategy_stats where id in (select id from strategy where name in ('AutoStrategyDsp-复本','BatchStrategyDsp-复本','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp'))");
		       int rest = stmt.executeUpdate("delete from strategy_regulate where strategy_id in (select id from strategy where name in ('DelAutoStrategyDsp','ProprietaryMediaStrategy','AutoStrategyDsp-复本','BatchStrategyDsp-复本','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp'))");
		       int resad = stmt.executeUpdate("delete from  strategy_ad_unit where id in (select id from strategy where name in ('DelAutoStrategyDsp','ProprietaryMediaStrategy','AutoStrategyDsp-复本','BatchStrategyDsp-复本','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp'))");
		       int res8 = stmt.executeUpdate("delete from strategy where name in ('DelAutoStrategyDsp','ProprietaryMediaStrategy','AutoStrategyDsp-复本','BatchStrategyDsp-复本','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp')");			       
		       System.out.println("策略删除完成");
//		       conn.commit();
//		       //删除移动DSP策略
		       int res51 = stmt.executeUpdate("delete from strategy_stats where id in (select id from strategy where name in('AutoStrategyMobApp','AutoStrategyMobWeb'))");
		       int res61 = stmt.executeUpdate("delete from strategy_status where id in (select id from strategy where name in('AutoStrategyMobApp','AutoStrategyMobWeb'))");
		       int res72 = stmt.executeUpdate("delete from strategy_black_white_url where id in (select id from strategy where name in('AutoStrategyMobApp','AutoStrategyMobWeb'))");
		       int res73 = stmt.executeUpdate("delete from strategy_creative_rel where  strategy_id in (select id from strategy where name  in('AutoStrategyMobApp','AutoStrategyMobWeb'))");
	           int restm = stmt.executeUpdate("delete from strategy_regulate where strategy_id in (select id from strategy where name in ('AutoStrategyMobApp','AutoStrategyMobWeb'))");
		       int resadm = stmt.executeUpdate("delete from  strategy_ad_unit where id in (select id from strategy where name in ('AutoStrategyMobApp','AutoStrategyMobWeb'))");
               int reswhapp = stmt.executeUpdate("DELETE FROM strategy_black_white_app WHERE id IN (SELECT id FROM strategy WHERE NAME IN ('AutoStrategyMobApp','AutoStrategyMobWeb'))");
		       int res81 = stmt.executeUpdate("delete from strategy where name in('AutoStrategyMobApp','AutoStrategyMobWeb')");	
		       System.out.println("移动DSP删除策略删除完成");
		       
 
//	       
//		       int resp = stmt.executeUpdate("DELETE FROM ad_unit_prediction WHERE id in (select id from ad_unit where name in('batchposition','autoadposition'))");
//		       int resa = stmt.executeUpdate("delete from ad_unit where name in ('autoadposition','batchposition')");
//		       int adsize = stmt.executeUpdate("DELETE FROM ad_size WHERE width=520 AND height=250");
//		       int resb = stmt.executeUpdate("delete from label where name = 'autopart'");
//		       int resc = stmt.executeUpdate("delete from website where name = 'autowebsite'");
//		       int resd = stmt.executeUpdate("delete from channel_pool_rel where channel_id in (select id from channel where name = 'autochannel')");
//		       int rese = stmt.executeUpdate("delete from channel where name = 'autochannel'");
		       
//		       System.out.println("媒体广告位删除完成");
		       //删除计划
		      
		       int res9 = stmt.executeUpdate("delete from campaign_stats where id in (select id from campaign where name in ('AlgorithmPlan','BatchPlan','AutoPlan','ProjectAutoPlan','MediumAutoPlan','SelfAutoPlan','BatchAutoPlan'))");
		       int res10 = stmt.executeUpdate("delete from campaign_status where id in (select id from campaign where name  in ('AlgorithmPlan','BatchPlan','AutoPlan','ProjectAutoPlan','MediumAutoPlan','SelfAutoPlan','BatchAutoPlan'))");
		       int res11 = stmt.executeUpdate("delete from campaign where name in ('AlgorithmPlan','BatchPlan','AutoPlan','ProjectAutoPlan','MediumAutoPlan','SelfAutoPlan','BatchAutoPlan')");
//		       
		       System.out.println("计划删除完成");
//		       //删除订单
		       int res12 = stmt.executeUpdate("DELETE FROM ad_order_stats WHERE id IN (SELECT id FROM ad_order WHERE NAME IN('DelAutoOrder','AutoOrder','ProjectAutoOrder','MediumAutoOrder','SelfAutoOrder'))");
		       int res13 = stmt.executeUpdate("delete from ad_order where name in('DelAutoOrder','AutoOrder','ProjectAutoOrder','MediumAutoOrder','SelfAutoOrder')");
//		       
		       System.out.println("订单删除完成");
//		       //删除分类代码
		       int resf = stmt.executeUpdate("delete from retarget_category where name in ('retargetingcode2','retargetingcode1','projectretargetingcode2','projectretargetingcode1','mediumretargetingcode2','mediumretargetingcode1')");
		       System.out.println("分类代码删除完成");
               //删除转化代码
              int resm = stmt.executeUpdate("delete from conversion_target where name in ('conversioncode1','conversioncode2','projectconversioncode2','projectconversioncode1','mediumconversioncode2','mediumconversioncode1')");
              System.out.println("转化代码删除完成");
//		       //删除广告主
		       int res14 = stmt.executeUpdate("delete from advertiser_balance where id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser') )");
		       int res15 = stmt.executeUpdate("delete from advertiser_owner where advertiser_id in (select id from advertiser where name in ('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser') )");
		       int res16 = stmt.executeUpdate("delete from advertiser_stats where id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser') )");
		       int res18 = stmt.executeUpdate("delete from conversion_target where belonged_advertiser_id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser') )");
		       int res19 = stmt.executeUpdate("delete from retarget_category where belonged_advertiser_id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser')) ");
		       int res20 = stmt.executeUpdate("delete from creative where advertiser_id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser')) ");
		       int res21 = stmt.executeUpdate("delete from advertiser_bill_info where advertiser_id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser') )");
		       int resi = stmt.executeUpdate("delete from advertiser_qualification where  advertiser_id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser'))");
		       int white = stmt.executeUpdate("delete from global_black_white_url  where advertiser_id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser') )");
		       int bill = stmt.executeUpdate("DELETE FROM agency_business_bill WHERE advertiser_id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser') )");
		       int adinfo = stmt.executeUpdate("DELETE FROM advertiser_operation_info WHERE id IN (SELECT id FROM advertiser WHERE NAME in('delautoadvertiser', 'autoadvertiser)')");
 		       int res22 = stmt.executeUpdate("delete from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser') ");
 		       System.out.println("广告主删除完成");
		       System.out.println("数据恢复完成");
			   
			   stmt.close();
			   conn.close();
		     }
		     catch (Exception ex)
		     {
		    	ex.printStackTrace();
		       System.out.println("Error : " + ex.toString());
		     }
			
			}
	 }
