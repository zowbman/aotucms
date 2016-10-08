package com.aotuspace.aotucms.web.spsysmcenter.view.action;

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
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeBinfo;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeBinfoKey;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeDepart;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeDinfo;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeStation;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:SysMMAction
 * Description:ϵͳ����Ա����
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:17:02
 *
 */

@Controller
@Scope("prototype")
public class SysMMAction extends BaseAction<SpEmployeeBinfo> {
	
	//��λѡ��Ids
	public Integer[] stationsIds;
	
	//��֯����Ids
	public Integer[] departIds;
	
	//Ա��ids
	public List<SpEmployeeBinfoKey> empIds;
	
	//��¼����
	public String login() throws Exception {
		//�����ݿ��������û�������
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.findByEpAccountAndEpPassword(model.getSpEpaccount(),
				model.getSpEppassword());
		if (spEmployeeBinfo == null) {//��¼ʧ��
			jsonResult.setCode(100001);//code
			jsonResult.setMsg("����ʧ��");//msg
			jsonResult.setData(null);//data
		}else{
			//��¼�ɹ�
			//����Session
			ActionContext.getContext().getSession().put("spEmployeeBinfo", spEmployeeBinfo);
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
			jsonResult.setData(null);
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//ע��
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("spEmployeeBInfo");
		return NONE;
	}
	
	//Ա����Ϣ�б�ҳ��
	public String list() throws Exception {
		return "list";
	}
	
	//Ա����Ϣdata
	public String listData() throws Exception {
		PageBean<SpEmployeeBinfo> spEmployeeBinfoList;
		if(model.getSpEmployeeDepart()!=null && model.getSpEmployeeDepart().getSpId()!=0){
			//��ѯ��֯�������ӽڵ�
			SpEmployeeDepart spEmployeeDepart=iSysDMService.getById(model.getSpEmployeeDepart().getSpId());
			if(spEmployeeDepart.getSpEpdechildren()!=null&&spEmployeeDepart.getSpEpdechildren().size()>0){//����Ǹ�
				departIds=new Integer[spEmployeeDepart.getSpEpdechildren().size()+1];
				int index = 0;
				for (SpEmployeeDepart childrenDepart : spEmployeeDepart.getSpEpdechildren()) {
					departIds[index++] = childrenDepart.getSpId();
				}
				departIds[index++]=model.getSpEmployeeDepart().getSpId();
			}else{
				int index = 0;
				departIds=new Integer[spEmployeeDepart.getSpEpdechildren().size()+1];
				departIds[index++] = model.getSpEmployeeDepart().getSpId();
			}
			spEmployeeBinfoList = iSysMMService
					.findSpEmployeeBinfoListByDepart(rows, page, departIds);
		}else{
			//��ѯ����Ա�б�����
			spEmployeeBinfoList = iSysMMService.findSpEmployeeBinfoList(rows, page);
		}
		
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeeBinfo spEmployeeBinfo : spEmployeeBinfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spEmployeeBinfo.getSpEmployeeBinfoKey().getSpId());
			rowMap.put("sp_EpId", spEmployeeBinfo.getSpEmployeeBinfoKey().getSpEpid());
			rowMap.put("sp_EpAccount", spEmployeeBinfo.getSpEpaccount());
			if(spEmployeeBinfo.getSpEmployeeDepart()!=null){
				rowMap.put("sp_EpDepart", spEmployeeBinfo.getSpEmployeeDepart().getSpEpdepartn());
			}
			rowMap.put("gender", "1993-11-17 12:12:00");
			listMaps.add(rowMap);
		}
		//easyui total ����  rows�б�
		pageListMap.put("total", spEmployeeBinfoList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	
	//Ա����Ϣ��ϸ
	public String detail() throws Exception {
		//System.out.println(model.getSpEmployeeBinfoKey().getSpId()+"---"+model.getSpEmployeeBinfoKey().getSpEpid());
		//��ѯԱ����Ϣ��ϸ
		SpEmployeeBinfo spEmployeeBinfoDetail = iSysMMService.getByObject(model
				.getSpEmployeeBinfoKey());
		ActionContext.getContext().put("spEmployeeBinfoDetail", spEmployeeBinfoDetail);
		return "detail";
	}

	//����û����Ƿ��ظ�
	public String checkSpEpAccount() throws Exception{
		System.out.println(model.getSpEpaccount());
		//�������˲�ѯ�û����Ƿ����
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.findBySpEpAccount(model.getSpEpaccount());
		if(spEmployeeBinfo==null){
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//���Ա��
	public String add() throws Exception {
		//����û���û���ظ����򱣴浽���ݿ�
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.findBySpEpAccount(model.getSpEpaccount());
		if (spEmployeeBinfo == null) {
			//1:������Epid�鴦(service�ӿ�ʵ���࣬����Ա����Ϣ��epid���н��򣬲��һ�ȡһ��select top 1 from �� order by desc <-- ��һ�����󣬶�����һ��list)
			Integer maxEpId = iSysMMService.findByMaxEpId();
			SpEmployeeBinfoKey newKey=new SpEmployeeBinfoKey();
			if (maxEpId == null) {
				newKey.setSpEpid(100000);
				//spEmployeeBinfo.setSpEmployeeBinfoKey(newKey);
			}else{
				newKey.setSpEpid(maxEpId + 1);
			}
			model.setSpEmployeeBinfoKey(newKey);
			//��ϸ��Ϣ
			SpEmployeeDinfo spEmployeeDinfo=model.getSpEmployeeDinfo();
			spEmployeeDinfo.setSpEmployeeBinfoKey(newKey);
			model.setSpEmployeeDinfo(spEmployeeDinfo);
			//��ȡ��֯��������
			if(model.getSpEmployeeDepart().getSpId()==0){
				model.setSpEmployeeDepart(null);
			}
			//2:���ӵ����ݿ�
			iSysMMService.save(model);
			//�ɹ�
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");	
		} else {//����û����ظ�������ʾ��������
			//ʧ��
			jsonResult.setCode(100001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//ɾ��Ա��
	public String delete() throws Exception {
		iSysMMService.deleteByList(empIds);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//����Ա��ҳ��
	public String edit() throws Exception{
		//��ȡԭ�������
		SpEmployeeBinfo spEmployeeBinfo=iSysMMService.getByObject(model.getSpEmployeeBinfoKey());
		if(spEmployeeBinfo!=null){
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
			jsonResult.setData("["+objectMapper.writeValueAsString(spEmployeeBinfo)+"]");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}

	//����Ա��
	public String editSubmit() throws Exception {
		//�����ݿ��ȡԴ����
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.getByObject(model.getSpEmployeeBinfoKey());
		if(spEmployeeBinfo!=null){
			//����Ҫ�޸ĵ�����
			spEmployeeBinfo.setSpEppassword(model.getSpEppassword());
			//��ϸ��Ϣ
			spEmployeeBinfo.getSpEmployeeDinfo().setSpEprealname(model.getSpEmployeeDinfo().getSpEprealname());;
			//��֯����
			SpEmployeeDepart spEmployeeDepart=iSysDMService.getById(model.getSpEmployeeDepart().getSpId());
			spEmployeeBinfo.setSpEmployeeDepart(spEmployeeDepart);
			//���µ����ݿ�
			iSysMMService.update(spEmployeeBinfo);
			jsonResult.setCode(0);
			jsonResult.setMsg("����ɹ�");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("����ʧ��");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//��ɫ����(��λ����)
	public String editPrivilege() throws Exception{
		//�����ݿ�ȡ��Դ����
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.getByObject(model);
		//���ù����ĸ�λ����
		List<SpEmployeeStation> spemployeestationList = iSysRMService.getByIds(stationsIds);
		spEmployeeBinfo.setSpEmployeeStations(new HashSet<SpEmployeeStation>(spemployeestationList));
		//���µ����ݿ�
		iSysMMService.update(spEmployeeBinfo);
		return NONE;
	}
	
	//Ա����֯��������tree
	public String departTree()throws Exception{
		//��ѯ��֯��������
		List<SpEmployeeDepart> spEmployeeDepartList = iSysDMService.findSpEmployeeDepartTreeData();
		//��װjson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeeDepart spEmployeeDepart : spEmployeeDepartList) {
			List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String, Object>>();
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spEmployeeDepart.getSpId());
			rowMap.put("text", spEmployeeDepart.getSpEpdepartn());
			for (SpEmployeeDepart childrenDepart : spEmployeeDepart.getSpEpdechildren()) {
				Map<String, Object> rowChildrenMap = new HashMap<String, Object>();
				rowChildrenMap.put("id", childrenDepart.getSpId());
				rowChildrenMap.put("text", childrenDepart.getSpEpdepartn());
				listChildrenMaps.add(rowChildrenMap);
			}
			rowMap.put("children", listChildrenMaps);
			listMaps.add(rowMap);
		}
		List<Map<String, Object>> allListMaps = new ArrayList<Map<String, Object>>();
		Map<String, Object> allMap = new HashMap<String, Object>();
		allMap.put("id", 0);
		allMap.put("text", "��͹�ռ�");
		allMap.put("checked", true);
		allMap.put("children", listMaps);
		allListMaps.add(allMap);
		//easyui total�����б�
		resp.getWriter().write(objectMapper.writeValueAsString(allListMaps));
		return NONE;
	}
	
	//Ա����֯����treeData����ӡ��༭combotree��
	public String departTreeData() throws Exception {
		boolean flag=true;
		//��ѯ�����б�����
		List<SpEmployeeDepart> spEmployeeDepartList = iSysDMService.findSpEmployeeDepartTreeData();
		//��װjson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeeDepart spEmployeeDepart : spEmployeeDepartList) {
			List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String, Object>>();
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spEmployeeDepart.getSpId());
			rowMap.put("text", spEmployeeDepart.getSpEpdepartn());
			for (SpEmployeeDepart childrenDepart : spEmployeeDepart.getSpEpdechildren()) {
				Map<String, Object> rowChildrenMap = new HashMap<String, Object>();
				rowChildrenMap.put("id", childrenDepart.getSpId());
				rowChildrenMap.put("text", childrenDepart.getSpEpdepartn());
				listChildrenMaps.add(rowChildrenMap);
			}
			if(flag){
				//Ĭ��ѡ��
				Map<String, Object> defaultrowMap = new HashMap<String, Object>();
				defaultrowMap.put("id", 0);
				defaultrowMap.put("text", "��ѡ��������֯����");
				listMaps.add(defaultrowMap);
				flag=false;
			}
			rowMap.put("children", listChildrenMaps);
			listMaps.add(rowMap);
		}
		//easyui total�����б�
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}

	//getter��setter
	public Integer[] getStationsIds() {
		return stationsIds;
	}

	public void setStationsIds(Integer[] stationsIds) {
		this.stationsIds = stationsIds;
	}

	public Integer[] getDepartIds() {
		return departIds;
	}

	public void setDepartIds(Integer[] departIds) {
		this.departIds = departIds;
	}
	

	public List<SpEmployeeBinfoKey> getEmpIds() {
		return empIds;
	}
	
	@JSON(serialize = true, deserialize = true)
	public void setEmpIds(List<SpEmployeeBinfoKey> empIds) {
		this.empIds = empIds;
	}

	
}
