package Controller.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Service.BookService;
import Domain.Common.Service.BookServiceImpl;

public class BookUpdateController  implements SubController{

	private BookService service= BookServiceImpl.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("BookUpdateController execute");
		
	}

}
