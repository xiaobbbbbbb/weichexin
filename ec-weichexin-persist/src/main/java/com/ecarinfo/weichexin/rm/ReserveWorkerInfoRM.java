package com.ecarinfo.weichexin.rm;
public class ReserveWorkerInfoRM {
	public static final String tableName="reserve_worker_info";//表名
	public static final String pk="id";//主键
	public static final String id="id";//对应数据库主键,ID
	public static final String wcxUserId="wcx_user_id";//
	public static final String workerId="worker_id";//
	public static final String ctime="ctime";//
	public static final String timePoint="time_point";//
	public static final String carNo="car_no";//
	public static final String contactTel="contact_tel";//
	public static final String discountNote="discount_note";//
	public static final String practicalWorkerId="practical_worker_id";//
	public static final String practicalTimePoint="practical_time_point";//
	public static final String practicalCarNo="practical_car_no";//
	public static final String status="status";//状态0，未处理，1,处理成功，2,处理失败,3已作废
	public static final String handleUserId="handle_user_id";//
	public static final String handleTime="handle_time";//
	public static final String reserveDate="reserve_date";//预约哪一天
	public static final String orgContactTel="org_contact_tel";//
	public static final String orgCode="org_code";//
	public static final String orgId="org_id";//
}
