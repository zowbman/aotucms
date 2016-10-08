package com.aotuspace.aotucms.web.spaotumcenter.hbm;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * Title:SpEmployeePrivilege
 * Description:权限表
 * Company:aotuspace
 * @author    sida
 * @date      2015-9-14 下午2:37:59
 *
 */
public class SpAotuspacePriv implements Serializable {
	
	private Integer spId;
	
	private String spName; //权限名称
	
	private String spUrl;//权限地址
	
	private SpAotuspacePriv privParent;
	
	private Set<SpAotuspacePriv> privsChildren=new TreeSet<SpAotuspacePriv>();
	
	private Set<SpUsersBinfo> spUsers=new TreeSet<SpUsersBinfo>();
	
	private Set<SpUsersIdentity> spUserIdents=new TreeSet<SpUsersIdentity>();
	
	private Set<SpAotuspaceRole> spUserRoles=new TreeSet<SpAotuspaceRole>();
	
	private String spState;//权限展开状态

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getSpUrl() {
		return spUrl;
	}

	public void setSpUrl(String spUrl) {
		this.spUrl = spUrl;
	}

	public SpAotuspacePriv getPrivParent() {
		return privParent;
	}

	public void setPrivParent(SpAotuspacePriv privParent) {
		this.privParent = privParent;
	}

	public Set<SpAotuspacePriv> getPrivsChildren() {
		return privsChildren;
	}

	public void setPrivsChildren(Set<SpAotuspacePriv> privsChildren) {
		this.privsChildren = privsChildren;
	}

	public String getSpState() {
		return spState;
	}

	public void setSpState(String spState) {
		this.spState = spState;
	}

	public Set<SpUsersIdentity> getSpUserIdents() {
		return spUserIdents;
	}

	public void setSpUserIdents(Set<SpUsersIdentity> spUserIdents) {
		this.spUserIdents = spUserIdents;
	}

	public Set<SpAotuspaceRole> getSpUserRoles() {
		return spUserRoles;
	}

	public void setSpUserRoles(Set<SpAotuspaceRole> spUserRoles) {
		this.spUserRoles = spUserRoles;
	}

	public Set<SpUsersBinfo> getSpUsers() {
		return spUsers;
	}

	public void setSpUsers(Set<SpUsersBinfo> spUsers) {
		this.spUsers = spUsers;
	}
}
