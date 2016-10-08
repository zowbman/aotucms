package com.aotuspace.aotucms.web.spaotumcenter.hbm;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * Title:SpUsersBinfo
 * Description:凹凸空间角色管理
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-10 下午4:48:54
 *
 */

public class SpAotuspaceRole  implements Serializable  {
	
	private int spId;

	private String spRolename;
	
	private SpAotuspaceRole spAotuspaceRoleparent; //上级部门
	
	private Set<SpAotuspaceRole> spAotuspaceRolechildren = new TreeSet<SpAotuspaceRole>();//下级部门
	
	private Set<SpAotuspacePriv> spUsersRolePrivileges =new TreeSet<SpAotuspacePriv>();//（角色-权限）
	
	private  Set<SpUsersBinfo> spUsersBinfos = new TreeSet<SpUsersBinfo>();

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
	}

	public String getSpRolename() {
		return spRolename;
	}

	public void setSpRolename(String spRolename) {
		this.spRolename = spRolename;
	}

	public SpAotuspaceRole getSpAotuspaceRoleparent() {
		return spAotuspaceRoleparent;
	}

	public void setSpAotuspaceRoleparent(SpAotuspaceRole spAotuspaceRoleparent) {
		this.spAotuspaceRoleparent = spAotuspaceRoleparent;
	}

	public Set<SpAotuspaceRole> getSpAotuspaceRolechildren() {
		return spAotuspaceRolechildren;
	}

	public void setSpAotuspaceRolechildren(Set<SpAotuspaceRole> spAotuspaceRolechildren) {
		this.spAotuspaceRolechildren = spAotuspaceRolechildren;
	}

	public Set<SpUsersBinfo> getSpUsersBinfos() {
		return spUsersBinfos;
	}

	public void setSpUsersBinfos(Set<SpUsersBinfo> spUsersBinfos) {
		this.spUsersBinfos = spUsersBinfos;
	}

	public Set<SpAotuspacePriv> getSpUsersRolePrivileges() {
		return spUsersRolePrivileges;
	}

	public void setSpUsersRolePrivileges(Set<SpAotuspacePriv> spUsersRolePrivileges) {
		this.spUsersRolePrivileges = spUsersRolePrivileges;
	}
}
