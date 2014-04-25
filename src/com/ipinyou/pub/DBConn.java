package com.ipinyou.pub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class DBConn {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://10.1.1.62:3306/fulltest?characterEncoding=UTF-8";
	private static Connection conn = null;
	private static String connname = "dispatch";
	private static String connpass = "optimus";
	private static ResultSet rs;
	private static PreparedStatement ps;
    private static  Statement stmt;

	public static Connection getconnection(){
		try {
			Class.forName(driver);
			conn = (Connection) DriverManager.getConnection(url, connname, connpass);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println( "�����������ݿ�ʧ��");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println( "�����������ݿ�ʧ�ܣ�����");
		}
		return conn;
	}
	public static int getcaseid(){
		String sql = "select * from case_id ORDER BY creation desc limit 1";
		int a = 0;
		Connection conn = getconnection();
		try{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
			    a = Integer.valueOf(rs.getString(2)) ;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println(a);
		return a;
	}
	
	//ɾ������
	public static void delCreative() throws SQLException{
		   conn = getconnection();
	       stmt = (Statement) conn.createStatement();
		   int res1 = stmt.executeUpdate("delete from strategy_creative_rel where creative_id in (select id from creative WHERE theme in ('delcreativetheme','batchcreativeswf','batchcreativeapp','batchcreativeweb','Uploadgif','Uploadpng','Uploadswf','Uploadflv','UploadCreative','batchcreative','googeladx','ownmediacreative','ProjectUploadCreative','MediumUploadCreative','SelfUploadCreative','delcreativetheme','statisticcreativetheme'))");
	       int res2 = stmt.executeUpdate("delete from creative_tag_rel where creative_id in (select id from creative WHERE theme in ('delcreativetheme','batchcreativeswf','batchcreativeapp','batchcreativeweb','Uploadgif','Uploadpng','Uploadswf','Uploadflv','UploadCreative','batchcreative','googeladx','ownmediacreative','ProjectUploadCreative','MediumUploadCreative','SelfUploadCreative','delcreativetheme','statisticcreativetheme'))");
	       int res3 = stmt.executeUpdate("delete from creative_audit where creative_id in (select id from creative WHERE theme in ('delcreativetheme','batchcreativeswf','batchcreativeapp','batchcreativeweb','Uploadgif','Uploadpng','Uploadswf','Uploadflv','UploadCreative','batchcreative','googeladx','ownmediacreative','ProjectUploadCreative','MediumUploadCreative','SelfUploadCreative','delcreativetheme','statisticcreativetheme'))");
	       int res4 = stmt.executeUpdate("delete from creative where theme in ('delcreativetheme','batchcreativeswf','batchcreativeapp','batchcreativeweb','Uploadgif','Uploadpng','Uploadswf','Uploadflv','UploadCreative','batchcreative','googeladx','ownmediacreative','ProjectUploadCreative','MediumUploadCreative','SelfUploadCreative','delcreativetheme','statisticcreativetheme')");
	       System.out.println("����ɾ�����");
	       closeClient();
	}
	//ɾ��DSP����
	public static void delDspStrategy() throws SQLException{
		conn = getconnection();
	       stmt = (Statement) conn.createStatement();

		   int res5 = stmt.executeUpdate("delete from strategy_stats where id in (select id from strategy where name in ('DelAutoStrategyDsp','ProprietaryMediaStrategy','AutoStrategyDsp-����','BatchStrategyDsp-����','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp','DelAutoStrategyDsp','statisticstrategy'))");
	       int res6 = stmt.executeUpdate("delete from strategy_status where id in (select id from strategy where name in ('DelAutoStrategyDsp','ProprietaryMediaStrategy','AutoStrategyDsp-����','BatchStrategyDsp-����','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp','DelAutoStrategyDsp','statisticstrategy'))");
	       int res7 = stmt.executeUpdate("delete from strategy_black_white_url where id in (select id from strategy where name in ('DelAutoStrategyDsp','ProprietaryMediaStrategy','AutoStrategyDsp-����','BatchStrategyDsp-����','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp','DelAutoStrategyDsp','statisticstrategy'))");
	       int res71 = stmt.executeUpdate("delete from strategy_creative_rel where  strategy_id in (select id from strategy where name in ('DelAutoStrategyDsp','ProprietaryMediaStrategy','AutoStrategyDsp-����','BatchStrategyDsp-����','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp','DelAutoStrategyDsp','statisticstrategy'))");
	       //int res81 = stmt.executeUpdate("delete from strategy_stats where id in (select id from strategy where name in ('AutoStrategyDsp-����','BatchStrategyDsp-����','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp'))");
	       int rest = stmt.executeUpdate("delete from strategy_regulate where strategy_id in (select id from strategy where name in ('DelAutoStrategyDsp','ProprietaryMediaStrategy','AutoStrategyDsp-����','BatchStrategyDsp-����','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp','DelAutoStrategyDsp','statisticstrategy'))");
	       int resad = stmt.executeUpdate("delete from  strategy_ad_unit where id in (select id from strategy where name in ('ProprietaryMediaStrategy','AutoStrategyDsp-����','BatchStrategyDsp-����','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp','DelAutoStrategyDsp','statisticstrategy'))");
	       int res8 = stmt.executeUpdate("delete from strategy where name in ('DelAutoStrategyDsp','ProprietaryMediaStrategy','AutoStrategyDsp-����','BatchStrategyDsp-����','AutoStrategyDsp','BatchStrategyDsp','AutoStrategyOmediaPut','MediumAutoStrategyDsp','ProjectAutoStrategyDsp','SelfAutoStrategyDsp','DelAutoStrategyDsp','statisticstrategy')");			       
	       System.out.println("����ɾ�����");
	       closeClient();
	}
	//ɾ���ƶ�DSP����
	public static void delMobStrategy() throws SQLException{
		conn = getconnection();
	      stmt = (Statement) conn.createStatement();
	      int res51 = stmt.executeUpdate("delete from strategy_stats where id in (select id from strategy where name in('AlgorithmStrategy-����','AutoStrategyMobApp-����','AutoStrategyMobWeb-����','AutoStrategyMobApp','AutoStrategyMobWeb','AlgorithmStrategy'))");
	      int res61 = stmt.executeUpdate("delete from strategy_status where id in (select id from strategy where name in('AlgorithmStrategy-����','AutoStrategyMobApp-����','AutoStrategyMobWeb-����','AutoStrategyMobApp','AutoStrategyMobWeb','AlgorithmStrategy'))");
	      int res72 = stmt.executeUpdate("delete from strategy_black_white_url where id in (select id from strategy where name in('AlgorithmStrategy-����','AutoStrategyMobApp-����','AutoStrategyMobWeb-����','AutoStrategyMobApp','AutoStrategyMobWeb','AlgorithmStrategy'))");
	      int res73 = stmt.executeUpdate("delete from strategy_creative_rel where  strategy_id in (select id from strategy where name  in('AlgorithmStrategy-����','AutoStrategyMobApp-����','AutoStrategyMobWeb-����','AutoStrategyMobApp','AutoStrategyMobWeb','AlgorithmStrategy'))");
          int restm = stmt.executeUpdate("delete from strategy_regulate where strategy_id in (select id from strategy where name in ('AlgorithmStrategy-����','AutoStrategyMobApp-����','AutoStrategyMobWeb-����','AutoStrategyMobApp','AutoStrategyMobWeb','AlgorithmStrategy'))");
	      int resadm = stmt.executeUpdate("delete from  strategy_ad_unit where id in (select id from strategy where name in ('AlgorithmStrategy-����','AutoStrategyMobApp-����','AutoStrategyMobWeb-����','AutoStrategyMobApp','AutoStrategyMobWeb','AlgorithmStrategy'))");
          int reswhapp = stmt.executeUpdate("DELETE FROM strategy_black_white_app WHERE id IN (SELECT id FROM strategy WHERE NAME IN ('AlgorithmStrategy-����','AutoStrategyMobApp-����','AutoStrategyMobWeb-����','AutoStrategyMobApp','AutoStrategyMobWeb','AlgorithmStrategy'))");
	      int res81 = stmt.executeUpdate("delete from strategy where name in('AlgorithmStrategy-����','AutoStrategyMobApp-����','AutoStrategyMobWeb-����','AutoStrategyMobApp','AutoStrategyMobWeb','AlgorithmStrategy')");	
	      System.out.println("�ƶ�DSPɾ������ɾ�����");
	      closeClient();
	}
	//����ɱ����
	public static void KillAllStrategy() throws SQLException{
		conn = getconnection();
	    stmt = (Statement) conn.createStatement();
		int res51 = stmt.executeUpdate("DELETE FROM strategy_stats WHERE id IN (SELECT id FROM strategy def campaign_id IN (SELECT id FROM campaign WHERE NAME IN ('AlgorithmPlan','BatchPlan','AutoPlan','ProjectAutoPlan','MediumAutoPlan','SelfAutoPlan','BatchAutoPlan')))");
	    int res61 = stmt.executeUpdate("DELETE FROM strategy_status WHERE id IN (SELECT id FROM strategy WHERE campaign_id IN (SELECT id FROM campaign WHERE NAME IN ('AlgorithmPlan','BatchPlan','AutoPlan','ProjectAutoPlan','MediumAutoPlan','SelfAutoPlan','BatchAutoPlan')))");
	    int res72 = stmt.executeUpdate("DELETE FROM strategy_black_white_url WHERE id IN (SELECT id FROM strategy WHERE campaign_id IN (SELECT id FROM campaign WHERE NAME IN ('AlgorithmPlan','BatchPlan','AutoPlan','ProjectAutoPlan','MediumAutoPlan','SelfAutoPlan','BatchAutoPlan')))");
	    int res73 = stmt.executeUpdate("DELETE FROM strategy_creative_rel WHERE  strategy_id IN (SELECT id FROM strategy WHERE campaign_id IN (SELECT id FROM campaign WHERE NAME IN ('AlgorithmPlan','BatchPlan','AutoPlan','ProjectAutoPlan','MediumAutoPlan','SelfAutoPlan','BatchAutoPlan')))");
        int restm = stmt.executeUpdate("DELETE FROM strategy_regulate WHERE strategy_id IN (SELECT id FROM strategy WHERE campaign_id IN (SELECT id FROM campaign WHERE NAME IN ('AlgorithmPlan','BatchPlan','AutoPlan','ProjectAutoPlan','MediumAutoPlan','SelfAutoPlan','BatchAutoPlan')))");
	    int resadm = stmt.executeUpdate("DELETE FROM  strategy_ad_unit WHERE id IN (SELECT id FROM strategy WHERE campaign_id IN (SELECT id FROM campaign WHERE NAME IN ('AlgorithmPlan','BatchPlan','AutoPlan','ProjectAutoPlan','MediumAutoPlan','SelfAutoPlan','BatchAutoPlan')))");
        int reswhapp = stmt.executeUpdate("DELETE FROM strategy_black_white_app WHERE id IN (SELECT id FROM strategy WHERE campaign_id IN (SELECT id FROM campaign WHERE NAME IN ('AlgorithmPlan','BatchPlan','AutoPlan','ProjectAutoPlan','MediumAutoPlan','SelfAutoPlan','BatchAutoPlan')))");
	    int res81 = stmt.executeUpdate("DELETE FROM strategy WHERE campaign_id IN(SELECT id FROM campaign WHERE NAME IN ('AlgorithmPlan','BatchPlan','AutoPlan','ProjectAutoPlan','MediumAutoPlan','SelfAutoPlan','BatchAutoPlan'))");
	    closeClient();
        System.out.println("����ȫ����գ�����������������������");
	}
	//ɾ��ý����λ
	public static void delMedium() throws SQLException{
		conn = getconnection();
	       stmt = (Statement) conn.createStatement();
	       int resp = stmt.executeUpdate("DELETE FROM ad_unit_prediction WHERE id in (select id from ad_unit where name in('batchposition','autoadposition'))");
	       int resa = stmt.executeUpdate("delete from ad_unit where name in ('autoadposition','batchposition')");
	       int adsize = stmt.executeUpdate("DELETE FROM ad_size WHERE width=520 AND height=250");
	       int resb = stmt.executeUpdate("delete from label where name = 'autopart'");
	       int resc = stmt.executeUpdate("delete from website where name = 'autowebsite'");
	       int resd = stmt.executeUpdate("delete from channel_pool_rel where channel_id in (select id from channel where name = 'autochannel')");
	       int rese = stmt.executeUpdate("delete from channel where name = 'autochannel'");
	       System.out.println("ý����λɾ�����");
	       closeClient();
	}
	//ɾ���ƻ�
	public static void delPlan() throws SQLException{
		conn = getconnection();
		 stmt = (Statement) conn.createStatement();
		 int res9 = stmt.executeUpdate("delete from campaign_stats where id in (select id from campaign where name in ('AlgorithmPlan','BatchPlan','AutoPlan','ProjectAutoPlan','MediumAutoPlan','SelfAutoPlan','BatchAutoPlan','DelPlan'))");
	     int res10 = stmt.executeUpdate("delete from campaign_status where id in (select id from campaign where name  in ('AlgorithmPlan','BatchPlan','AutoPlan','ProjectAutoPlan','MediumAutoPlan','SelfAutoPlan','BatchAutoPlan','DelPlan'))");
	     int res11 = stmt.executeUpdate("delete from campaign where name in ('AlgorithmPlan','BatchPlan','AutoPlan','ProjectAutoPlan','MediumAutoPlan','SelfAutoPlan','BatchAutoPlan','DelPlan')");
	     System.out.println("�ƻ�ɾ�����");
	     closeClient();
	}
	
	//ɾ������
	public static void delOrder() throws SQLException{
		conn = getconnection();
		  stmt = (Statement) conn.createStatement();
		  int res12 = stmt.executeUpdate("DELETE FROM ad_order_stats WHERE id IN (SELECT id FROM ad_order WHERE NAME IN('DelAutoOrder','AutoOrder','ProjectAutoOrder','MediumAutoOrder','SelfAutoOrder','DelAutoOrder'))");
	      int res13 = stmt.executeUpdate("delete from ad_order where name in('DelAutoOrder','AutoOrder','ProjectAutoOrder','MediumAutoOrder','SelfAutoOrder','DelAutoOrder')");
	      System.out.println("����ɾ�����");
	      closeClient();
	}
	//ɾ������
	public static void delCode() throws SQLException{
		conn = getconnection();
		stmt = (Statement) conn.createStatement();
		int resf = stmt.executeUpdate("delete from retarget_category where name in ('retargetingcode2','retargetingcode1','projectretargetingcode2','projectretargetingcode1','mediumretargetingcode2','mediumretargetingcode1','selfretargetingcode1','selfretargetingcode2')");
	    System.out.println("�������ɾ�����");
          //ɾ��ת������
        int resm = stmt.executeUpdate("delete from conversion_target where name in ('conversioncode1','conversioncode2','projectconversioncode2','projectconversioncode1','mediumconversioncode2','mediumconversioncode1','selfconversioncode1','selfconversioncode2')");
        System.out.println("ת������ɾ�����");
        closeClient();
	}
	//ɾ�������
	public static void delAdvertiser() throws SQLException{
		   conn = getconnection();
		   stmt = (Statement) conn.createStatement();
//		   int res14 = stmt.executeUpdate("delete from advertiser_balance where id in (select id from advertiser where name in('selfautoadvertiser','TestMobile','aatest0315') )");
//	       int res15 = stmt.executeUpdate("delete from advertiser_owner where advertiser_id in (select id from advertiser where name in ('selfautoadvertiser','TestMobile','aatest0315') )");
//	       int res16 = stmt.executeUpdate("delete from advertiser_stats where id in (select id from advertiser where name in('selfautoadvertiser','TestMobile','aatest0315') )");
//	       int res18 = stmt.executeUpdate("delete from conversion_target where belonged_advertiser_id in (select id from advertiser where name in('selfautoadvertiser','TestMobile','aatest0315') )");
//	       int res19 = stmt.executeUpdate("delete from retarget_category where belonged_advertiser_id in (select id from advertiser where name in('selfautoadvertiser','TestMobile','aatest0315')) ");
//	       int res20 = stmt.executeUpdate("delete from creative where advertiser_id in (select id from advertiser where name in('selfautoadvertiser','TestMobile','aatest0315')) ");
//	       int res21 = stmt.executeUpdate("delete from advertiser_bill_info where advertiser_id in (select id from advertiser where name in('selfautoadvertiser','TestMobile','aatest0315') )");
//	       int resi = stmt.executeUpdate("delete from advertiser_qualification where  advertiser_id in (select id from advertiser where name in('selfautoadvertiser','TestMobile','aatest0315'))");
//	       int white = stmt.executeUpdate("delete from global_black_white_url  where advertiser_id in (select id from advertiser where name in('selfautoadvertiser','TestMobile','aatest0315') )");
//	       int bill = stmt.executeUpdate("DELETE FROM agency_business_bill WHERE advertiser_id in (select id from advertiser where name in('selfautoadvertiser','TestMobile','aatest0315') )");
//	       int adinfo = stmt.executeUpdate("DELETE FROM advertiser_operation_info WHERE id IN (SELECT id FROM advertiser WHERE NAME in('selfautoadvertiser','TestMobile','aatest0315'))");
//	       int res22 = stmt.executeUpdate("delete from advertiser where name in('selfautoadvertiser','TestMobile','aatest0315') ");
	       
	       
	       
	       int res14 = stmt.executeUpdate("delete from advertiser_balance where id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser','delautoadvertiser') )");
	       int res15 = stmt.executeUpdate("delete from advertiser_owner where advertiser_id in (select id from advertiser where name in ('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser','delautoadvertiser') )");
	       int res16 = stmt.executeUpdate("delete from advertiser_stats where id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser','delautoadvertiser') )");
	       int res18 = stmt.executeUpdate("delete from conversion_target where belonged_advertiser_id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser','delautoadvertiser') )");
	       int res19 = stmt.executeUpdate("delete from retarget_category where belonged_advertiser_id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser','delautoadvertiser')) ");
	       int res20 = stmt.executeUpdate("delete from creative where advertiser_id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser','delautoadvertiser')) ");
	       int res21 = stmt.executeUpdate("delete from advertiser_bill_info where advertiser_id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser','delautoadvertiser') )");
	       int resi = stmt.executeUpdate("delete from advertiser_qualification where  advertiser_id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser','delautoadvertiser'))");
	       int white = stmt.executeUpdate("delete from global_black_white_url  where advertiser_id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser','delautoadvertiser') )");
	       int bill = stmt.executeUpdate("DELETE FROM agency_business_bill WHERE advertiser_id in (select id from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser','delautoadvertiser') )");
	       int adinfo = stmt.executeUpdate("DELETE FROM advertiser_operation_info WHERE id IN (SELECT id FROM advertiser WHERE NAME in('delautoadvertiser', 'autoadvertiser'))");
	       int res22 = stmt.executeUpdate("delete from advertiser where name in('delautoadvertiser','autoadvertiser','projectautoadvertiser','mediumautoadvertiser','delautoadvertiser') ");
	       
	       System.out.println("�����ɾ�����");
	       closeClient();
	}
	
	public static void delAgency() throws SQLException{
		  conn = getconnection();
		  stmt = (Statement) conn.createStatement();
		  int a = stmt.executeUpdate("DELETE FROM message_setting WHERE user_id IN (SELECT a.id FROM usr_user a LEFT JOIN usr_pool b ON a.pool_id=b.id WHERE b.name IN ('AutoTest'))");
		  int b = stmt.executeUpdate("DELETE FROM usr_user_role WHERE user_id IN (SELECT a.id FROM usr_user a LEFT JOIN usr_pool b ON a.`pool_id`=b.`id` WHERE b.`name` IN('AutoTest'))");
		  int c = stmt.executeUpdate("DELETE FROM pay_password WHERE user_id IN (SELECT a.id FROM usr_user a LEFT JOIN usr_pool b ON a.`pool_id`=b.`id` WHERE b.`name` IN ('AutoTest'))");
		  int d = stmt.executeUpdate("DELETE FROM pool_distribution WHERE operator_id IN (SELECT a.id FROM usr_user a LEFT JOIN usr_pool b ON a.`pool_id`=b.`id` WHERE b.`name` IN ('AutoTest'))");
		  int e = stmt.executeUpdate("DELETE FROM usr_user WHERE pool_id IN (SELECT id FROM usr_pool WHERE NAME IN ('AutoTest'))");
		  int f = stmt.executeUpdate("DELETE FROM pool_balance WHERE pool_id IN (SELECT id FROM usr_pool WHERE NAME IN ('AutoTest'))");
		  int h = stmt.executeUpdate("DELETE FROM usr_pool_role WHERE pool_id IN (SELECT id FROM usr_pool WHERE NAME IN ('AutoTest'))");
		  int i = stmt.executeUpdate("DELETE FROM usr_pool WHERE NAME IN('AutoTest')");
		  System.out.println("������ɾ����ɣ�����");
		  closeClient();
	}
	
	public static void closeClient() throws SQLException{
		   stmt.close();
		   conn.close();
	}
	public static void main(String args[]) throws SQLException{
		delCreative();
		delDspStrategy();
		delMobStrategy();
		delPlan();
		//KillAllStrategy();
		delDspStrategy();
		delMobStrategy();
		delOrder();
		delAdvertiser();
////		delMedium();
//		
		delCode();
		delAgency();
		
		
		
	}
	
}
