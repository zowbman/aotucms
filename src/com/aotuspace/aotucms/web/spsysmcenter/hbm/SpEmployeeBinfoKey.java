package com.aotuspace.aotucms.web.spsysmcenter.hbm;

import java.io.Serializable;

/**
 * 
 * Title:SpEmployeeBinfoKey
 * Description:SpEmplyeeBinfoKey��������
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-24 ����6:04:46
 *
 */
public class SpEmployeeBinfoKey implements Serializable {
	
	// id
	private Integer spId;
	// Ա��id
	private Integer spEpid;

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Integer getSpEpid() {
		return spEpid;
	}

	public void setSpEpid(Integer spEpid) {
		this.spEpid = spEpid;
	}
}
