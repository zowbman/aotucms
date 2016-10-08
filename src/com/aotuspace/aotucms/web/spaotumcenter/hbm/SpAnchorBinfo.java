package com.aotuspace.aotucms.web.spaotumcenter.hbm;

import java.io.Serializable;

/**
 * 
 * Title:SpUsersBinfo
 * Description:代言主播基本信息
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-10 下午4:48:54
 *
 */
public class SpAnchorBinfo implements Serializable {

	private Integer spId;

	private Integer spAtuid;//凹凸id

	private String spResume;//个人简介

	private SpAnchorDetail spAnchorDetail;//详细信息

	public SpAnchorBinfo() {
	}

	public SpAnchorBinfo(String spResume, Integer spAtuid, SpAnchorDetail spAnchorDetail) {
		this.spAtuid = spAtuid;
		this.spResume = spResume;
		this.spAnchorDetail = spAnchorDetail;
	}

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Integer getSpAtuid() {
		return spAtuid;
	}

	public void setSpAtuid(Integer spAtuid) {
		this.spAtuid = spAtuid;
	}

	public String getSpResume() {
		return spResume;
	}

	public void setSpResume(String spResume) {
		this.spResume = spResume;
	}

	public SpAnchorDetail getSpAnchorDetail() {
		return spAnchorDetail;
	}

	public void setSpAnchorDetail(SpAnchorDetail spAnchorDetail) {
		this.spAnchorDetail = spAnchorDetail;
	}

}
