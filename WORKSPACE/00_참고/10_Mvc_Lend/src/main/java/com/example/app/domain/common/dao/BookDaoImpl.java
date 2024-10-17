package com.example.app.domain.common.dao;

 
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.app.domain.common.dao.Common.CommonDao;
import com.example.app.domain.common.dto.BookDto;
import com.example.app.domain.common.dto.Criteria;
import com.example.app.domain.common.dto.PageDto;

 

public class BookDaoImpl extends CommonDao implements BookDao {
	

	private static BookDao instance ;
	public static BookDao getInstance() throws Exception {
		if(instance==null)
			instance=new BookDaoImpl();
		return instance;
	}
	private BookDaoImpl() throws Exception{
		System.out.println("[DAO] BookDaoImpl's INIT.." + conn);
	}
	
	//INSERT
	@Override
	public boolean Insert(BookDto dto) throws Exception{
		pstmt = conn.prepareStatement("insert into book values(?,?,?,?)");
		pstmt.setInt(1, dto.getBookCode());
		pstmt.setString(2, dto.getBookName());
		pstmt.setString(3, dto.getPublisher());
		pstmt.setString(4, dto.getIsbn());
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		return result>0;
	}
	
	//UPDATE
	//DELETE
	
	//SELECTALL
	@Override
	public List<BookDto> SelectAll() throws Exception{
		pstmt = conn.prepareStatement("select * from book");
		rs =  pstmt.executeQuery();
		List<BookDto> list = new ArrayList();
		BookDto dto = null;
		if(rs!=null)
		{
			while(rs.next()) {
				dto = new BookDto();
				dto.setBookCode(rs.getInt("bookCode"));
				dto.setBookName(rs.getString("bookName"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setIsbn(rs.getString("isbn"));
				list.add(dto);
			}
		}	

		freeConnection(pstmt,rs);
		return list;
	}
	
	
	//SELECTONE
	@Override
	public BookDto Select(int bookCode) throws Exception {
		pstmt = conn.prepareStatement("select * from book where bookCode=?");
		pstmt.setInt(1, bookCode);
		rs =  pstmt.executeQuery();
		
		BookDto dto = null;
		if(rs!=null)
		{
				rs.next();
				dto = new BookDto();
				dto.setBookCode(rs.getInt("bookCode"));
				dto.setBookName(rs.getString("bookName"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setIsbn(rs.getString("isbn"));		
		}	
		
		freeConnection(pstmt,rs);
		return dto;
	}
	
	
	@Override
	public List<BookDto> SelectAll(Integer pageNo) throws SQLException {
		
		pstmt = conn.prepareStatement("select * from book order by bookCode desc");
		rs =  pstmt.executeQuery();
		List<BookDto> list = new ArrayList();
		BookDto dto = null;
		if(rs!=null)
		{
			while(rs.next()) {
				dto = new BookDto();
				dto.setBookCode(rs.getInt("bookCode"));
				dto.setBookName(rs.getString("bookName"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setIsbn(rs.getString("isbn"));
				list.add(dto);
			}
		}	

		freeConnection(pstmt,rs);
		return list;
	}
	

	
	
	
	@Override
	public int count() throws Exception {
		
		pstmt = conn.prepareStatement("select count(*) from book");
		rs =  pstmt.executeQuery();
		int count = -1;
		if(rs!=null&&rs.next())
			count = rs.getInt(1);
		freeConnection(pstmt,rs);
		
		return count;		
	}
	
	// 07		
	@Override
	public int count(Criteria criteria) throws Exception {
		
		System.out.println("count func type : " + criteria.getType());
		pstmt = conn.prepareStatement("select count(*) from book where "+criteria.getType()+" like '%"+criteria.getKeyword()+"%'");
		rs =  pstmt.executeQuery();
		int count = -1;
		if(rs!=null&&rs.next())
			count = rs.getInt(1);
		freeConnection(pstmt,rs);
		
		return count;		
	}
	

	
	
	@Override
	public List<BookDto> Select(PageDto pageDto, int offset) throws SQLException {
		
		pstmt = conn.prepareStatement("select * from book order by bookCode desc limit ?,?");
		pstmt.setInt(1, offset);	// 시작 offset
		pstmt.setInt(2, pageDto.getCriteria().getAmount());

		rs =  pstmt.executeQuery();
		
		List<BookDto> list = new ArrayList();
		BookDto dto = null;
		if(rs!=null)
		{
			while(rs.next()) {
				dto = new BookDto();
				dto.setBookCode(rs.getInt("bookCode"));
				dto.setBookName(rs.getString("bookName"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setIsbn(rs.getString("isbn"));
				list.add(dto);
			}
		}	
		
		freeConnection(pstmt,rs);
		return list;
		
	}
	
	
	
	
	//07_Mvc_Book(키워드 키필드 )
	@Override
	public List<BookDto> Select(PageDto pageDto, int offset,String type, String keyword) throws SQLException {
		
		pstmt = conn.prepareStatement("select * from book where "+type+" like '%"+keyword+"%' order by bookCode desc limit ?,?");

		pstmt.setInt(1, offset);	// 시작 offset
		pstmt.setInt(2, pageDto.getCriteria().getAmount());

		rs =  pstmt.executeQuery();
		
		List<BookDto> list = new ArrayList();
		BookDto dto = null;
		if(rs!=null)
		{
			while(rs.next()) {
				dto = new BookDto();
				dto.setBookCode(rs.getInt("bookCode"));
				dto.setBookName(rs.getString("bookName"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setIsbn(rs.getString("isbn"));
				list.add(dto);
			}
		}	
		
		freeConnection(pstmt,rs);
		return list;
		
	}
	@Override
	public boolean Update(BookDto dto) throws SQLException {

		pstmt = conn.prepareStatement("update book set bookName=? , publisher=? , isbn=? where bookCode=?");
		pstmt.setString(1, dto.getBookName());
		pstmt.setString(2, dto.getPublisher());
		pstmt.setString(3, dto.getIsbn());
		pstmt.setInt(4, dto.getBookCode());
		int result = pstmt.executeUpdate();

		freeConnection(pstmt);
		return result>0;

	}
	@Override
	public boolean Delete(String bookCode) throws Exception {
		
		pstmt = conn.prepareStatement("delete from book where bookCode=?");
		pstmt.setInt(1, Integer.parseInt(bookCode));
		int result = pstmt.executeUpdate();
		freeConnection(pstmt);
		return result>0;
	}
	
		
	
	
	
}
