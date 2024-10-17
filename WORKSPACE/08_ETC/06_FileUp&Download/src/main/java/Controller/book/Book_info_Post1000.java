package Controller.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

public class Book_info_Post1000 {
	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost/bookdb?serverTimezone=UTC";
		String id = "root";	//ID
		String pw = "1234";		//PW
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
					
			conn =DriverManager.getConnection(url,id,pw);

			for (int i = 0; i < 1000; i++) {
				pstmt = conn.prepareStatement("insert into tbl_book values(?,?,?,?)");
				pstmt.setInt(1, 1111+i);
				pstmt.setString(2, "BookName"+i);
				pstmt.setString(3, "00출판사" + i);
				pstmt.setString(4, new Random().nextInt(10000)+"");
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}

		}
	}
}
