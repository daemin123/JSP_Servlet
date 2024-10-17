package Domain.Common.Service;

import java.sql.Date;

import Domain.Common.Dao.LogDao;
import Domain.Common.Dao.LogDaoImpl;
import Domain.Common.Dto.LogDto;

public class LogServiceImpl implements LogService {

	private LogDao dao;
	
	// 싱글톤
	private static LogService instance;

	public static LogService getInstance() {
		if (instance == null)
			instance = new LogServiceImpl();
		return instance;
	}
	//
	private LogServiceImpl() {
		dao = LogDaoImpl.getInstance();
	}
	
	//
	@Override
	public boolean addLog() throws Exception{
		Date curdate = new Date(System.currentTimeMillis());
		System.out.println("LogServiceImpl's addLog Func.. curdate : " +curdate);
		LogDto dto =  dao.select(curdate);
		if(dto.getDate()==null)
			return dao.insert()>0;
		
		System.out.println("기존 로그가 있습니다.");
		return false;
	}
	
	@Override
	public boolean updateLog() {
		int num=0;
		try {
			num = dao.update();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num>0;
		
	}
	@Override
	public LogDto getLogToday() throws Exception{
		Date curdate = new Date(System.currentTimeMillis());
		
		return dao.select(curdate);
	}
	

}
