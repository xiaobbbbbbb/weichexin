package com.ecarinfo.weichexin.rm;
public class RalUserRM {
	public static final String tableName="ral_user";//表名
	public static final String pk="user_id";//主键
	public static final String userId="user_id";//对应数据库主键,用户id
	public static final String loginName="loginName";//登录名
	public static final String password="password";//密码
	public static final String name="name";//姓名
	public static final String phone="phone";//电话
	public static final String email="email";//邮件
	public static final String orgId="org_id";//组织id
	public static final String depId="dep_id";//部门id
	public static final String isAway="isAway";//账号是否启用，默认为0启用
	public static final String isManager="isManager";//是否是管理员
	public static final String createDate="createDate";//创建日期
	public static final String level="level";//级别(排序)
	public static final String message="message";//备注
}
