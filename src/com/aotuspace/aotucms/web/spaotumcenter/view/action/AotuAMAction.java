package com.aotuspace.aotucms.web.spaotumcenter.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotucms.web.base.BaseAction;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAnchorBinfo;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersBinfo;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeePrivilege;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:AotuUMAction
 * Description:��͹�ռ�����������
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:17:02
 *
 */

@Controller
@Scope("prototype")
public class AotuAMAction extends BaseAction<SpAnchorBinfo> {

	//��͹id
	private int appAtuId;

	//��������ģ��ҳ��
	public String index() throws Exception {
		return "index";
	}

	//������������ģ�鹦���б�
	public String anchorM() throws Exception {
		//��ѯ���������������б�
		List<SpEmployeePrivilege> spEmployeePrivileges = iSysPMService.findSpEmployeePrivilegeByParentId(92);
		//��װjson
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeePrivilege spEmployeePrivilege : spEmployeePrivileges) {
			List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String, Object>>();
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

	//������������
	public String application() throws Exception {
		return "application";
	}

	//�������������û���Ϣ
	public String userbDetail() throws Exception {
		SpUsersBinfo spUsersBinfo = iAotuUMService.findBySpAtuId(appAtuId);
		ActionContext.getContext().put("spUser", spUsersBinfo);
		return "userDetail";
	}

	public int getAppAtuId() {
		return appAtuId;
	}

	public void setAppAtuId(int appAtuId) {
		this.appAtuId = appAtuId;
	}

}
