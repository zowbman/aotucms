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
 * Description:凹凸空间代言主播入口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:17:02
 *
 */

@Controller
@Scope("prototype")
public class AotuAMAction extends BaseAction<SpAnchorBinfo> {

	//凹凸id
	private int appAtuId;

	//代言主播模块页面
	public String index() throws Exception {
		return "index";
	}

	//代言主播功能模块功能列表
	public String anchorM() throws Exception {
		//查询代言主播管理功能列表
		List<SpEmployeePrivilege> spEmployeePrivileges = iSysPMService.findSpEmployeePrivilegeByParentId(92);
		//封装json
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
		//easyui total总数列表
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}

	//代言主播申请
	public String application() throws Exception {
		return "application";
	}

	//代言主播申请用户信息
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
