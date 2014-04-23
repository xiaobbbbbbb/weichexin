package com.ecarinfo.weichexin.httpserver.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ecarinfo.persist.criteria.Criteria;
import com.ecarinfo.persist.criteria.Criteria.CondtionSeparator;
import com.ecarinfo.persist.exdao.GenericDao;
import com.ecarinfo.weichexin.httpserver.dto.request.FullWXRequest;
import com.ecarinfo.weichexin.httpserver.utils.ConfigUtils;
import com.ecarinfo.weichexin.po.Activity;
import com.ecarinfo.weichexin.po.CarSerialGroup;
import com.ecarinfo.weichexin.po.CarTip;
import com.ecarinfo.weichexin.po.ServiceOrg;
import com.ecarinfo.weichexin.po.WcxTrafficCars;
import com.ecarinfo.weichexin.po.WcxUserInfo;
import com.ecarinfo.weichexin.rm.ServiceOrgRM;
import com.ecarinfo.weichexin.rm.WcxUserInfoRM;


/**
 * <xml>
*<ToUserName><![CDATA[${toUserName}]]></ToUserName>
*<FromUserName><![CDATA[${fromUserName}]]></FromUserName>
*<CreateTime>${createTime}</CreateTime>
*<MsgType><![CDATA[text]]></MsgType>
*<Content><![CDATA[${content}]]></Content>
*</xml>
 *
 */
@Service
public class WXRequestProcessor {
	@Resource
	private GenericDao genericDao;
	
	@Resource
	private WeichexinseviceImpl weichexinseviceImpl;
	
	@Resource
	ConfigUtils configUtils;
	
	/*点击违章查询菜单*/
	public Map<String, String> clickTrafficMenu(FullWXRequest wx) {
		Map<String, String> model = new HashMap<String, String>();
		String openid = wx.getFromUserName();
		String orgCode = wx.getOrgCode(); 
		List<WcxTrafficCars> list = this.weichexinseviceImpl.getTrafficCars(openid, orgCode,0l);
		/*
		 * 要判断是否已经添加了车辆,没有的话则没有违章查询的模板，只有添加
		 */
		if(list!=null&&list.size()>0){
			model.put("msgType", "news3");
			model.put("${title3}", "[管理车牌]修改已录入的车牌信息");
			model.put("${picUrl3}", this.configUtils.getDomain()+"/static/wcx/traffic-update.png");
			model.put("${url3}", this.configUtils.getDomain()+"/driverHelper/carList?openid="+wx.getFromUserName()+"&org_code="+wx.getOrgCode());
		}else{
			model.put("msgType", "news2");
		}
			model.put("${toUserName}", wx.getFromUserName());
			model.put("${fromUserName}", wx.getToUserName());
			model.put("${createTime}", String.valueOf(new Date().getTime()));		
			
			model.put("${title1}", "车辆违章信息查询");
			model.put("${picUrl1}", this.configUtils.getDomain()+"/static/wcx/traffic.png");
			model.put("${url1}", this.configUtils.getDomain()+"/driverHelper/carList?openid="+wx.getFromUserName()+"&org_code="+wx.getOrgCode());
			
			model.put("${title2}", "[新增车牌]查询新车牌违章信息");
			model.put("${picUrl2}", this.configUtils.getDomain()+"/static/wcx/traffic-add.png");
			model.put("${url2}", this.configUtils.getDomain()+"/driverHelper/addList?openid="+wx.getFromUserName()+"&org_code="+wx.getOrgCode());
			
			
		return model;
	}
	/*点击贴士菜单*/
	public Map<String, String> clickTipMenu(FullWXRequest wx) {
		
		String openid = wx.getFromUserName();
		String orgCode = wx.getOrgCode(); 
		List<CarTip> list = this.weichexinseviceImpl.tipsModel(openid, orgCode, 4);
		Map<String, String> model = new HashMap<String, String>();
		String ctx=configUtils.getImg_url();//图片地址头
		String domain =this.configUtils.getDomain();
		if(list!=null&&list.size()>0){
			model.put("msgType", "news"+list.size());
			model.put("${toUserName}", wx.getFromUserName());
			model.put("${fromUserName}", wx.getToUserName());
			model.put("${createTime}", String.valueOf(new Date().getTime()));
			if(list.size()<=3){
				for(int i=0;i<list.size();i++){
					model.put("${title"+(i+1)+"}", list.get(i).getTitle());
					if(list.get(i).getImage()!=null&&!"".equals(list.get(i).getImage())){
						model.put("${picUrl"+(i+1)+"}", ctx+list.get(i).getImage());
					}else{
						model.put("${picUrl"+(i+1)+"}", domain+"/static/wcx/tieshi.png");
					}
					model.put("${url"+(i+1)+"}", this.configUtils.getDomain()+"/tips/detail?id="+list.get(i).getId());
				}
			}
			if(list.size()==4){
				for(int i=0;i<3;i++){
					model.put("${title"+(i+1)+"}", list.get(i).getTitle());
					if(list.get(i).getImage()!=null&&!"".equals(list.get(i).getImage())){
						model.put("${picUrl"+(i+1)+"}", ctx+list.get(i).getImage());
					}else{
						model.put("${picUrl"+(i+1)+"}", domain+"/static/wcx/tieshi.png");
					}
					model.put("${url"+(i+1)+"}", this.configUtils.getDomain()+"/tips/detail?id="+list.get(i).getId());
				}
				model.put("${title4}", "查看更多  >>");
				model.put("${url4}", this.configUtils.getDomain()+"/tips/list?openid="+openid+"&org_code="+orgCode);
			}
		}else{
			model.put("msgType", "text");
			model.put("${toUserName}", wx.getFromUserName());
			model.put("${fromUserName}", wx.getToUserName());
			model.put("${createTime}", String.valueOf(new Date().getTime()));		
			model.put("${content}", "没有贴士，敬请关注！");
		}
		return model;
	}
	
	/*点击活动菜单*/
	public Map<String, String> clickActivityMenu(FullWXRequest wx) {
		String openid = wx.getFromUserName();
		String orgCode = wx.getOrgCode(); 
		List<Activity> list = this.weichexinseviceImpl.activityModel(openid, orgCode, 2,null,false);
		Map<String, String> model = new HashMap<String, String>();
		String ctx=configUtils.getImg_url();//图片地址头
		String domain =this.configUtils.getDomain();
		if(list!=null&&list.size()>0){
			
			model.put("${toUserName}", wx.getFromUserName());
			model.put("${fromUserName}", wx.getToUserName());
			model.put("${createTime}", String.valueOf(new Date().getTime()));
			if(list.size()==1){
				model.put("msgType", "news1");
				model.put("${title1}", list.get(0).getTitle());
				if(list.get(0).getImage()!=null&&!"".equals(list.get(0).getImage())){
					model.put("${picUrl1}", ctx+list.get(0).getImage());
				}else{
					model.put("${picUrl1}", domain+"/static/wcx/huodong.png");
				}
				model.put("${url1}", this.configUtils.getDomain()+"/activity/brief?id="+list.get(0).getId()+"&openid="+openid+"&orgCode="+orgCode);
			}else if(list.size()==2){
				
				model.put("msgType", "news4");
				
				model.put("${title1}", "精彩活动");
				model.put("${picUrl1}", domain+"/static/wcx/huodong.png");
				model.put("${url1}", this.configUtils.getDomain()+"/activity/list?openid="+openid+"&org_code="+orgCode);
				
				for(int i=0;i<list.size();i++){
					model.put("${title"+(i+2)+"}", list.get(i).getTitle());
					if(list.get(i).getImage()!=null&&!"".equals(list.get(i).getImage())){
						model.put("${picUrl"+(i+2)+"}", ctx+list.get(i).getImage());
					}else{
						model.put("${picUrl"+(i+2)+"}", domain+"/static/wcx/huodong.png");
					}
					model.put("${url"+(i+2)+"}", this.configUtils.getDomain()+"/activity/brief?id="+list.get(i).getId()+"&openid="+openid+"&orgCode="+orgCode);
				}
				
				model.put("${title4}", "查看更多 >>");
				model.put("${url4}", this.configUtils.getDomain()+"/activity/list?openid="+openid+"&org_code="+orgCode);
				
			}
		}
//			if(list.size()<=3){
//				for(int i=0;i<list.size();i++){
//						model.put("${title"+(i+1)+"}", list.get(i).getTitle());
//						if(list.get(i).getImage()!=null&&!"".equals(list.get(i).getImage())){
//							model.put("${picUrl"+(i+1)+"}", ctx+list.get(i).getImage());
//						}else{
//							model.put("${picUrl"+(i+1)+"}", domain+"/static/wcx/huodong.png");
//						}
//						model.put("${url"+(i+1)+"}", this.configUtils.getDomain()+"/activity/brief?id="+list.get(i).getId()+"&openid="+openid+"&orgCode="+orgCode);
//				}
//			}
//			if(list.size()==4){
//				for(int i=0;i<3;i++){
//					model.put("${title"+(i+1)+"}", list.get(i).getTitle());
//					if(list.get(i).getImage()!=null&&!"".equals(list.get(i).getImage())){
//						model.put("${picUrl"+(i+1)+"}", ctx+list.get(i).getImage());
//					}else{
//						model.put("${picUrl"+(i+1)+"}", domain+"/static/wcx/huodong.png");
//					}
//					model.put("${url"+(i+1)+"}", this.configUtils.getDomain()+"/activity/brief?id="+list.get(i).getId()+"&openid="+openid+"&orgCode="+orgCode);
//				}
//				model.put("${title4}", "查看更多 >>");
//				model.put("${url4}", this.configUtils.getDomain()+"/activity/list?openid="+openid+"&org_code="+orgCode);
//			}
//		}
		else{
			model.put("msgType", "text");
			model.put("${toUserName}", wx.getFromUserName());
			model.put("${fromUserName}", wx.getToUserName());
			model.put("${createTime}", String.valueOf(new Date().getTime()));		
			model.put("${content}", "近期没有活动，敬请关注！");
		}
//		model.put("msgType", "text");
				
		return model;
	}
	
	/*点击投诉菜单*/
	public Map<String, String> clickTousuMenu(FullWXRequest wx) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("msgType", "text");
		model.put("${toUserName}", wx.getFromUserName());
		model.put("${fromUserName}", wx.getToUserName());
		model.put("${createTime}", String.valueOf(new Date().getTime()));		
		model.put("${content}", "尊敬的车主您好，如果您对我们的服务不满意，可输入文字或语音进行投诉");
		return model;
	}
	
	/*点击联系我们菜单*/
	public Map<String, String> clickContactMenu(FullWXRequest wx) {
		String orgCode=wx.getOrgCode();
		ServiceOrg org=null;
		if(StringUtils.isNotEmpty(orgCode)){
			
			org=this.genericDao.findOne(ServiceOrg.class,new Criteria().eq(ServiceOrgRM.code,orgCode));
		}	
		StringBuffer sb=new StringBuffer();
		if(org.getTelForSales()!=null&&!"".equals(org.getTelForSales())){
			sb.append("销售热线:"+org.getTelForSales()+"\n\n");
		}
		if(org.getTelForMaintenance()!=null&&!"".equals(org.getTelForMaintenance())){
			sb.append("预约保养:"+org.getTelForMaintenance()+"\n\n");
		}
		if(org.getRenewInsureTel()!=null&&!"".equals(org.getRenewInsureTel())){
			sb.append("续保预约:"+org.getRenewInsureTel()+"\n\n");
		}
		if(org.getUsedCarServeTel()!=null&&!"".equals(org.getUsedCarServeTel())){
			sb.append("二手车:"+org.getUsedCarServeTel()+"\n\n");
		}
		if(org.getYearCheckServeTel()!=null&&!"".equals(org.getYearCheckServeTel())){
			sb.append("年检服务:"+org.getYearCheckServeTel()+"\n\n");
		}
		if(org.getHelpTel()!=null&&!"".equals(org.getHelpTel())){
			sb.append("救援电话:"+org.getHelpTel()+"\n\n");
		}
		/*
		 * 续保预约
		 */
		if(StringUtils.isNotEmpty(org.getAddress())){
			sb.append("公司地址:"+org.getAddress());
		}
		
		Map<String, String> model = new HashMap<String, String>();
		model.put("msgType", "text");
		model.put("${toUserName}", wx.getFromUserName());
		model.put("${fromUserName}", wx.getToUserName());
		model.put("${createTime}", String.valueOf(new Date().getTime()));		
		model.put("${content}", sb.toString() );
		return model;
	}
	
	/*用户订阅微车信*/
	public Map<String, String> subscribeWCX(FullWXRequest wx) {
		
		WcxUserInfo wuserInfo= null;
		wuserInfo =	this.genericDao.findOne(WcxUserInfo.class, new Criteria().eq(WcxUserInfoRM.openId, wx.getFromUserName())
							.eq(WcxUserInfoRM.orgCode, wx.getOrgCode(), CondtionSeparator.AND));
		//存在更新状态
		if(wuserInfo!=null){
			wuserInfo.setSubscribe(1);
			wuserInfo.setSubscribeTime(new Date());
			this.genericDao.update(wuserInfo);
		}else{//没有就插入
			wuserInfo=new WcxUserInfo();
			wuserInfo.setOpenId(wx.getFromUserName());
			wuserInfo.setOrgCode(wx.getOrgCode());
			wuserInfo.setSubscribeTime(new Date());
			wuserInfo.setSubscribe(1);//关注1,0取消
			this.genericDao.insert(wuserInfo);
		}
		ServiceOrg org = this.genericDao.findOne(ServiceOrg.class, new Criteria().eq(ServiceOrgRM.code, wx.getOrgCode()));
		Map<String, String> model = new HashMap<String, String>();
		model.put("msgType", "text");
		model.put("${toUserName}", wx.getFromUserName());
		model.put("${fromUserName}", wx.getToUserName());
		model.put("${createTime}", String.valueOf(new Date().getTime()));		
		model.put("${content}", "您好，欢迎关注" + org.getName());
		return model;
	}
	
	/*用户取消订阅微车信*/
	public Map<String, String> unsubscribeWCX(FullWXRequest wx) {
		WcxUserInfo 
			wuserInfo =	this.genericDao.findOne(WcxUserInfo.class, new Criteria().eq(WcxUserInfoRM.openId, wx.getFromUserName()));
		wuserInfo.setSubscribe(0);
		wuserInfo.setUnsubscribeTime(new Date());
		this.genericDao.update(wuserInfo);
		Map<String, String> model = new HashMap<String, String>();
		model.put("msgType", "text");
		model.put("${toUserName}", wx.getFromUserName());
		model.put("${fromUserName}", wx.getToUserName());
		model.put("${createTime}", String.valueOf(new Date().getTime()));		
		model.put("${content}", "接收到你的请求:" + wx.toString());
		return model;
	}
	
	/*用户普通消息车信*/
	public Map<String, String> receiveUserMessage(FullWXRequest wx) {
		//保存用户消息
		WcxUserInfo wi = this.genericDao.findOne(WcxUserInfo.class, new Criteria().eq(WcxUserInfoRM.openId, wx.getFromUserName()));
		ServiceOrg org  = this.genericDao.findOne(ServiceOrg.class,new Criteria().eq(ServiceOrgRM.code,wi.getOrgCode()));
/*		WcxUserMsg wcxUserMsg = new WcxUserMsg();
		wcxUserMsg.setOpenId(wx.getFromUserName());
		wcxUserMsg.setUserId(wi.getId());
		wcxUserMsg.setContent(wx.getContent());
		if(StringUtils.isNotBlank(wx.getLocation_X())){
			wcxUserMsg.setLocationX(new Float(wx.getLocation_X()));
		}
		if(StringUtils.isNotBlank(wx.getLocation_Y())){
			wcxUserMsg.setLocationY(new Float(wx.getLocation_Y()));
		}
		wcxUserMsg.setMediaFormat(wx.getFormat());
		if(StringUtils.isNotBlank(wx.getMediaId())){
			wcxUserMsg.setMediaId(wx.getMediaId());
		}
		if(StringUtils.isNotBlank(wx.getMsgId())){
			wcxUserMsg.setMsgId(new Long(wx.getMsgId()));
		}
		if(StringUtils.isNotBlank(wx.getScale())){
			wcxUserMsg.setScale(new Integer(wx.getScale()));
		}
		wcxUserMsg.setLable(wx.getLabel());
		wcxUserMsg.setOrgCode(wi.getOrgCode());
		wcxUserMsg.setOrgId(org.getSid());
		if(StringUtils.isNotBlank(wx.getThumbMediaId())){
			wcxUserMsg.setThumbMediaId(wx.getThumbMediaId());
		}
		
		wcxUserMsg.setTime(new Date(Long.parseLong(wx.getCreateTime())*1000));
		wcxUserMsg.setTitle(wx.getTitle());
		wcxUserMsg.setType(WcxMsgTypes.fromString(wx.getMsgType()).getDBValue()); //type 应该是String类型
		wcxUserMsg.setUrl(wx.getUrl());
		wcxUserMsg.setCtime(new Date());
		wcxUserMsg.setStatus(0);
		this.genericDao.insert(wcxUserMsg);*/
		
		Map<String, String> model = new HashMap<String, String>();
		model.put("msgType", "text");
		model.put("${toUserName}", wx.getFromUserName());
		model.put("${fromUserName}", wx.getToUserName());
		model.put("${createTime}", String.valueOf(new Date().getTime()));		
		model.put("${content}", "感谢您的留言。");
		
		
		return model;
	}
	
	/*用户发送其它消息*/
	public Map<String, String> receiveOtherMessage(FullWXRequest wx) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("msgType", "text");
		model.put("${toUserName}", wx.getFromUserName());
		model.put("${fromUserName}", wx.getToUserName());
		model.put("${createTime}", String.valueOf(new Date().getTime()));		
		model.put("${content}", "对不起，我们暂时只能受理文字和语音类留言，敬请谅解！");
		return model;
	}
	
	/*用车提醒*/
	public Map<String, String> clickNoticeMenu(FullWXRequest wx) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("msgType", "news2");
		model.put("${toUserName}", wx.getFromUserName());
		model.put("${fromUserName}", wx.getToUserName());
		model.put("${createTime}", String.valueOf(new Date().getTime()));		
		
		model.put("${title1}", "用车提醒");
		model.put("${description1}", "用车提醒");
		model.put("${picUrl1}", this.configUtils.getDomain()+"/static/wcx/remind.png");
		model.put("${url1}", this.configUtils.getDomain()+"/carManager/setRemind?openid="+wx.getFromUserName()+"&org_code="+wx.getOrgCode());
		model.put("${title2}", "点击设置提醒内容，从此\"忘不了\"");
		model.put("${url2}", this.configUtils.getDomain()+"/carManager/setRemind?openid="+wx.getFromUserName()+"&org_code="+wx.getOrgCode());
		return model;
	}
	
	/*环保达人*/
	public Map<String, String> clickHuanbaoMenu(FullWXRequest wx) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("msgType", "news2");
		model.put("${toUserName}", wx.getFromUserName());
		model.put("${fromUserName}", wx.getToUserName());
		model.put("${createTime}", String.valueOf(new Date().getTime()));		
		
		model.put("${title1}", "环保达人");
		model.put("${description1}", "环保达人");
		model.put("${picUrl1}", this.configUtils.getDomain()+"/static/wcx/huanbao.png");
		model.put("${url1}", this.configUtils.getDomain()+"/carManager/rank?openid="+wx.getFromUserName()+"&org_code="+wx.getOrgCode());
		model.put("${title2}", "点击查看环保排名");
		model.put("${url2}", this.configUtils.getDomain()+"/carManager/rank?openid="+wx.getFromUserName()+"&org_code="+wx.getOrgCode());
		return model;
	}
	/*行车报告*/
	public Map<String, String> clickReportMenu(FullWXRequest wx) {
		Map<String, String> model = new HashMap<String, String>();
		model.put("msgType", "news2");
		model.put("${toUserName}", wx.getFromUserName());
		model.put("${fromUserName}", wx.getToUserName());
		model.put("${createTime}", String.valueOf(new Date().getTime()));		
		
		model.put("${title1}", "行车报告");
		model.put("${description1}", "行车报告");
		model.put("${picUrl1}", this.configUtils.getDomain()+"/static/wcx/report.png");
		model.put("${title2}", "点击查看月度或上周行车报告");
		model.put("${url1}", this.configUtils.getDomain()+"/carManager/carReport?openid="+wx.getFromUserName()+"&org_code="+wx.getOrgCode());
		model.put("${url2}", this.configUtils.getDomain()+"/carManager/carReport?openid="+wx.getFromUserName()+"&org_code="+wx.getOrgCode());
		return model;
	}
		
		/*设置*/
		public Map<String, String> clickSettingsMenu(FullWXRequest wx) {
			
			Map<String, String> model = new HashMap<String, String>();
			model.put("msgType", "news2");
			model.put("${toUserName}", wx.getFromUserName());
			model.put("${fromUserName}", wx.getToUserName());
			model.put("${createTime}", String.valueOf(new Date().getTime()));		
			
			model.put("${title1}", "设置");
			model.put("${description1}","设置");
			model.put("${picUrl1}", this.configUtils.getDomain()+"/static/wcx/settings.png");
			model.put("${url1}", this.configUtils.getDomain()+"/myfs/setting?openid="+wx.getFromUserName()+"&org_code="+wx.getOrgCode());
			model.put("${title2}", "点击进入车辆关联界面，享更优服务");
			model.put("${url2}", this.configUtils.getDomain()+"/myfs/setting?openid="+wx.getFromUserName()+"&org_code="+wx.getOrgCode());
			
			return model;
		}
		
		/*保养预约*/
		public Map<String, String> clickYuyueMenu(FullWXRequest wx) {
			Map<String, String> model = new HashMap<String, String>();
			model.put("msgType", "news2");
			model.put("${toUserName}", wx.getFromUserName());
			model.put("${fromUserName}", wx.getToUserName());
			model.put("${createTime}", String.valueOf(new Date().getTime()));		
			
			model.put("${title1}", "保养预约");
			model.put("${description1}", "保养预约");
			model.put("${picUrl1}", this.configUtils.getDomain()+"/static/wcx/yuyue.png");
			model.put("${url1}", this.configUtils.getDomain()+"/myfs/yuyue?openid="+wx.getFromUserName()+"&org_code="+wx.getOrgCode());
			
			model.put("${title2}", "点击立即进行保养预约，享受尊贵服务");
			model.put("${url2}", this.configUtils.getDomain()+"/myfs/yuyue?openid="+wx.getFromUserName()+"&org_code="+wx.getOrgCode());
			return model;
		}
		
		/*下载APP*/
		public Map<String, String> clickDownloadMenu(FullWXRequest wx) {
			Map<String, String> model = new HashMap<String, String>();
			model.put("msgType", "news3");
			model.put("${toUserName}", wx.getFromUserName());
			model.put("${fromUserName}", wx.getToUserName());
			model.put("${createTime}", String.valueOf(new Date().getTime()));		
			
			model.put("${title1}", "车信APP下载");
			model.put("${description1}", "车信APP下载");
			model.put("${picUrl1}", this.configUtils.getDomain()+"/static/wcx/download.png");
			
			model.put("${title2}", "【android】客户端下载");
			model.put("${picUrl2}", this.configUtils.getDomain()+"/static/wcx/android-big.png");
			model.put("${url2}", this.configUtils.getDomain()+"/app/android");
			model.put("${title3}", "【ios】客户端下载");
			model.put("${picUrl3}", this.configUtils.getDomain()+"/static/wcx/ios.png");
			model.put("${url3}", this.configUtils.getDomain()+"/app/ios");
			return model;
		}
		
		//试乘试驾
		public Map<String,String> clickDriveMenu(FullWXRequest wx){
			Map<String, String> model = new HashMap<String, String>();
			String openid = wx.getFromUserName();
			String orgCode = wx.getOrgCode(); 
			ServiceOrg org=null;
			if(StringUtils.isNotEmpty(orgCode)){
				
				org=this.genericDao.findOne(ServiceOrg.class,new Criteria().eq(ServiceOrgRM.code,orgCode));
			}
			//查询厂牌
//			List<CarBrand> brandList = this.weichexinseviceImpl.getCarBrandByModel(org.getSid().intValue());
			//直接发送group图文消息
				List<CarSerialGroup> groupList = weichexinseviceImpl.getCarSerialGroup(org.getSid().intValue());
				int groupSize = groupList.size(); 
				if(groupList!=null&&groupList.size()>0){
					model.put("msgType", "news"+(groupSize+1));
					model.put("${toUserName}", wx.getFromUserName());
					model.put("${fromUserName}", wx.getToUserName());
					model.put("${createTime}", String.valueOf(new Date().getTime()));
					model.put("${title1}", "试乘试驾");
					model.put("${picUrl1}",  this.configUtils.getDomain()+"/static/wcx/drive.png");
					model.put("${url1}", this.configUtils.getDomain()+"/drive/serial_list?orgId="+org.getSid()+"&groupId="+groupList.get(0).getId()+"&openId="+openid);
					if(groupList.size()<=9){
						for(int i=0;i<groupList.size();i++){
							model.put("${title"+(i+2)+"}", groupList.get(i).getName());
							model.put("${url"+(i+2)+"}", this.configUtils.getDomain()+"/drive/serial_list?orgId="+org.getSid()+"&groupId="+groupList.get(i).getId()+"&openId="+openid);
						}
					}
					if(groupList.size()>=10){
						model.put("msgType", "news10");
						for(int i=0;i<9;i++){
							model.put("${title"+(i+2)+"}", groupList.get(i).getName());
							model.put("${url"+(i+2)+"}", this.configUtils.getDomain()+"/buy/serial_list?orgId="+org.getSid()+"&groupId="+groupList.get(i).getId()+"&openId="+openid);
						}
						model.put("${title10}", "查看更多  >>");
						model.put("${url10}", this.configUtils.getDomain()+"/buy/serial_list?orgId="+org.getSid()+"&groupId="+groupList.get(9).getId()+"&openId="+openid);
					}
				}
				else{
					model.put("msgType", "text");
					model.put("${toUserName}", wx.getFromUserName());
					model.put("${fromUserName}", wx.getToUserName());
					model.put("${createTime}", String.valueOf(new Date().getTime()));		
					model.put("${content}", "暂无试驾车型，敬请关注！");
				}
				
			
				
			return model;
		}
		
		//购车服务
		public Map<String,String> clickBuyMenu(FullWXRequest wx){
			Map<String, String> model = new HashMap<String, String>();
			String openid = wx.getFromUserName();
			String orgCode = wx.getOrgCode(); 
			ServiceOrg org=null;
			if(StringUtils.isNotEmpty(orgCode)){
				org=this.genericDao.findOne(ServiceOrg.class,new Criteria().eq(ServiceOrgRM.code,orgCode));
			}
			//查询厂牌
//					List<CarBrand> brandList = this.weichexinseviceImpl.getCarBrandByModel(org.getSid().intValue());
			//则直接发送group图文消息
			List<CarSerialGroup> groupList = weichexinseviceImpl.getCarSerialGroup(org.getSid().intValue());
				if(groupList!=null&&groupList.size()>0){
					model.put("msgType", "news"+(groupList.size()+1));
					model.put("${toUserName}", wx.getFromUserName());
					model.put("${fromUserName}", wx.getToUserName());
					model.put("${createTime}", String.valueOf(new Date().getTime()));
					model.put("${title1}", "购车服务");
					model.put("${picUrl1}",  this.configUtils.getDomain()+"/static/wcx/buyservice.png");
					model.put("${url1}", this.configUtils.getDomain()+"/buy/serial_list?orgId="+org.getSid()+"&groupId="+groupList.get(0).getId()+"&openId="+openid+"&orgCode="+orgCode);
					if(groupList.size()<=9){
						for(int i=0;i<groupList.size();i++){
							model.put("${title"+(i+2)+"}", groupList.get(i).getName());
							model.put("${url"+(i+2)+"}", this.configUtils.getDomain()+"/buy/serial_list?orgId="+org.getSid()+"&groupId="+groupList.get(i).getId()+"&openId="+openid+"&orgCode="+orgCode);
						}
					}
					if(groupList.size()>=10){
						model.put("msgType", "news10");
						for(int i=0;i<9;i++){
							model.put("${title"+(i+2)+"}", groupList.get(i).getName());
							model.put("${url"+(i+2)+"}", this.configUtils.getDomain()+"/buy/serial_list?orgId="+org.getSid()+"&groupId="+groupList.get(i).getId()+"&openId="+openid+"&orgCode="+orgCode);
						}
						model.put("${title10}", "查看更多  >>");
						model.put("${url10}", this.configUtils.getDomain()+"/buy/serial_list?orgId="+org.getSid()+"&groupId="+groupList.get(9).getId()+"&openId="+openid+"&orgCode="+orgCode);
					}
				}else{
					model.put("msgType", "text");
					model.put("${toUserName}", wx.getFromUserName());
					model.put("${fromUserName}", wx.getToUserName());
					model.put("${createTime}", String.valueOf(new Date().getTime()));		
					model.put("${content}", "暂无车型，敬请关注！");
					}
					
				return model;
			}
}
