package com.aotuspace.aotucms.web.spsysmcenter.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotucms.web.base.BaseAction;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeePrivilege;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 
 * Title:SysPMAction
 * Description:ϵͳȨ�޹���
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:19:52
 *
 */

@Controller
@Scope("prototype")
public class SysPMAction extends BaseAction<SpEmployeePrivilege> {
	
	//Ȩ�޹����б�ҳ��
	public String list() throws Exception {
		return "list";
	}
	
	//Ȩ���б�����
	public String listData() throws Exception {
		//��ѯ��ɫ�б�����
		PageBean<SpEmployeePrivilege> spEmployeePrivilegeList = iSysPMService.findSpemployeeTopPrivilegeList(rows,
				page);
		//��װJson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeePrivilege spEmployeePrivilege : spEmployeePrivilegeList.getRecordList()) {//һ��Ȩ�ޣ��˵����༶��
			List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String, Object>>();
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spEmployeePrivilege.getSpId());
			rowMap.put("sp_EpName", spEmployeePrivilege.getSpEpname());
			rowMap.put("sp_EpUrl", spEmployeePrivilege.getSpEpurl());
			rowMap.put("sp_IconCls", spEmployeePrivilege.getSpIconcls());
			if(spEmployeePrivilege.getSpEpchildren().size()>0){
				for (SpEmployeePrivilege childrenPrivilege : spEmployeePrivilege.getSpEpchildren()) {//����Ȩ�ޣ��˵�����
					List<Map<String, Object>> listChildren2Maps = new ArrayList<Map<String, Object>>();
					Map<String, Object> rowChildrenMap = new HashMap<String, Object>();
					rowChildrenMap.put("sp_id", childrenPrivilege.getSpId());
					rowChildrenMap.put("sp_EpName", childrenPrivilege.getSpEpname());
					rowChildrenMap.put("sp_EpUrl", childrenPrivilege.getSpEpurl());
					rowChildrenMap.put("sp_IconCls", spEmployeePrivilege.getSpIconcls());
					if(childrenPrivilege.getSpEpchildren().size()>0){
						for (SpEmployeePrivilege children2Privilege : childrenPrivilege.getSpEpchildren()) {//����Ȩ�ޣ���ť����
							Map<String, Object> rowChildren2Map = new HashMap<String, Object>();
							rowChildren2Map.put("sp_id", children2Privilege.getSpId());
							rowChildren2Map.put("sp_EpName", children2Privilege.getSpEpname());
							rowChildren2Map.put("sp_EpUrl", children2Privilege.getSpEpurl());
							rowChildren2Map.put("sp_IconCls", children2Privilege.getSpIconcls());
							listChildren2Maps.add(rowChildren2Map);
						}
						rowChildrenMap.put("children", listChildren2Maps);
					}
					listChildrenMaps.add(rowChildrenMap);
				}
			}
			rowMap.put("children", listChildrenMaps);
			listMaps.add(rowMap);
		}
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}
	
	//���ڵ�Ȩ��comboTreeData����
	public String parentTreeData() throws Exception {
		boolean flag = true;
		//��ѯȨ���б�����
		List<SpEmployeePrivilege> spEmployeePrivilegeList = iSysPMService.findSpEmployeePrivilegeTreeData();
		//��װjson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeePrivilege spEmployeePrivilege : spEmployeePrivilegeList) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spEmployeePrivilege.getSpId());
			rowMap.put("text", spEmployeePrivilege.getSpEpname());
			if (flag) {
				//Ĭ��ѡ��
				Map<String, Object> defaultrowMap = new HashMap<String, Object>();
				defaultrowMap.put("id", 0);
				defaultrowMap.put("text", "��ѡ�������ϼ�Ȩ��");
				listMaps.add(defaultrowMap);
				flag = false;
			}
			listMaps.add(rowMap);
		}
		//easyui total�����б�
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}

	//����Ȩ�����
	public String add() throws Exception {
		if (model.getSpEpparent().getSpId() == 0) {
			model.setSpEpparent(null);
		}
		iSysPMService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//����Ȩ���޸Ļ���
	public String edit() throws Exception {
		//�����ݿ�ȡ��Դ����
		SpEmployeePrivilege spEmployeePrivilege = iSysPMService.getById(model.getSpId());
		//�ǿ�
		if (spEmployeePrivilege != null) {
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
			jsonResult.setData("[" + objectMapper.writeValueAsString(spEmployeePrivilege) + "]");
		} else {
			//��
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write("[" + objectMapper.writeValueAsString(jsonResult) + "]");
		return NONE;
	}

	//����Ȩ���޸�
	public String editSubmit() throws Exception {
		//�����ݿ�ȡ��Դ����
		SpEmployeePrivilege spEmployeePrivilege = iSysPMService.getById(model.getSpId());
		//����Ҫ�޸ĵ�����
		spEmployeePrivilege.setSpEpname(model.getSpEpname());
		spEmployeePrivilege.setSpEpurl(model.getSpEpurl());
		if (model.getSpEpparent().getSpId() == 0) {
			spEmployeePrivilege.setSpEpparent(null);
			spEmployeePrivilege.setSpEpurl(null);
		} else {
			spEmployeePrivilege.setSpEpparent(model.getSpEpparent());
			spEmployeePrivilege.setSpEpurl(model.getSpEpurl());
		}
		//���µ����ݿ�
		iSysPMService.update(spEmployeePrivilege);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//����Ȩ��ɾ��
	public String delete() throws Exception {
		iSysPMService.delete(model.getSpId());
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
}
