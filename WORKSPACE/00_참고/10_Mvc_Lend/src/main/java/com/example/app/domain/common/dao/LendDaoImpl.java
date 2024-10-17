package com.example.app.domain.common.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.example.app.domain.common.dao.Common.CommonDao;
import com.example.app.domain.common.dto.LendDto;



public class LendDaoImpl extends CommonDao{

	private static LendDaoImpl instance ;
	public static LendDaoImpl getInstance() throws Exception {
		if(instance==null)
			instance=new LendDaoImpl();
		return instance;
	}
	private LendDaoImpl() throws Exception{
		System.out.println("[DAO] LendDaoImpl's INIT.." + conn);
	}
	
	
	//INSERT
	public boolean Insert(LendDto dto) throws SQLException {
		pstmt = conn.prepareStatement("insert into lend(lendId,bookCode,username,isReqLend,reqDate,isAcceptedLend) values(null,?,?,1,now(),0)");
		pstmt.setInt(1, dto.getBookCode());
		pstmt.setString(2, dto.getUsername());
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		return result>0;	
	}
	//UPDATE
	public boolean Update() {	
		return false;
	}
	//DELETE
	public boolean Delete() {	
		return false;
	}
	//SELECT
	public LendDto Select(int LendId){
		return null;
	}
	public LendDto Select(int LendId,int bookCode) throws SQLException{
		pstmt = conn.prepareStatement("select * from lend where bookCode=?");
		pstmt.setInt(1, bookCode);
		rs =  pstmt.executeQuery();
		LendDto dto = null;
		if(rs!=null && rs.next()) {
			dto = new LendDto();
			dto.setLendId(rs.getInt("lendId"));
			dto.setBookCode(rs.getInt("bookCode"));
			dto.setUsername(rs.getString("username"));
			dto.setReqLend(rs.getBoolean("isReqLend"));
			if(rs.getDate("reqDate")!=null)
				dto.setReqDate( new Timestamp(rs.getDate("reqDate").getTime()).toLocalDateTime() );
			dto.setAcceptedLend(rs.getBoolean("isAcceptedLend"));
			if(rs.getDate("acceptedDate")!=null)
				dto.setAcceptedDate(new Timestamp(rs.getDate("acceptedDate").getTime()).toLocalDateTime());
			if(rs.getDate("dueDate")!=null)
				dto.setDueDate(new Timestamp(rs.getDate("dueDate").getTime()).toLocalDateTime());
			
		}
		
		return dto;
	}
	//SELECT
	public List<LendDto> Select(String username) throws SQLException{
		pstmt = conn.prepareStatement("select * from lend where username=?");
		pstmt.setString(1, username);
		rs =  pstmt.executeQuery();
		List<LendDto> list = new ArrayList();
		LendDto dto = null;
		if(rs!=null)
		{
			while(rs.next()) {
				dto = new LendDto();
				dto.setLendId(rs.getInt("lendId"));
				dto.setBookCode(rs.getInt("bookCode"));
				dto.setUsername(rs.getString("username"));
				dto.setReqLend(rs.getBoolean("isReqLend"));
				if(rs.getDate("reqDate")!=null)
					dto.setReqDate( new Timestamp(rs.getDate("reqDate").getTime()).toLocalDateTime() );
				dto.setAcceptedLend(rs.getBoolean("isAcceptedLend"));
				if(rs.getDate("acceptedDate")!=null)
					dto.setAcceptedDate(new Timestamp(rs.getDate("acceptedDate").getTime()).toLocalDateTime());
				if(rs.getDate("dueDate")!=null)
					dto.setDueDate(new Timestamp(rs.getDate("dueDate").getTime()).toLocalDateTime());
				list.add(dto);
			}			
		}
			
		return list;
	}
	
	//SELECTALL
	public List<LendDto> SelectAll() throws SQLException{
		pstmt = conn.prepareStatement("select * from lend");

		rs =  pstmt.executeQuery();
		List<LendDto> list = new ArrayList();
		LendDto dto = null;
		if(rs!=null)
		{
			while(rs.next()) {
				dto = new LendDto();
				dto.setLendId(rs.getInt("lendId"));
				dto.setBookCode(rs.getInt("bookCode"));
				dto.setUsername(rs.getString("username"));
				dto.setReqLend(rs.getBoolean("isReqLend"));
				if(rs.getDate("reqDate")!=null)
					dto.setReqDate( new Timestamp(rs.getDate("reqDate").getTime()).toLocalDateTime() );
				dto.setAcceptedLend(rs.getBoolean("isAcceptedLend"));
				if(rs.getDate("acceptedDate")!=null)
					dto.setAcceptedDate(new Timestamp(rs.getDate("acceptedDate").getTime()).toLocalDateTime());
				if(rs.getDate("dueDate")!=null)
					dto.setDueDate(new Timestamp(rs.getDate("dueDate").getTime()).toLocalDateTime());
				list.add(dto);
			}			
		}
			
		return list;
	}
	public List<LendDto> SelectAll(boolean isAccepted) throws SQLException {
		pstmt = conn.prepareStatement("select * from lend where isAcceptedLend=?");
		pstmt.setBoolean(1, isAccepted);
		rs =  pstmt.executeQuery();
		List<LendDto> list = new ArrayList();
		LendDto dto = null;
		if(rs!=null)
		{
			while(rs.next()) {
				dto = new LendDto();
				dto.setLendId(rs.getInt("lendId"));
				dto.setBookCode(rs.getInt("bookCode"));
				dto.setUsername(rs.getString("username"));
				dto.setReqLend(rs.getBoolean("isReqLend"));
				if(rs.getDate("reqDate")!=null)
					dto.setReqDate( new Timestamp(rs.getDate("reqDate").getTime()).toLocalDateTime() );
				dto.setAcceptedLend(rs.getBoolean("isAcceptedLend"));
				if(rs.getDate("acceptedDate")!=null)
					dto.setAcceptedDate(new Timestamp(rs.getDate("acceptedDate").getTime()).toLocalDateTime());
				if(rs.getDate("dueDate")!=null)
					dto.setDueDate(new Timestamp(rs.getDate("dueDate").getTime()).toLocalDateTime());
				list.add(dto);
			}			
		}
			
		return list;
	}
	public boolean Update(Integer lendId) throws SQLException {
	
		pstmt = conn.prepareStatement("update lend set isAcceptedLend=1 where lendId=?");
		pstmt.setInt(1, lendId);
		
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		return result>0;
	}
	public boolean Update(LendDto dto) throws SQLException{
		
		String sql = "update lend set ";
		sql+="membername=?,isAcceptedLend=?,acceptedDate=?,dueDate=? where lendId=?";
	
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, dto.getMembername());
		pstmt.setBoolean(2, dto.isAcceptedLend());
		pstmt.setDate(3, Date.valueOf(dto.getAcceptedDate().toLocalDate()));
		pstmt.setDate(4, Date.valueOf(dto.getDueDate().toLocalDate()));
		pstmt.setInt(5, dto.getLendId());
		
		int result = pstmt.executeUpdate();
		
		freeConnection(pstmt);
		
		return result>0;

	}
	
	
}
