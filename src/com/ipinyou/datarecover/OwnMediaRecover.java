package com.ipinyou.datarecover;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

public class OwnMediaRecover {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;
		Statement smt;
		Connection conn = null;
	    Statement stmt = null;
	    ResultSet res = null;
	    
	    try{
	    	
	    	 Class.forName("com.mysql.jdbc.Driver").newInstance();
		       conn = DriverManager.getConnection("jdbc:mysql://10.1.1.62:3306/fulltest","dispatch", "optimus");

		       stmt = (Statement) conn.createStatement();
//	    	//删除自有媒体创意
		       int res1 = stmt.executeUpdate("delete from strategy_creative_rel where creative_id in (select id from creative WHERE theme = 'ownmediacreative')");
		       int res2 = stmt.executeUpdate("delete from creative_tag_rel where creative_id in (select id from creative WHERE theme = 'ownmediacreative')");
		       int res3 = stmt.executeUpdate("delete from creative_audit where creative_id in (select id from creative WHERE theme = 'ownmediacreative')");
		       int res4 = stmt.executeUpdate("delete from creative where theme = 'ownmediacreative'");
//	    	  
//		     //删除自有媒体策略
		       int res5 = stmt.executeUpdate("delete from strategy_stats where id in (select id from strategy where name = 'AutoStrategyOmediaPut')");
		       int res6 = stmt.executeUpdate("delete from strategy_status where id in (select id from strategy where name = 'AutoStrategyOmediaPut')");
	           int res7 = stmt.executeUpdate("delete from strategy_black_white_url where id in (select id from strategy where name = 'AutoStrategyOmediaPut')");
		       int res8 = stmt.executeUpdate("delete from strategy_creative_rel where  strategy_id in (select id from strategy where name = 'AutoStrategyOmediaPut')");
		       int res9 = stmt.executeUpdate("delete from strategy where name = 'AutoStrategyOmediaPut'");	
		       //删除自有媒体广告位
//		       int res1a = stmt.executeUpdate("delete from  ad_unit_prediction where id in (select id from ad_unit where name = 'autoadposition')");
//		       int resa = stmt.executeUpdate("delete from ad_unit where name = 'autoadposition'");
//		       int resbatch = stmt.executeUpdate("delete from  ad_unit_prediction where id in (select id from ad_unit where name = 'batchposition')");  
//		       int resbatch1 = stmt.executeUpdate("delete from ad_unit where name = 'batchposition'");
//		       int ressize = stmt.executeUpdate("delete from ad_size where width=520 && height=250");
//
//		       int resb = stmt.executeUpdate("delete from label where name = 'autopart'");
//		       int resc = stmt.executeUpdate("delete from website where name = 'autowebsite'");
//		       int resd = stmt.executeUpdate("delete from channel_pool_rel where channel_id in (select id from channel where name = 'autochannel')");
//		       int rese = stmt.executeUpdate("delete from channel where name = 'autochannel'");
		       
		       
		      
		       System.out.println("恢复完成");
	    }catch(Exception ex){
	    	 System.out.println("Error : " + ex.toString());
	    }
	    
	}

}
