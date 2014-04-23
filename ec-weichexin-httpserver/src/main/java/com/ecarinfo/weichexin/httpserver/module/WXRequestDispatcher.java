package com.ecarinfo.weichexin.httpserver.module;

import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ecarinfo.frame.httpserver.core.annotation.Customized;
import com.ecarinfo.frame.httpserver.core.annotation.MessageModule;
import com.ecarinfo.frame.httpserver.core.annotation.RequestURI;
import com.ecarinfo.frame.httpserver.core.http.bean.ParamValue;
import com.ecarinfo.frame.httpserver.core.http.util.HttpParamsUtils;
import com.ecarinfo.frame.httpserver.core.type.RequestMethod;
import com.ecarinfo.persist.service.GenericService;
import com.ecarinfo.weichexin.httpserver.dto.enums.WCXClickMenus;
import com.ecarinfo.weichexin.httpserver.dto.request.FullWXRequest;
import com.ecarinfo.weichexin.httpserver.dto.response.WXResponseTemplates;
import com.ecarinfo.weichexin.httpserver.service.WXRequestProcessor;
import com.ecarinfo.weichexin.po.WcxPublicAccount;
import com.ecarinfo.weichexin.rm.WcxPublicAccountRM;

@Component
@MessageModule("/")
public class WXRequestDispatcher {
	private static Logger logger = Logger.getLogger(WXRequestDispatcher.class);
	@Resource
	protected WXRequestProcessor wxrequestRequestProcessor;
	@Resource
	protected GenericService baseService;
	@Customized
	@RequestURI(value = "/index.php", method = RequestMethod.POST)
	public void dispatcher(HttpRequest request, HttpResponse response) {
		FullWXRequest wxrequest = getFullWXRequest(request);
		WcxPublicAccount wcxAccount = baseService.findOneByAttr(WcxPublicAccount.class, WcxPublicAccountRM.orgCode, wxrequest.getOrgCode());		
		if (wcxAccount==null) {
			throw new IllegalArgumentException("the WcxPublicAccount not exists for org_code:" + wxrequest.getOrgCode());
		}
		if (wcxAccount.getToken()==null) {
			throw new IllegalArgumentException("the token not exists for org_code:" + wxrequest.getOrgCode());
		}
		
		if (StringUtils.hasText(wxrequest.getEchostr()) && wcxAccount.getStatus()==0) {//&& isFromWXPlatform(wxrequest, wcxAccount.getToken())
			//token还未验证,则确认验证
			wcxAccount.setStatus(1);
			baseService.update(wcxAccount); //状态设为已验证
			sayYesToWeixin(wxrequest , response);
			return;
		} 
						
		logger.info("[RECV_MSG]:" + wxrequest);
		Map<String, String> model = null;
		if (wxrequest.getEvent()!=null) {
			if (wxrequest.getEvent().equals("CLICK") && wxrequest.getEventKey()!=null) { //菜单点击消息				
				switch (WCXClickMenus.getFromMenuKey(wxrequest.getEventKey())) {		
				//违章查询
				case TRAFFIC_MENU:
					model = wxrequestRequestProcessor.clickTrafficMenu(wxrequest);
					break;		
				case TIP_MENU:
					model = wxrequestRequestProcessor.clickTipMenu(wxrequest);
					break;
				case ACTIVITY_MENU:
					model = wxrequestRequestProcessor.clickActivityMenu(wxrequest);
					break;
				case TOUSU_MENU:	
					model = wxrequestRequestProcessor.clickTousuMenu(wxrequest);
					break;
				case NOTICE_MENU:
					model = wxrequestRequestProcessor.clickNoticeMenu(wxrequest);
					break;
				case HUANBAO_MENU:
					model = wxrequestRequestProcessor.clickHuanbaoMenu(wxrequest);
					break;
				//行车报告
				case REPORT_MENU:
					model = wxrequestRequestProcessor.clickReportMenu(wxrequest);
					break;
				//设置
				case SETTINGS_MENU:
					model = wxrequestRequestProcessor.clickSettingsMenu(wxrequest);
					break;
				//保养预约
				case BAOYANG_MENU:
					model = wxrequestRequestProcessor.clickYuyueMenu(wxrequest);
					break;
				case CONTACTUS_MENU://联系我们
					model = wxrequestRequestProcessor.clickContactMenu(wxrequest);
					break;
				case DOWNLOAD_MENU:
					model = wxrequestRequestProcessor.clickDownloadMenu(wxrequest);
					break;
				case CARTIP_MENU:
					model = wxrequestRequestProcessor.clickTipMenu(wxrequest);
					break;
				//购车服务
				case BUYCAR_MENU:
					model = wxrequestRequestProcessor.clickBuyMenu(wxrequest);
					break;
				//试乘试驾
				case TESTDRIVE_MENU:
					model = wxrequestRequestProcessor.clickDriveMenu(wxrequest);
				default:
					break;
				}	
			} else if (wxrequest.getEvent().equals("subscribe")) {
				model = wxrequestRequestProcessor.subscribeWCX(wxrequest);
			} else if (wxrequest.getEvent().equals("unsubscribe")) {
				model = wxrequestRequestProcessor.unsubscribeWCX(wxrequest);
			}
		} else if(wxrequest.getMsgType()!=null&&wxrequest.getMsgType().equals("text")||wxrequest.getMsgType().equals("voice")){//语音文本消息
			model = wxrequestRequestProcessor.receiveUserMessage(wxrequest);
		}
		else if (wxrequest.getRecognition()!=null){ //语音识别结果消息
			model = wxrequestRequestProcessor.receiveUserMessage(wxrequest);
		} else { //其它消息
			model = wxrequestRequestProcessor.receiveUserMessage(wxrequest);
		}	
		
		if (model!=null ) {
			String xml = WXResponseTemplates.parseTemplate(model.get("msgType"), model);
			logger.info("---------[XML]:" + xml);
			response.setContent(ChannelBuffers.copiedBuffer(xml, CharsetUtil.UTF_8));
			response.setHeader(CONTENT_TYPE, "text/plain; charset=UTF-8");
		}
	}
	
	private FullWXRequest getFullWXRequest(HttpRequest request){
		Map<String, ParamValue> headerParams = HttpParamsUtils.getGetParamsMap(request);
		Map<String, String> params= readWXRequestContents(request);
		FullWXRequest r = new FullWXRequest();
		if (headerParams.containsKey("org_code")) {
			r.setOrgCode(headerParams.get("org_code").getVal());
		} 
		if (headerParams.containsKey("signature")) {
			r.setSignature(headerParams.get("signature").getVal());
		}  
		if (headerParams.containsKey("timestamp")) {
			r.setTimestamp(headerParams.get("timestamp").getVal());
		} 
		if (headerParams.containsKey("nonce")) {
			r.setNonce(headerParams.get("nonce").getVal());
		} 
		if (headerParams.containsKey("echostr")) {
			r.setEchostr(headerParams.get("echostr").getVal());
		} 
		try {
			for (Entry<String, String> e : params.entrySet()) {
				BeanUtils.setProperty(r, StringUtils.uncapitalize(e.getKey()), e.getValue());
			}
		} catch (Exception e) {
			logger.error("error.", e);
		}	
		return r;
	}
	
	public void sayYesToWeixin(FullWXRequest r, HttpResponse response) {
		response.setContent(ChannelBuffers.copiedBuffer(r.getEchostr(), CharsetUtil.UTF_8));
		response.setHeader(CONTENT_TYPE, "text/plain; charset=UTF-8");
	}
	
	private boolean isFromWXPlatform(FullWXRequest r, String token) {
		String[] params = new String[]{token, r.getTimestamp(), r.getNonce()};
		Arrays.sort(params);
		if (r.getSignature().equals(DigestUtils.shaHex(params[0]+params[1]+params[2]))) {
			return true;
		}
		return false;		
	}
	
	private Map<String, String> readWXRequestContents(HttpRequest request) {
		Map<String, String> kvs = new HashMap<String, String>();
		SAXReader saxReader = new SAXReader();
		String postData = null;
		try {
			postData = HttpParamsUtils.getPostData(request);
			if (StringUtils.hasText(postData)) {
				logger.info("[WXREQUEST_DATA]:" + postData);
				saxReader.setEncoding("UTF-8");
				Document document = saxReader.read(new ByteArrayInputStream(postData.getBytes(Charset.forName("UTF-8"))));
				Element root = document.getRootElement();
				
				for (Object o : root.elements()) {
					Element e = (Element)o;
					String name = e.getName();
					String value = e.getTextTrim();
					kvs.put(name, value);		
				}
			}			
		} catch (DocumentException e) {
			logger.error("readWXRequestContents ERROR FOR POST_DATA:" + postData,e);
		}
		return kvs;
	}
	
}
