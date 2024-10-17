package Domain.Common.Service;

import java.util.List;

import Domain.Common.Dto.LogDto;

public interface LogService {

	//
	boolean addLog() throws Exception;

	boolean updateLog();

	LogDto getLogToday() throws Exception;

}