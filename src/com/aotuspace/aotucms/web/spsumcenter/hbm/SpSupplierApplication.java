package com.aotuspace.aotucms.web.spsumcenter.hbm;

import java.io.Serializable;
/**
 * 
 * Title:SpSupplierApplication
 * Description:��Ӧ�������(sp_supplier_application)					
 * Company:aotuspace
 * @author    sida
 * @date      2015-10-13 ����11:34:53
 *
 */
@SuppressWarnings("serial")
public class SpSupplierApplication implements Serializable {
	
	//id
	private Integer spId;
	
	//�̼�����
	private String spSusup;
	
	//�̼���ϵ������
	private String spSucont;
	
	//�̼���ϵ�绰
	private String spSutel;
	
	//�̼��ֻ�����
	private String spSumobie;
	
	//�̼����ڵ�
	private String spSudistrict;
	
	//�̼���ϸ��ַ
	private String spSuaddress;
	
	//�̼�������ҵID
	private Integer spSutraid;

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpSusup() {
		return spSusup;
	}

	public void setSpSusup(String spSusup) {
		this.spSusup = spSusup;
	}

	public String getSpSucont() {
		return spSucont;
	}

	public void setSpSucont(String spSucont) {
		this.spSucont = spSucont;
	}

	public String getSpSutel() {
		return spSutel;
	}

	public void setSpSutel(String spSutel) {
		this.spSutel = spSutel;
	}

	public String getSpSumobie() {
		return spSumobie;
	}

	public void setSpSumobie(String spSumobie) {
		this.spSumobie = spSumobie;
	}

	public String getSpSudistrict() {
		return spSudistrict;
	}

	public void setSpSudistrict(String spSudistrict) {
		this.spSudistrict = spSudistrict;
	}

	public String getSpSuaddress() {
		return spSuaddress;
	}

	public void setSpSuaddress(String spSuaddress) {
		this.spSuaddress = spSuaddress;
	}

	public Integer getSpSutraid() {
		return spSutraid;
	}

	public void setSpSutraid(Integer spSutraid) {
		this.spSutraid = spSutraid;
	}
	
	
}
