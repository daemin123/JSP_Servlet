package com.example.app.domain.common.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.app.domain.common.dao.common.CommonDao;
import com.example.app.domain.common.dto.BookDto;
import com.example.app.domain.common.dto.Criteria;




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
	
	//COUNT
	@Override
	public int Count() throws Exception {
		pstmt = conn.prepareStatement("select count(*) from book");
		rs =  pstmt.executeQuery();
		
		int count = 0;
		if(rs.next())
			count = rs.getInt(1);
			
		
		freeConnection(pstmt,rs);
		return count;
	}
	@Override
	public int Count(Criteria criteria) throws Exception  {
		pstmt = conn.prepareStatement("select count(*) from book where "+criteria.getType()+" like '%"+criteria.getKeyword()+"%' ");
		rs =  pstmt.executeQuery();
		
		int count = 0;
		if(rs.next())
			count = rs.getInt(1);
			
		
		freeConnection(pstmt,rs);
		return count;
	}	
	
	@Override
	public List<BookDto> selectAll(int offset, int amount) throws SQLException {
		pstmt = conn.prepareStatement("select * from book order by bookCode desc limit ?,?");
		pstmt.setInt(1, offset);
		pstmt.setInt(2, amount);
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
	public List<BookDto> selectAll(int offset, int amount, Criteria criteria) throws Exception {
		pstmt = conn.prepareStatement("select * from book where "+criteria.getType()+" like '%"+criteria.getKeyword()+"%' order by bookCode desc limit ?,?");
		pstmt.setInt(1, offset);
		pstmt.setInt(2, amount);
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

	
	
	
		
}
