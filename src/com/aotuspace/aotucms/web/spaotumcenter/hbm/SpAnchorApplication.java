package com.aotuspace.aotucms.web.spaotumcenter.hbm;

import java.io.Serializable;
import java.util.Date;

import com.aotuspace.aotucms.web.spdictionary.hbm.livestation.SpLivestationInfo;

/**
 * 
 * Title:SpUsersBinfo
 * Description:代言主播申请信息
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-10 下午4:48:54
 *
 */
public class SpAnchorApplication implements Serializable {

	private Integer spId;

	private Integer spAtuid;//凹凸id

	private SpLivestationInfo spLivestationInfo;//直播平台

	private String spLiSrc;//直播链接

	private String spLinickname;//直播昵称

	private String spLiscreensort;//直播截图

	private Date spApplydate;//申请时间

	private SpAnchorApplicationDetail spAnchorApplicationDetail;//申请详细信息

	//审核申请结果（1:审核中，2:通过,3:不通过）
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

	public SpLivestationInfo getSpLivestationInfo() {
		return spLivestationInfo;
	}

	public void setSpLivestationInfo(SpLivestationInfo spLivestationInfo) {
		this.spLivestationInfo = spLivestationInfo;
	}

	public String getSpLiSrc() {
		return spLiSrc;
	}

	public void setSpLiSrc(String spLiSrc) {
		this.spLiSrc = spLiSrc;
	}

	public String getSpLinickname() {
		return spLinickname;
	}

	public void setSpLinickname(String spLinickname) {
		this.spLinickname = spLinickname;
	}

	public String getSpLiscreensort() {
		return spLiscreensort;
	}

	public void setSpLiscreensort(String spLiscreensort) {
		this.spLiscreensort = spLiscreensort;
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
