package com.aotuspace.aotucms.web.spaotumcenter.hbm;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * Title:SpUsersDinfo
 * Description:用户详细信息
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-10 下午4:59:19
 *
 */
public class SpUsersDinfo implements Serializable {

	private SpUsersBinfoKey spUsersBinfoKey;

	private String spNickname;

	private String spIcon;

	private String spRealname;

	private String spSex;

	private Date spBirth;

	private int spAnimal;

	private int spAge;

	private int spCon;
/*
	private int spCountry;

	private int spProvince;

	private int spCity;

	private int spArea;*/

	private String spAddress;
	
	private String spUserdistrict;

	public SpUsersBinfoKey getSpUsersBinfoKey() {
		return spUsersBinfoKey;
	}

	public void setSpUsersBinfoKey(SpUsersBinfoKey spUsersBinfoKey) {
		this.spUsersBinfoKey = spUsersBinfoKey;
	}

	public String getSpNickname() {
		return spNickname;
	}

	public void setSpNickname(String spNickname) {
		this.spNickname = spNickname;
	}

	public String getSpIcon() {
		return spIcon;
	}

	public void setSpIcon(String spIcon) {
		this.spIcon = spIcon;
	}

	public String getSpRealname() {
		return spRealname;
	}

	public void setSpRealname(String spRealname) {
		this.spRealname = spRealname;
	}

	public String getSpSex() {
		return spSex;
	}

	public void setSpSex(String spSex) {
		this.spSex = spSex;
	}

	public Date getSpBirth() {
		return spBirth;
	}

	public void setSpBirth(Date spBirth) {
		this.spBirth = spBirth;
	}

	public int getSpAnimal() {
		return spAnimal;
	}

	public void setSpAnimal(int spAnimal) {
		this.spAnimal = spAnimal;
	}

	public int getSpAge() {
		return spAge;
	}

	public void setSpAge(int spAge) {
		this.spAge = spAge;
	}

	public int getSpCon() {
		return spCon;
	}

	public void setSpCon(int spCon) {
		this.spCon = spCon;
	}

	/*public int getSpCountry() {
		return spCountry;
	}

	public void setSpCountry(int spCountry) {
		this.spCountry = spCountry;
	}

	public int getSpProvince() {
		return spProvince;
	}

	public void setSpProvince(int spProvince) {
		this.spProvince = spProvince;
	}

	public int getSpCity() {
		return spCity;
	}

	public void setSpCity(int spCity) {
		this.spCity = spCity;
	}

	public int getSpArea() {
		return spArea;
	}

	public void setSpArea(int spArea) {
		this.spArea = spArea;
	}*/


	public String getSpUserdistrict() {
		return spUserdistrict;
	}

	public void setSpUserdistrict(String spUserdistrict) {
		this.spUserdistrict = spUserdistrict;
	}
	

	public String getSpAddress() {
		return spAddress;
	}

	public void setSpAddress(String spAddress) {
		this.spAddress = spAddress;
	}

}
