package com.aotuspace.aotucms.web.spsysmcenter.hbm;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * Title:EmployeeBinfo
 * Description:������Ϣ��
 * Company:aotuspace
 * @author    sida
 * @date      2015-9-2 ����5:32:53
 *
 */

public class SpEmployeeDepart implements Serializable {
	
	//id
	private Integer spId;
	
	public Set<SpEmployeeBinfo> spEmployeeBinfos = new TreeSet<SpEmployeeBinfo>();
	
	//��������
	private String spEpdepartn;

	private SpEmployeeDepart spEpdeparent; //�ϼ�����
	
	private Set<SpEmployeeDepart> spEpdechildren = new TreeSet<SpEmployeeDepart>();//�¼�����
	
	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public Set<SpEmployeeBinfo> getSpEmployeeBinfos() {
		return spEmployeeBinfos;
	}

	public void setSpEmployeeBinfos(Set<SpEmployeeBinfo> spEmployeeBinfos) {
		this.spEmployeeBinfos = spEmployeeBinfos;
	}

	public String getSpEpdepartn() {
		return spEpdepartn;
	}

	public void setSpEpdepartn(String spEpdepartn) {
		this.spEpdepartn = spEpdepartn;
	}

	public SpEmployeeDepart getSpEpdeparent() {
		return spEpdeparent;
	}

	public void setSpEpdeparent(SpEmployeeDepart spEpdeparent) {
		this.spEpdeparent = spEpdeparent;
	}

	public Set<SpEmployeeDepart> getSpEpdechildren() {
		return spEpdechildren;
	}

	public void setSpEpdechildren(Set<SpEmployeeDepart> spEpdechildren) {
		this.spEpdechildren = spEpdechildren;
	}
	
	
}
