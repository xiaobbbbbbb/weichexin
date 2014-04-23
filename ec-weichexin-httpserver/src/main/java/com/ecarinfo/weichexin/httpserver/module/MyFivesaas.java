package com.ecarinfo.weichexin.httpserver.module;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
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
import com.ecarinfo.persist.criteria.Criteria.OrderType;
import com.ecarinfo.persist.exdao.GenericDao;
import com.ecarinfo.persist.service.GenericService;
import com.ecarinfo.weichexin.httpserver.service.WeichexinseviceImpl;
import com.ecarinfo.weichexin.httpserver.vo.YuyueDate;
import com.ecarinfo.weichexin.po.CarModel;
import com.ecarinfo.weichexin.po.CarSerial;
import com.ecarinfo.weichexin.po.CarSerialGroup;
import com.ecarinfo.weichexin.po.FivesaasCarInfo;
import com.ecarinfo.weichexin.po.ReserveWorkerInfo;
import com.ecarinfo.weichexin.po.ServiceOrg;
import com.ecarinfo.weichexin.po.ServiceWorker;
import com.ecarinfo.weichexin.po.WcxUserInfo;
import com.ecarinfo.weichexin.po.WorkerServeTimePoints;
import com.ecarinfo.weichexin.rm.CarModelRM;
import com.ecarinfo.weichexin.rm.CarSerialRM;
import com.ecarinfo.weichexin.rm.FivesaasCarInfoRM;
import com.ecarinfo.weichexin.rm.ReserveWorkerInfoRM;
import com.ecarinfo.weichexin.rm.ServiceOrgRM;
import com.ecarinfo.weichexin.rm.ServiceWorkerRM;
import com.ecarinfo.weichexin.rm.WcxUserInfoRM;
import com.ecarinfo.weichexin.rm.WorkerServeTimePointsRM;
import com.ecarinfo.yunying.dto.EYProvinceAndCityDto;
import com.ecarinfo.yunying.service.EYProvinceAndCityService;
import com.ecarinfo.yunying.vo.EYProvinceAndCity;
import com.ecarinfo.yunying.vo.EYViolationAreaView;

/**
 * 我的5S
 */
@Component
@MessageModule("/myfs")
public class MyFivesaas {
	private static final Logger logger = Logger.getLogger(MyFivesaas.class);
	
	@Resource
	GenericService genericService;
	
	@Resource
	GenericDao genericDao;
	
	@Resource
	WeichexinseviceImpl weichexinseviceImpl;
	@Resource
	EYProvinceAndCityService eyProvinceAndCityService;
	//设置
	@RequestURI(value = "/setting", method = RequestMethod.GET)
	public String setting(String openid,String org_code){
		String html = null;
		try {
			/*
			 * 判断是否有过设置过车辆
			 */
			WcxUserInfo wui = this.genericDao.findOne(WcxUserInfo.class,new Criteria().eq(WcxUserInfoRM.openId,openid).eq(WcxUserInfoRM.orgCode, org_code, CondtionSeparator.AND));
			FivesaasCarInfo five = this.genericDao.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.wcxUserId, wui.getId()).eq(FivesaasCarInfoRM.orgCode, org_code,CondtionSeparator.AND));
			if(five!=null){
				StringBuffer sb = new StringBuffer();
				if(five.getSerialId()!=null){
					CarSerial cs = this.genericDao.findOne(CarSerial.class,new Criteria().eq(CarSerialRM.id,five.getSerialId()));
					sb.append(cs.getName());
				}if(five.getModelId()!=null){
					CarModel cm = this.genericDao.findOne(CarModel.class,new Criteria().eq(CarModelRM.id,five.getModelId()));
					sb.append(cm.getName());
				}
				Map<String, Object> configs = new HashMap<String, Object>();
				configs.put("email", five.getEmail());
				configs.put("carNo", five.getCarNo());
				configs.put("deviceNo", five.getDeviceNo()!=null?five.getDeviceNo():"");
				configs.put("carModel",sb.toString());
				configs.put("carSource", five.getCarSource());
				configs.put("fid",five.getId().toString());
				html = ReportMaker.exeute4Content(configs, "5s-userinfo.ftl");
			}else{
				Map<String, Object> configs = new HashMap<String, Object>();
				configs.put("orgCode", wui.getOrgCode());
				configs.put("uid", wui.getId());
//				List<EYProvinceAndCity> listCity= eyProvinceAndCityService.getAll();
//				List<EYViolationAreaView>  eyViolationAreaView = eyProvinceAndCityService.findCityInfoByProvinceName("");
//				configs.put("listCity", listCity);
				html = ReportMaker.exeute4Content(configs, "5s-setting.ftl");//设置
			}
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	
	}
	
	@RequestURI(value = "/setInfo", method = RequestMethod.GET)
	public String setInfo(String uid,String org_code){
		String html = null;
		try {
			
			FivesaasCarInfo five = this.genericDao.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.wcxUserId, uid).eq(FivesaasCarInfoRM.orgCode, org_code, CondtionSeparator.AND));
			StringBuffer sb = new StringBuffer();
			if(five.getSerialId()!=null){
				CarSerial cs = this.genericDao.findOne(CarSerial.class,new Criteria().eq(CarSerialRM.id,five.getSerialId()));
				sb.append(cs.getName());
			}if(five.getModelId()!=null){
				CarModel cm = this.genericDao.findOne(CarModel.class,new Criteria().eq(CarModelRM.id,five.getModelId()));
				sb.append(cm.getName());
			}
			Map<String, Object> configs = new HashMap<String, Object>();
			configs.put("email", five.getEmail());
			configs.put("carNo", five.getCarNo());
			configs.put("deviceNo", five.getDeviceNo()!=null?five.getDeviceNo():"");
			configs.put("carModel",sb.toString());
			configs.put("carSource", five.getCarSource());
			configs.put("fid",five.getId().toString());
			html = ReportMaker.exeute4Content(configs, "5s-userinfo.ftl");//设置
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	
	}
	
	//设置
	@RequestURI(value = "/addCar", method = RequestMethod.POST)
	public String addCar(String org_code,String uid,String carNo,String email){
		String html = null;
		try{
			logger.info("addMessage-------------------:"+org_code+uid+carNo+email);
			/*
			 * 1.根据邮箱和用户名查找是否存在车辆信息
			 * 2.查勘邮箱是否存在
			 * 3.查看车牌是否存在
			 */
			FivesaasCarInfo carinfo1 = this.genericService.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.email, email)
										.eq(FivesaasCarInfoRM.carNo, carNo, CondtionSeparator.AND));
			 
			if(carinfo1!=null){
				carinfo1.setWcxUserId((long)new Integer(uid));
	//			carinfo1.setCarSource(1);
				this.genericService.update(carinfo1);
			}else{
				ServiceOrg serviceOrg = this.genericService.findOne(ServiceOrg.class,new Criteria().eq(ServiceOrgRM.code, org_code));
				FivesaasCarInfo carinfo2 = this.genericService.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.email,email));
				if(carinfo2!=null){
					html = "邮箱已存在！";
					return html;
				}
				FivesaasCarInfo carinfo3 = this.genericService.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.carNo,carNo));
				if(carinfo3!=null){
					html = "车辆已存在！";
					return html;
				}
				//验证通过则新加车辆
				FivesaasCarInfo carinfo =new  FivesaasCarInfo();
				carinfo.setCarNo(carNo);
				carinfo.setOrgCode(org_code);
				
				if(serviceOrg!=null){
					carinfo.setOrgId(serviceOrg.getSid());
				}
				
				carinfo.setWcxUserId((long)new Integer(uid));
				carinfo.setCarSource(1);
				carinfo.setEmail(email);
				carinfo.setCtime(new Date());
				carinfo.setBindCount(0);//不知道干嘛的，非空字段
				this.genericDao.insert(carinfo);
			}
			html= "true";
		
		} catch (Exception e) {
			logger.error("设置失败!", e);
		}
		return html;
	}
	
	//修改界面
	@RequestURI(value = "/updatePage", method = RequestMethod.GET)
	public String updatePage(Long id){
		String html=null;
		try{
			
			FivesaasCarInfo fi=this.genericDao.findByPK(FivesaasCarInfo.class, id);
			Map<String, Object> configs = new HashMap<String, Object>();
			configs.put("email", fi.getEmail());
			configs.put("carNo", fi.getCarNo());
			configs.put("fid", id);
			configs.put("uid", fi.getWcxUserId().toString());
			configs.put("orgCode", fi.getOrgCode());
			html = ReportMaker.exeute4Content(configs, "5s-car-update.ftl");//未预约
		}catch(Exception e){
			logger.error("解析模板文件异常!",e);
		}
		return html;
	}
	//修改
		@RequestURI(value = "/updateCar", method = RequestMethod.POST)
		public String updateCar(String org_code,String fid,String carNo,String email){
			String html = null;
			try{
				logger.info("addMessage---id=======----------------:"+fid);
				FivesaasCarInfo carinfo = this.genericService.findByPK(FivesaasCarInfo.class, fid);
				/*
				 * 1.修改之后的是已存在的APP用户 则绑定，删除原来用户 
				 * 2.修改之后不存在的直接修改
				 */
				FivesaasCarInfo carinfo1 = this.genericService.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.email, email)
											.eq(FivesaasCarInfoRM.carNo, carNo, CondtionSeparator.AND).notEquals(FivesaasCarInfoRM.carSource, 1, CondtionSeparator.AND));
				if(carinfo1!=null){
					carinfo1.setWcxUserId(carinfo.getWcxUserId());
		//			carinfo1.setCarSource(1);
					this.genericService.update(carinfo1);
					this.genericService.deleteByPK(FivesaasCarInfo.class, fid);
				}else{
					FivesaasCarInfo carinfo2 = this.genericService.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.email,email).notEquals(FivesaasCarInfoRM.id, fid, CondtionSeparator.AND));
					if(carinfo2!=null){
						html = "邮箱已存在！";
						return html;
					}
					FivesaasCarInfo carinfo3 = this.genericService.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.carNo,carNo).notEquals(FivesaasCarInfoRM.id, fid, CondtionSeparator.AND));
					if(carinfo3!=null){
						html = "车辆已存在！";
						return html;
					}
					//验证通过则更新车辆
					carinfo.setCarNo(carNo);
					carinfo.setEmail(email);
					carinfo.setUpdateTime(new Date());
					this.genericDao.update(carinfo);
				}
				html= "true";
			
			} catch (Exception e) {
				logger.error("设置失败!", e);
			}
			return html;
		}
	//联系我们
	@RequestURI(value = "/contactus", method = RequestMethod.GET)
	public String contactus(String org_code){
		String html = null;
		try {
			html = ReportMaker.exeute4Content(null, "5s-userinfo.ftl");
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	
	//保养预约
	@RequestURI(value = "/yuyue", method = RequestMethod.GET)
	public String yuyue(String org_code,String openid,String reserveDate){
		String html = null;
		try {
			Map<String, Object> configs = new HashMap<String, Object>();
			
			WcxUserInfo wui = this.genericDao.findOne(WcxUserInfo.class,new Criteria().eq(WcxUserInfoRM.openId,openid).eq(WcxUserInfoRM.orgCode, org_code, CondtionSeparator.AND));
			ReserveWorkerInfo findOne =null;
			//查勘是否有预约历史 按天查
			if(reserveDate!=null){
				findOne = this.genericDao.findOne(ReserveWorkerInfo.class, new Criteria()
						.eq(ReserveWorkerInfoRM.wcxUserId,wui.getId())
						.eq(ReserveWorkerInfoRM.orgCode, org_code, CondtionSeparator.AND)
						.eq(ReserveWorkerInfoRM.reserveDate,reserveDate, CondtionSeparator.AND)
						.orderBy(ReserveWorkerInfoRM.ctime,OrderType.DESC));

			}else{
				findOne = this.genericDao.findOne(ReserveWorkerInfo.class, new Criteria()
						.eq(ReserveWorkerInfoRM.wcxUserId,wui.getId())
						.eq(ReserveWorkerInfoRM.orgCode, org_code, CondtionSeparator.AND)
						.orderBy(ReserveWorkerInfoRM.ctime,OrderType.DESC));
			}
				//判断是否过期 按时间点算
				if(null!=findOne&&!isOverDate(findOne)){
					configs.put("reserveDate", DateUtils.dateToString(findOne.getReserveDate(),TimeFormatter.YYYY_MM_DD));
					configs.put("timePoint",findOne.getTimePoint());
					configs.put("openid", openid);
					configs.put("orgCode", org_code);
					configs.put("dayofweek", getWeekDay(findOne.getReserveDate()));
					if(findOne.getStatus()==0){//预约中
						html = ReportMaker.exeute4Content(configs, "5s-yuyue-ing.ftl");
					}
					else if(findOne.getStatus()==1){//预约成功
						configs.put("timePoint",findOne.getPracticalTimePoint());
						html = ReportMaker.exeute4Content(configs, "5s-yuyue-success.ftl");
					}
					else if(findOne.getStatus()==2){//预约失败
						configs.put("openid", openid);
						configs.put("orgCode", org_code);
						html = ReportMaker.exeute4Content(configs, "5s-yuyue-failed.ftl");
					}
					else if(findOne.getStatus()==3){//过期
						configs.put("openid", openid);
						configs.put("orgCode", org_code);
						html = ReportMaker.exeute4Content(configs, "5s-yuyue-failed.ftl");
					}
				}
				else{
					ServiceOrg org = this.genericDao.findOne(ServiceOrg.class, new Criteria().eq(ServiceOrgRM.code,org_code));
					FivesaasCarInfo fis = this.genericDao.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.orgCode,org_code).eq(FivesaasCarInfoRM.wcxUserId, wui.getId(), CondtionSeparator.AND));
					List<YuyueDate> daymap = getYuyueDate(org_code,wui.getId().toString(),org.getReserveAvailableDays()!=null?org.getReserveAvailableDays():1);
					if(fis!=null){
						configs.put("carNo", fis.getCarNo());
					}
					configs.put("uid", wui.getId());
					configs.put("openid", openid);
					configs.put("orgCode", org_code);
					configs.put("daymap", daymap);
					configs.put("note", org.getReserveDiscountNote()!=null?org.getReserveDiscountNote():"");
					html = ReportMaker.exeute4Content(configs, "5s-yuyue-setting.ftl");//未预约
				}
			
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	//在次预约
	
	//保养预约
	@RequestURI(value = "/yuyueSet", method = RequestMethod.GET)
	public String yuyueSet(String org_code,String openid){
		String html = null;
		try {
			Map<String, Object> configs = new HashMap<String, Object>();
			
			WcxUserInfo wui = this.genericDao.findOne(WcxUserInfo.class,new Criteria().eq(WcxUserInfoRM.openId,openid).eq(WcxUserInfoRM.orgCode, org_code, CondtionSeparator.AND));
			
				ServiceOrg org = this.genericDao.findOne(ServiceOrg.class, new Criteria().eq(ServiceOrgRM.code,org_code));
				FivesaasCarInfo fis = this.genericDao.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.orgCode,org_code).eq(FivesaasCarInfoRM.wcxUserId, wui.getId(), CondtionSeparator.AND));
				if(fis!=null){
					configs.put("carNo", fis.getCarNo());
				}
				List<YuyueDate> daymap = getYuyueDate(org_code,wui.getId().toString(),org.getReserveAvailableDays()!=null?org.getReserveAvailableDays():1);
				configs.put("uid", wui.getId());
				configs.put("openid", openid);
				configs.put("orgCode", org_code);
				configs.put("daymap", daymap);
				configs.put("note", org.getReserveDiscountNote()!=null?org.getReserveDiscountNote():"");
				html = ReportMaker.exeute4Content(configs, "5s-yuyue-setting.ftl");//未预约
			
			
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
	
	@RequestURI(value = "/times", method = RequestMethod.GET)
	public List<YuyueDate> getTimes(String org_code,String uid,String date){
		
		List<YuyueDate> list =new ArrayList<YuyueDate>();
		Calendar nowDate =Calendar.getInstance();
		nowDate.setTime(DateUtils.stringToDate(date, TimeFormatter.YYYY_MM_DD));
		int week=nowDate.get(Calendar.DAY_OF_WEEK);
		
		if(week==1){
			week=7;
		}else{
			week--;
		}
		try{
			//有效工位
			List<ServiceWorker> sw =  this.genericDao.findList(ServiceWorker.class,new Criteria().eq(ServiceWorkerRM.orgCode,org_code).eq(ServiceWorkerRM.status, 1, CondtionSeparator.AND));
			
			Integer ob[] = null;
			if (sw != null && sw.size() > 0) {
				ob = new Integer[sw.size()];
				for (int i = 0; i < sw.size(); i++) {
					ob[i] = sw.get(i).getId();
				}
			}
			//查找配置中的时间点
			List<WorkerServeTimePoints> points = this.genericDao.findList(WorkerServeTimePoints.class, new Criteria().eq(WorkerServeTimePointsRM.weekIndex, week).eq(WorkerServeTimePointsRM.orgCode, org_code, CondtionSeparator.AND).in(WorkerServeTimePointsRM.workerId, ob, CondtionSeparator.AND).groupBy(WorkerServeTimePointsRM.timePoint));
			if(null!=points&&points.size()>0){
				/*
				 * 如果此时间点下工位都被预约则过滤此时间点
				 */
				for(WorkerServeTimePoints point:points){
					YuyueDate yuyueDate = new YuyueDate();
					/*
					 * 1.统计设置同一时间点的数量
					 * 2.统计被预约时间点的数量
					 * 3.做减法
					 */

					StringBuffer sb = new StringBuffer();
					sb.append("SELECT id,wcx_user_id,worker_id,ctime,time_point,car_no,contact_tel,discount_note,practical_worker_id,practical_time_point,practical_car_no,status,handle_user_id,handle_time,reserve_date,org_contact_tel,org_code,org_id FROM reserve_worker_info WHERE 1=1 ");
					if(StringUtils.isNotBlank(org_code)){
						sb.append(" and org_code='"+org_code+"'");
					}
					if(StringUtils.isNotBlank(date)){
						sb.append(" and reserve_date='"+date+"'");
					}					
					sb.append(" and ((time_point='"+ point.getTimePoint()+"' and status = 0) OR (practical_time_point ='" + point.getTimePoint()+ "' and status = 1))");
					List<WorkerServeTimePoints> points2 = this.genericDao.findList(WorkerServeTimePoints.class, new Criteria().eq(WorkerServeTimePointsRM.weekIndex, week)
															.eq(WorkerServeTimePointsRM.timePoint, point.getTimePoint(), CondtionSeparator.AND).eq(WorkerServeTimePointsRM.orgCode, org_code, CondtionSeparator.AND).in(WorkerServeTimePointsRM.workerId, ob, CondtionSeparator.AND));
					List<ReserveWorkerInfo> workerList = this.genericDao.findListBySql(ReserveWorkerInfo.class, sb.toString());
					if(workerList!=null&&workerList.size()>0){
						if(points2.size()-workerList.size()>0){
							yuyueDate.setTimepoint(point.getTimePoint());
							list.add(yuyueDate);
						}
					}else{
						yuyueDate.setTimepoint(point.getTimePoint());
						list.add(yuyueDate);
					}
				
				}
			}
		}
				
		catch(Exception e){
			logger.error("查询剩余工位时间出错",e);
		}
		
		return list;
	}
	
	@RequestURI(value = "/worker", method = RequestMethod.GET)
	public YuyueDate worker(String org_code,String uid,String date,String timepoint){
		YuyueDate yuyue= new YuyueDate();
		
		Calendar nowDate =Calendar.getInstance();
		nowDate.setTime(DateUtils.stringToDate(date, TimeFormatter.YYYY_MM_DD));
		int week=nowDate.get(Calendar.DAY_OF_WEEK);
		
		if(week==1){
			week=7;
		}else{
			week--;
		}
		try{
			
			//有效工位
			List<ServiceWorker> sw =  this.genericDao.findList(ServiceWorker.class,new Criteria().eq(ServiceWorkerRM.orgCode,org_code).eq(ServiceWorkerRM.status, 1, CondtionSeparator.AND));
			
			Integer ob[] = null;
			if (sw != null && sw.size() > 0) {
				ob = new Integer[sw.size()];
				for (int i = 0; i < sw.size(); i++) {
					ob[i] = sw.get(i).getId();
				}
			}
			/*
			 * 1.统计设置同一时间点的数量
			 * 2.统计被预约时间点的数量
			 * 3.做减法
			 */
			
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT id,wcx_user_id,worker_id,ctime,time_point,car_no,contact_tel,discount_note,practical_worker_id,practical_time_point,practical_car_no,status,handle_user_id,handle_time,reserve_date,org_contact_tel,org_code,org_id FROM reserve_worker_info WHERE 1=1 ");
			if(StringUtils.isNotBlank(org_code)){
				sb.append(" and org_code='"+org_code+"'");
			}
			if(StringUtils.isNotBlank(date)){
				sb.append(" and reserve_date='"+date+"'");
			}
			sb.append("and ((time_point='"+timepoint+"' and status = 0) OR (practical_time_point ='" +timepoint+ "' and status = 1))");
						
			List<WorkerServeTimePoints> points2 = this.genericDao.findList(WorkerServeTimePoints.class, new Criteria().eq(WorkerServeTimePointsRM.weekIndex, week)
										.eq(WorkerServeTimePointsRM.timePoint, timepoint, CondtionSeparator.AND).in(WorkerServeTimePointsRM.workerId, ob, CondtionSeparator.AND));
			
			List<ReserveWorkerInfo> workerList = this.genericDao.findListBySql(ReserveWorkerInfo.class, sb.toString());
					
//					this.genericDao.findList(ReserveWorkerInfo.class,whereBy);
			if(workerList!=null){
				yuyue.setWorker(points2.size()-workerList.size());
			}else{
				yuyue.setWorker(points2.size());
			}
			yuyue.setItem(points2.get(0).getDiscountNote());
		}catch(Exception e ){
			logger.error("工位查询失败",e);
		}
		return yuyue;
	}
	
	@RequestURI(value = "/addYuyue", method = RequestMethod.POST)
	public String addYuyue(String org_code,Long uid,String dateSelect,String timeSelect,String contact_tel ,String car_no ){
		String html=null;
		try{
			//查勘是否有预约历史 按天查
			ReserveWorkerInfo findOne = this.genericDao.findOne(ReserveWorkerInfo.class, new Criteria()
											.eq(ReserveWorkerInfoRM.wcxUserId,uid)
											.eq(ReserveWorkerInfoRM.orgCode, org_code, CondtionSeparator.AND)
											.eq(ReserveWorkerInfoRM.reserveDate, dateSelect, CondtionSeparator.AND)
											.notEquals(ReserveWorkerInfoRM.status, 1, CondtionSeparator.AND)
											.notEquals(ReserveWorkerInfoRM.status, 2, CondtionSeparator.AND)
											.notEquals(ReserveWorkerInfoRM.status, 3, CondtionSeparator.AND)//作废的不算
											.orderBy(ReserveWorkerInfoRM.ctime,OrderType.DESC));
			
			//判断是否过期 按时间点算
			if(null!=findOne&&!isOverDate(findOne)){
				html="过当已预约，预约失败";
				logger.info("重复提交");
			}else{
				ReserveWorkerInfo info = new ReserveWorkerInfo();
				ServiceOrg org= this.genericDao.findOne(ServiceOrg.class, new Criteria().eq(ServiceOrgRM.code, org_code));
				info.setCarNo(car_no);
				info.setPracticalCarNo(car_no);
				info.setCtime(new Date());
				info.setContactTel(contact_tel);
				info.setOrgCode(org_code);
				info.setOrgId(org.getSid());
				info.setWcxUserId(uid);
				info.setTimePoint(timeSelect);
				info.setStatus(0);//预约中
				info.setReserveDate(DateUtils.stringToDate(dateSelect,TimeFormatter.YYYY_MM_DD));
				this.genericDao.insert(info);
				
				//预约成功发送邮件
				try{
					logger.info("-------------------邮件发送----------");
					this.weichexinseviceImpl.mailSend(org_code,info);
				}catch(Exception e ){
					logger.error("邮件发送失败",e);
				}
				html="true";
			}
			
		}catch(Exception e){
			logger.error("预约失败！",e);
			html="预约失败";
		}
		return html;
		
	}
	
	private List<YuyueDate> getYuyueDate(String org_code,String uid,Integer days){
		List<YuyueDate> daymap = new ArrayList<YuyueDate>();
		Calendar nowDate =Calendar.getInstance();
		nowDate.setTime(new Date());
		for(int i=1;i<=days;i++){
			YuyueDate yuyue= new YuyueDate();
			nowDate.add(Calendar.DAY_OF_MONTH, 1);
			logger.info("星期几:"+nowDate.get(Calendar.DAY_OF_WEEK));
			String week="";
			switch(nowDate.get(Calendar.DAY_OF_WEEK)){
				case 1: week="  星期日";break;
				case 2: week="  星期一";break;
				case 3: week="  星期二";break;
				case 4: week="  星期三";break;
				case 5: week="  星期四";break;
				case 6: week="  星期五";break;
				case 7: week="  星期六";break;
			}
			int year = nowDate.get(Calendar.YEAR);
			int month = nowDate.get(Calendar.MONTH)+1;
			int day = nowDate.get(Calendar.DAY_OF_MONTH);
			logger.info(year+"年"+month+"月"+day+"日");
			List<YuyueDate> dates=this.getTimes(org_code, uid,year+"-"+month+"-"+day);
			if(dates!=null&&dates.size()>0){
				yuyue.setKey(year+"-"+month+"-"+day);
				yuyue.setValue(year+"-"+month+"-"+day+week);
				daymap.add(yuyue);
			}
		}
//		return year+"年"+month+"月"+day+"日";
		return daymap;
	}
	
	private String getWeekDay(Date date){
		Calendar nowDate =Calendar.getInstance();
		if(date!=null){
			nowDate.setTime(date);
			String week="";
			switch(nowDate.get(Calendar.DAY_OF_WEEK)){
				case 1: week="  星期日";break;
				case 2: week="  星期一";break;
				case 3: week="  星期二";break;
				case 4: week="  星期三";break;
				case 5: week="  星期四";break;
				case 6: week="  星期五";break;
				case 7: week="  星期六";break;
			}
			return week;
		}
		return "";
	}
	
	private boolean isOverDate(ReserveWorkerInfo info ){
		try{
			Calendar date1 =Calendar.getInstance();
			date1.setTime(new Date());
			Calendar date2 =Calendar.getInstance();
			date2.setTime(info.getReserveDate());
		
			if(info.getStatus()!=null&&info.getStatus()==1){//预约中
				Integer hour=new Integer(info.getPracticalTimePoint().split(":")[0]);
				Integer mi=new Integer(info.getPracticalTimePoint().split(":")[1]);
				date2.set(Calendar.HOUR_OF_DAY, hour);
				date2.set(Calendar.MINUTE, mi);
			}else{
				Integer hour=new Integer(info.getTimePoint().split(":")[0]);
				Integer mi=new Integer(info.getTimePoint().split(":")[1]);
				date2.set(Calendar.HOUR_OF_DAY, hour);
				date2.set(Calendar.MINUTE, mi);
			}
			long timeOne=date1.getTimeInMillis();  
			long timeTwo=date2.getTimeInMillis();
			if(timeOne>timeTwo){//当前时间大于预约时间过期
				return true;
			}
		}catch(Exception e){
				logger.error("时间解析出错",e);
				return false;
			}
		
		return false;
	}
	
	//新车展厅
	@RequestURI(value = "/buyCarService", method = RequestMethod.GET)
	public String buyCarService(String org_code,String openid){
		String html = null;
		try {
			Map<String, Object> configs = new HashMap<String, Object>();
			html = ReportMaker.exeute4Content(configs, "5s-carshow.ftl");//未预约
			
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}
		
}
