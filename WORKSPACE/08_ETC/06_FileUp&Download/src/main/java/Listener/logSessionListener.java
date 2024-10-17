package Listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import Domain.Common.Service.LogService;
import Domain.Common.Service.LogServiceImpl;
import Listener.log.State;


public class logSessionListener implements HttpSessionListener {
	private LogService logService = LogServiceImpl.getInstance();
	@Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("세션 생성: " + httpSessionEvent.getSession().getId());
        //세션생성시 ServletContext의 속성중 TotalCnt의 값증가
        State.total=1;	//전체 접속수 증가
        State.user=0;	//user = 0
        State.member=0;	//member=0;

        logService.updateLog();
        System.out.printf("logSessionListener Total : %d user : %d member : %d\n",State.total,State.user,State.member);
        
     
        
        
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("세션 소멸: " + httpSessionEvent.getSession().getId());
    }
}
