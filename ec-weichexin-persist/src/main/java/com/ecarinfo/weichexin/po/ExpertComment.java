package com.ecarinfo.weichexin.po;
import java.io.Serializable;
import java.util.Date;

public class ExpertComment implements Serializable {

	private static final long serialVersionUID = -2260388125919493487L;
	private Integer id;//ID
	private Integer expertId;//专家ID
	private String content;//内容
	private Integer score = 0;//评分
	private Date ctime;//创建时间
	private Long carId;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public Integer getExpertId () {
        return expertId;
    }

    public void setExpertId (Integer expertId) {
        this.expertId = expertId;
    }

    public String getContent () {
        return content;
    }

    public void setContent (String content) {
        this.content = content;
    }

    public Integer getScore () {
        return score;
    }

    public void setScore (Integer score) {
        this.score = score;
    }

    public Date getCtime () {
        return ctime;
    }

    public void setCtime (Date ctime) {
        this.ctime = ctime;
    }

    public Long getCarId () {
        return carId;
    }

    public void setCarId (Long carId) {
        this.carId = carId;
    }
}