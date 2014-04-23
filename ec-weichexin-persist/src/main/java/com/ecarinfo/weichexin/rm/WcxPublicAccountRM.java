package com.ecarinfo.weichexin.rm;
public class WcxPublicAccountRM {
	public static final String tableName="wcx_public_account";//表名
	public static final String pk="id";//主键
	public static final String id="id";//对应数据库主键,ID
	public static final String cuserId="cuser_id";//
	public static final String name="name";//
	public static final String loginName="login_name";//
	public static final String url="url";//
	public static final String token="token";//
	public static final String appKey="app_key";//
	public static final String appSecret="app_secret";//
	public static final String wcxMenuIds="wcx_menu_ids";//
	public static final String ctime="ctime";//
	public static final String status="status";//状态0，初始态，1：已验证url，2，已输入appkey、appSecret，3，已创建菜单
	public static final String orgCode="org_code";//
	public static final String orgId="org_id";//
}
