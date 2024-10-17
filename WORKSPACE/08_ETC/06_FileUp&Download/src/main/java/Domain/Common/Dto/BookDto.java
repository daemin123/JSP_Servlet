package Domain.Common.Dto;


public class BookDto{
	private int bookcode;
	private String bookname;
	private String publisher;
	private String isbn;
	//getter and setter
	//생성자
	//toString재정의
	public BookDto() {}
	@Override
	public String toString() {
		return "BookDto [bookcode=" + bookcode + ", bookname=" + bookname + ", publisher=" + publisher + ", isbn="
				+ isbn + "]";
	}
	public BookDto(int bookcode, String bookname, String publisher, String isbn) {
		super();
		this.bookcode = bookcode;
		this.bookname = bookname;
		this.publisher = publisher;
		this.isbn = isbn;
	}
	public int getBookcode() {
		return bookcode;
	}
	public void setBookcode(int bookcode) {
		this.bookcode = bookcode;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
	
	
 
	
	
}
