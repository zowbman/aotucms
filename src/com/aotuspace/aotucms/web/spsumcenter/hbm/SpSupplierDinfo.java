package com.aotuspace.aotucms.web.spsumcenter.hbm;

import java.io.Serializable;
/**
 * 
 * Title:SpSupplierDinfo
 * Description:��Ӧ����ϸ��Ϣ��(sp_supplier_dinfo)sp_supplier_detailinfo					
 * Company:aotuspace
 * @author    sida
 * @date      2015-10-13 ����12:16:51
 *
 */
@SuppressWarnings("serial")
public class SpSupplierDinfo implements Serializable {
	
	//��������
	private SpSupplierBinfoKey spSupplierBinfoKey;
	
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
	
	//�̼Ҽ��
	private String spSuresume;
	
	//�̼�logo
	private String spSulogo;

	public SpSupplierBinfoKey getSpSupplierBinfoKey() {
		return spSupplierBinfoKey;
	}

	public void setSpSupplierBinfoKey(SpSupplierBinfoKey spSupplierBinfoKey) {
		this.spSupplierBinfoKey = spSupplierBinfoKey;
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

	public String getSpSuresume() {
		return spSuresume;
	}

	public void setSpSuresume(String spSuresume) {
		this.spSuresume = spSuresume;
	}

	public String getSpSulogo() {
		return spSulogo;
	}

	public void setSpSulogo(String spSulogo) {
		this.spSulogo = spSulogo;
	}
	
	
}
