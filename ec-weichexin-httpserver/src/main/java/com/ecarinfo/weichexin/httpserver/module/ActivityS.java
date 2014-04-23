package com.ecarinfo.weichexin.httpserver.module;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.common.utils.ReportMaker;
import com.ecarinfo.common.utils.DateUtils.TimeFormatter;
import com.ecarinfo.frame.httpserver.core.annotation.MessageModule;
import com.ecarinfo.frame.httpserver.core.annotation.RequestURI;
import com.ecarinfo.frame.httpserver.core.type.RequestMethod;
import com.ecarinfo.persist.criteria.Criteria;
import com.ecarinfo.persist.criteria.Criteria.CondtionSeparator;
import com.ecarinfo.persist.exdao.GenericDao;
import com.ecarinfo.persist.service.GenericService;
import com.ecarinfo.weichexin.httpserver.service.WeichexinseviceImpl;
import com.ecarinfo.weichexin.httpserver.utils.ConfigUtils;
import com.ecarinfo.weichexin.httpserver.vo.ActivityHistoryVO;
import com.ecarinfo.weichexin.httpserver.vo.HistoryVO;
import com.ecarinfo.weichexin.po.Activity;
import com.ecarinfo.weichexin.po.ActivityAppoint;
import com.ecarinfo.weichexin.po.CarModel;
import com.ecarinfo.weichexin.po.ServiceOrg;
import com.ecarinfo.weichexin.po.TestdriveAppoint;
import com.ecarinfo.weichexin.po.WcxUserInfo;
import com.ecarinfo.weichexin.rm.ActivityAppointRM;
import com.ecarinfo.weichexin.rm.ServiceOrgRM;
import com.ecarinfo.weichexin.rm.TestdriveAppointRM;

import freemarker.template.utility.HtmlEscape;

/**
 * 优惠活动
 */
@Component
@MessageModule("/activity")
public class ActivityS {

	private static final Logger logger = Logger.getLogger(ActivityS.class);

	@Resource
	private WeichexinseviceImpl weichexinseviceImpl;

	@Resource
	ConfigUtils configUtils;

	@Resource
	GenericService genericService;

	// 优惠活动列表
	@RequestURI(value = "/list", method = RequestMethod.GET)
	public String list(String openid, String org_code) {
		String html = null;
		logger.info("org_code===============" + org_code);
		try {
			String ctx = configUtils.getImg_url();
			Map<String, Object> configs = new HashMap<String, Object>();
			List<Activity> dtos = weichexinseviceImpl.activityModel(openid,
					org_code, null, null,false);
			if(dtos!=null && dtos.size()>0){
				for (Activity activity : dtos) {
					activity.setContent(getText(activity.getContent()));
				}
			}
			configs.put("dtos", dtos);
			configs.put("ctx", ctx);
			configs.put("openid", openid);
			configs.put("orgCode", org_code);
			html = ReportMaker.exeute4Content(configs,
					"assistant-activity-list.ftl");
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}

	/**
	 * 去掉HTML元素
	 * @param htmlStr
	 * @return
	 */
	public static String getText(String htmlStr) {
		if(htmlStr==null || "".equals(htmlStr)) return "";
		     String textStr ="";    
		     java.util.regex.Pattern pattern;    
		     java.util.regex.Matcher matcher;    
		     try {
			      String regEx_remark = "<!--.+?-->";
			      String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }    
			      String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }    
		          String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式    
		          String regEx_html1 = "<[^>]+";
		          htmlStr = htmlStr.replaceAll("\n","");
		          htmlStr = htmlStr.replaceAll("\t","");
		          pattern=Pattern.compile(regEx_remark);//过滤注释标签
		          matcher=pattern.matcher(htmlStr);
		          htmlStr=matcher.replaceAll("")    ;
			      pattern = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);    
			      matcher = pattern.matcher(htmlStr);    
		          htmlStr = matcher.replaceAll(""); //过滤script标签    
		          pattern = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);    
		          matcher = pattern.matcher(htmlStr);    
		          htmlStr = matcher.replaceAll(""); //过滤style标签    
		          pattern = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);    
		          matcher = pattern.matcher(htmlStr);    
		          htmlStr = matcher.replaceAll(""); //过滤html标签    
		          pattern = Pattern.compile(regEx_html1,Pattern.CASE_INSENSITIVE);    
		          matcher = pattern.matcher(htmlStr);    
		          htmlStr = matcher.replaceAll(""); //过滤html标签    
		          textStr = htmlStr.trim();    
			   }catch(Exception e) {
				   System.out.println("获取HTML中的text出错:");
				   e.printStackTrace();
			   }    
		  return textStr;//返回文本字符串
	}
	
	// 优惠活动概要
	@RequestURI(value = "/brief", method = RequestMethod.GET)
	public String brief(Integer id, String openid, String orgCode) {
		String html = null;

		Activity activity = this.genericService.findByPK(Activity.class, id);
		activity.setWcxClickNums(activity.getWcxClickNums() + 1);
		this.genericService.update(activity);
		logger.info("===============" + id);
		try {
			Map<String, Object> configs = new HashMap<String, Object>();
			ActivityAppoint activityAppoint =this.genericService.findOne(ActivityAppoint.class, new Criteria().eq(ActivityAppointRM.openId, openid).eq(ActivityAppointRM.activityId, id, CondtionSeparator.AND).notEquals(ActivityAppointRM.status, 2, CondtionSeparator.AND).notEquals(ActivityAppointRM.status, 3, CondtionSeparator.AND));
			if(activityAppoint!=null){
				configs.put("activityAppoint", activityAppoint);
			}
			String ctx = configUtils.getImg_url();
			configs.put("activity", activity);
			configs.put("ctx", ctx);
			configs.put("openid", openid);
			configs.put("orgCode", orgCode);
			html = ReportMaker.exeute4Content(configs,
					"assistant-activity-brief.ftl");
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}

	// 优惠活动详情
	@RequestURI(value = "/detail", method = RequestMethod.GET)
	public String detail(Integer id, String openid, String orgCode) {
		String html = null;
		Activity activity = this.genericService.findByPK(Activity.class, id);
		
		activity.setWcxClickNums(activity.getWcxClickNums() + 1);
		this.genericService.update(activity);
		logger.info("org_code===============" + orgCode);
		try {
			Map<String, Object> configs = new HashMap<String, Object>();
			ActivityAppoint activityAppoint =this.genericService.findOne(ActivityAppoint.class, new Criteria().eq(ActivityAppointRM.openId, openid).eq(ActivityAppointRM.activityId, id, CondtionSeparator.AND).notEquals(ActivityAppointRM.status, 2, CondtionSeparator.AND).notEquals(ActivityAppointRM.status, 3, CondtionSeparator.AND));
			if(activityAppoint!=null){
				configs.put("activityAppoint", activityAppoint);
			}
			String ctx = configUtils.getImg_url();
			configs.put("activity", activity);
			configs.put("ctx", ctx);
			configs.put("orgCode", orgCode);
			configs.put("openid", openid);
			html = ReportMaker.exeute4Content(configs,
					"assistant-activity-detail.ftl");
		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}

	// 活动预约
	@RequestURI(value = "/yuyue", method = RequestMethod.GET)
	public String yuyue(Integer id, String openid, String orgCode) {
		String html = null;
		try {

			Map<String, Object> configs = new HashMap<String, Object>();
			Activity activity = this.genericService.findByPK(Activity.class, id);
			configs.put("activity", activity);
			configs.put("openid", openid);
			configs.put("orgCode", orgCode);
			html = ReportMaker.exeute4Content(configs,
					"assistant-activity-add.ftl");// 未预约

		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}

	// 预约 添加
	@RequestURI(value = "/add", method = RequestMethod.POST)
	public String add(String orgCode, String openid, Integer activityId,
			String telNo, String name, String comment) {
		String html = "true";
		ServiceOrg org = this.genericService.findOne(ServiceOrg.class,
				new Criteria().eq(ServiceOrgRM.code, orgCode));
		ActivityAppoint oppoint = new ActivityAppoint();
		oppoint.setActivityId((long) activityId);
		oppoint.setName(name);
		oppoint.setOpenId(openid);
		oppoint.setComment(comment);
		oppoint.setStatus(0);
		oppoint.setTelNo(telNo);
		oppoint.setRequestTime(new Date());
		oppoint.setOrgId(org.getSid());
		this.genericService.save(oppoint);
		return html;
	}

	// 预约历史
	@RequestURI(value = "/history", method = RequestMethod.GET)
	public String history(String  orgCode, String openid) {
		String html = null;
		try {
			ServiceOrg org = this.genericService.findOne(ServiceOrg.class,
					new Criteria().eq(ServiceOrgRM.code, orgCode));
			Map<String, Object> configs = new HashMap<String, Object>();
			List<ActivityHistoryVO> list = this.weichexinseviceImpl.getActivityHistoryList(openid, org.getSid().intValue());
			configs.put("list", list);
			configs.put("openid", openid);
			configs.put("orgCode", orgCode);
			String ctx = configUtils.getImg_url();
			configs.put("ctx", ctx);
			html = ReportMaker.exeute4Content(configs,
					"assistant-activity-history.ftl");

		} catch (Exception e) {
			logger.error("解析模板文件异常!", e);
		}
		return html;
	}

	// 取消预约
	@RequestURI(value = "/cancel", method = RequestMethod.POST)
	public String cancel(Integer id) {
		String html = "false";
		try {
			this.genericService.updateWithCriteria(
					ActivityAppoint.class,
					new Criteria().update(ActivityAppointRM.status, 2).eq(
							ActivityAppointRM.pk, id));
			html = "true";
			return html;
		} catch (Exception e) {
			logger.error("取消失败!", e);
			html = "false";
		}
		return html;
	}

}
