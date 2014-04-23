package com.ecarinfo.weichexin.httpserver;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecarinfo.frame.httpserver.core.http.ECHttpServer;
import com.ecarinfo.weichexin.httpserver.utils.ConfigUtils;



public class WCXHttpServer {
	static Logger logger = Logger.getLogger(WCXHttpServer.class);
	static {
		
	}
	
    public static void main( String[] args )
    {
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    	logger.info("Spring container of WXHttpServer init finished...");
    	ConfigUtils config = (ConfigUtils)ctx.getBean("configUtils");
    	int port = config.getPort();
    	new ECHttpServer(ctx, port).setStaticRootPath(config.getStaticRootPath()).run();
    }
}
