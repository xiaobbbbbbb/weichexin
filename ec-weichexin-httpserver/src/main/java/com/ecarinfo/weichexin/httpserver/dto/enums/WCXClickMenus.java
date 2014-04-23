package com.ecarinfo.weichexin.httpserver.dto.enums;

/**
 * 微车信 click类型的菜单
 *
 */
public enum WCXClickMenus {
	//行车助手
	TRAFFIC_MENU,
	TIP_MENU,
	//我的5S
	TOUSU_MENU,
	//用车提醒
	NOTICE_MENU,
	//环保达人
	HUANBAO_MENU,
	//行车报告
	REPORT_MENU,
	//设置
	SETTINGS_MENU,
	//保养预约
	BAOYANG_MENU,
	//联系我们
	CONTACTUS_MENU,
	//下载APP
	DOWNLOAD_MENU,
	//贴士
	CARTIP_MENU,
	//活动
	ACTIVITY_MENU,
	//购车服务
	BUYCAR_MENU,
	//试乘试驾
	TESTDRIVE_MENU
	;
	
	public static WCXClickMenus getFromMenuKey(String key) {
		return WCXClickMenus.valueOf(key.toUpperCase());
	}
	/**
	 * 	$menu_first['name'] = '行车助手';
		$menu_first['sub_button'] = array(
				array('name'=>'违章查询','type'=>'click','key'=>'traffic_menu'),
				array('name'=>'车学社','type'=>'view','url'=>'http://dev.weixin.otra.cn/tips_list.php?org_code=888888'),
				array('name'=>'活动优惠','type'=>'view','url'=>'http://dev.weixin.otra.cn/activity_list.php?org_code=888888'),
				// array('name'=>'附近路况','type'=>'click','key'=>'lukuan_menu'),
			);
	
		$menus[]=$menu_first;
	
		$menu_second = array();
		$menu_second['name'] = '用车管家';
		$menu_second['sub_button'] = array(
				array('name'=>'行车报告','type'=>'click','key'=>'report_menu'),
				array('name'=>'用车提醒','type'=>'click','key'=>'notice_menu'),
				array('name'=>'环保达人','type'=>'click','key'=>'huanbao_menu'),
				// array('name'=>'健康度','type'=>'click','key'=>'jiankang_menu'),
			);
		$menus[] = $menu_second;
	
		$menu_third = array();
		$menu_third['name'] = '我的5S';
		$menu_third['sub_button'] = array(
				array('name'=>'设置','type'=>'click','key'=>'settings_menu'),
				array('name'=>'下载App','type'=>'click','key'=>'menu_download_menu'),
				array('name'=>'客户投诉','type'=>'click','key'=>'tousu_menu'),
				array('name'=>'保养预约','type'=>'click','key'=>'yuyue_menu'),
				array('name'=>'联系我们','type'=>'click','key'=>'contactus_menu'),		
			);
	 */
}
