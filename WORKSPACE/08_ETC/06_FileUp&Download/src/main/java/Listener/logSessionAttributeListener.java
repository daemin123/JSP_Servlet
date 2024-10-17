package Listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import Domain.Common.Service.LogService;
import Domain.Common.Service.LogServiceImpl;
import Listener.log.State;


public class logSessionAttributeListener  implements HttpSessionAttributeListener {
		private LogService logService = LogServiceImpl.getInstance();

	  @Override
	  public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
	        System.out.println("HttpSessionAttributeListener 속성 추가: " + httpSessionBindingEvent.getName() + " = " + httpSessionBindingEvent.getValue() +
	                " (세션: " + httpSessionBindingEvent.getSession().getId() + ")");
	        
	        //ROLE확인
	        if(httpSessionBindingEvent.getName().equals("ROLE"))
	        {
	        	if(httpSessionBindingEvent.getValue().equals("ROLE_USER")){
	        		State.total=0;
	        		State.user=1;
	        		State.member=0;
	                System.out.printf("logSessionAttributeListener Total : %d user : %d member : %d\n",State.total,State.user,State.member);
	                logService.updateLog();				
	        	}
	        	else if(httpSessionBindingEvent.getValue().equals("ROLE_MEMBER")) {
	        		State.total=0;
	        		State.user=0;
	        		State.member=1;
	        		logService.updateLog();			
	        		System.out.printf("logSessionAttributeListener Total : %d user : %d member : %d\n",State.total,State.user,State.member);


	        	}
	        }
	        
	   }
}
