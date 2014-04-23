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
import com.ecarinfo.weichexin.po.CarModel;
import com.ecarinfo.weichexin.po.CarSerial;
import com.ecarinfo.weichexin.po.CarSerialGroup;
import com.ecarinfo.weichexin.po.CarSerialImages;
import com.ecarinfo.weichexin.po.ServiceOrg;
import com.ecarinfo.weichexin.po.TestdriveAppoint;
import com.ecarinfo.weichexin.rm.CarSerialImagesRM;
import com.ecarinfo.weichexin.rm.TestdriveAppointRM;
import com.ecarinfo.yunying.service.EYProvinceAndCityService;

/**
 * 试乘试驾
 */
@Component
@MessageModule("/drive")
public class TestDriveService {
	private static final Logger logger = Logger.getLogger(TestDriveService.class);
	
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
	@RequestURI(value="/group_list")
	public String groupList(Integer orgId,String openId ){
		String html = null;
		try {
			Map<String, Object> configs = new HashMap<String, Object>();
			List<CarSerialGroup> list = weichexinseviceImpl.getCarSerialGroup(orgId);
			configs.put("openId", openId);
			configs.put("orgId", orgId);
			configs.put("list", list);
			html = ReportMaker.exeute4Content(configs, "/testdrive/car-group-list.ftl");//未预约
			
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	//车系列表
	@RequestURI(value="/serial_list")
	public String buyService(Integer orgId,String openId,Integer groupId ){
		String html = null;
		try {
			Map<String, Object> configs = new HashMap<String, Object>();
			CarSerialGroup group = genericDao.findByPK(CarSerialGroup.class, groupId);
			List<CarSerial> list = weichexinseviceImpl.getCarSerialByGroupId(orgId, groupId);
			List<CarSerialImagesVO> carSerialImagesVO = weichexinseviceImpl.getCarSerialImagesByGroupId(orgId, groupId);
			configs.put("openId", openId);
			configs.put("orgId", orgId);
			configs.put("list", list);
			configs.put("carSerialImagesVO", carSerialImagesVO);
			configs.put("groupName", group.getName());
			configs.put("ctx", configUtils.getImg_url());
			html = ReportMaker.exeute4Content(configs, "/testdrive/car-serial-list.ftl");//未预约
			
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
				CarSerialImages image = genericDao.findOne(CarSerialImages.class,new  Criteria().eq(CarSerialImagesRM.orgId,orgId).eq(CarSerialImagesRM.serialId, serialId,CondtionSeparator.AND).eq(CarSerialImagesRM.status, 1, CondtionSeparator.AND));
				String imageUrl=null;
				
				if(null!=image){
					imageUrl= configUtils.getImg_url()+image.getUrl();
				}
				configs.put("imageUrl", imageUrl);
				configs.put("serial", serial);
				List<CarModelVO> list = weichexinseviceImpl.getCarModelBySerialId(orgId, serialId);
				configs.put("openId", openId);
				configs.put("orgId", orgId);
				configs.put("list", list);
				ServiceOrg serviceOrg=genericService.findByPK(ServiceOrg.class, orgId);
				configs.put("phoneNo", serviceOrg.getTelForSales());//销售电话
				html = ReportMaker.exeute4Content(configs, "/testdrive/car-model-list.ftl");//未预约
				
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
				html = ReportMaker.exeute4Content(configs, "/testdrive/test-drive-add.ftl");//未预约
				
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
	public String history(Integer orgId,String openId,String modelId){
		String html = null;
		try {
			Map<String, Object> configs = new HashMap<String, Object>();
			List<HistoryVO> list = this.weichexinseviceImpl.getHistoryList(openId,orgId);
			configs.put("list", list);
			configs.put("openId", openId);
			configs.put("orgId", orgId);
			configs.put("modelId", modelId);
			html = ReportMaker.exeute4Content(configs, "/testdrive/test-drive-history.ftl");
			
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
			logger.error("取消失败!", e);
			html="false";
		}
		return html;
	}
	
}
