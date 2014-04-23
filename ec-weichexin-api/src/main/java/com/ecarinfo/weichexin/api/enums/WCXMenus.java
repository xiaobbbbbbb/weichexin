package com.ecarinfo.weichexin.api.enums;

/**
 * 微车信 click类型的菜单
 * @author zhanglu
 *
 */
public enum WCXMenus {
	//行车助手
	TRAFFIC_MENU,
	LUKUAN_MENU,
	
	//用车管家
	REPORT_MENU,
	NOTICE_MENU,
	HUANBAO_MENU,
	JIANKANG_MENU,
	
	//我的5S
	SETTING_MENU,
	DOWNLOAD_MENU,
	TOUSU_MENU,
	YUYUE_MENU,
	CONTACTUS_MENU;
	;
	
	
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
