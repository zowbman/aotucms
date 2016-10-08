package com.aotuspace.aotucms.web.spaotumcenter.hbm;

import java.io.Serializable;

/**
 * 
 * Title:SpUsersBinfo
 * Description:��������������Ϣ
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-10 ����4:48:54
 *
 */
public class SpAnchorBinfo implements Serializable {

	private Integer spId;

	private Integer spAtuid;//��͹id

	private String spResume;//���˼��

	private SpAnchorDetail spAnchorDetail;//��ϸ��Ϣ

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
