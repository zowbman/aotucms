package com.aotuspace.aotucms.web.spdictionary.hbm.bank;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Title:SpBanks
 * Description:银行总行信息
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-12-2 下午8:18:02
 *
 */
public class SpBanks {

	// Fields    

	private Integer id;
	private String name;
	private String nameen;
	private String briefname;
	private String code;
	private Set<SpBankBranch> spBankBranchs = new HashSet<SpBankBranch>();

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameen() {
		return this.nameen;
	}

	public void setNameen(String nameen) {
		this.nameen = nameen;
	}

	public String getBriefname() {
		return this.briefname;
	}

	public void setBriefname(String briefname) {
		this.briefname = briefname;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<SpBankBranch> getSpBankBranchs() {
		return spBankBranchs;
	}

	public void setSpBankBranchs(Set<SpBankBranch> spBankBranchs) {
		this.spBankBranchs = spBankBranchs;
	}

}