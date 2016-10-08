package com.aotuspace.aotucms.web.spdictionary.hbm.bank;

/**
 * 
 * Title:SpBankBranch
 * Description:
 * Company:aotuspace
 * @author    Î°±¦
 * @date      2015-12-2 ÏÂÎç8:20:17
 *
 */
public class SpBankBranch {

	private long id;
	private String name;
	private String address;
	private String telephone;
	private Integer proid;
	private Integer cityid;
	private Integer bankid;

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getProid() {
		return proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
	}

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public Integer getBankid() {
		return bankid;
	}

	public void setBankid(Integer bankid) {
		this.bankid = bankid;
	}

}