package com.example.app.domain.common.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LendDto {
	private int lendId;
	private int bookCode;
	private String username;				// 회원계정
	private String membername;				// 사서계정
	
	private boolean isReqLend;				// 대여 신청 여부(사서)
	private LocalDateTime reqDate;			// 대여 신청 날짜
	
	private boolean isAcceptedLend;			// 대여 승인 여부(사서)
	private LocalDateTime acceptedDate;		// 대여 승인 날짜	
	private LocalDateTime dueDate;			// 반납일
	public int getLendId() {
		return lendId;
	}
	public void setLendId(int lendId) {
		this.lendId = lendId;
	}
	public int getBookCode() {
		return bookCode;
	}
	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	public boolean isReqLend() {
		return isReqLend;
	}
	public void setReqLend(boolean isReqLend) {
		this.isReqLend = isReqLend;
	}
	public LocalDateTime getReqDate() {
		return reqDate;
	}
	public void setReqDate(LocalDateTime reqDate) {
		this.reqDate = reqDate;
	}
	public boolean isAcceptedLend() {
		return isAcceptedLend;
	}
	public void setAcceptedLend(boolean isAcceptedLend) {
		this.isAcceptedLend = isAcceptedLend;
	}
	public LocalDateTime getAcceptedDate() {
		return acceptedDate;
	}
	public void setAcceptedDate(LocalDateTime acceptedDate) {
		this.acceptedDate = acceptedDate;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	@Override
	public String toString() {
		return "LendDto [lendId=" + lendId + ", bookCode=" + bookCode + ", username=" + username + ", membername="
				+ membername + ", isReqLend=" + isReqLend + ", reqDate=" + reqDate + ", isAcceptedLend="
				+ isAcceptedLend + ", acceptedDate=" + acceptedDate + ", dueDate=" + dueDate + "]";
	}
	

	
		
	
}
