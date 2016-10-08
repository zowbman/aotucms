package com.aotuspace.aotucms.web.spaotumcenter.hbm;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Title:SpUsersBinfo
 * Description:其他代言主播申请信息
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-10 下午4:48:54
 *
 */
public class SpArtistApplication implements Serializable {

	private Integer spId;

	private Integer spAtuid;//凹凸id

	private String spArname;//艺名

	private String spArcontent;//内容概述

	private String spArscreensort;//演出截图

	private Date spApplydate;//申请时间

	private SpAnchorApplicationDetail spAnchorApplicationDetail;//申请详细信息

	private SpApplicationResult spApplicationResult;//申请结果

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

	public String getSpArname() {
		return spArname;
	}

	public void setSpArname(String spArname) {
		this.spArname = spArname;
	}

	public String getSpArcontent() {
		return spArcontent;
	}

	public void setSpArcontent(String spArcontent) {
		this.spArcontent = spArcontent;
	}

	public String getSpArscreensort() {
		return spArscreensort;
	}

	public void setSpArscreensort(String spArscreensort) {
		this.spArscreensort = spArscreensort;
	}

	public Date getSpApplydate() {
		return spApplydate;
	}

	public void setSpApplydate(Date spApplydate) {
		this.spApplydate = spApplydate;
	}

	public SpApplicationResult getSpApplicationResult() {
		return spApplicationResult;
	}

	public void setSpApplicationResult(SpApplicationResult spApplicationResult) {
		this.spApplicationResult = spApplicationResult;
	}

	public SpAnchorApplicationDetail getSpAnchorApplicationDetail() {
		return spAnchorApplicationDetail;
	}

	public void setSpAnchorApplicationDetail(SpAnchorApplicationDetail spAnchorApplicationDetail) {
		this.spAnchorApplicationDetail = spAnchorApplicationDetail;
	}

}
