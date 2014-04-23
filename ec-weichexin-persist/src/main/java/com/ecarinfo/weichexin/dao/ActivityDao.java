package com.ecarinfo.weichexin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ecarinfo.persist.exdao.ECDao;
import com.ecarinfo.weichexin.po.Activity;

public interface ActivityDao extends ECDao<Activity> {
	public abstract List<Activity> findActivitysListEx(@Param("model_id") Integer paramString1, @Param("serial_id") Integer paramString2, @Param("brand_id") Integer paramString3, @Param("org_code") String paramLong2, @Param("current_mileage") Float paramInteger2, @Param("limit") Integer paramInteger3, @Param("image_exists") Boolean paramBoolean, @Param("today") String paramString4,@Param("category_id") Integer paramString5, @Param("has_image") Boolean has_image);

}
