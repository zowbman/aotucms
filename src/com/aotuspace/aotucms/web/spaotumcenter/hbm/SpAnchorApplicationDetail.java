package com.aotuspace.aotucms.web.spaotumcenter.hbm;

import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBankBranch;
import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBankRegion;
import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBanks;

/**
 * 
 * Title:SpAnchorApplicationDetail
 * Description:����������������
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-12-1 ����7:54:02
 *
 */
public class SpAnchorApplicationDetail {

	private Integer spId;//���
	private String spRealname;//��������
	private String spMobie;//�ֻ�����
	private String spIdNum;//���֤
	private String spIdNumSort;//���֤��ͼ

	private SpBanks spBanks;//��������
	private SpBankRegion spBankRegion;//������
	private SpBankBranch spBankBranch;//֧��

	private String spBankUserName;//������
	private long spBaId;//����

	public Integer getSpId() {
		return this.spId;
	}

	public void setSpId(Integer spId) {
		this.spId = spId;
	}

	public String getSpIdNum() {
		return this.spIdNum;
	}

	public void setSpIdNum(String spIdNum) {
		this.spIdNum = spIdNum;
	}

	public String getSpIdNumSort() {
		return this.spIdNumSort;
	}

	public void setSpIdNumSort(String spIdNumSort) {
		this.spIdNumSort = spIdNumSort;
	}

	public SpBanks getSpBanks() {
		return spBanks;
	}

	public void setSpBanks(SpBanks spBanks) {
		this.spBanks = spBanks;
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

	public String getSpBankUserName() {
		return spBankUserName;
	}

	public void setSpBankUserName(String spBankUserName) {
		this.spBankUserName = spBankUserName;
	}

	public long getSpBaId() {
		return spBaId;
	}

	public void setSpBaId(long spBaId) {
		this.spBaId = spBaId;
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

}