package com.aotuspace.aotucms.web.spsysmcenter.view.action;

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
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeBinfo;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeBinfoKey;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeStation;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 
 * Title:SysRMAction
 * Description:ϵͳ��ɫ����
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:18:20
 *
 */
@Controller
@Scope("prototype")
public class SysRMAction extends BaseAction<SpEmployeeStation> {
	
	//Ա��ids
	public List<SpEmployeeBinfoKey> empIds;
	
	//��ɫ�б�ҳ��
	public String list() throws Exception{
		return "list";
	}
	
	//��ɫ�б�
	public String listData() throws Exception{
	//��ѯ��ɫ�б�����
	PageBean<SpEmployeeStation>  spEmployeeStationList = iSysRMService.findSpEmployeeTopStationList(rows, page);
	//��װJson
	List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
	for (SpEmployeeStation  spEmployeeStation : spEmployeeStationList.getRecordList()) {
		List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String,Object>>();
		Map<String, Object> rowMap = new HashMap<String, Object>();
		rowMap.put("sp_id", spEmployeeStation.getSpId());
		rowMap.put("sp_EpStN", spEmployeeStation.getSpEpstn());
		for (SpEmployeeStation childrenStation : spEmployeeStation.getSpEpstchildren()) {
			Map<String, Object> rowChildrenMap = new HashMap<String, Object>();
			rowChildrenMap.put("sp_id", childrenStation.getSpId());
			rowChildrenMap.put("sp_EpStN", childrenStation.getSpEpstn());
			listChildrenMaps.add(rowChildrenMap);
		}
		rowMap.put("children", listChildrenMaps);
		listMaps.add(rowMap);
	}
	resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
	return NONE;
	}
	
	//������ɫcomboTreeData����
	public String parentTreeData() throws Exception{
		boolean flag = true;
		//��ѯ��ɫ�б�����
		List<SpEmployeeStation> spEmployeeStationList = iSysRMService.findSpEmployeeStationTreeData();
		//��װjson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
		for (SpEmployeeStation spEmployeeStation : spEmployeeStationList) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spEmployeeStation.getSpId());
			rowMap.put("text", spEmployeeStation.getSpEpstn());
			if(flag){
				//Ĭ��ѡ��
				Map<String, Object> defaultrowMap = new HashMap<String, Object>();
				defaultrowMap.put("id", 0);
				defaultrowMap.put("text", "��ѡ��������ɫ");
				listMaps.add(defaultrowMap);
				flag=false;
			}
			listMaps.add(rowMap);
		}
		//easyui total�����б�
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}
	
	// ��ɫ���
	public String add() throws Exception {
		if (model.getSpEpstparent().getSpId() == 0) {
			model.setSpEpstparent(null);
		}
		iSysRMService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	// �޸Ļ�������
	public String edit() throws Exception {
		// �����ݿ��ȡԴ����
		SpEmployeeStation spEmployeeStation = iSysRMService.getById(model
				.getSpId());
		// �������Ϊ�գ���
		if (spEmployeeStation != null) {
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
			jsonResult.setData("["+ objectMapper.writeValueAsString(spEmployeeStation) + "]");
		} else { // ����
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}
	
	//��ɫ�޸�
	public String editSubmit() throws Exception{
		//�����ݿ�ȡ������
		SpEmployeeStation spEmployeeStation = iSysRMService.getById(model.getSpId());
		//����Ҫ�޸ĵ�����
		spEmployeeStation.setSpEpstn(model.getSpEpstn());
		if(model.getSpEpstparent().getSpId()==0){
			spEmployeeStation.setSpEpstparent(null);
		}else{
			spEmployeeStation.setSpEpstparent(model.getSpEpstparent());
		}
		//���µ����ݿ�
		iSysRMService.update(spEmployeeStation);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	// ��ɫɾ��
	public String delete() throws Exception {
		iSysRMService.delete(model.getSpId());
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//��ɫԱ������
	public String roleEmplEdit() throws Exception{
		//System.out.println(model.getSpId());
		//��ѯ��ɫ�����ҷ��ؽ�ɫ��ӵ�е�Ա��
		SpEmployeeStation spEmployeeStation = iSysRMService.getById(model.getSpId());
		objectMapper.registerModule(new Hibernate4Module());
		Map<String, Object> spEmplStaJSONMap=new HashMap<String, Object>();
		spEmplStaJSONMap.put("spId", spEmployeeStation.getSpId());
		spEmplStaJSONMap.put("spEpstn", spEmployeeStation.getSpEpstn());
		Set<Map<String, Object>> emplBinfoJSONSetMap=new HashSet<Map<String,Object>>();
		if(spEmployeeStation.getSpEmployeeBinfos()!=null){
			for (SpEmployeeBinfo spEmployeeBinfo : spEmployeeStation.getSpEmployeeBinfos()) {
				Map<String, Object> emplBinfoMap=new HashMap<String, Object>();
				emplBinfoMap.put("id", spEmployeeBinfo.getSpEmployeeBinfoKey().getSpId()+"-"+ spEmployeeBinfo.getSpEmployeeBinfoKey().getSpEpid());//id Ա�����
				emplBinfoMap.put("account", spEmployeeBinfo.getSpEpaccount());//�û��� 
				emplBinfoMap.put("realname", spEmployeeBinfo.getSpEmployeeDinfo().getSpEprealname());//��ʵ����
				emplBinfoJSONSetMap.add(emplBinfoMap);
			}
		}
		spEmplStaJSONMap.put("employeeBinfos", emplBinfoJSONSetMap);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		jsonResult.setData("["+objectMapper.writeValueAsString(spEmplStaJSONMap)+"]");
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}
	
	//���Ա���б�
	public String emplAll() throws Exception{
		//ȫ����û��ѡ���Ա��
		List<SpEmployeeBinfo> spEmployeeBinfos=iSysMMService.findSpEmployeeBinfosNoStation(model.getSpId());
		Map<String, Object> spEmplStaJSONMap=new HashMap<String, Object>();
		if(spEmployeeBinfos!=null){
			Set<Map<String, Object>> emplBinfoJSONSetMap=new HashSet<Map<String,Object>>();
			for (SpEmployeeBinfo spEmployeeBinfo : spEmployeeBinfos) {
				Map<String, Object> emplBinfoMap=new HashMap<String, Object>();
				emplBinfoMap.put("id", spEmployeeBinfo.getSpEmployeeBinfoKey().getSpId()+"-"+ spEmployeeBinfo.getSpEmployeeBinfoKey().getSpEpid());//id Ա�����
				emplBinfoMap.put("account", spEmployeeBinfo.getSpEpaccount());//�û��� 
				emplBinfoMap.put("realname", spEmployeeBinfo.getSpEmployeeDinfo().getSpEprealname());//��ʵ����
				emplBinfoJSONSetMap.add(emplBinfoMap);
			}
			spEmplStaJSONMap.put("employeeBinfos", emplBinfoJSONSetMap);
		}
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		jsonResult.setData("["+objectMapper.writeValueAsString(spEmplStaJSONMap)+"]");
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}
	
	//�Ƴ���ɫԱ��
	public String deleteRoleEmpl() throws Exception{
		//�����ɫ
		SpEmployeeStation spEmployeeStation=iSysRMService.getById(model.getSpId());
		//Ҫ�Ƴ���ɫԱ����id
		if(empIds!=null){
			for(SpEmployeeBinfoKey key : empIds){
				for(SpEmployeeBinfo spBinfo : spEmployeeStation.getSpEmployeeBinfos()){
					if(spBinfo.getSpEmployeeBinfoKey().getSpId().equals(key.getSpId())){
						spEmployeeStation.getSpEmployeeBinfos().remove(spBinfo);
						break;
					}
				}
			}
			iSysRMService.update(spEmployeeStation);
		}
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//��ս�ɫԱ��
	public String clearRoleEmpl() throws Exception{
		//�����ɫ
		SpEmployeeStation spEmployeeStation=iSysRMService.getById(model.getSpId());
		spEmployeeStation.setSpEmployeeBinfos(null);
		iSysRMService.update(spEmployeeStation);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//��ӽ�ɫԱ��
	public String addRoleEmpl() throws Exception{
		SpEmployeeStation spEmployeeStation=iSysRMService.getById(model.getSpId());
		Map<String, Object> spEmplStaJSONMap=new HashMap<String, Object>();
		if(empIds!=null){
			Set<Map<String, Object>> emplBinfoJSONSetMap=new HashSet<Map<String,Object>>();
			for (SpEmployeeBinfoKey spEmployeeBinfoKey : empIds) {
				SpEmployeeBinfo spEmployeeBinfo=iSysMMService.getByObject(spEmployeeBinfoKey);
				spEmployeeStation.getSpEmployeeBinfos().add(spEmployeeBinfo);
				//�ش�json
				Map<String, Object> emplBinfoMap=new HashMap<String, Object>();
				emplBinfoMap.put("id", spEmployeeBinfo.getSpEmployeeBinfoKey().getSpId()+"-"+ spEmployeeBinfo.getSpEmployeeBinfoKey().getSpEpid());//id Ա�����
				emplBinfoMap.put("account", spEmployeeBinfo.getSpEpaccount());//�û��� 
				emplBinfoMap.put("realname", spEmployeeBinfo.getSpEmployeeDinfo().getSpEprealname());//��ʵ����
				emplBinfoJSONSetMap.add(emplBinfoMap);
			}
			spEmplStaJSONMap.put("employeeBinfos", emplBinfoJSONSetMap);
			iSysRMService.update(spEmployeeStation);
		}
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		jsonResult.setData("["+objectMapper.writeValueAsString(spEmplStaJSONMap)+"]");
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}
	private Integer[] spIds;

	public Integer[] getSpIds() {
		return spIds;
	}

	public void setSpIds(Integer[] spIds) {
		this.spIds = spIds;
	}
	
	public List<SpEmployeeBinfoKey> getEmpIds() {
		return empIds;
	}

	@JSON(serialize = true, deserialize = true)
	public void setEmpIds(List<SpEmployeeBinfoKey> empIds) {
		this.empIds = empIds;
	}
	
}
