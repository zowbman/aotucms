package com.aotuspace.aotucms.web.spaotumcenter.hbm;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Title:SpUsersBinfo
 * Description:������������������Ϣ
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-10 ����4:48:54
 *
 */
public class SpArtistApplication implements Serializable {

	private Integer spId;

	private Integer spAtuid;//��͹id

	private String spArname;//����

	private String spArcontent;//���ݸ���

	private String spArscreensort;//�ݳ���ͼ

	private Date spApplydate;//����ʱ��

	private SpAnchorApplicationDetail spAnchorApplicationDetail;//������ϸ��Ϣ

	private SpApplicationResult spApplicationResult;//������

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
