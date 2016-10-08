package com.aotuspace.aotucms.web.spsysmcenter.hbm;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:EmployeeBinfo 
 * Description:Ա��������Ϣ��
 * Company:aotuspace
 * 
 * @author sida
 * @date 2015-9-2 ����5:32:53
 * 
 */

@SuppressWarnings("unchecked")
public class SpEmployeeBinfo  implements Serializable {
	
	//��������
	private SpEmployeeBinfoKey spEmployeeBinfoKey;
	
	// Ա���ʺ�
	private String spEpaccount;

	// Ա������
	private String spEppassword;

	// Ա������
	
	public SpEmployeeDepart spEmployeeDepart;

	/*private Set<SpEmployeeStation> spEmployeeStations = new HashSet<SpEmployeeStation>();*/
	
	private Set<SpEmployeeStation> spEmployeeStations = new TreeSet<SpEmployeeStation>();

	private SpEmployeeDinfo spEmployeeDinfo;
	
	/**
	 * �жϱ��û��Ƿ���ָ�����Ƶ�Ȩ��
	 * @return
	 */
	public boolean hasPrivilegeByName(String name){
		//��������Ա�����е�Ȩ��
		if(isAdmin()){
			return true;
		}
		
		//��ͨ�û��ж��Ƿ������Ȩ��
		for (SpEmployeeStation spEmployeeStation : spEmployeeStations) {
			for (SpEmployeePrivilege priv : spEmployeeStation.getSpEmployeePrivileges()) {
				if(priv.getSpEpname().equals(name)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * �жϱ��û��Ƿ���ָ��URL��Ȩ��
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String privUrl){
		//��������Ա������Ȩ��
		if(isAdmin()){
			return true;
		}
		
		// >>ȥ������Ĳ���
		int pos = privUrl.indexOf("?");
		if(pos > -1){
			privUrl = privUrl.substring(0,pos);
		}
		
		// >>ȥ��UI��׺
		if(privUrl.endsWith("Submit")){
			privUrl = privUrl.substring(0, privUrl.length() - 6);
		}
		
		// �����URL����Ҫ���ƣ����½�û��Ϳ���ʹ��
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext.getContext().getApplication()
				.get("allPrivilegeUrls");
		if (!allPrivilegeUrls.contains(privUrl)) {
			return true;
		}else{
			//��ͨ�û��ж��Ƿ������Ȩ��
			for (SpEmployeeStation spEmployeeStation : spEmployeeStations) {
				for(SpEmployeePrivilege  priv : spEmployeeStation.getSpEmployeePrivileges()){
					if(privUrl.equals(priv.getSpEpurl())){
						return true;
					}
				}
			}
			return false;
		}
	}

	
	/**
	 * �жϱ��û��ǲ��ǳ�������Ա
	 * @return
	 */
	public boolean isAdmin(){
		return "admin".equals(spEpaccount);
	}
	
	public SpEmployeeBinfoKey getSpEmployeeBinfoKey() {
		return spEmployeeBinfoKey;
	}

	public void setSpEmployeeBinfoKey(SpEmployeeBinfoKey spEmployeeBinfoKey) {
		this.spEmployeeBinfoKey = spEmployeeBinfoKey;
	}

	public String getSpEpaccount() {
		return spEpaccount;
	}

	public void setSpEpaccount(String spEpaccount) {
		this.spEpaccount = spEpaccount;
	}

	public String getSpEppassword() {
		return spEppassword;
	}

	public void setSpEppassword(String spEppassword) {
		this.spEppassword = spEppassword;
	}

	public SpEmployeeDepart getSpEmployeeDepart() {
		return spEmployeeDepart;
	}

	public void setSpEmployeeDepart(SpEmployeeDepart spEmployeeDepart) {
		this.spEmployeeDepart = spEmployeeDepart;
	}

	public Set<SpEmployeeStation> getSpEmployeeStations() {
		return spEmployeeStations;
	}

	public void setSpEmployeeStations(Set<SpEmployeeStation> spEmployeeStations) {
		this.spEmployeeStations = spEmployeeStations;
	}

	public SpEmployeeDinfo getSpEmployeeDinfo() {
		return spEmployeeDinfo;
	}

	public void setSpEmployeeDinfo(SpEmployeeDinfo spEmployeeDinfo) {
		this.spEmployeeDinfo = spEmployeeDinfo;
	}
}
