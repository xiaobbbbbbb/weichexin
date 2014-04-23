package com.ecarinfo.weichexin.rm;
public class LogActionRM {
	public static final String tableName="log_action";//表名
	public static final String pk="id";//主键
	public static final String id="id";//对应数据库主键,
	public static final String sid="sid";//点击那个主题的id
	public static final String type="type";//1:活动优惠;2:贴士;3:故障求助;4:保养预约,5登陆记录
	public static final String ctime="ctime";//创建时间
	public static final String loginTime="login_time";//登录时间
	public static final String outTime="out_time";//退出时间
	public static final String userId="user_id";//用户id
	public static final String ip="ip";//操作ip
	public static final String groupId="group_id";//集团id
	public static final String groupCode="group_code";//集团编码
	public static final String orgId="org_id";//4s店id
	public static final String orgCode="org_code";//4s店编码
}
