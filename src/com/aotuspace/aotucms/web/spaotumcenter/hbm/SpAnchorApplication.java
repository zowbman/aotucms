package com.aotuspace.aotucms.web.spaotumcenter.hbm;

import java.io.Serializable;
import java.util.Date;

import com.aotuspace.aotucms.web.spdictionary.hbm.livestation.SpLivestationInfo;

/**
 * 
 * Title:SpUsersBinfo
 * Description:��������������Ϣ
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-10 ����4:48:54
 *
 */
public class SpAnchorApplication implements Serializable {

	private Integer spId;

	private Integer spAtuid;//��͹id

	private SpLivestationInfo spLivestationInfo;//ֱ��ƽ̨

	private String spLiSrc;//ֱ������

	private String spLinickname;//ֱ���ǳ�

	private String spLiscreensort;//ֱ����ͼ

	private Date spApplydate;//����ʱ��

	private SpAnchorApplicationDetail spAnchorApplicationDetail;//������ϸ��Ϣ

	//�����������1:����У�2:ͨ��,3:��ͨ����
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
