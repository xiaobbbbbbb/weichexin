package com.ecarinfo.weichexin.rm;
public class BackgroundUserRM {
	public static final String tableName="background_user";//表名
	public static final String pk="id";//主键
	public static final String id="id";//对应数据库主键,ID
	public static final String loginName="login_name";//登陆名称
	public static final String password="password";//登陆密码
	public static final String name="name";//姓名
	public static final String email="email";//emial
	public static final String roleId="role_id";//角色，预留的
	public static final String addTime="add_time";//添加时间
	public static final String orgId="org_id";//4s店id
	public static final String orgCode="org_code";//4s店编码
	public static final String groupId="group_id";//集团ID
	public static final String groupCode="group_code";//集团编码
	public static final String isAway="isAway";//是否启用，0为启用
	public static final String delFlag="delFlag";//是否删除，删除为1
	public static final String mark="mark";//
	public static final String isReserveHandler="is_reserve_handler";//是否预约处理人员
}
