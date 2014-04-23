package com.ecarinfo.weichexin.rm;
public class FivesaasCarInfoTempRM {
	public static final String tableName="fivesaas_car_info_temp";//表名
	public static final String pk="car_id";//主键
	public static final String carId="car_id";//对应数据库主键,车辆ID
	public static final String carNo="car_no";//车牌号
	public static final String modelId="model_id";//车型ID
	public static final String toMTime="to_m_time";//下次维护时间
	public static final String toMMileage="to_m_mileage";//下次维护里程
	public static final String currentMileage="current_mileage";//行车里程
	public static final String dayMileage="day_mileage";//日均里程
	public static final String avgSpeed="avg_speed";//平均车速
	public static final String avgOilOneHundred="avg_oil_one_hundred";//百公里油耗
	public static final String fairNum="fair_num";//故障次数
	public static final String serialId="serial_id";//车系ID
	public static final String brandId="brand_id";//车牌ID
	public static final String deviceNo="device_no";//车猫编号
	public static final String deviceVersion="device_version";//车猫版本
	public static final String email="email";//邮箱
	public static final String ctime="ctime";//注册日期
	public static final String faultCode="fault_code";//最近一次错误码
	public static final String faultTime="fault_time";//最后一次故障时间
	public static final String orgId="org_id";//4s店
	public static final String orgCode="org_code";//4s店编码
	public static final String bindCount="bind_count";//绑定后判断是否是登陆，还是修改
	public static final String oldOrgCode="old_org_code";//解绑状态，解绑的旧的机构编码
	public static final String groupId="group_id";//集团ID
	public static final String groupCode="group_code";//集团编码
	public static final String maintenanceNoticeTime="maintenance_notice_time";//保养提醒时间
	public static final String lastActivityId="last_activity_id";//最近活动ID(过期用的）
	public static final String lastActivityTime="last_activity_time";//最近活动时间
	public static final String lastTipId="last_tip_id";//最近贴士ID
	public static final String lastTipTime="last_tip_time";//最近贴士时间
	public static final String faultFollowActivityId="fault_follow_activity_id";//最近跟进该车辆的活动ID
	public static final String faultFollowActivityTime="fault_follow_activity_time";//最近关怀故障车辆的时间
	public static final String maintenanceFollowActivityId="maintenance_follow_activity_id";//保养跟进活动ID
	public static final String maintenanceFollowActivityTime="maintenance_follow_activity_time";//保养跟进活动时间
}
