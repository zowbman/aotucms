package com.aotuspace.aotucms.web.spaotumcenter.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotucms.web.base.BaseAction;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAotuspacePriv;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersIdentity;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 
 * Title:AotuUMAction
 * Description:��͹�ռ��û���ݹ���
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:17:02
 *
 */

@Controller
@Scope("prototype")
public class AotuUIMAction extends BaseAction<SpUsersIdentity> {
	
	//�û�ids
	public List<Integer> useridentIds;
	
	//Ȩ��ids
	private Integer[] privIds;
	
	//�û����id
	private Integer selectedUserIdentId;
	
	//�û�����б�ҳ��
	public String list() throws Exception {
		return "list";
	}
	
	//�û����data
	public String listData() throws Exception {
		//��ѯ�û���ҳ��Ϣ
		PageBean<SpUsersIdentity> spUsersIdentityList = iAotuUIMService.findspUsersIdentityList(rows, page);
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpUsersIdentity spUsersIdentity : spUsersIdentityList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spUsersIdentity.getSpId());
			rowMap.put("sp_IdentityN", spUsersIdentity.getSpIdentityn());
			listMaps.add(rowMap);
		}
		//easyui total ����  rows�б�
		pageListMap.put("total", spUsersIdentityList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	
	//����û����
	public String add() throws Exception {
		//����û���û���ظ����򱣴浽���ݿ�
		SpUsersIdentity spUsersIdentity = iAotuUIMService.findBySpIdentityn(model.getSpIdentityn());
		if (spUsersIdentity == null) {
			//���ӵ����ݿ�
			iAotuUIMService.save(model);
			//�ɹ�
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");	
		} else {//��������ظ�������ʾ��������
			//ʧ��
			jsonResult.setCode(100001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//ɾ���û�
	public String delete() throws Exception {
		Integer[] ids=new Integer[useridentIds.size()];
		for (int i = 0; i < useridentIds.size(); i++) {
			ids[i]=useridentIds.get(i);
		}
		iAotuUIMService.delete(ids);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//�����û�ҳ��
	public String edit() throws Exception{
		//��ȡԭ�������
		SpUsersIdentity spUsersIdentity=iAotuUIMService.getById(model.getSpId());;
		if(spUsersIdentity!=null){
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
			jsonResult.setData("["+objectMapper.writeValueAsString(spUsersIdentity)+"]");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}

	//�����û�
	public String editSubmit() throws Exception {
		//�����ݿ��ȡԴ����
		SpUsersIdentity spUsersIdentity = iAotuUIMService.getById(model.getSpId());
		if(spUsersIdentity!=null){
			//����Ҫ�޸ĵ�����
			spUsersIdentity.setSpIdentityn(model.getSpIdentityn());
			//���µ����ݿ�
			iAotuUIMService.update(spUsersIdentity);
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//Ȩ��combotree
	public String privComboTree() throws Exception{
		List<SpAotuspacePriv> SpAotuspacePrivList = iAotuPMService.findSpAotuspacePrivComboTreeData();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuspacePriv spAotuspacePriv : SpAotuspacePrivList) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spAotuspacePriv.getSpId());
			rowMap.put("text", spAotuspacePriv.getSpName());
			listMaps.add(rowMap);
		}
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}
	
	//Ȩ��treegrid
	public String privTree() throws Exception {
		//��ѯȨ������
		List<SpAotuspacePriv> SpAotuspacePrivList = iAotuPMService.findSpAotuspacePrivTreeData();
		//����id��ѯȨ��
		/*SpEmployeePrivilege spEmployeePrivilege =iSysPMService.getById(id)*/
		//��װjson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuspacePriv spAotuspacePriv : SpAotuspacePrivList) {
			List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String, Object>>();
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spAotuspacePriv.getSpId());
			rowMap.put("text", spAotuspacePriv.getSpName());
			if (spAotuspacePriv.getPrivsChildren().size() == 0) {
				if (selectedUserIdentId != null) {
					for (SpUsersIdentity spUsersIdentity : spAotuspacePriv.getSpUserIdents()) {
						if (selectedUserIdentId.equals(spUsersIdentity.getSpId())) {
							rowMap.put("checked", "true");
							break;
						}
					}
				}
			}
			for (SpAotuspacePriv childrenPriv : spAotuspacePriv.getPrivsChildren()) {
				Map<String, Object> rowChildrenMap = new HashMap<String, Object>();
				rowChildrenMap.put("id", childrenPriv.getSpId());
				rowChildrenMap.put("text", childrenPriv.getSpName());
				if (childrenPriv.getPrivsChildren().size() == 0) {
					if (selectedUserIdentId != null) {
						for (SpUsersIdentity spUsersIdentity : childrenPriv.getSpUserIdents()) {
							if (selectedUserIdentId.equals(spUsersIdentity.getSpId())) {
								rowMap.put("checked", "true");
								break;
							}
						}
					}
				}else if (childrenPriv.getPrivsChildren().size() > 0) {
					List<Map<String, Object>> listChildren2Maps = new ArrayList<Map<String, Object>>();
					for (SpAotuspacePriv chilren2Priv : childrenPriv.getPrivsChildren()) {
						Map<String, Object> rowChilren2Map = new HashMap<String, Object>();
						rowChilren2Map.put("id", chilren2Priv.getSpId());
						rowChilren2Map.put("text", chilren2Priv.getSpName());
						if (selectedUserIdentId != null) {
							for (SpUsersIdentity spUsersIdentity : chilren2Priv.getSpUserIdents()) {
								if (selectedUserIdentId.equals(spUsersIdentity.getSpId())) {
									rowChilren2Map.put("checked", "true");
									break;
								}
							}
						}
						listChildren2Maps.add(rowChilren2Map);
					}
					rowChildrenMap.put("children", listChildren2Maps);
				}
				listChildrenMaps.add(rowChildrenMap);

			}
			rowMap.put("children", listChildrenMaps);
			listMaps.add(rowMap);
		}
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}
	
	//�û����-����Ȩ�ޣ�Ȩ�����ã�
	public String userIdentPrivSet() throws Exception {
		SpUsersIdentity spUsersIdentity = iAotuUIMService.getById(selectedUserIdentId);
		List<SpAotuspacePriv> spAotuspacePrivs = iAotuPMService.getByIds(privIds);
		spUsersIdentity.setSpUsersIdentPrivileges(new HashSet<SpAotuspacePriv>(spAotuspacePrivs));
		iAotuUIMService.update(spUsersIdentity);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//getter��setter
	public List<Integer> getUseridentIds() {
		return useridentIds;
	}

	public void setUseridentIds(List<Integer> useridentIds) {
		this.useridentIds = useridentIds;
	}

	public Integer[] getPrivIds() {
		return privIds;
	}

	@JSON(serialize = true, deserialize = true)
	public void setPrivIds(Integer[] privIds) {
		this.privIds = privIds;
	}

	public Integer getSelectedUserIdentId() {
		return selectedUserIdentId;
	}

	public void setSelectedUserIdentId(Integer selectedUserIdentId) {
		this.selectedUserIdentId = selectedUserIdentId;
	}
}
