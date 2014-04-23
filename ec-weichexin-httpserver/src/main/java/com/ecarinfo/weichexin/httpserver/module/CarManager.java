package com.ecarinfo.weichexin.httpserver.module;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.common.utils.BeanUtils;
import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.DateUtils.TimeFormatter;
import com.ecarinfo.common.utils.ReportMaker;
import com.ecarinfo.core.dobbo.service.ECCarInfoService;
import com.ecarinfo.core.rpc.vo.ECCarInfoItemVO;
import com.ecarinfo.core.rpc.vo.ECCarRankingVO;
import com.ecarinfo.frame.httpserver.core.annotation.MessageModule;
import com.ecarinfo.frame.httpserver.core.annotation.RequestURI;
import com.ecarinfo.frame.httpserver.core.type.RequestMethod;
import com.ecarinfo.persist.criteria.Criteria;
import com.ecarinfo.persist.criteria.Criteria.CondtionSeparator;
import com.ecarinfo.persist.exdao.GenericDao;
import com.ecarinfo.persist.service.GenericService;
import com.ecarinfo.server.dobbo.service.ESCarNoticeService;
import com.ecarinfo.server.dubbo.dto.CarNoticeDto;
import com.ecarinfo.weichexin.po.FivesaasCarInfo;
import com.ecarinfo.weichexin.po.WcxUserInfo;
import com.ecarinfo.weichexin.rm.FivesaasCarInfoRM;
import com.ecarinfo.weichexin.rm.WcxUserInfoRM;

/**
 * 用车管家 
 */
@Component
@MessageModule("/carManager")
public class CarManager {

	private static final Logger logger = Logger.getLogger(CarManager.class);
	
	@Resource
	private ECCarInfoService ecCarInfoService;
	
	@Resource
	private GenericService genericService;
	
	@Resource
	private GenericDao genericDao;
	
	@Resource 
	private ESCarNoticeService esCarNoticeService;

	//提醒设置
	@RequestURI(value = "/setRemind", method = RequestMethod.GET)
	public String setRemind(String openid,String org_code){
		String html = null;
		try {
			/*
			 * 判断是否有过设置过车辆
			 */
			WcxUserInfo wui = this.genericDao.findOne(WcxUserInfo.class,new Criteria().eq(WcxUserInfoRM.openId,openid).eq(WcxUserInfoRM.orgCode, org_code, CondtionSeparator.AND));
			FivesaasCarInfo five = this.genericDao.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.wcxUserId, wui.getId()).eq(FivesaasCarInfoRM.orgCode, org_code,  CondtionSeparator.AND));
			ECCarRankingVO vo =null;
			Map<String, Object> configs = new HashMap<String, Object>();
			if(five==null){//没有设置进入设置界面
				
				configs.put("orgCode", wui.getOrgCode());
				configs.put("uid", wui.getId());
				
				html = ReportMaker.exeute4Content(configs, "5s-setting.ftl");//设置
//			}else if(five!=null&&five.getDeviceNo()!=null){
//				
//				html = ReportMaker.exeute4Content(null, "manager-report.ftl");
			}else{
				if(five.getMaintenanceNoticeTime()!=null
						||five.getToMMileage()!=null||
						five.getCurrentMileage()!=null||
						five.getYearCheckDate()!=null||
						five.getRenewInsuranceDate()!=null||
						five.getDrivingLicenseYearCareDate()!=null||
						five.getDrivingLicenseExpireDate()!=null
						){
					
						configs.put("bydate", reduceDays(five.getToMTime()));
						configs.put("rbydate", getYMD(five.getToMTime()));
						//里程
//						configs.put("mileage",five.getToMMileage()!=null?five.getToMMileage().toString():"");
//						configs.put("currentMileage",five.getCurrentMileage()!=null?five.getCurrentMileage().toString():"");
						//里程
//						configs.put("mileage",five.getToMMileage()!=null?((int)five.getToMMileage().intValue()):"");
						configs.put("currentMileage",five.getCurrentMileage()!=null?five.getCurrentMileage().toString():"0");
						if(five.getToMMileage()!=null){
							if(five.getCurrentMileage()!=null){
								configs.put("mileage",(five.getToMMileage()-five.getCurrentMileage())>0?(five.getToMMileage()-five.getCurrentMileage()):0);
							}else{
								configs.put("mileage",five.getToMMileage());
							}
						}
						//年检日期
						configs.put("yearCheckDate",reduceDays(five.getYearCheckDate()));
						configs.put("ryearCheckDate",getYMD(five.getYearCheckDate()));
						//续保
						configs.put("xbdate",reduceDays(five.getRenewInsuranceDate()));
						configs.put("rxbdate",getYMD(five.getRenewInsuranceDate()));
						//驾照年审
						configs.put("licenseYearDate",reduceDays(five.getDrivingLicenseYearCareDate()));
						configs.put("rlicenseYearDate",getYMD(five.getDrivingLicenseYearCareDate()));
						//换证
						configs.put("changeDate",reduceDays(five.getDrivingLicenseExpireDate()));
						configs.put("rchangeDate",getYMD(five.getDrivingLicenseExpireDate()));
						configs.put("uid", wui.getId());
						configs.put("orgCode", wui.getOrgCode());
						html = ReportMaker.exeute4Content(configs, "manager-remind-list.ftl");
					}else{
					
					configs.put("orgCode", wui.getOrgCode());
					configs.put("uid", wui.getId());
					html = ReportMaker.exeute4Content(configs, "manager-remind-add.ftl");
				}
			}
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	//提醒设置
	@RequestURI(value = "/addRemind", method = RequestMethod.GET)
	public String addRemind(Long uid,String org_code,String bydate,Float bymiles,String njdate,String xbdate,String licenseDate,String changedate){
		String html = null;
		try {
			FivesaasCarInfo five = this.genericDao.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.wcxUserId, uid).eq(FivesaasCarInfoRM.orgCode, org_code, CondtionSeparator.AND));
				Criteria whereBy=new Criteria();
				CarNoticeDto dto = new CarNoticeDto();
				this.updateMaintanceNoticeTime(five, bydate, bymiles);
				if(five.getMaintenanceNoticeTime()!=null){
					whereBy.update(FivesaasCarInfoRM.maintenanceNoticeTime, DateUtils.dateToString(five.getMaintenanceNoticeTime(),TimeFormatter.FORMATTER1));
				}else{
					whereBy.update(FivesaasCarInfoRM.maintenanceNoticeTime, null);
				}
				if(StringUtils.isNotBlank(bydate)){//保养时间
					whereBy.update(FivesaasCarInfoRM.toMTime, bydate);
					dto.setNextMaintenanceTime(DateUtils.stringToDate(bydate,TimeFormatter.YYYY_MM_DD ));
				}
				if(bymiles!=null&&bymiles>0){//保养里程
					whereBy.update(FivesaasCarInfoRM.toMMileage, bymiles);
					dto.setNextMaintenanceMile(bymiles.intValue());
				}
				if(StringUtils.isNotBlank(njdate)){//年检日期
					whereBy.update(FivesaasCarInfoRM.yearCheckDate, njdate);
					dto.setYearCheckTime(DateUtils.stringToDate(njdate,TimeFormatter.YYYY_MM_DD ));
				}
				if(StringUtils.isNotBlank(xbdate)){//续保日期
					whereBy.update(FivesaasCarInfoRM.renewInsuranceDate, xbdate);
					dto.setXubaoNoticeTime(DateUtils.stringToDate(xbdate,TimeFormatter.YYYY_MM_DD ));
				}
				if(StringUtils.isNotBlank(licenseDate)){//驾照年审
					whereBy.update(FivesaasCarInfoRM.drivingLicenseYearCareDate, licenseDate);
					dto.setDriveLiceiceSlaveTime(DateUtils.stringToDate(licenseDate,TimeFormatter.YYYY_MM_DD ));
				}
				if(StringUtils.isNotBlank(changedate)){//驾照换证
					whereBy.update(FivesaasCarInfoRM.drivingLicenseExpireDate, changedate);
					dto.setDriveLiceiceExpiredTime(DateUtils.stringToDate(changedate,TimeFormatter.YYYY_MM_DD ));
				}
				this.genericService.updateWithCriteria(
						FivesaasCarInfo.class,
						whereBy.eq(FivesaasCarInfoRM.wcxUserId, uid).eq(FivesaasCarInfoRM.orgCode, org_code, CondtionSeparator.AND));
				if(five.getCarSource()!=1){//同步中心
					logger.info("----------REMIND    SEND  START-----------------");
					dto.setCarId(five.getCarId());
					esCarNoticeService.updateNotice(dto);
					logger.info("----------REMIND    SEND  end-----------------");
				}
			html = "true";
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
			html = "false";
		}
		return html;
	}
	
	//修改提醒
	@RequestURI(value = "/alert", method = RequestMethod.GET)
	public String alertRemind(Long uid,String org_code){
		String html = null;
		Map<String, Object> configs = new HashMap<String, Object>();
		try{
			FivesaasCarInfo five = this.genericDao.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.wcxUserId, uid).eq(FivesaasCarInfoRM.orgCode, org_code, CondtionSeparator.AND));
			//保养时间
			configs.put("bydate", five.getToMTime()!=null?DateUtils.dateToString(five.getToMTime(),
					TimeFormatter.YYYY_MM_DD):"");
			//里程
			configs.put("mileage",five.getToMMileage()!=null?five.getToMMileage().toString():"");
			//年检日期
			configs.put("yearCheckDate",five.getYearCheckDate()!=null?DateUtils.dateToString(five.getYearCheckDate(),
					TimeFormatter.YYYY_MM_DD):"");
			//续保
			configs.put("xbdate",five.getRenewInsuranceDate()!=null?DateUtils.dateToString(five.getRenewInsuranceDate(),
					TimeFormatter.YYYY_MM_DD):"");
			//驾照年审
			configs.put("licenseYearDate",five.getDrivingLicenseYearCareDate()!=null?DateUtils.dateToString(five.getDrivingLicenseYearCareDate(),
					TimeFormatter.YYYY_MM_DD):"");
			//换证
			configs.put("changeDate",five.getDrivingLicenseExpireDate()!=null?DateUtils.dateToString(five.getDrivingLicenseExpireDate(),
					TimeFormatter.YYYY_MM_DD):"");
			configs.put("uid",uid);
			configs.put("orgCode",org_code);
			html = ReportMaker.exeute4Content(configs, "manager-remind-alert.ftl");
		}
		catch(Exception e){
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	//提醒列表
	@RequestURI(value = "/remindList", method = RequestMethod.GET)
	public String remindList(Long uid,String org_code){
		Map<String, Object> configs = new HashMap<String, Object>();
		String html = null;
		try {
			FivesaasCarInfo five = this.genericDao.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.wcxUserId, uid).eq(FivesaasCarInfoRM.orgCode, org_code, CondtionSeparator.AND));
			//保养时间
			configs.put("bydate", reduceDays(five.getToMTime()));
			configs.put("rbydate", getYMD(five.getToMTime()));
			//里程
//			configs.put("mileage",five.getToMMileage()!=null?((int)five.getToMMileage().intValue()):"");
			configs.put("currentMileage",five.getCurrentMileage()!=null?five.getCurrentMileage().toString():"");
			if(five.getToMMileage()!=null){
				if(five.getCurrentMileage()!=null){
					configs.put("mileage",(five.getToMMileage()-five.getCurrentMileage())>0?(five.getToMMileage()-five.getCurrentMileage()):0);
				}else{
					configs.put("mileage",five.getToMMileage());
				}
			}
			
			//年检日期
			configs.put("yearCheckDate",reduceDays(five.getYearCheckDate()));
			configs.put("ryearCheckDate",getYMD(five.getYearCheckDate()));
			//续保
			configs.put("xbdate",reduceDays(five.getRenewInsuranceDate()));
			configs.put("rxbdate",getYMD(five.getRenewInsuranceDate()));
			//驾照年审
			configs.put("licenseYearDate",reduceDays(five.getDrivingLicenseYearCareDate()));
			configs.put("rlicenseYearDate",getYMD(five.getDrivingLicenseYearCareDate()));
			//换证
			configs.put("changeDate",reduceDays(five.getDrivingLicenseExpireDate()));
			configs.put("rchangeDate",getYMD(five.getDrivingLicenseExpireDate()));
			configs.put("uid",uid);
			configs.put("orgCode",org_code);
			html = ReportMaker.exeute4Content(configs, "manager-remind-list.ftl");
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	//用车达人-排行
	@RequestURI(value = "/rank", method = RequestMethod.GET)
	public String rank(String openid,String org_code){
		String html = null;
		try {
			
			/*
			 * 判断是否有过设置过车辆
			 */
			WcxUserInfo wui = this.genericDao.findOne(WcxUserInfo.class,new Criteria().eq(WcxUserInfoRM.openId,openid).eq(WcxUserInfoRM.orgCode, org_code, CondtionSeparator.AND));
			FivesaasCarInfo five = this.genericDao.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.wcxUserId, wui.getId()).eq(FivesaasCarInfoRM.orgCode, org_code, CondtionSeparator.AND).eq(FivesaasCarInfoRM.carSource, 0,CondtionSeparator.AND));
			ECCarRankingVO vo =null;
			Map<String, Object> configs = new HashMap<String, Object>();
			if(five!=null){
				logger.error("carNo========"+five.getCarNo()+"===five.getModelId()===="+five.getModelId());
				 vo = this.ecCarInfoService.getOilRanking(five.getCarNo(),null, 20);
				 configs.put("deviceNo",five.getDeviceNo()!=null?five.getDeviceNo():"");
//				 logger.error("111111"+BeanUtils.toString(vo));
			}else{
				 vo = this.ecCarInfoService.getOilRanking(null,null, 20);
				 logger.error("222222"+BeanUtils.toString(vo));
				 configs.put("deviceNo","");
			}
			
			
			configs.put("vo",vo);
			html = ReportMaker.exeute4Content(configs, "manager-rank.ftl");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("解析模板文件异常!", e);
			
		}
		return html;
	}
	
	//行车报告
	@RequestURI(value = "/carReport", method = RequestMethod.GET)
	public String carReport(String openid,String org_code,Integer flag){
		String html = null;
		if(flag==null){
			flag=0;
		}
		Map<String, Object> configs = new HashMap<String, Object>();
		try {
			/*
			 * 判断是否有过设置过车辆
			 */
			WcxUserInfo wui = this.genericDao.findOne(WcxUserInfo.class,new Criteria().eq(WcxUserInfoRM.openId,openid).eq(WcxUserInfoRM.orgCode, org_code, CondtionSeparator.AND));
			FivesaasCarInfo five = this.genericDao.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.wcxUserId, wui.getId()).eq(FivesaasCarInfoRM.orgCode, org_code, CondtionSeparator.AND));
			if(five==null){//没有设置进入设置界面
				
				configs.put("orgCode", wui.getOrgCode());
				configs.put("uid", wui.getId());
				
				html = ReportMaker.exeute4Content(configs, "5s-setting.ftl");//设置
			}else if(five!=null&&five.getDeviceNo()!=null){//有车猫
				//月报
					ECCarInfoItemVO vo_month=this.ecCarInfoService.getLastMonthCarInfo(five.getCarId());
					if(vo_month!=null){
						logger.error("--------------monthReport------vo----"+BeanUtils.toString(vo_month));
						int m = vo_month.getTotalDriveTime();
						int n = m/3600;
							m = m%3600;
						int k = m/60;
						configs.put("driveTime_month", n+"小时"+k+"分");
					}
					List<ECCarInfoItemVO> list_month = this.ecCarInfoService.getLastMonthDayReports(five.getCarId());
					if(list_month!=null&&list_month.size()>0){
						logger.error("--------------monthReport--list--------"+BeanUtils.toString(list_month));
						
					}
					list_month=monthList(list_month);
					
				//周报
					ECCarInfoItemVO vo=this.ecCarInfoService.getLastWeekCarInfo(five.getCarId());
					logger.error("--------------weekReport---eeeeeeee-------five.getCarId()==="+five.getCarId());
					if(vo!=null){
						logger.error("--------------weekReport----------"+BeanUtils.toString(vo));
						int m = vo.getTotalDriveTime();
						int n = m/3600;
							m = m%3600;
						int k = m/60;
						configs.put("driveTime_week", n+"小时"+k+"分");
					} else {
						logger.error("--------------weekReport----------vo="+vo);
					}
					logger.error("--------------weekReport---eeeeee11111ee-------");
					List<ECCarInfoItemVO> list = this.ecCarInfoService.getLastWeekDayReports(five.getCarId());
					logger.error("--------------weekReport---eeee2222222eeee-------");
					if(list!=null&&list.size()>0){
						logger.error("--------------weekReport--list--------"+BeanUtils.toString(list));
						logger.error("--------------weekReport--list--------"+BeanUtils.toString(list.get(0)));
					}
					
					list=weekList(list);
					configs.put("vo", vo);
					configs.put("volist", list);
					configs.put("vo_month", vo_month);
					configs.put("list_month", list_month);
					configs.put("orgCode", wui.getOrgCode());
					configs.put("openid", openid);
					html = ReportMaker.exeute4Content(configs, "manager-report.ftl");
			}else{//未关联车猫
//				configs.put("orgCode", wui.getOrgCode());
//				configs.put("openid", openid);
				html = ReportMaker.exeute4Content(null, "error.ftl");
			}
			
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	//未关联车猫页面
	@RequestURI(value = "/error", method = RequestMethod.GET)
	public String error(String openid){
		String html = null;
		try {
			html = ReportMaker.exeute4Content(null, "error.ftl");
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	private long reduceDays(Date date){
		Calendar nowDate =Calendar.getInstance();
		if(date!=null){
			nowDate.setTime(new Date());
			Calendar oldDate=Calendar.getInstance();
			oldDate.setTime(date);
			long timeNow=nowDate.getTimeInMillis();
			long timeOld=oldDate.getTimeInMillis();
			if(timeOld-timeNow<0){
				return 0;
			}else{
				return (timeOld-timeNow)/(1000*60*60*24);//化为天
			}
		}
		else{
			return -1;
		}
	}
	
	private String getYMD(Date date){
		if(date!=null){
			Calendar nowDate =Calendar.getInstance();
			nowDate.setTime(date);
			int year = nowDate.get(Calendar.YEAR);
			int month = nowDate.get(Calendar.MONTH)+1;
			int day = nowDate.get(Calendar.DAY_OF_MONTH);
			return year+"年"+month+"月"+day+"日";
		}else {
			return "";
		}
	}
	
	private List<ECCarInfoItemVO> monthList(List<ECCarInfoItemVO> list){
		Calendar nowDate =Calendar.getInstance();
		Calendar canlendar =Calendar.getInstance();
		List<ECCarInfoItemVO> voList = new ArrayList<ECCarInfoItemVO>();
		try{
		
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			nowDate.setTime(format.parse(format.format(new Date())));
			nowDate.set(Calendar.DAY_OF_MONTH,1);//设置为当月1号
			nowDate.add(Calendar.DAY_OF_MONTH,-1);
			String s= format.format(nowDate.getTime());
			int lastday = nowDate.get(Calendar.DAY_OF_MONTH);
			int lastDay = nowDate.get(Calendar.DAY_OF_MONTH);//上月最后一天
			if(list!=null&&list.size()>0){
				for(int i=1 ;i<=lastDay;i++){
					boolean flag=true;
					nowDate.set(Calendar.DAY_OF_MONTH, i);
					for(int k=0;k<list.size();k++){
						if(list.get(k).getFromDay()!=null){
							canlendar.setTime(list.get(k).getFromDay());
							if(nowDate.getTimeInMillis()==canlendar.getTimeInMillis()){//判断当前时间和vo的时间是否为同一天
								voList.add(list.get(k));
								flag=false;
								break;
							}
						}
					}
					if(flag){
							ECCarInfoItemVO vo =new ECCarInfoItemVO();
							vo.setFromDay(format.parse(format.format(nowDate.getTime())));
							vo.setTotalOil(0f);
							vo.setTotalMileage(0f);
							voList.add(vo);
							BeanUtils.toString(vo);
						}
						
				}
			}else{//没有数据全部为0
				for(int i=1 ;i<=lastDay;i++){
					nowDate.set(Calendar.DAY_OF_MONTH, i);
					format.format(nowDate.getTime());
					ECCarInfoItemVO vo =new ECCarInfoItemVO();
					vo.setFromDay(format.parse(format.format(nowDate.getTime())));
					vo.setTotalOil(0f);
					vo.setTotalMileage(0f);
					voList.add(vo);
					BeanUtils.toString(vo);
				}
			}
		}catch(Exception e){
			logger.error("月数据转换异常",e);
			
		}
		return voList;
	}
	
	private List<ECCarInfoItemVO> weekList(List<ECCarInfoItemVO> list){
		Calendar nowDate =Calendar.getInstance();
		Calendar canlendar =Calendar.getInstance();
		List<ECCarInfoItemVO> voList = new ArrayList<ECCarInfoItemVO>();
		try{
		
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			nowDate.setTime(format.parse(format.format(new Date())));
			nowDate.add(Calendar.WEEK_OF_YEAR,-1);
			String s= format.format(nowDate.getTime());
			//获取上周1日期
			nowDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//
			int lastday = nowDate.get(Calendar.DAY_OF_MONTH);//
			nowDate.add(Calendar.DAY_OF_YEAR, -1);
			if(list!=null&&list.size()>0){
				for(int i=0 ;i<7;i++){
					boolean flag=true;
					nowDate.add(Calendar.DAY_OF_YEAR, 1);
					for(int k=0;k<list.size();k++){
						if(list.get(k).getFromDay()!=null){
							canlendar.setTime(list.get(k).getFromDay());
							if(nowDate.getTimeInMillis()==canlendar.getTimeInMillis()){//判断当前时间和vo的时间是否为同一天
								voList.add(list.get(k));
								flag=false;
								break;
							}
						}
					}
					if(flag){
							ECCarInfoItemVO vo =new ECCarInfoItemVO();
							vo.setFromDay(format.parse(format.format(nowDate.getTime())));
							vo.setTotalOil(0f);
							vo.setTotalMileage(0f);
							voList.add(vo);
							BeanUtils.toString(vo);
						}
						
				}
			}else{//没有数据全部为0
				for(int i=1 ;i<=7;i++){
					nowDate.add(Calendar.DAY_OF_YEAR, 1);
					format.format(nowDate.getTime());
					ECCarInfoItemVO vo =new ECCarInfoItemVO();
					vo.setFromDay(format.parse(format.format(nowDate.getTime())));
					vo.setTotalOil(0f);
					vo.setTotalMileage(0f);
					voList.add(vo);
					BeanUtils.toString(vo);
				}
			}
		}catch(Exception e){
			logger.error("月数据转换异常",e);
			
		}
		return voList;
	}
	
	private void updateMaintanceNoticeTime(FivesaasCarInfo car, String toMTime, Float toMMileageVo) {
		if (StringUtils.isNotEmpty(toMTime) || toMMileageVo>0) {
			Date toMTimeVo = null;
			if (StringUtils.isNotEmpty(toMTime)) {
				toMTimeVo = DateUtils.stringToDate(toMTime,TimeFormatter.FORMATTER2);
			}
			
			if (!equals(car.getToMMileage(), toMMileageVo) || !equals(car.getToMTime(), toMTimeVo)) {
				logger.info("---------------re generate MaintenanceNoticeTime of car:" + car.getCarId());
				float currentMileage = car.getCurrentMileage()==null?0:car.getCurrentMileage();
				boolean needClear = true; //是否需要将MaintenanceNoticeTime置空
				if (toMMileageVo!=null && (toMMileageVo-currentMileage)<=300) {
					car.setMaintenanceNoticeTime(new Date());
					needClear = false;				
				} else {
					needClear = true;
					if (toMTimeVo!=null) {
						long interval = DateUtils.getDaysBetweenInDateLevelCase(new Date(), toMTimeVo);
						if (interval<=30 && interval>=0) {
							car.setMaintenanceNoticeTime(new Date());
							needClear = false;
						} 
					}
				}
				
				if (needClear) {
					car.setMaintenanceNoticeTime(null);
					logger.info("-------------set MaintenanceNoticeTime to null");
				}
			}	
		}
	}

	private boolean equals(Object o1, Object o2) {
		if (o1==null && o2==null) {
			return true;
		}
		if (o1!=null && o2!=null && o1.equals(o2)) {
			return true;
		}
		return false;		
	}
	

}
