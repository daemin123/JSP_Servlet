package Domain.Common.Dao;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;

import Domain.etc.Dto.FileDto;

public class ConnectionPool {
	protected static String id="root";
	protected static String pw="1234";
	protected static String url;
	
	public static Connection conn;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	public static void createConnection(String ServerIP) {
		url = "jdbc:mysql://"+ServerIP+":3306/bookdb";
		
		
		
		try {
			if(conn==null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection(url,id,pw);
				System.out.println("ConnectionPool Connection 생성");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected ConnectionPool(){
	}

	
}
