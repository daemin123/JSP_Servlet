package Domain.Common.Dto;

import java.sql.Date;

public class LogDto {
	private int id;
	private int total;
	private int user;
	private int member;
	private Date date;
	public LogDto() {}
	
	public LogDto(int id, int total, int user, int member, Date date) {
		super();
		this.id = id;
		this.total = total;
		this.user = user;
		this.member = member;
		this.date = date;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getMember() {
		return member;
	}
	public void setMember(int member) {
		this.member = member;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "LogDto [id=" + id + ", total=" + total + ", user=" + user + ", member=" + member + ", date=" + date
				+ "]";
	}
	
	
}
