package com.aotuspace.aotucms.web.spaotumcenter.hbm;

import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * Title:SpUsersIdentity
 * Description:�û����
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-10 ����5:11:52
 *
 */
public class SpUsersIdentity {
	
	private Integer spId;
	private String spIdentityn;
	
	private Set<SpAotuspacePriv> spUsersIdentPrivileges =new TreeSet<SpAotuspacePriv>();//�����-Ȩ�ޣ�

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpIdentityn() {
		return spIdentityn;
	}

	public void setSpIdentityn(String spIdentityn) {
		this.spIdentityn = spIdentityn;
	}

	public Set<SpAotuspacePriv> getSpUsersIdentPrivileges() {
		return spUsersIdentPrivileges;
	}

	public void setSpUsersIdentPrivileges(Set<SpAotuspacePriv> spUsersIdentPrivileges) {
		this.spUsersIdentPrivileges = spUsersIdentPrivileges;
	}
}
