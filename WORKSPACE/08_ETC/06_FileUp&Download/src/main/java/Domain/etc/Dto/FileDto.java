package Domain.etc.Dto;

public class FileDto {
	private int id;
	private String user;
	private String dir;
	private String filename;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Override
	public String toString() {
		return "FileDto [id=" + id + ", user=" + user + ", dir=" + dir + ", filename=" + filename + "]";
	}
	public FileDto(int id, String user, String dir, String filename) {
		super();
		this.id = id;
		this.user = user;
		this.dir = dir;
		this.filename = filename;
	}
	public FileDto() {}
	
	
}
