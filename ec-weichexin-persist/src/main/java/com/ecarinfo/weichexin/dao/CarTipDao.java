package com.ecarinfo.weichexin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ecarinfo.persist.exdao.ECDao;
import com.ecarinfo.weichexin.po.CarTip;

public interface CarTipDao extends ECDao<CarTip> {
	public abstract List<CarTip> findTipsListEx(@Param("model_id") Integer paramString1, @Param("serial_id") Integer paramString2, @Param("brand_id") Integer paramString3, @Param("org_code") String paramLong2, @Param("current_mileage") Float paramInteger2, @Param("limit") Integer paramInteger3, @Param("image_exists") Boolean paramBoolean);
}
