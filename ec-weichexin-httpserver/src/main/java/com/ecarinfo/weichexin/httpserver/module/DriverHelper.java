package com.ecarinfo.weichexin.httpserver.module;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.DateUtils.TimeFormatter;
import com.ecarinfo.common.utils.ReportMaker;
import com.ecarinfo.frame.httpserver.core.annotation.MessageModule;
import com.ecarinfo.frame.httpserver.core.annotation.RequestURI;
import com.ecarinfo.frame.httpserver.core.bean.ResponseDto;
import com.ecarinfo.frame.httpserver.core.type.RequestMethod;
import com.ecarinfo.persist.criteria.Criteria;
import com.ecarinfo.persist.criteria.Criteria.CondtionSeparator;
import com.ecarinfo.persist.criteria.Criteria.OrderType;
import com.ecarinfo.persist.exdao.GenericDao;
import com.ecarinfo.traffic.rpc.api.TrafficSearchApi;
import com.ecarinfo.traffic.rpc.api.dto.TrafficInfoRPCDto;
import com.ecarinfo.weichexin.httpserver.dto.wcx.ActivityResponse;
import com.ecarinfo.weichexin.httpserver.service.WeichexinseviceImpl;
import com.ecarinfo.weichexin.httpserver.utils.ConfigUtils;
import com.ecarinfo.weichexin.httpserver.vo.CityVo;
import com.ecarinfo.weichexin.httpserver.vo.WcxTrafficCarsVo;
import com.ecarinfo.weichexin.po.EtTrafficItem;
import com.ecarinfo.weichexin.po.WcxTrafficCars;
import com.ecarinfo.weichexin.po.WcxUserInfo;
import com.ecarinfo.weichexin.rm.EtTrafficItemRM;
import com.ecarinfo.weichexin.rm.WcxTrafficCarsRM;
import com.ecarinfo.weichexin.rm.WcxUserInfoRM;

/**
 * 行车助手 
 * 
 */
@Component
@MessageModule("/driverHelper")
public class DriverHelper {

	private static final Logger logger = Logger.getLogger(DriverHelper.class);
	@Resource
	private WeichexinseviceImpl WeichexinseviceImpl;
	
	@Resource
	private GenericDao genericDao;
	
	@Resource
	private ConfigUtils configUtils;
	@Resource
	TrafficSearchApi trafficSearchApi;
	
	@RequestURI(value = "/model", method = RequestMethod.GET)
	public String model(String openid){
		String html = null;
		try {
			html = ReportMaker.exeute4Content(null, "jquery-mobile-model.ftl");
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	//活动列表
	@RequestURI(value = "/activityList", method = RequestMethod.GET)
	public String activityList(String org_code){
		String html = null;
		logger.info("org_code==============="+org_code);
		try {
			String ctx=configUtils.getImg_url();
			Map<String, Object> configs = new HashMap<String, Object>();
			ActivityResponse dtos=WeichexinseviceImpl.getActivityList("1", "111", 1, 1);
			configs.put("dtos",dtos);
			configs.put("ctx", ctx);
			html = ReportMaker.exeute4Content(configs, "assistant-activity-list.ftl");
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	//贴士列表
	@RequestURI(value = "/tipsList", method = RequestMethod.GET)
	public String tipsList(String openid){
		String html = null;
		try {
			String ctx=configUtils.getImg_url();
			Map<String, Object> configs = new HashMap<String, Object>();
			ActivityResponse dtos=WeichexinseviceImpl.getActivityList("1", "111", 1, 1);
			configs.put("dtos",dtos);
			configs.put("ctx", ctx);
			html = ReportMaker.exeute4Content(configs, "assistant-activity-list.ftl");
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	//违章查询 添加车辆页面
	@RequestURI(value = "/addList", method = RequestMethod.GET)
	public String addList(String openid,String org_code){
		String html = null;
		WcxUserInfo wu = this.genericDao.findOne(WcxUserInfo.class,new Criteria().eq(WcxUserInfoRM.openId,openid).eq(WcxUserInfoRM.orgCode, org_code, CondtionSeparator.AND) );
		Map<String, Object> configs = new HashMap<String, Object>();
		try {
			List<CityVo> list = this.WeichexinseviceImpl.getCityList(org_code);
			configs.put("orgCode",org_code);
			configs.put("uid",wu.getId());
			configs.put("volist", list);
			html = ReportMaker.exeute4Content(configs, "assistant-traffic-add.ftl");
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	//违章查询 添加车辆页面
	@RequestURI(value = "/add", method = RequestMethod.GET)
	public String add(Long uid,String org_code,String select_city,String carNo,String carFrame,String carEngineNo){
		String html = null;
		try {
			/*
			 * 最多3辆车
			 */
			List<WcxTrafficCars> list = this.WeichexinseviceImpl.getTrafficCars(null, org_code,uid);
			if(list!=null&&list.size()>=3){
				html = "最多可定制3辆车";
				return html;
			}
			WcxTrafficCars findOne = this.genericDao.findOne(WcxTrafficCars.class, new Criteria().eq(WcxTrafficCarsRM.wcxUserId,uid).eq(WcxTrafficCarsRM.orgCode, org_code,CondtionSeparator.AND).eq(WcxTrafficCarsRM.carNo, carNo, CondtionSeparator.AND));
			if(findOne!=null){
				html="车牌已存在！";
				return html;
			}
			else{
				WcxTrafficCars car = new WcxTrafficCars();
				car.setCarFrameNo(carFrame);
				car.setCarEngineNo(carEngineNo);
				car.setCarNo(carNo);
				car.setCity(select_city);
				car.setWcxUserId(uid);
				car.setOrgCode(org_code);
				car.setCtime(new Date());
				car.setStatus(1);
				this.genericDao.insert(car);
			}
			html = "true";
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
			html = "添加失败！";
		}
		return html;
	}
	
	//违章查询 修改车辆页面
	@RequestURI(value = "/update", method = RequestMethod.GET)
		public String update(Long carId,String org_code,String select_city,String carNo,String carFrame,String carEngineNo){
			String html = null;
			Map<String, Object> configs = new HashMap<String, Object>();
			try {
				WcxTrafficCars findOne = this.genericDao.findOne(WcxTrafficCars.class, new Criteria().eq(WcxTrafficCarsRM.orgCode, org_code).eq(WcxTrafficCarsRM.carNo, carNo, CondtionSeparator.AND)
											.notEquals(WcxTrafficCarsRM.id, carId, CondtionSeparator.AND));
				if(findOne!=null){
					html="车牌已存在！";
					return html;
				}
				Criteria whereBy=new Criteria();
				if(StringUtils.isNotBlank(select_city)){
					whereBy.update(WcxTrafficCarsRM.city, select_city);
				}
				if(StringUtils.isNotBlank(carNo)){
					whereBy.update(WcxTrafficCarsRM.carNo,carNo);
				}
				if(StringUtils.isNotBlank(carFrame)){
					whereBy.update(WcxTrafficCarsRM.carFrameNo,carFrame);
				}
				if(StringUtils.isNotBlank(carEngineNo)){
					whereBy.update(WcxTrafficCarsRM.carEngineNo,carEngineNo);
				}
				this.genericDao.updateWithCriteria(WcxTrafficCars.class, whereBy.update(WcxTrafficCarsRM.utime,DateUtils.dateToString(new Date(),
						TimeFormatter.FORMATTER1) ).eq(WcxTrafficCarsRM.pk, carId));
				html="true";
			} catch (Exception e) {
				logger.error("解析模板文件异常!", e);
				html="修改失败";
			}
			return html;
		}
	
	//删除
		@RequestURI(value = "/del", method = RequestMethod.POST)
			public String del(Long id){
				String html = null;
				Map<String, Object> configs = new HashMap<String, Object>();
				try {
					Criteria whereBy=new Criteria();
					
					this.genericDao.updateWithCriteria(WcxTrafficCars.class, whereBy.update(WcxTrafficCarsRM.utime,DateUtils.dateToString(new Date(),
							TimeFormatter.FORMATTER1) ).update(WcxTrafficCarsRM.status, 0).eq(WcxTrafficCarsRM.pk, id));
					html="true";
				} catch (Exception e) {
					logger.error("解析模板文件异常!", e);
					html="修改失败";
				}
				return html;
			}
		
		@RequestURI(value = "/upPage", method = RequestMethod.GET)
		public String uppage(Long id,String orgCode){
			String html = null;
			Map<String, Object> configs = new HashMap<String, Object>();
			try {
				WcxTrafficCars  car= this.genericDao.findByPK(WcxTrafficCars.class, id);
				List<CityVo> list = this.WeichexinseviceImpl.getCityList(orgCode);
				configs.put("car", car);
				configs.put("volist", list);
				html = ReportMaker.exeute4Content(configs, "assistant-traffic-alert.ftl");
			} catch (Exception e) {
				logger.error("解析模板文件异常!", e);
			}
			return html;
		}
	//违章查询 车辆列表
		@RequestURI(value = "/carList", method = RequestMethod.GET)
		public String carList(String org_code,String openid,Long uid){
			String html = null;
			try {
				List<WcxTrafficCarsVo> list = this.WeichexinseviceImpl.getTrafficItems(openid, org_code,uid);
				Map<String, Object> configs = new HashMap<String, Object>();
				if(list!=null&&list.size()>0){
					configs.put("size", list.size());
				}else{
					configs.put("size", "0");
				}
				configs.put("carList", list);
				configs.put("uid", uid);
				System.err.println("org_code:"+org_code);
				configs.put("orgCode", org_code);
				html = ReportMaker.exeute4Content(configs, "assistant-car-list.ftl");
			} catch (Exception e) {
				logger.error("解析模板文件异常!", e);
			}
			return html;
		}
		
	//违章查询 违章列表
	@RequestURI(value = "/detail", method = RequestMethod.GET)
	public String detail(String carNo){
		String html = null;
		try {
			List<TrafficInfoRPCDto> itemlist= trafficSearchApi.getTrafficInfos(carNo);//rpc 调用违章系统查询违章
//			List<EtTrafficItem> itemlist =	this.genericDao.findList(EtTrafficItem.class, new Criteria().eq(EtTrafficItemRM.carNo, carNo).eq(EtTrafficItemRM.isValid, 1, CondtionSeparator.AND).orderBy(EtTrafficItemRM.trafficTime, OrderType.ASC));
			Map<String, Object> configs = new HashMap<String, Object>();
			configs.put("itemlist", itemlist);
			configs.put("carNo", carNo);
			html = ReportMaker.exeute4Content(configs, "assistant-traffic-detail.ftl");
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
}
