package com.aotuspace.aotucms.web.spsumcenter.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.BaseAction;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsumcenter.hbm.SpSupplierApplication;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeePrivilege;
import com.aotuspace.aotucms.web.spsysmcenter.service.ISysPMService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:SysUAMAction
 * Description:��Ӧ���������
 * Company:aotuspace
 * @author    sida
 * @date      2015-10-28 ����10:28:21
 *
 */
@Controller
@Scope("prototype")
@SuppressWarnings("unused")
public class SysUAMAction extends BaseAction<SpSupplierApplication> {

	//��Ӧ����ϸID
	private int appDetailId;
	
	//��Ӧ������ids
	private Integer[] appIds;
	
	//��Ӧ��ģ��ҳ��	
	public String index() throws Exception{
		return "index";
	}
	
	//��Ӧ�̹���ģ���б�
	public String anchorM() throws Exception{
		//��ѯ��Ӧ�̹������б�
		List<SpEmployeePrivilege> spEmployeePrivileges = iSysPMService.findSpEmployeePrivilegeByParentId(100);
		//��װjson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
		for (SpEmployeePrivilege spEmployeePrivilege : spEmployeePrivileges) {
			List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String,Object>>();
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spEmployeePrivilege.getSpId());
			rowMap.put("text", spEmployeePrivilege.getSpEpname());
			rowMap.put("url", spEmployeePrivilege.getSpEpurl());
			for (SpEmployeePrivilege childrenspEmployeePrivilege : spEmployeePrivilege.getSpEpchildren()) {
				Map<String, Object> rowChildrenMap = new HashMap<String, Object>();
				rowChildrenMap.put("id", childrenspEmployeePrivilege.getSpId());
				rowChildrenMap.put("text", childrenspEmployeePrivilege.getSpEpname());
				rowChildrenMap.put("url", childrenspEmployeePrivilege.getSpEpurl());
				listChildrenMaps.add(rowChildrenMap);
			}
			rowMap.put("children", listChildrenMaps);
			listMaps.add(rowMap);
		}
		//easyui total�����б�
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}

	//��Ӧ������
	public String application() throws Exception{
		return "application";
	}
	
	//��Ӧ�������б�
	public String applicationList() throws Exception{
		//��ѯ���빩Ӧ�̷�ҳ��Ϣ
		PageBean<SpSupplierApplication> spSupplierApplicationList = iSysUAMService .findSpSupplierApplicationList(rows,page);
		//��װjson
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
		for (SpSupplierApplication spSupplierApplication : spSupplierApplicationList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spSupplierApplication.getSpId());
			rowMap.put("sp_SuSup", spSupplierApplication.getSpSusup());//�̼�����
			rowMap.put("sp_SuCont", spSupplierApplication.getSpSucont());//�̼���ϵ������
			rowMap.put("sp_SuTel", spSupplierApplication.getSpSutel());//�̼���ϵ�绰
			rowMap.put("sp_SuMobie", spSupplierApplication.getSpSumobie());//�̼��ֻ�����
			rowMap.put("sp_SuDistrict", spSupplierApplication.getSpSudistrict());//�̼����ڵ�
			rowMap.put("sp_SuAddress", spSupplierApplication.getSpSuaddress());//�̼���ϸ��ַ
			rowMap.put("sp_SuTraId", spSupplierApplication.getSpSutraid());//�̼�������ҵID
			listMaps.add(rowMap);
		}
		//easyui total ����rows�б�
		pageListMap.put("total", spSupplierApplicationList.getPageCount());//������
		pageListMap.put("rows", listMaps);//�б�
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//��Ӧ��������ϸ
	public String applicationDetail() throws Exception{
		SpSupplierApplication spSupplierApplication = iSysUAMService.getById(appDetailId);
		ActionContext.getContext().put("spSupplierApplication", spSupplierApplication);
		return "applicationDetail";
	}
	
	//ɾ����Ӧ��������Ϣ
	public String delete() throws Exception{
		iSysUAMService.delete(appIds);
		jsonResult.setCode(0);
		jsonResult.setMsg("����ɹ�");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//-------------------------------------
	public Integer[] getAppIds() {
		return appIds;
	}
	@JSON(serialize=true, deserialize=true)
	public void setAppIds(Integer[] appIds) {
		this.appIds = appIds;
	}

	public int getAppDetailId() {
		return appDetailId;
	}

	public void setAppDetailId(int appDetailId) {
		this.appDetailId = appDetailId;
	}
	
	
}
