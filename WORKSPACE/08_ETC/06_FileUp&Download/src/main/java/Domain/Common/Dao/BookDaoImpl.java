package Domain.Common.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Domain.Common.Dto.BookDto;

public class BookDaoImpl extends ConnectionPool implements BookDao {

	// 싱글톤 패턴
	private static BookDao instance;

	public static BookDao getInstance() {
		if (instance == null)
			instance = new BookDaoImpl();
		return instance;
	}
	//

	private BookDaoImpl() {

	}

	// CRUD
	@Override
	public int insert(BookDto dto) throws Exception {

		pstmt = conn.prepareStatement("insert into tbl_book values(?,?,?,?)");
		pstmt.setInt(1, dto.getBookcode());
		pstmt.setString(2, dto.getBookname());
		pstmt.setString(3, dto.getPublisher());
		pstmt.setString(4, dto.getIsbn());
		int result = pstmt.executeUpdate();
		pstmt.close();

		return result;

	}

	@Override
	public List<BookDto> select() throws Exception {
		List<BookDto> list = new ArrayList();
		BookDto dto = null;
		pstmt = conn.prepareStatement("select * from tbl_book");
		rs = pstmt.executeQuery();
		if (rs != null) {
			while (rs.next()) {
				dto = new BookDto();
				dto.setBookcode(rs.getInt("bookcode"));
				dto.setBookname(rs.getString("bookname"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setIsbn(rs.getString("isbn"));
				list.add(dto);
			}
			rs.close();
		}
		pstmt.close();

		return list;
	}

	@Override
	public BookDto select(int bookcode) throws Exception {

		BookDto dto = null;
		pstmt = conn.prepareStatement("select * from tbl_book where bookcode=?");
		pstmt.setInt(1, bookcode);
		rs = pstmt.executeQuery();
		if (rs != null && rs.isBeforeFirst()) {
			rs.next();
			dto = new BookDto();
			dto.setBookcode(rs.getInt("bookcode"));
			dto.setBookname(rs.getString("bookname"));
			dto.setPublisher(rs.getString("publisher"));
			dto.setIsbn(rs.getString("isbn"));

			rs.close();
		}
		pstmt.close();
		return dto;
	}

	@Override
	public List<BookDto> select(String keyword) {
		return null;
	}


	@Override
	public int update(BookDto dto) throws Exception {
		pstmt = conn.prepareStatement("update tbl_book set bookname=?,publisher=?,isbn=? where bookcode=?");
		pstmt.setString(1, dto.getBookname());
		pstmt.setString(2, dto.getPublisher());
		pstmt.setString(3, dto.getIsbn());
		pstmt.setInt(4, dto.getBookcode());
		int result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	@Override
	public int delete(int bookcode) throws Exception {

		pstmt = conn.prepareStatement("delete from tbl_book where bookcode=?");
		pstmt.setInt(1, bookcode);
		int result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	@Override
	public int getAmount() throws Exception {

		int cnt = 0;
		pstmt = conn.prepareStatement("select count(*) from tbl_book");

		rs = pstmt.executeQuery();
		if (rs != null) {
			rs.next();
			cnt = rs.getInt(1);
		}
		return cnt;
	}

	@Override
	public List<BookDto> select(int startno, int amount) throws Exception {
		
		List<BookDto> list = new ArrayList();
		BookDto dto = null;
		
		pstmt=conn.prepareStatement("select * from tbl_book order by bookcode desc limit ?,?");
		pstmt.setInt(1, startno);
		pstmt.setInt(2, amount);
		rs=pstmt.executeQuery();
		if(rs!=null)
		{
			while(rs.next())
			{
				dto = new BookDto();
				dto.setBookcode(rs.getInt("bookcode"));
				dto.setBookname(rs.getString("bookname"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setIsbn(rs.getString("isbn"));
				list.add(dto);
			}
			
		}	
		
		return list;
	}

}
