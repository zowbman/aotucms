package com.aotuspace.aotucms.web.spaotumcenter.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotucms.web.base.BaseAction;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAotuspacePriv;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 
 * Title:SysPMAction
 * Description:��͹�ռ�Ȩ�޹���
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:19:52
 *
 */

@Controller
@Scope("prototype")
public class AotuPMAction extends BaseAction<SpAotuspacePriv> {
	
	//Ȩ�޹����б�ҳ��
	public String list() throws Exception {
		return "list";
	}
	
	//Ȩ���б�����
	public String listData() throws Exception {
		//��ѯ��ɫ�б�����
		PageBean<SpAotuspacePriv> spAotuspacePrivList = iAotuPMService.findSpAotuspaceTopPrivList(rows,page);
		//��װJson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuspacePriv spAotuspacePriv : spAotuspacePrivList.getRecordList()) {//һ��Ȩ�ޣ��˵����༶��
			List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String, Object>>();
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spAotuspacePriv.getSpId());
			rowMap.put("sp_Name", spAotuspacePriv.getSpName());
			rowMap.put("sp_Url", spAotuspacePriv.getSpUrl());
			if(spAotuspacePriv.getPrivsChildren().size()>0){
				for (SpAotuspacePriv childrenPriv : spAotuspacePriv.getPrivsChildren()) {//����Ȩ�ޣ��˵�����
					List<Map<String, Object>> listChildren2Maps = new ArrayList<Map<String, Object>>();
					Map<String, Object> rowChildrenMap = new HashMap<String, Object>();
					rowChildrenMap.put("sp_id", childrenPriv.getSpId());
					rowChildrenMap.put("sp_Name", childrenPriv.getSpName());
					rowChildrenMap.put("sp_Url", childrenPriv.getSpUrl());
					if(childrenPriv.getPrivsChildren().size()>0){
						for (SpAotuspacePriv children2Priv : childrenPriv.getPrivsChildren()) {//����Ȩ�ޣ���ť����
							Map<String, Object> rowChildren2Map = new HashMap<String, Object>();
							rowChildren2Map.put("sp_id", children2Priv.getSpId());
							rowChildren2Map.put("sp_Name", children2Priv.getSpName());
							rowChildren2Map.put("sp_Url", children2Priv.getSpUrl());
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
		//��ѯȨ���б�����
		List<SpAotuspacePriv> spAotuspacePrivList = iAotuPMService.findSpAotuspacePrivComboTreeData();
		//��װjson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		Map<String, Object> rowMap;
		//Ĭ��ѡ��
		rowMap = new HashMap<String, Object>();
		rowMap.put("id", 0);
		rowMap.put("text", "��ѡ�������ϼ�Ȩ��");
		listMaps.add(rowMap);
		for (SpAotuspacePriv spAotuspacePriv : spAotuspacePrivList) {
			rowMap = new HashMap<String, Object>();
			rowMap.put("id", spAotuspacePriv.getSpId());
			rowMap.put("text", spAotuspacePriv.getSpName());
			listMaps.add(rowMap);
		}
		
		//easyui total�����б�
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}

	//����Ȩ�����
	public String add() throws Exception {
		if (model.getPrivParent().getSpId() == 0) {
			model.setPrivParent(null);
		}
		iAotuPMService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//����Ȩ���޸Ļ���
	@SuppressWarnings("unused")
	public String edit() throws Exception {
		//�����ݿ�ȡ��Դ����
		SpAotuspacePriv spAotuspacePriv = iAotuPMService.getById(model.getSpId());
		//�ǿ�
		if (spAotuspacePriv != null) {
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
			jsonResult.setData("[" + objectMapper.writeValueAsString(spAotuspacePriv) + "]");
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
		SpAotuspacePriv spAotuspacePriv = iAotuPMService.getById(model.getSpId());
		//����Ҫ�޸ĵ�����
		spAotuspacePriv.setSpName(model.getSpName());
		spAotuspacePriv.setSpUrl(model.getSpUrl());
		if (model.getPrivParent().getSpId() == 0) {
			spAotuspacePriv.setPrivParent(null);
			spAotuspacePriv.setSpUrl(null);
		} else {
			spAotuspacePriv.setPrivParent(model.getPrivParent());
			spAotuspacePriv.setSpUrl(model.getSpUrl());
		}
		//���µ����ݿ�
		iAotuPMService.update(spAotuspacePriv);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//����Ȩ��ɾ��
	public String delete() throws Exception {
		iAotuPMService.delete(model.getSpId());
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
}
