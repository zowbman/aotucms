package com.aotuspace.aotucms.web.spsysmcenter.hbm;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * Title:SpEmployeePrivilege
 * Description:Ȩ�ޱ�
 * Company:aotuspace
 * @author    sida
 * @date      2015-9-14 ����2:37:59
 *
 */
public class SpEmployeePrivilege implements Serializable {
	
	private Integer spId;
	
	private String spEpname; //Ȩ������
	
	private String spEpurl;//Ȩ�޵�ַ
	
	private String spIconcls;//Ȩ��ͼ��
	
	private String spState;//Ȩ��չ��״̬
	
	private Set<SpEmployeeStation> spEmployeeStations = new TreeSet<SpEmployeeStation>();
	
	private SpEmployeePrivilege spEpparent; //�ϼ�Ȩ��
	
	@JsonIgnore
	private Set<SpEmployeePrivilege> spEpchildren = new TreeSet<SpEmployeePrivilege>(); //�¼�Ȩ��

	public SpEmployeePrivilege(){
			
	}
	
	public SpEmployeePrivilege(String spEpname, String spEpurl, String spIconcls,String spState,
			SpEmployeePrivilege spEpparent) {
		super();
		this.spEpname = spEpname;//Ȩ������
		this.spEpurl = spEpurl;//Ȩ�޵�ַ
		this.spIconcls=spIconcls;//Ȩ��ͼ��
		this.spState=spState;//Ȩ��չ��״̬
		this.spEpparent = spEpparent;//��Ȩ��
	}

	
	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpEpname() {
		return spEpname;
	}

	public void setSpEpname(String spEpname) {
		this.spEpname = spEpname;
	}

	public String getSpEpurl() {
		return spEpurl;
	}

	public void setSpEpurl(String spEpurl) {
		this.spEpurl = spEpurl;
	}

	public Set<SpEmployeeStation> getSpEmployeeStations() {
		return spEmployeeStations;
	}

	public void setSpEmployeeStations(Set<SpEmployeeStation> spEmployeeStations) {
		this.spEmployeeStations = spEmployeeStations;
	}

	public SpEmployeePrivilege getSpEpparent() {
		return spEpparent;
	}

	public void setSpEpparent(SpEmployeePrivilege spEpparent) {
		this.spEpparent = spEpparent;
	}

	public Set<SpEmployeePrivilege> getSpEpchildren() {
		return spEpchildren;
	}

	public void setSpEpchildren(Set<SpEmployeePrivilege> spEpchildren) {
		this.spEpchildren = spEpchildren;
	}

	public String getSpIconcls() {
		return spIconcls;
	}

	public void setSpIconcls(String spIconcls) {
		this.spIconcls = spIconcls;
	}

	public String getSpState() {
		return spState;
	}

	public void setSpState(String spState) {
		this.spState = spState;
	}
	
	
}
