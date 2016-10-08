package com.aotuspace.aotucms.web.spsumcenter.hbm;

import java.io.Serializable;
/**
 * 
 * Title:SpSupplierApplication
 * Description:供应商申请表(sp_supplier_application)					
 * Company:aotuspace
 * @author    sida
 * @date      2015-10-13 上午11:34:53
 *
 */
@SuppressWarnings("serial")
public class SpSupplierApplication implements Serializable {
	
	//id
	private Integer spId;
	
	//商家名称
	private String spSusup;
	
	//商家联系人姓名
	private String spSucont;
	
	//商家联系电话
	private String spSutel;
	
	//商家手机号码
	private String spSumobie;
	
	//商家所在地
	private String spSudistrict;
	
	//商家详细地址
	private String spSuaddress;
	
	//商家所属行业ID
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
