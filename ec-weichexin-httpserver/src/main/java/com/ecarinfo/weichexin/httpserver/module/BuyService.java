package com.ecarinfo.weichexin.httpserver.module;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.DateUtils.TimeFormatter;
import com.ecarinfo.common.utils.ReportMaker;
import com.ecarinfo.frame.httpserver.core.annotation.MessageModule;
import com.ecarinfo.frame.httpserver.core.annotation.RequestURI;
import com.ecarinfo.frame.httpserver.core.type.RequestMethod;
import com.ecarinfo.persist.criteria.Criteria;
import com.ecarinfo.persist.criteria.Criteria.CondtionSeparator;
import com.ecarinfo.persist.exdao.GenericDao;
import com.ecarinfo.persist.service.GenericService;
import com.ecarinfo.weichexin.httpserver.service.WeichexinseviceImpl;
import com.ecarinfo.weichexin.httpserver.utils.ConfigUtils;
import com.ecarinfo.weichexin.httpserver.vo.CarModelVO;
import com.ecarinfo.weichexin.httpserver.vo.CarSerialImagesVO;
import com.ecarinfo.weichexin.httpserver.vo.HistoryVO;
import com.ecarinfo.weichexin.po.Activity;
import com.ecarinfo.weichexin.po.CarModel;
import com.ecarinfo.weichexin.po.CarModelYouhui;
import com.ecarinfo.weichexin.po.CarSerial;
import com.ecarinfo.weichexin.po.CarSerialGroup;
import com.ecarinfo.weichexin.po.CarSerialImages;
import com.ecarinfo.weichexin.po.ServiceOrg;
import com.ecarinfo.weichexin.po.TestdriveAppoint;
import com.ecarinfo.weichexin.po.WcxShowCarModels;
import com.ecarinfo.weichexin.rm.CarModelYouhuiRM;
import com.ecarinfo.weichexin.rm.CarSerialImagesRM;
import com.ecarinfo.weichexin.rm.TestdriveAppointRM;
import com.ecarinfo.weichexin.rm.WcxShowCarModelsRM;
import com.ecarinfo.yunying.service.EYProvinceAndCityService;

/**
 * 购车服务
 */
@Component
@MessageModule("/buy")
public class BuyService {
	private static final Logger logger = Logger.getLogger(BuyService.class);
	
	@Resource
	GenericService genericService;
	
	@Resource
	GenericDao genericDao;
	
	@Resource
	ConfigUtils configUtils;
	
	@Resource
	WeichexinseviceImpl weichexinseviceImpl;
	@Resource
	EYProvinceAndCityService eyProvinceAndCityService;
	
	//车系列表
	@RequestURI(value="/serial_list")
	public String buyService(Integer orgId,String openId,Integer groupId,String orgCode ){
		String html = null;
		try {
			Map<String, Object> configs = new HashMap<String, Object>();
			ServiceOrg org= this.genericService.findByPK(ServiceOrg.class, orgId);
			//精彩活动图片
			List<Activity> activityList = this.weichexinseviceImpl.activityModel(openId,org.getCode(),3,1,true);
			CarSerialGroup group = genericDao.findByPK(CarSerialGroup.class, groupId);
			List<CarSerialImagesVO> list = weichexinseviceImpl.getCarSerialImagesByGroupId(orgId, groupId);
			String ctx=configUtils.getImg_url(); 
			configs.put("openId", openId);
			configs.put("orgCode", orgCode);
			configs.put("orgId", orgId);
			configs.put("ctx", ctx);
			configs.put("list", list);
			configs.put("activityList", activityList);
			configs.put("groupName", group.getName());
			html = ReportMaker.exeute4Content(configs, "/buyservice/car-serial-list.ftl");//未预约
			
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	//车 型列表
	@RequestURI(value="/serial_detail")
	public String serialDetail(Integer orgId,String openId,Integer serialId ){
		String html = null;
			try {
				Map<String, Object> configs = new HashMap<String, Object>();
				CarSerial serial = genericDao.findByPK(CarSerial.class, serialId);
				CarSerialImages image=genericService.findOne(CarSerialImages.class, new Criteria().eq(CarSerialImagesRM.serialId, serialId));
				configs.put("serial", serial);
				List<CarModelVO> list = weichexinseviceImpl.getCarModelBySerialId(orgId, serialId);
				configs.put("openId", openId);
				configs.put("orgId", orgId);
				configs.put("list", list);
				ServiceOrg serviceOrg=genericService.findByPK(ServiceOrg.class, orgId);
				configs.put("phoneNo", serviceOrg.getTelForSales());//销售电话
				
				
				String ctx=configUtils.getImg_url(); 
				if(image !=null && image.getUrl()!=null){
					configs.put("imageUrl", ctx+image.getUrl());
				}
				html = ReportMaker.exeute4Content(configs, "/buyservice/car-model-list.ftl");//未预约
				
			} catch (Exception e) {
				logger.error("解析模板文件异常!", e);
			}
			return html;
		}
	
	//试驾
	@RequestURI(value="/test_drive")
	public String testDrive(Integer orgId,String openId,Integer modelId ){
		String html = null;
			try {
				Map<String, Object> configs = new HashMap<String, Object>();
				CarModel model = genericDao.findByPK(CarModel.class, modelId);
				configs.put("model", model);
				configs.put("openId", openId);
				configs.put("orgId", orgId);
				html = ReportMaker.exeute4Content(configs, "/buyservice/test-drive-add.ftl");//未预约
				
			} catch (Exception e) {
				logger.error("解析模板文件异常!", e);
			}
			return html;
	}

	//试驾预约 添加
	@RequestURI(value="/add", method = RequestMethod.POST)
	public String add(Integer orgId,String openId,Integer modelId,String appointTime,String telNo,String name ,String comment){
		String  html = "true";
		
		if(modelId==null||modelId<1){
			html = "未选择车型";
			return html;
		}
		if(com.alibaba.dubbo.common.utils.StringUtils.isEmpty(appointTime)){
			html ="未选择预约时间";
			return html;
		}
		TestdriveAppoint oppoint = new TestdriveAppoint();
		oppoint.setModelId(modelId);
		oppoint.setName(name);
		oppoint.setOpenId(openId);
		oppoint.setComment(comment);
		oppoint.setStatus(0);
		oppoint.setTelNo(telNo);
		oppoint.setRequestTime(new Date());
		oppoint.setOrgId(new Long(orgId));
		oppoint.setAppointTime(DateUtils.stringToDate(appointTime, TimeFormatter.FORMATTER2));
		this.genericDao.insert(oppoint);
		return html;
	}
	
	//预约历史
	@RequestURI(value="/history", method = RequestMethod.GET)
	public String history(Integer orgId,String openId){
		String html = null;
		try {
			Map<String, Object> configs = new HashMap<String, Object>();
			List<HistoryVO> list = this.weichexinseviceImpl.getHistoryList(openId,orgId);
			configs.put("list", list);
			configs.put("openId", openId);
			configs.put("orgId", orgId);
			html = ReportMaker.exeute4Content(configs, "/buyservice/test-drive-history.ftl");
			
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	//取消预约
	@RequestURI(value="/cancel", method = RequestMethod.POST)
	public String cancel(Integer id){
		String html = "false";
		try {
			this.genericService.updateWithCriteria(TestdriveAppoint.class, new Criteria().update(TestdriveAppointRM.status, 2).eq(TestdriveAppointRM.pk, id));
			html ="true";
			return html;
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
			html="false";
		}
		return html;
	}
	
	//车型展示
	@RequestURI(value="/showModel", method = RequestMethod.GET)
	public String showModel(Integer orgId,String openId,Integer modelId){
		String html = null;
		try {
			Map<String, Object> configs = new HashMap<String, Object>();
			CarModel model = genericService.findByPK(CarModel.class, modelId);
			CarSerial carserial = genericService.findByPK(CarSerial.class, model.getSerialId());
			WcxShowCarModels showmodel =  genericService.findOne(WcxShowCarModels.class, new Criteria().eq(WcxShowCarModelsRM.orgId,orgId).eq(WcxShowCarModelsRM.modelId, modelId, CondtionSeparator.AND));
			List<CarSerialImages> images = genericService.findList(CarSerialImages.class, new Criteria().eq(CarSerialImagesRM.status, 1).eq(CarSerialImagesRM.serialId, model.getSerialId(), CondtionSeparator.AND));
			List<CarModelYouhui> youhui = genericService.findList(CarModelYouhui.class, new Criteria()
				.eq(CarModelYouhuiRM.status, 1)
				.eq(CarModelYouhuiRM.modelId, modelId, CondtionSeparator.AND)
				.lessThenOrEquals(CarModelYouhuiRM.btime,DateUtils.dateToString(new Date(), TimeFormatter.FORMATTER2) , CondtionSeparator.AND)
				.greateThenOrEquals(CarModelYouhuiRM.etime, DateUtils.dateToString(new Date(), TimeFormatter.FORMATTER2), CondtionSeparator.AND)
					);
			String ctx = configUtils.getImg_url(); 
			configs.put("model", model);
			configs.put("showmodel", showmodel);
			configs.put("openId", openId);
			configs.put("orgId", orgId);
			configs.put("ctx", ctx);
			configs.put("youhui", youhui);
			if(youhui!=null && youhui.size()>0 &&youhui.get(0).getHasGift()){
				configs.put("li", "li");
			}
			configs.put("carserial", carserial);
			if(images !=null && images.size()>0){
				configs.put("images", images);
				configs.put("imageUrl", ctx+images.get(0).getUrl());
			}
			ServiceOrg serviceOrg=genericService.findByPK(ServiceOrg.class, orgId);
			configs.put("phoneNo", serviceOrg.getTelForSales());//销售电话
			html = ReportMaker.exeute4Content(configs, "/buyservice/car-model-show.ftl");
			
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
}
