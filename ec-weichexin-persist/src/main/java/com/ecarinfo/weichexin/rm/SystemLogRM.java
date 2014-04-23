package com.ecarinfo.weichexin.rm;
public class SystemLogRM {
	public static final String tableName="system_log";//表名
	public static final String pk="id";//主键
	public static final String id="id";//对应数据库主键,主键id
	public static final String actionTime="action_time";//操作的时间
	public static final String action="action";// 操作内容
	public static final String type="type";//操作类型(1登陆、2退出、3添加、4修改、5删除)
	public static final String ip="ip";//操作时间
	public static final String userId="user_id";//操作用户id
	public static final String userName="user_name";//操作用户名
}
