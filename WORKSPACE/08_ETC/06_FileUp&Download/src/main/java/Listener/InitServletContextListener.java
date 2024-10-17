package Listener;


import java.net.InetAddress;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;

import Domain.Common.Dao.ConnectionPool;
import Domain.Common.Service.LogService;
import Domain.Common.Service.LogServiceImpl;


public class InitServletContextListener implements ServletContextListener{
	private LogService logService = LogServiceImpl.getInstance();
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("C01ServletContextListener's contextInitialized Call!");
		try {
		InetAddress serverIP  = InetAddress.getLocalHost();
		System.out.println("Server IP : " + serverIP);
		serverIP.getHostAddress();
		//-------------
		if(ConnectionPool.conn==null) {
			//create Connection
			ConnectionPool.createConnection(serverIP.getHostAddress());
		}
		
		//--------------
		
			logService.addLog();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//---------------
		
		
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("C01ServletContextListener's contextDestroyed Call!");

	}

	

	
}