package com.aotuspace.aotucms.web.spaotumcenter.hbm;

import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBankBranch;
import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBankRegion;
import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBanks;

public class SpAnchorDetail implements java.io.Serializable {

	private Integer spId;//ÐòºÅ
	private SpBankRegion spBankRegion;
	private SpBankBranch spBankBranch;
	private SpBanks spBanks;
	private String spRealname;
	private String spMobie;
	private String spIdnum;
	private String spIdnumsort;
	private String spBankusername;
	private long spBaid;

	public SpAnchorDetail() {
	}

	public SpAnchorDetail(SpBankRegion spBankRegion, SpBankBranch spBankBranch, SpBanks spBanks, String spRealName,
			String spMobie, String spIdNum, String spIdNumSort, String spBankUserName, long spBaId) {
		this.spBankRegion = spBankRegion;
		this.spBankBranch = spBankBranch;
		this.spBanks = spBanks;
		this.spRealname = spRealName;
		this.spMobie = spMobie;
		this.spIdnum = spIdNum;
		this.spIdnumsort = spIdNumSort;
		this.spBankusername = spBankUserName;
		this.spBaid = spBaId;
	}

	public Integer getSpId() {
		return spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public SpBankRegion getSpBankRegion() {
		return spBankRegion;
	}

	public void setSpBankRegion(SpBankRegion spBankRegion) {
		this.spBankRegion = spBankRegion;
	}

	public SpBankBranch getSpBankBranch() {
		return spBankBranch;
	}

	public void setSpBankBranch(SpBankBranch spBankBranch) {
		this.spBankBranch = spBankBranch;
	}

	public SpBanks getSpBanks() {
		return spBanks;
	}

	public void setSpBanks(SpBanks spBanks) {
		this.spBanks = spBanks;
	}

	public String getSpRealname() {
		return spRealname;
	}

	public void setSpRealname(String spRealname) {
		this.spRealname = spRealname;
	}

	public String getSpMobie() {
		return spMobie;
	}

	public void setSpMobie(String spMobie) {
		this.spMobie = spMobie;
	}

	public String getSpIdnum() {
		return spIdnum;
	}

	public void setSpIdnum(String spIdnum) {
		this.spIdnum = spIdnum;
	}

	public String getSpIdnumsort() {
		return spIdnumsort;
	}

	public void setSpIdnumsort(String spIdnumsort) {
		this.spIdnumsort = spIdnumsort;
	}

	public String getSpBankusername() {
		return spBankusername;
	}

	public void setSpBankusername(String spBankusername) {
		this.spBankusername = spBankusername;
	}

	public long getSpBaid() {
		return spBaid;
	}

	public void setSpBaid(long spBaid) {
		this.spBaid = spBaid;
	}


}