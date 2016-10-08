package com.aotuspace.aotucms.web.spsysmcenter.hbm;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * Title:EmployeeBinfo Description:Ա��������Ϣ�� Company:aotuspace
 * 
 * @author sida
 * @date 2015-9-2 ����5:32:53
 * 
 */

public class SpEmployeeDinfo implements Serializable{
	
	//��������
	private SpEmployeeBinfoKey spEmployeeBinfoKey;
	
	//Ա����ʵ����
	private String spEprealname;
	
	//Ա��ͷ��
	private String spEpicon;
	
	//Ա����������
	private Date spEpbirth;
	
	//Ա������
	private Integer spEpcon;
	
	//Ա���ֻ�����
	private String spEpmobie;
	
	//Ա�����֤����
	private String spEpidnum;
	
	//Ա����Ф
	private Integer spEpanimal;
	
	//Ա������
	private Integer spEpage;
	
	//Ա��ʡ��
	private String spEmpdistrict;
	
	//Ա����ϸ��ַ
	private String spEpaddress;
	
	//�Ա�
	private String spEpsex;
	
	public SpEmployeeBinfoKey getSpEmployeeBinfoKey() {
		return spEmployeeBinfoKey;
	}

	public void setSpEmployeeBinfoKey(SpEmployeeBinfoKey spEmployeeBinfoKey) {
		this.spEmployeeBinfoKey = spEmployeeBinfoKey;
	}

	public String getSpEprealname() {
		return spEprealname;
	}

	public void setSpEprealname(String spEprealname) {
		this.spEprealname = spEprealname;
	}

	public String getSpEpicon() {
		return spEpicon;
	}

	public void setSpEpicon(String spEpicon) {
		this.spEpicon = spEpicon;
	}

	public Integer getSpEpcon() {
		return spEpcon;
	}

	public void setSpEpcon(Integer spEpcon) {
		this.spEpcon = spEpcon;
	}

	public String getSpEpmobie() {
		return spEpmobie;
	}

	public void setSpEpmobie(String spEpmobie) {
		this.spEpmobie = spEpmobie;
	}

	public String getSpEpidnum() {
		return spEpidnum;
	}

	public void setSpEpidnum(String spEpidnum) {
		this.spEpidnum = spEpidnum;
	}

	public Integer getSpEpanimal() {
		return spEpanimal;
	}

	public void setSpEpanimal(Integer spEpanimal) {
		this.spEpanimal = spEpanimal;
	}

	public Integer getSpEpage() {
		return spEpage;
	}

	public void setSpEpage(Integer spEpage) {
		this.spEpage = spEpage;
	}

	public String getSpEpaddress() {
		return spEpaddress;
	}

	public void setSpEpaddress(String spEpaddress) {
		this.spEpaddress = spEpaddress;
	}

	public Date getSpEpbirth() {
		return spEpbirth;
	}

	public void setSpEpbirth(Date spEpbirth) {
		this.spEpbirth = spEpbirth;
	}

	public String getSpEmpdistrict() {
		return spEmpdistrict;
	}

	public void setSpEmpdistrict(String spEmpdistrict) {
		this.spEmpdistrict = spEmpdistrict;
	}

	public String getSpEpsex() {
		return spEpsex;
	}

	public void setSpEpsex(String spEpsex) {
		this.spEpsex = spEpsex;
	}
}
