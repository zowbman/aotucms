package com.aotuspace.aotucms.web.spdictionary.hbm.bank;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Title:SpBankRegion
 * Description:银行区域
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-12-2 下午8:19:00
 *
 */

public class SpBankRegion {

	private Integer id;
	private String name;
	private Integer level;
	private Set<SpBankRegion> spBankRegionsChildren = new HashSet<SpBankRegion>();

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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Set<SpBankRegion> getSpBankRegionsChildren() {
		return spBankRegionsChildren;
	}

	public void setSpBankRegionsChildren(Set<SpBankRegion> spBankRegionsChildren) {
		this.spBankRegionsChildren = spBankRegionsChildren;
	}

}