package com.aotuspace.aotucms.web.spaotumcenter.hbm;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * Title:SpUsersBinfo
 * Description:用户基本信息
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-10 下午4:48:54
 *
 */
public class SpUsersBinfo  implements Serializable  {
	
	private SpUsersBinfoKey spUsersBinfoKey;
	
	private String spAccount;

	private String spPassword;

	private SpUsersIdentity spUsersIdentity;

	private Date spRedate;

	private String spReplace;

	private String spReip;

	private SpUsersStatus spUsersStatus;
	
	private SpUsersDinfo spUsersDinfo;
	
	private Set<SpAotuspaceRole> spAotuspaceRoles = new TreeSet<SpAotuspaceRole>();
	
	private Set<SpAotuspacePriv> spUsersPrivileges =new TreeSet<SpAotuspacePriv>();//（用户-权限）

	public String getSpAccount() {
		return spAccount;
	}

	public void setSpAccount(String spAccount) {
		this.spAccount = spAccount;
	}

	public String getSpPassword() {
		return spPassword;
	}

	public void setSpPassword(String spPassword) {
		this.spPassword = spPassword;
	}

	public SpUsersIdentity getSpUsersIdentity() {
		return spUsersIdentity;
	}

	public void setSpUsersIdentity(SpUsersIdentity spUsersIdentity) {
		this.spUsersIdentity = spUsersIdentity;
	}

	public Date getSpRedate() {
		return spRedate;
	}

	public void setSpRedate(Date spRedate) {
		this.spRedate = spRedate;
	}

	public String getSpReplace() {
		return spReplace;
	}

	public void setSpReplace(String spReplace) {
		this.spReplace = spReplace;
	}

	public String getSpReip() {
		return spReip;
	}

	public void setSpReip(String spReip) {
		this.spReip = spReip;
	}

	public SpUsersStatus getSpUsersStatus() {
		return spUsersStatus;
	}

	public void setSpUsersStatus(SpUsersStatus spUsersStatus) {
		this.spUsersStatus = spUsersStatus;
	}

	public SpUsersBinfoKey getSpUsersBinfoKey() {
		return spUsersBinfoKey;
	}

	public void setSpUsersBinfoKey(SpUsersBinfoKey spUsersBinfoKey) {
		this.spUsersBinfoKey = spUsersBinfoKey;
	}

	public SpUsersDinfo getSpUsersDinfo() {
		return spUsersDinfo;
	}

	public void setSpUsersDinfo(SpUsersDinfo spUsersDinfo) {
		this.spUsersDinfo = spUsersDinfo;
	}

	public Set<SpAotuspaceRole> getSpAotuspaceRoles() {
		return spAotuspaceRoles;
	}

	public void setSpAotuspaceRoles(Set<SpAotuspaceRole> spAotuspaceRoles) {
		this.spAotuspaceRoles = spAotuspaceRoles;
	}

	public Set<SpAotuspacePriv> getSpUsersPrivileges() {
		return spUsersPrivileges;
	}

	public void setSpUsersPrivileges(Set<SpAotuspacePriv> spUsersPrivileges) {
		this.spUsersPrivileges = spUsersPrivileges;
	}
}
