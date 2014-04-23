package com.ecarinfo.weichexin.rm;
public class CarTipRM {
	public static final String tableName="car_tip";//表名
	public static final String pk="id";//主键
	public static final String id="id";//对应数据库主键,ID
	public static final String title="title";//
	public static final String content="content";//
	public static final String categoryId="category_id";//类别ID
	public static final String image="image";//广告图片
	public static final String orgId="org_id";//4s店ID
	public static final String orgCode="org_code";//
	public static final String groupId="group_id";//4s集团ID
	public static final String ctime="ctime";//添加时间
	public static final String status="status";//审核状态
	public static final String cuserId="cuser_id";//创建用户ID
	public static final String auserId="auser_id";//审核userID
	public static final String checktime="checktime";//审核时间
	public static final String checkReason="check_reason";// 不通过审核原因
	public static final String clickNum="click_num";//
	public static final String pushNum="push_num";//
	public static final String auserName="auser_name";//
	public static final String cuserName="cuser_name";//创建者登录名
	public static final String publishCriterias="publish_criterias";//发布条件
	public static final String discardUserId="discard_user_id";//作废用户id
	public static final String discardTime="discard_time";//作废时间
	public static final String wcxClickNums="wcx_click_nums";//
	public static final String publishChannel="publish_channel";//全部/ APP及微车信/APP/微车信
}
