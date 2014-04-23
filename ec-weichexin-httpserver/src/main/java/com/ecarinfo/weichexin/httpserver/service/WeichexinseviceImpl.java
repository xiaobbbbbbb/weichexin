package com.ecarinfo.weichexin.httpserver.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.DateUtils.TimeFormatter;
import com.ecarinfo.persist.criteria.Criteria;
import com.ecarinfo.persist.criteria.Criteria.CondtionSeparator;
import com.ecarinfo.persist.criteria.Criteria.OrderType;
import com.ecarinfo.persist.exdao.GenericDao;
import com.ecarinfo.persist.paging.ECPage;
import com.ecarinfo.persist.paging.PagingManager;
import com.ecarinfo.persist.simple.DaoTool;
import com.ecarinfo.traffic.rpc.api.TrafficSearchApi;
import com.ecarinfo.weichexin.dao.ActivityDao;
import com.ecarinfo.weichexin.dao.CarTipDao;
import com.ecarinfo.weichexin.httpserver.dto.wcx.ActivityItemResponse;
import com.ecarinfo.weichexin.httpserver.dto.wcx.ActivityResponse;
import com.ecarinfo.weichexin.httpserver.utils.ConfigUtils;
import com.ecarinfo.weichexin.httpserver.utils.MailTask;
import com.ecarinfo.weichexin.httpserver.vo.ActivityHistoryVO;
import com.ecarinfo.weichexin.httpserver.vo.CarModelVO;
import com.ecarinfo.weichexin.httpserver.vo.CarSerialImagesVO;
import com.ecarinfo.weichexin.httpserver.vo.City;
import com.ecarinfo.weichexin.httpserver.vo.CityVo;
import com.ecarinfo.weichexin.httpserver.vo.HistoryVO;
import com.ecarinfo.weichexin.httpserver.vo.WcxTrafficCarsVo;
import com.ecarinfo.weichexin.po.Activity;
import com.ecarinfo.weichexin.po.BackgroundUser;
import com.ecarinfo.weichexin.po.CarBrand;
import com.ecarinfo.weichexin.po.CarModel;
import com.ecarinfo.weichexin.po.CarSerial;
import com.ecarinfo.weichexin.po.CarSerialGroup;
import com.ecarinfo.weichexin.po.CarTip;
import com.ecarinfo.weichexin.po.EtTrafficItem;
import com.ecarinfo.weichexin.po.FivesaasCarInfo;
import com.ecarinfo.weichexin.po.ReserveWorkerInfo;
import com.ecarinfo.weichexin.po.WcxShowCarModels;
import com.ecarinfo.weichexin.po.WcxTrafficCars;
import com.ecarinfo.weichexin.po.WcxUserInfo;
import com.ecarinfo.weichexin.rm.ActivityRM;
import com.ecarinfo.weichexin.rm.BackgroundUserRM;
import com.ecarinfo.weichexin.rm.CarSerialRM;
import com.ecarinfo.weichexin.rm.EtTrafficItemRM;
import com.ecarinfo.weichexin.rm.FivesaasCarInfoRM;
import com.ecarinfo.weichexin.rm.WcxShowCarModelsRM;
import com.ecarinfo.weichexin.rm.WcxTrafficCarsRM;
import com.ecarinfo.weichexin.rm.WcxUserInfoRM;

@Service
public class WeichexinseviceImpl {
	private static Logger logger = Logger.getLogger(WeichexinseviceImpl.class);
	@Resource
	private GenericDao genericDao;
	
	@Resource
	private ActivityDao activityDao;
	
	@Resource
	private CarTipDao carTipDao;
	
	@Resource
	JavaMailSenderImpl mailSender;
	
	@Resource
	ConfigUtils configUtils;
		
	@Resource
	TrafficSearchApi trafficSearchApi;
	
	/**
	 * 获取优惠活动列表
	 * 废弃
	 * @param org_code 机构编码
	 * @param auth_code 认证码
	 * @param car_id 车辆ID
	 * @param car_no 车牌号
	 * @param car_type 车辆类型编号
	 * @param page_no 当前页码
	 * @param is_detail 0 or 1 是否请求详细的贴士内容(此字段为1时响应结果中会返回详细的贴士内容)
	 * @return
	 */
	public ActivityResponse getActivityList(String org_code, String openid, Integer page_no, int is_detail) {
			Criteria whereBy = new Criteria();
			whereBy.eq(ActivityRM.orgId, org_code);
			whereBy.eq(ActivityRM.status, 2, CondtionSeparator.AND);
			
			if (page_no != null && page_no < 1)
				page_no = 1;
			if (page_no > 0)
				whereBy.setPage(page_no, 20);
			ECPage<Activity> page = PagingManager.list(genericDao, Activity.class, whereBy.orderBy(ActivityRM.ctime, OrderType.DESC));

			ActivityResponse apiDtos = new ActivityResponse();
			apiDtos.setCurrent_page(page_no);
			apiDtos.setTotal_page(page.getTotalPage());

			List<ActivityItemResponse> dtos = new ArrayList<ActivityItemResponse>();
			for (Activity dto : page.getList()) {
				ActivityItemResponse apiDto = new ActivityItemResponse();
				apiDto.setAddress(dto.getAddress());
				if (is_detail > 0)
					apiDto.setContent(dto.getContent());
				apiDto.setCtime(DateUtils.dateToString(dto.getCtime(), TimeFormatter.YYYY_MM_DD_HH_MM_SS));
				apiDto.setEnd_date(DateUtils.dateToString(dto.getEtime(), TimeFormatter.YYYY_MM_DD));
				apiDto.setStart_date(DateUtils.dateToString(dto.getBtime(), TimeFormatter.YYYY_MM_DD));
//				apiDto.setLink_man(dto.getLinkMan());
//				apiDto.setLink_tel(dto.getServiceTel());
				apiDto.setSid(dto.getId());
				apiDto.setTitle(dto.getTitle());
				apiDto.setUrl(dto.getImage());
//					apiDto.setScreenshot_img(imgUrl + getAppImage(dto.getImage()));
				dtos.add(apiDto);
			}
			apiDtos.setActivity_items(dtos);
	
		return apiDtos;
	}
	
	//获取活动列表
	public List<Activity> activityModel(String openid,String orgCode,Integer limit,Integer category_id,boolean has_image){
		/*
		 * 1.判断用户类型，是否关联app车辆
		 */
		WcxUserInfo wuserInfo= null;
		wuserInfo =	DaoTool.queryForOne(WcxUserInfo.class, "select * from wcx_user_info where org_code = ? and open_id = ? ", orgCode, openid);
		//wuserInfo =	this.genericDao.findOne(WcxUserInfo.class, new Criteria().eq(WcxUserInfoRM.openId, openid)
		//					.eq(WcxUserInfoRM.orgCode, orgCode, CondtionSeparator.AND));
		FivesaasCarInfo five = this.genericDao.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.wcxUserId, wuserInfo.getId())
				.eq(FivesaasCarInfoRM.orgCode, orgCode, CondtionSeparator.AND));
		String date = DateUtils.dateToString(new Date(), TimeFormatter.YYYY_MM_DD);
		List<Activity> list = new ArrayList<Activity>();
		if(five==null||(five!=null&&five.getCarSource()==1)){//和app车辆无关联 没有车型
			//查找机构下的活动信息
//			@Param("model_id")
//			 @Param("serial_id") String paramString2, 
//			 @Param("brand_id") String paramString3,
//			 @Param("org_id") Long paramLong2, 
//			 @Param("current_mileage") Integer paramInteger2, 
//			 @Param("limit") Integer paramInteger3, 
//			 @Param("image_exists") Boolean paramBoolean, 
//			 @Param("today") String paramString4);
			list = this.activityDao.findActivitysListEx(null,null, null, orgCode, null, limit, null, date,category_id,has_image);
			
		}if(five!=null&&five.getCarSource()!=1){
			list = this.activityDao.findActivitysListEx(five.getModelId(),five.getSerialId(), five.getBrandId(), orgCode, five.getCurrentMileage(), limit, null, date,category_id,has_image);
		}
		return list;
	}
	
	//贴士列表
	public List<CarTip> tipsModel(String openid,String orgCode,Integer limit){
		/*
		 * 1.判断用户类型，是否关联app车辆
		 */
		WcxUserInfo wuserInfo= null;
		wuserInfo =	this.genericDao.findOne(WcxUserInfo.class, new Criteria().eq(WcxUserInfoRM.openId, openid)
							.eq(WcxUserInfoRM.orgCode, orgCode, CondtionSeparator.AND));
		FivesaasCarInfo five = this.genericDao.findOne(FivesaasCarInfo.class, new Criteria().eq(FivesaasCarInfoRM.wcxUserId, wuserInfo.getId())
				.eq(FivesaasCarInfoRM.orgCode, orgCode, CondtionSeparator.AND));
		String date = DateUtils.dateToString(new Date(), TimeFormatter.YYYY_MM_DD);
		List<CarTip> list = new ArrayList<CarTip>();
		if(five==null||(five!=null&&five.getCarSource()==1)){//和app车辆无关联 没有车型
			//查找机构下的活动信息
//			@Param("model_id")
//			 @Param("serial_id") String paramString2, 
//			 @Param("brand_id") String paramString3,
//			 @Param("org_id") Long paramLong2, 
//			 @Param("current_mileage") Integer paramInteger2, 
//			 @Param("limit") Integer paramInteger3, 
//			 @Param("image_exists") Boolean paramBoolean, 
			list = this.carTipDao.findTipsListEx(null,null, null, orgCode, null, limit, null);
			
		}if(five!=null&&five.getCarSource()!=1){
			list = this.carTipDao.findTipsListEx(five.getModelId(),five.getSerialId(), five.getBrandId(), orgCode, five.getCurrentMileage(), limit, null);
		}
		return list;
	}
	
	//违章车辆
	public List<WcxTrafficCars> getTrafficCars(String openid,String orgCode,Long id){
		if(StringUtils.isNotBlank(openid)){
			WcxUserInfo wcxUserInfo=this.genericDao.findOne(WcxUserInfo.class, new Criteria().eq(WcxUserInfoRM.openId, openid).eq(WcxUserInfoRM.orgCode, orgCode, CondtionSeparator.AND));
			return this.genericDao.findList(WcxTrafficCars.class,new Criteria().eq(WcxTrafficCarsRM.wcxUserId, wcxUserInfo.getId()).eq(WcxTrafficCarsRM.orgCode, orgCode, CondtionSeparator.AND).eq(WcxTrafficCarsRM.status, 1, CondtionSeparator.AND));
		}if(id>0){
			return  this.genericDao.findList(WcxTrafficCars.class,new Criteria().eq(WcxTrafficCarsRM.wcxUserId, id).eq(WcxTrafficCarsRM.orgCode, orgCode, CondtionSeparator.AND).eq(WcxTrafficCarsRM.status, 1, CondtionSeparator.AND));
		}
		return null;
	}
	
	//违章查询
	public List<WcxTrafficCarsVo> getTrafficItems(String openid,String orgCode,Long id){
		List<WcxTrafficCarsVo> volist = new ArrayList<WcxTrafficCarsVo>();
		List<WcxTrafficCars> carlist = new ArrayList<WcxTrafficCars>();
		if(StringUtils.isNotBlank(openid)){
			WcxUserInfo wcxUserInfo=this.genericDao.findOne(WcxUserInfo.class, new Criteria().eq(WcxUserInfoRM.openId, openid).eq(WcxUserInfoRM.orgCode, orgCode, CondtionSeparator.AND));
			carlist = this.genericDao.findList(WcxTrafficCars.class,new Criteria().eq(WcxTrafficCarsRM.wcxUserId, wcxUserInfo.getId()).eq(WcxTrafficCarsRM.orgCode, orgCode, CondtionSeparator.AND).eq(WcxTrafficCarsRM.status, 1, CondtionSeparator.AND));
		}if(id!=null&&id>0){
			carlist = this.genericDao.findList(WcxTrafficCars.class,new Criteria().eq(WcxTrafficCarsRM.wcxUserId, id).eq(WcxTrafficCarsRM.orgCode, orgCode, CondtionSeparator.AND).eq(WcxTrafficCarsRM.status, 1, CondtionSeparator.AND));
		}
		if(carlist!=null&&carlist.size()>0){
			for(WcxTrafficCars car:carlist){
				Integer count =0 ;			
				try {
					count = trafficSearchApi.getTrafficInfos(car.getCarNo()).size();
				} catch (Exception e) {
					logger.error(String.format("trafficSearchApi.getTrafficInfos(%s) error", car.getCarNo()), e);
				}				
				WcxTrafficCarsVo vo =new WcxTrafficCarsVo(count,car);
				volist.add(vo);
			}
		}
		return volist;
	}
	
	//查询城市
	public List<CityVo>  getCityList(String orgCode){
		/*
		 * 数据暂时写死 拿海南的城市 
		 */
		List<CityVo> volist = new ArrayList<CityVo>();
		
				
		CityVo voHN= new CityVo();
		voHN.setPid(25);
		voHN.setPname("海南省");

		List<City> cityListHN = new ArrayList<City>();
		City city1 = new City(278,25,"海口","琼A");
		City city2 = new City(278,25,"三亚","琼B");
		City city3 = new City(278,25,"琼北","琼C");
		City city4 = new City(278,25,"琼南","琼D");
		City city5 = new City(278,25,"洋浦","琼E");
		cityListHN.add(city1);
		cityListHN.add(city2);
		cityListHN.add(city3);
		cityListHN.add(city4);
		cityListHN.add(city5);
		voHN.setCityList(cityListHN);
		volist.add(voHN);
		
		CityVo voGD= new CityVo();		
		voGD.setPid(25);
		voGD.setPname("广东省");

		List<City> cityListGD = new ArrayList<City>();
		City city = new City(278,25, "深圳","粤B");
		cityListGD.add(city);
		voGD.setCityList(cityListGD);
		volist.add(voGD);
		
		return volist;
	}
	
	//发送邮件
	public void mailSend(String org_code,ReserveWorkerInfo info){
		String url = configUtils.getEcar5s_url();
		String text = "<html>您好：<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有新的保养预约请求需要及时处理,车主信息如下：<hr>手机号："+info.getContactTel()+"&nbsp;&nbsp;&nbsp;车牌号："+info.getCarNo()
				+"&nbsp;&nbsp;&nbsp;预约时间："+DateUtils.dateToString(info.getReserveDate(), TimeFormatter.YYYY_MM_DD)+"&nbsp;&nbsp;"+info.getTimePoint()+"" +
						"<br>请登录：<a href=\""+url+"\">"+url+"</a>及时处理。" +
						"</html>";
		String title = "新的保养预约请求,请及时处理";
		String scc = this.configUtils.getEmail();
		StringBuffer sb =new StringBuffer();
		sb.append(scc+",");
		if(StringUtils.isNotBlank(org_code)){
			List<BackgroundUser> list = this.genericDao.findList(BackgroundUser.class, new Criteria().eq(BackgroundUserRM.orgCode, org_code).eq(BackgroundUserRM.isReserveHandler, 1, CondtionSeparator.AND).eq(BackgroundUserRM.isAway, 0, CondtionSeparator.AND).eq(BackgroundUserRM.delFlag, 0, CondtionSeparator.AND));
			
			if(list!=null&&list.size()>0){
				for(int i=0;i<list.size();i++){
					if(StringUtils.isNotBlank(list.get(i).getEmail())){
						sb.append(list.get(i).getEmail()+",");
					}
				}
			}
			String toAddress[]=sb.toString().substring(0,sb.toString().lastIndexOf(",")).split(",");
			
			if(toAddress.length>0){
				for(int i=0;i<toAddress.length;i++){
					/*ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
					//获取JavaMailSender bean
					JavaMailSenderImpl sender = (JavaMailSenderImpl) ctx.getBean("mailSender");
					*/
					MailTask mailTask = new MailTask(mailSender,text,toAddress[i], null, title);
					mailTask.send();
				}
			}
		}
	}
	
	/*查车型*/
	public List<WcxShowCarModels>  getShowModels(Integer orgId){
		
		return	this.genericDao.findList(WcxShowCarModels.class, new Criteria().eq(WcxShowCarModelsRM.status, 1).eq(WcxShowCarModelsRM.orgId, orgId, CondtionSeparator.AND));
		
	}
	
	/* 根据车型反查出车系*/
	public List<CarSerial> getCarSerialByModel(Integer orgId){
		
		List<WcxShowCarModels> modelList = getShowModels(orgId);
		Integer [] modelIds=null;
		if(modelList!=null&&modelList.size()>0){
			modelIds = new Integer[modelList.size()];
			for(int i =0 ;i<modelList.size();i++){
				modelIds[i]=modelList.get(i).getId();
			}
		}
		List<CarSerial> serialList = this.genericDao.findList(CarSerial.class, new Criteria().in(CarSerialRM.id, modelIds,CondtionSeparator.AND));
		return serialList;
	}
	
	/*根据车型找出车的所属厂牌*/
	
	public List<CarBrand> getCarBrandByModel(Integer orgId){
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DISTINCT b.* from wcx_show_car_models wm LEFT join car_model m on wm.model_id =m.id"+
					" LEFT JOIN car_serial s on s.id = m.serial_id "+
					" LEFT JOIN car_brand b on  b.id = s.brand_id  "+
					" where wm.status=1");
		sb.append(String.format(" and wm.org_id= %d", orgId));
		List<CarBrand> list = DaoTool.queryForList(CarBrand.class, sb.toString());
		return list;
	}
	
	/*
	 * 根据厂牌查找现有的车型分组
	 */
	public List<CarSerialGroup> getCarSerialGroupByBrandId(Integer orgId,Integer brandId){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DISTINCT g.* from wcx_show_car_models wm LEFT join car_model m on wm.model_id =m.id"+
			" LEFT JOIN car_serial s on s.id = m.serial_id "+
			" LEFT JOIN car_brand b on  b.id = s.brand_id  "+
			" LEFT JOIN car_serial_group g on  g.id = s.group_id"+
			" where wm.status=1 " );
		sb.append(String.format(" and wm.org_id=%d", orgId));
		sb.append(String.format(" and b.id=%d", brandId));
		List<CarSerialGroup> list = DaoTool.queryForList(CarSerialGroup.class, sb.toString());
		return list;
	}
	
	/*
	 * 根据厂牌查找现有的车型分组
	 */
	public List<CarSerialGroup> getCarSerialGroup(Integer orgId){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DISTINCT g.* from wcx_show_car_models wm LEFT join car_model m on wm.model_id =m.id"+
			" LEFT JOIN car_serial s on s.id = m.serial_id "+
			" LEFT JOIN car_brand b on  b.id = s.brand_id  "+
			" LEFT JOIN car_serial_group g on  g.id = s.group_id"+
			" where wm.status=1 " );
		sb.append(String.format(" and wm.org_id=%d", orgId));
		sb.append(String.format(" order by wm.is_top desc,wm.top_time desc,wm.pub_time desc"));
		List<CarSerialGroup> list = DaoTool.queryForList(CarSerialGroup.class, sb.toString());
		return list;
	}
	
	/*
	 * 根据groupId 查出车系
	 */
	public List<CarSerial> getCarSerialByGroupId(Integer orgId,Integer groupId){
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DISTINCT s.* from wcx_show_car_models wm LEFT join car_model m on wm.model_id =m.id"+
			" LEFT JOIN car_serial s on s.id = m.serial_id "+
			" where wm.status=1 " );
		sb.append(String.format(" and wm.org_id=%d", orgId));
		sb.append(String.format(" and s.group_id=%d", groupId));
		sb.append(String.format(" order by wm.is_top desc,wm.top_time desc,wm.pub_time desc"));
		List<CarSerial> list = DaoTool.queryForList(CarSerial.class, sb.toString());
		return list;
	}
	
	/*
	 * 根据groupId 查出车系
	 */
	public List<CarSerialImagesVO> getCarSerialImagesByGroupId(Integer orgId,Integer groupId){
		StringBuffer sb = new StringBuffer();
		String bDate=DateUtils.dateToString(new Date(), TimeFormatter.FORMATTER2);
		String eDate =bDate+" 23:59:59";
		sb.append("SELECT ci.url as image_url, s.* , " +
			" (case when sum(h.id)>0  THEN 1 ELSE NULL END) as has_youhui, "+
			" (case when sum(h.has_gift)>0  THEN 1 ELSE NULL END) as has_gift "+
			" from wcx_show_car_models wm " +
			" LEFT join car_model m on wm.model_id =m.id"+
			" LEFT JOIN car_serial s on s.id = m.serial_id "+
			" LEFT JOIN car_serial_images ci on ( ci.serial_id=s.id and ci.status=1 )"+
			" LEFT JOIN car_model_youhui h ON (wm.model_id = h.model_id and h.status=1 and h.btime<= '"+bDate+"' and h.etime>='"+eDate+"'  )"+
			" where wm.status=1 " );
		sb.append(String.format(" and wm.org_id=%d", orgId));
		sb.append(String.format(" and s.group_id=%d", groupId));
		sb.append(String.format(" group by s.id"));
		sb.append(String.format(" order by wm.is_top desc,wm.top_time desc,wm.pub_time desc"));
		List<CarSerialImagesVO> list = DaoTool.queryForList(CarSerialImagesVO.class, sb.toString());
		return list;
	}
	/*
	 * 根据车系查车型
	 */
	public List<CarModelVO> getCarModelBySerialId(Integer orgId,Integer serialId){
		StringBuffer sb = new StringBuffer();
		String bDate=DateUtils.dateToString(new Date(), TimeFormatter.FORMATTER2);
		String eDate =bDate+" 23:59:59";
		sb.append("SELECT DISTINCT m.* ,wm.ref_price as ref_price,wm.our_price as our_price ,h.has_gift has_gift, h.id AS has_youhui  from wcx_show_car_models wm LEFT join car_model m on wm.model_id =m.id"+
			" LEFT JOIN car_model_youhui h ON (wm.model_id = h.model_id and h.status=1 and h.btime<= '"+bDate+"' and h.etime>='"+eDate+"'  ) where wm.status=1 " );
		sb.append(String.format(" and wm.org_id=%d", orgId));
		sb.append(String.format(" and m.serial_id=%d", serialId));
		sb.append(" order by wm.is_top desc,wm.top_time desc,wm.pub_time desc");
		List<CarModelVO> list = DaoTool.queryForList(CarModelVO.class, sb.toString());
		return list;
	}
	
	//试驾预约历史清单
	public List<HistoryVO> getHistoryList(String openid,Integer orgId){
		StringBuffer sb = new StringBuffer();
		sb.append("select ta.id,ta.appoint_time,ta.`status` ,cm.`name`, cm.serial_name,cm.`year`  from testdrive_appoint ta LEFT JOIN car_model cm on ta.model_id=cm.id where 1=1 " );
		if(com.alibaba.dubbo.common.utils.StringUtils.isNotEmpty(openid)){
			sb.append(String.format(" and  ta.open_id='%s'", openid));
		}
		if(null!=orgId&&orgId>0){
			sb.append(String.format(" and  ta.org_id='%d'", orgId));
		}
		List<HistoryVO> list = DaoTool.queryForList(HistoryVO.class, sb.toString());
		return list;
	}
	
	//活动预约历史清单
	public List<ActivityHistoryVO> getActivityHistoryList(String openid,Integer orgId){
		StringBuffer sb = new StringBuffer();
		sb.append("select aa.*, a.title as title,a.btime as btime  ,a.etime as etime,a.image as image , a.phone_no as phone_no from activity_appoint aa LEFT JOIN activity a on aa.activity_id = a.id where 1=1 " );
		if(com.alibaba.dubbo.common.utils.StringUtils.isNotEmpty(openid)){
			sb.append(String.format(" and  aa.open_id='%s'", openid));
		}
		if(null!=orgId&&orgId>0){
			sb.append(String.format(" and  aa.org_id='%d'", orgId));
		}
		sb.append(" order by aa.request_time desc");
		List<ActivityHistoryVO> list = DaoTool.queryForList(ActivityHistoryVO.class, sb.toString());
		return list;
	}
}
