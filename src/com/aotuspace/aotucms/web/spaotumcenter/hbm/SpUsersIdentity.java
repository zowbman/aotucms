package com.aotuspace.aotucms.web.spaotumcenter.hbm;

import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * Title:SpUsersIdentity
 * Description:用户身份
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-10 下午5:11:52
 *
 */
public class SpUsersIdentity {
	
	private Integer spId;
	private String spIdentityn;
	
	private Set<SpAotuspacePriv> spUsersIdentPrivileges =new TreeSet<SpAotuspacePriv>();//（身份-权限）

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
