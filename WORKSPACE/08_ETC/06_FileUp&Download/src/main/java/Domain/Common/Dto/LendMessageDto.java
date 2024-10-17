package Domain.Common.Dto;

public class LendMessageDto {
	private int msgId;
	private String userId;
	private String msg;
	@Override
	public String toString() {
		return "LendMessageDto [msgId=" + msgId + ", userId=" + userId + ", msg=" + msg + "]";
	}
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public LendMessageDto(int msgId, String userId, String msg) {
		super();
		this.msgId = msgId;
		this.userId = userId;
		this.msg = msg;
	}
	public LendMessageDto() {}
	//toString
	//getter and Setter
	//기본생성자
	//모든 인자 생성자
	
	
}
