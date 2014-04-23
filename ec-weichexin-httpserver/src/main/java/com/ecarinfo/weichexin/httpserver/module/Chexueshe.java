package com.ecarinfo.weichexin.httpserver.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.common.utils.ReportMaker;
import com.ecarinfo.frame.httpserver.core.annotation.MessageModule;
import com.ecarinfo.frame.httpserver.core.annotation.RequestURI;
import com.ecarinfo.frame.httpserver.core.type.RequestMethod;
import com.ecarinfo.persist.exdao.GenericDao;
import com.ecarinfo.weichexin.httpserver.dto.wcx.ActivityResponse;
import com.ecarinfo.weichexin.httpserver.service.WeichexinseviceImpl;
import com.ecarinfo.weichexin.httpserver.utils.ConfigUtils;
import com.ecarinfo.weichexin.po.Activity;
import com.ecarinfo.weichexin.po.CarTip;

/**
 *车学社
 */
@Component
@MessageModule("/tips")
public class Chexueshe {

	private static final Logger logger = Logger.getLogger(Chexueshe.class);
	
	@Resource
	private WeichexinseviceImpl WeichexinseviceImpl;
	
	@Resource
	ConfigUtils configUtils;
	
	@Resource
	GenericDao genericDao;
	
	//贴士列表
	@RequestURI(value = "/list", method = RequestMethod.GET)
	public String list(String openid,String org_code){
		String html = null;
		logger.info("org_code==============="+org_code);
		try {
			String ctx=configUtils.getImg_url();
			Map<String, Object> configs = new HashMap<String, Object>();
			List<CarTip> dtos= this.WeichexinseviceImpl.tipsModel(openid, org_code, null);
			configs.put("dtos",dtos);
			configs.put("ctx", ctx);
			html = ReportMaker.exeute4Content(configs, "assistant-tips-list.ftl");
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	//贴士详情
	@RequestURI(value = "/detail", method = RequestMethod.GET)
	public String detail(Integer id){
		String html = null;
		CarTip tip = this.genericDao.findByPK(CarTip.class, id);
		tip.setWcxClickNums(tip.getWcxClickNums()+1);
		this.genericDao.update(tip);
		String ctx=configUtils.getImg_url();
		logger.info("org_code==============="+id);
		try {
			if(tip.getImage()!=null&&!"".equals(tip.getImage())){
				int lastIndex= tip.getImage().lastIndexOf(".");
				String s= tip.getImage().substring(0, lastIndex);
				tip.setImage(s+"_640x392.jpg");
			}
			Map<String, Object> configs = new HashMap<String, Object>();
			configs.put("tip", tip);
			configs.put("ctx", ctx);
			html = ReportMaker.exeute4Content(configs, "assistant-tips-detail.ftl");
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
}
