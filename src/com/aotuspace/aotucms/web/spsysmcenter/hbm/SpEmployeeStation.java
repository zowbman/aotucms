package com.aotuspace.aotucms.web.spsysmcenter.hbm;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;


/**
 * 
 * Title:SpEmployeeStation
 * Description:��λ��Ϣ��
 * Company:aotuspace
 * @author    sida
 * @date      2015-9-5 ����11:12:29
 *
 */

public class SpEmployeeStation implements Serializable {
	
	//id
	private int spId;
	
	//��λ����
	private String spEpstn;
	
	private  Set<SpEmployeeBinfo> spEmployeeBinfos = new TreeSet<SpEmployeeBinfo>();
	
	private  Set<SpEmployeePrivilege> spEmployeePrivileges = new TreeSet<SpEmployeePrivilege>();
	
	private SpEmployeeStation spEpstparent; //�ϼ���ɫ
	
	private Set<SpEmployeeStation> spEpstchildren = new TreeSet<SpEmployeeStation>();//�¼���ɫ

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
	}

	public String getSpEpstn() {
		return spEpstn;
	}

	public void setSpEpstn(String spEpstn) {
		this.spEpstn = spEpstn;
	}

	public Set<SpEmployeeBinfo> getSpEmployeeBinfos() {
		return spEmployeeBinfos;
	}

	public void setSpEmployeeBinfos(Set<SpEmployeeBinfo> spEmployeeBinfos) {
		this.spEmployeeBinfos = spEmployeeBinfos;
	}

	public Set<SpEmployeePrivilege> getSpEmployeePrivileges() {
		return spEmployeePrivileges;
	}

	public void setSpEmployeePrivileges(
			Set<SpEmployeePrivilege> spEmployeePrivileges) {
		this.spEmployeePrivileges = spEmployeePrivileges;
	}

	public SpEmployeeStation getSpEpstparent() {
		return spEpstparent;
	}

	public void setSpEpstparent(SpEmployeeStation spEpstparent) {
		this.spEpstparent = spEpstparent;
	}

	public Set<SpEmployeeStation> getSpEpstchildren() {
		return spEpstchildren;
	}

	public void setSpEpstchildren(Set<SpEmployeeStation> spEpstchildren) {
		this.spEpstchildren = spEpstchildren;
	}
	
	
}
