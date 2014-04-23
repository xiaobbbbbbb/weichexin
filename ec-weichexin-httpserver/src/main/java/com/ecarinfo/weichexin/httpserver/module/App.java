package com.ecarinfo.weichexin.httpserver.module;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.common.utils.ReportMaker;
import com.ecarinfo.frame.httpserver.core.annotation.MessageModule;
import com.ecarinfo.frame.httpserver.core.annotation.RequestURI;
import com.ecarinfo.frame.httpserver.core.type.RequestMethod;

/**
 * 用车管家 
 */
@Component
@MessageModule("/app")
public class App {

	private static final Logger logger = Logger.getLogger(App.class);
	
	//下载app
	@RequestURI(value = "/download", method = RequestMethod.GET)
	public String download(String org_code){
		String html = null;
		System.out.println("org_code====="+org_code);
		try {
			html = ReportMaker.exeute4Content(null, "app-download.ftl");
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	//android
	@RequestURI(value = "/android", method = RequestMethod.GET)
	public String android(String org_code){
		String html = null;
		System.out.println("org_code====="+org_code);
		try {
			html = ReportMaker.exeute4Content(null, "app-download-android.ftl");
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
		//ios
		@RequestURI(value = "/ios", method = RequestMethod.GET)
		public String ios(String org_code){
			String html = null;
			System.out.println("org_code====="+org_code);
			try {
				html = ReportMaker.exeute4Content(null, "app-download-ios.ftl");
			} catch (Exception e) {
				logger.error("解析模板文件异常!", e);
			}
			return html;
		}
	
}
