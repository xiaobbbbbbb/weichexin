package com.ecarinfo.weichexin.rm;
public class ServiceOrgRM {
	public static final String tableName="service_org";//表名
	public static final String pk="sid";//主键
	public static final String sid="sid";//对应数据库主键,ID
	public static final String code="code";//店编码
	public static final String brands="brands";//该机构拥有的厂牌按照逗号分隔
	public static final String name="name";//
	public static final String groupId="group_id";//集团ID
	public static final String groupCode="group_code";//集团编码
	public static final String logo="logo";//LOGO
	public static final String address="address";//
	public static final String tel="tel";//服务电话
	public static final String ctime="ctime";//添加时间
	public static final String mark="mark";//备注
	public static final String startTime="start_time";//服务开始时间
	public static final String endTime="end_time";//服务结束时间
	public static final String isValid="is_valid";//机构是否有效
	public static final String delFlag="delFlag";//是否删除
	public static final String activityPublishMonth="activity_publish_month";//yyyy-mm(活动发布当前月份）
	public static final String activityPublishNum="activity_publish_num";//当前月份活动发布次数
	public static final String activityPublishMaxNum="activity_publish_max_num";//当前月活动发布上限
	public static final String updateTime="update_time";//更新时间
	public static final String telForComplaint="tel_for_complaint";//投诉服务电话
	public static final String telForMaintenance="tel_for_maintenance";//保养服务电话
	public static final String telForSales="tel_for_sales";//销售服务电话
	public static final String renewInsureTel="renew_insure_tel";//保养服务电话
	public static final String usedCarServeTel="used_car_serve_tel";//2手车服务电话
	public static final String yearCheckServeTel="year_check_serve_tel";//年检服务电话
	public static final String helpTel="help_tel";//救援热线
	public static final String reserveAvailableDays="reserve_available_days";//预约可提前天数
	public static final String reserveDiscountNote="reserve_discount_note";//预约折扣备注
}
