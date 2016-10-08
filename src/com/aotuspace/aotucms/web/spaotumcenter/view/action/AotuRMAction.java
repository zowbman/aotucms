package com.aotuspace.aotucms.web.spaotumcenter.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotucms.web.base.BaseAction;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAotuspacePriv;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAotuspaceRole;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersBinfo;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersBinfoKey;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 
 * Title:AotuURMAction
 * Description:��͹�ռ��ɫ����
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-12 ����7:13:52
 *
 */

@Controller
@Scope("prototype")
public class AotuRMAction extends BaseAction<SpAotuspaceRole> {
	
	//Ȩ��ids
	private Integer[] privIds;
	
	//�û����id
	private Integer selectedUserRoleId;
	
	//Ա��ids
	public List<SpUsersBinfoKey> userIds;
	
	//��͹�ռ��ɫ�б�ҳ��
	public String list() throws Exception {
		return "list";
	}
	
	//��ɫ�б�
	public String listData() throws Exception {
		//��ѯ��ɫ�б�����
		PageBean<SpAotuspaceRole> spAotuspaceRoleList = iAotuRMService.findSpAotuspaceTopRoleList(rows, page);
		//��װJson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuspaceRole spAotuspaceRole : spAotuspaceRoleList.getRecordList()) {
			List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String, Object>>();
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spAotuspaceRole.getSpId());
			rowMap.put("sp_RoleName", spAotuspaceRole.getSpRolename());
			for (SpAotuspaceRole childrenRole : spAotuspaceRole.getSpAotuspaceRolechildren()) {
				Map<String, Object> rowChildrenMap = new HashMap<String, Object>();
				rowChildrenMap.put("sp_id", childrenRole.getSpId());
				rowChildrenMap.put("sp_RoleName", childrenRole.getSpRolename());
				listChildrenMaps.add(rowChildrenMap);
			}
			rowMap.put("children", listChildrenMaps);
			listMaps.add(rowMap);
		}
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}

	//������ɫcomboTreeData����
	public String parentTreeData() throws Exception {
		boolean flag = true;
		//��ѯ��ɫ�б�����
		List<SpAotuspaceRole> spAotuspaceRoleList = iAotuRMService.findSpAotuspaceRoleTreeData();
		//��װjson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuspaceRole spAotuspaceRole : spAotuspaceRoleList) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spAotuspaceRole.getSpId());
			rowMap.put("text", spAotuspaceRole.getSpRolename());
			if (flag) {
				//Ĭ��ѡ��
				Map<String, Object> defaultrowMap = new HashMap<String, Object>();
				defaultrowMap.put("id", 0);
				defaultrowMap.put("text", "��ѡ��������ɫ");
				listMaps.add(defaultrowMap);
				flag = false;
			}
			listMaps.add(rowMap);
		}
		//easyui total�����б�
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}
	
	// ��ɫ���
	public String add() throws Exception {
		if (model.getSpAotuspaceRoleparent().getSpId() == 0) {
			model.setSpAotuspaceRoleparent(null);
		}
		iAotuRMService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	// �޸Ļ�������
	public String edit() throws Exception {
		// �����ݿ��ȡԴ����
		SpAotuspaceRole spAotuspaceRole = iAotuRMService.getById(model.getSpId());
		// �������Ϊ�գ���
		if (spAotuspaceRole != null) {
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
			jsonResult.setData("[" + objectMapper.writeValueAsString(spAotuspaceRole) + "]");
		} else { // ����
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write("[" + objectMapper.writeValueAsString(jsonResult) + "]");
		return NONE;
	}

	//��ɫ�޸�
	public String editSubmit() throws Exception {
		//�����ݿ�ȡ������
		SpAotuspaceRole spAotuspaceRole = iAotuRMService.getById(model.getSpId());
		//����Ҫ�޸ĵ�����
		spAotuspaceRole.setSpRolename(model.getSpRolename());
		if (model.getSpAotuspaceRoleparent().getSpId() == 0) {
			spAotuspaceRole.setSpAotuspaceRoleparent(null);
		} else {
			spAotuspaceRole.setSpAotuspaceRoleparent(model.getSpAotuspaceRoleparent());
		}
		//���µ����ݿ�
		iAotuRMService.update(spAotuspaceRole);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//��ɫ�˺Ź���
	public String roleUserEdit() throws Exception {
		//��ѯ��ɫ�����ҷ��ؽ�ɫ��ӵ�е�Ա��
		SpAotuspaceRole spAotuspaceRole = iAotuRMService.getById(model.getSpId());
		objectMapper.registerModule(new Hibernate4Module());
		Map<String, Object> spUserRoleJSONMap = new HashMap<String, Object>();
		spUserRoleJSONMap.put("spId", spAotuspaceRole.getSpId());
		spUserRoleJSONMap.put("spRolename", spAotuspaceRole.getSpRolename());
		Set<Map<String, Object>> userBinfoJSONSetMap = new HashSet<Map<String, Object>>();
		if (spAotuspaceRole.getSpUsersBinfos() != null) {
			for (SpUsersBinfo spUsersBinfo : spAotuspaceRole.getSpUsersBinfos()) {
				Map<String, Object> userBinfoMap = new HashMap<String, Object>();
				userBinfoMap.put("id", spUsersBinfo.getSpUsersBinfoKey().getSpId());//id
				userBinfoMap.put("account", spUsersBinfo.getSpAccount());//�û��� 
				userBinfoMap.put("realname", spUsersBinfo.getSpUsersDinfo().getSpRealname());//��ʵ����
				userBinfoJSONSetMap.add(userBinfoMap);
			}
		}
		spUserRoleJSONMap.put("userBinfos", userBinfoJSONSetMap);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		jsonResult.setData("[" + objectMapper.writeValueAsString(spUserRoleJSONMap) + "]");
		resp.getWriter().write("[" + objectMapper.writeValueAsString(jsonResult) + "]");
		return NONE;
	}
	
	//���Ա���б�
	public String userAll() throws Exception {
		//ȫ����û��ѡ���Ա��
		List<SpUsersBinfo> spUsersBinfos = iAotuUMService.findSpUsersBinfosNoRole(model.getSpId());
		Map<String, Object> spUserRoleJSONMap = new HashMap<String, Object>();
		if (spUsersBinfos != null) {
			Set<Map<String, Object>> userBinfoJSONSetMap = new HashSet<Map<String, Object>>();
			for (SpUsersBinfo spUsersBinfo : spUsersBinfos) {
				Map<String, Object> userBinfoMap = new HashMap<String, Object>();
				userBinfoMap.put("id", spUsersBinfo.getSpUsersBinfoKey().getSpId()+"-"+spUsersBinfo.getSpUsersBinfoKey().getSpAtuid());//id Ա�����
				userBinfoMap.put("account", spUsersBinfo.getSpAccount());//�û��� 
				userBinfoMap.put("realname", spUsersBinfo.getSpUsersDinfo().getSpRealname());//��ʵ����
				userBinfoJSONSetMap.add(userBinfoMap);
			}
			spUserRoleJSONMap.put("userBinfos", userBinfoJSONSetMap);
		}
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		jsonResult.setData("[" + objectMapper.writeValueAsString(spUserRoleJSONMap) + "]");
		resp.getWriter().write("[" + objectMapper.writeValueAsString(jsonResult) + "]");
		return NONE;
	}
	
	//��ӽ�ɫ�û�
	public String addRoleUser() throws Exception {
		SpAotuspaceRole spAotuspaceRole = iAotuRMService.getById(model.getSpId());
		Map<String, Object> spUserRoleJSONMap = new HashMap<String, Object>();
		if (userIds != null) {
			Set<Map<String, Object>> userBinfoJSONSetMap = new HashSet<Map<String, Object>>();
			for (SpUsersBinfoKey spUsersBinfoKey : userIds) {
				SpUsersBinfo spUsersBinfo = iAotuUMService.getByObject(spUsersBinfoKey);
				spAotuspaceRole.getSpUsersBinfos().add(spUsersBinfo);
				//�ش�json
				Map<String, Object> userBinfoMap = new HashMap<String, Object>();
				userBinfoMap.put("id", spUsersBinfo.getSpUsersBinfoKey().getSpId() + "-"
						+ spUsersBinfo.getSpUsersBinfoKey().getSpAtuid());//id ��͹id
				userBinfoMap.put("account", spUsersBinfo.getSpAccount());//�û��� 
				userBinfoMap.put("realname", spUsersBinfo.getSpUsersDinfo().getSpRealname());//��ʵ����
				userBinfoJSONSetMap.add(userBinfoMap);
			}
			spUserRoleJSONMap.put("userBinfos", userBinfoJSONSetMap);
			iAotuRMService.update(spAotuspaceRole);
		}
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		jsonResult.setData("[" + objectMapper.writeValueAsString(spUserRoleJSONMap) + "]");
		resp.getWriter().write("[" + objectMapper.writeValueAsString(jsonResult) + "]");
		return NONE;
	}
	
	//�Ƴ���ɫԱ��
	public String deleteRoleUser() throws Exception {
		//�����ɫ
		SpAotuspaceRole spAotuspaceRole = iAotuRMService.getById(model.getSpId());
		//Ҫ�Ƴ���ɫԱ����id
		if (userIds != null) {
			for (SpUsersBinfoKey key : userIds) {
				for (SpUsersBinfo spBinfo : spAotuspaceRole.getSpUsersBinfos()) {
					if (spBinfo.getSpUsersBinfoKey().getSpId().equals(key.getSpId())) {
						spAotuspaceRole.getSpUsersBinfos().remove(spBinfo);
						break;
					}
				}
			}
			iAotuRMService.update(spAotuspaceRole);
		}
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//��ս�ɫԱ��
	public String clearRoleUser() throws Exception {
		//�����ɫ
		SpAotuspaceRole spAotuspaceRole = iAotuRMService.getById(model.getSpId());
		spAotuspaceRole.setSpUsersBinfos(null);
		iAotuRMService.update(spAotuspaceRole);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
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
				if (selectedUserRoleId != null) {
					for (SpAotuspaceRole spAotuspaceRole : spAotuspacePriv.getSpUserRoles()) {
						if (selectedUserRoleId.equals(spAotuspaceRole.getSpId())) {
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
					if (selectedUserRoleId != null) {
						for (SpAotuspaceRole spAotuspaceRole : childrenPriv.getSpUserRoles()) {
							if (selectedUserRoleId.equals(spAotuspaceRole.getSpId())) {
								rowMap.put("checked", "true");
								break;
							}
						}
					}
				} else if (childrenPriv.getPrivsChildren().size() > 0) {
					List<Map<String, Object>> listChildren2Maps = new ArrayList<Map<String, Object>>();
					for (SpAotuspacePriv chilren2Priv : childrenPriv.getPrivsChildren()) {
						Map<String, Object> rowChilren2Map = new HashMap<String, Object>();
						rowChilren2Map.put("id", chilren2Priv.getSpId());
						rowChilren2Map.put("text", chilren2Priv.getSpName());
						if (selectedUserRoleId != null) {
							for (SpAotuspaceRole spAotuspaceRole : chilren2Priv.getSpUserRoles()) {
								if (selectedUserRoleId.equals(spAotuspaceRole.getSpId())) {
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

	//�û���ɫ-����Ȩ�ޣ�Ȩ�����ã�
	public String userRolePrivSet() throws Exception {
		SpAotuspaceRole spAotuspaceRole = iAotuRMService.getById(selectedUserRoleId);
		List<SpAotuspacePriv> spAotuspacePrivs = iAotuPMService.getByIds(privIds);
		spAotuspaceRole.setSpUsersRolePrivileges(new HashSet<SpAotuspacePriv>(spAotuspacePrivs));
		iAotuRMService.update(spAotuspaceRole);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	public List<SpUsersBinfoKey> getUserIds() {
		return userIds;
	}

	@JSON(serialize = true, deserialize = true)
	public void setUserIds(List<SpUsersBinfoKey> userIds) {
		this.userIds = userIds;
	}

	public Integer[] getPrivIds() {
		return privIds;
	}

	public void setPrivIds(Integer[] privIds) {
		this.privIds = privIds;
	}

	public Integer getSelectedUserRoleId() {
		return selectedUserRoleId;
	}

	public void setSelectedUserRoleId(Integer selectedUserRoleId) {
		this.selectedUserRoleId = selectedUserRoleId;
	}
	
	
}
