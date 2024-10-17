package Controller.lend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Controller.SubController;
import Domain.Common.Dto.LendDto;
import Domain.Common.Service.LendService;
import Domain.Common.Service.LendServiceImpl;

public class LendSearchController  implements SubController{
	private LendService service= LendServiceImpl.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("LendSearchController execute");
		//01
		try {
		//02
		
		//03
		List<LendDto> list = 	service.getLendList(req);
		
		
		//04
		
		
		// JAVA -> JSON 변환
		ObjectMapper objectMapper = new ObjectMapper();	//날짜정보도 포함되어있음..
		
		String jsonConverted = objectMapper.writeValueAsString(list);
		
		// 4 View로 전달			
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.print(jsonConverted);
		
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
