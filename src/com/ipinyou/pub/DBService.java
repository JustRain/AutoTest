package com.ipinyou.pub;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBService {
	public static void databasecheck(String sql,String reminder) throws SQLException{
		Connection conn = (Connection) DBConn.getconnection();
		Statement stmt = (Statement) conn.createStatement();
		int result = 0;
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			result = rs.getInt(1);
			System.out.println(result);
			
		}
		
		if(result!= 1){
			Assert.fail(reminder);
		}else{
			System.out.println("É¾³ý³É¹¦");
		}
	}
	

}
