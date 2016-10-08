package com.aotuspace.aotucms.web.spsysmcenter.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotucms.web.base.BaseAction;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeDepart;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 
 * Title:SysDMAction
 * Description:��֯��������
 * Company:aotuspace
 * @author    sida
 * @date      2015-9-28 ����4:29:55
 *
 */
@Controller
@Scope("prototype")
public class SysDMAction extends BaseAction<SpEmployeeDepart> {
	
	//�����б�ҳ��
	public String list() throws Exception {
		return "list";
	}
	
	//��֯�����б�
	public String listData() throws Exception {
		//��ѯ��֯�����б�����
		PageBean<SpEmployeeDepart> spEmployeeDepartList = iSysDMService.findSpEmployeeTopDepartList(rows, page);
		//��װjson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeeDepart spEmployeeDepart : spEmployeeDepartList.getRecordList()) {
			List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String, Object>>();
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spEmployeeDepart.getSpId());
			rowMap.put("sp_EpDepartN", spEmployeeDepart.getSpEpdepartn());
			for (SpEmployeeDepart childrenDepart : spEmployeeDepart.getSpEpdechildren()) {
				Map<String, Object> rowChildrenMap = new HashMap<String, Object>();
				rowChildrenMap.put("sp_id", childrenDepart.getSpId());
				rowChildrenMap.put("sp_EpDepartN", childrenDepart.getSpEpdepartn());
				listChildrenMaps.add(rowChildrenMap);
			}
			rowMap.put("children", listChildrenMaps);
			listMaps.add(rowMap);
		}
		//easyui total�����б�
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}
	
	//���ڵ㲿��comboTreeData����
	public String parentTreeData() throws Exception{
		boolean flag=true;
		//��ѯ��֯�����б�����
		List<SpEmployeeDepart> spEmployeeDepartList = iSysDMService.findSpEmployeeDepartTreeData();
		//��װjson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeeDepart spEmployeeDepart : spEmployeeDepartList) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spEmployeeDepart.getSpId());
			rowMap.put("text", spEmployeeDepart.getSpEpdepartn());
			if(flag){
				//Ĭ��ѡ��
				Map<String, Object> defaultrowMap = new HashMap<String, Object>();
				defaultrowMap.put("id", 0);
				defaultrowMap.put("text", "��ѡ��������֯����");
				listMaps.add(defaultrowMap);
				flag=false;
			}
			listMaps.add(rowMap);
		}
		//easyui total�����б�
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}
	
	//��֯�������
	public String add() throws Exception {
		if(model.getSpEpdeparent().getSpId()==0){
			model.setSpEpdeparent(null);
		}
		iSysDMService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//�޸Ļ�������
	public String edit() throws Exception {
		//�����ݿ��ȡԴ����
		SpEmployeeDepart spEmployeeDepart = iSysDMService.getById(model.getSpId());
		if(spEmployeeDepart!=null){
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
			jsonResult.setData("["+objectMapper.writeValueAsString(spEmployeeDepart)+"]");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}
	
	//��֯�����޸�
	public String editSubmit() throws Exception{
		//�����ݿ��ȡԴ����
		SpEmployeeDepart spEmployeeDepart = iSysDMService.getById(model.getSpId());
		//����Ҫ�޸ĵ�����
		spEmployeeDepart.setSpEpdepartn(model.getSpEpdepartn());
		if(model.getSpEpdeparent().getSpId()==0){
			spEmployeeDepart.setSpEpdeparent(null);
		}else{
			spEmployeeDepart.setSpEpdeparent(model.getSpEpdeparent());
		}
		//���µ����ݿ�
		iSysDMService.update(spEmployeeDepart);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//��֯����ɾ��
	public String delete() throws Exception {
		iSysDMService.delete(model.getSpId());
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	private Integer[] spIds;

	public Integer[] getSpIds() {
		return spIds;
	}

	public void setSpIds(Integer[] spIds) {
		this.spIds = spIds;
	}
}
