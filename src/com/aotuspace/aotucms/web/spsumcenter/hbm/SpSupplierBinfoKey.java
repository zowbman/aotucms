package com.aotuspace.aotucms.web.spsumcenter.hbm;

import java.io.Serializable;

/**
 * 
 * Title:SpSupperlierBinfoKey
 * Description:��Ӧ�̻�����Ϣ��������
 * Company:aotuspace
 * @author    sida
 * @date      2015-10-12 ����2:52:09
 *
 */
public class SpSupplierBinfoKey implements Serializable {
	
	// id
	private Integer spId;
	// Ա��id
	private Integer spSuid;

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Integer getSpSuid() {
		return spSuid;
	}

	public void setSpSuid(Integer spSuid) {
		this.spSuid = spSuid;
	}


}
