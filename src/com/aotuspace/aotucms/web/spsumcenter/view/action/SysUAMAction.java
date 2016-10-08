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
 * Description:供应商申请管理
 * Company:aotuspace
 * @author    sida
 * @date      2015-10-28 上午10:28:21
 *
 */
@Controller
@Scope("prototype")
@SuppressWarnings("unused")
public class SysUAMAction extends BaseAction<SpSupplierApplication> {

	//供应商详细ID
	private int appDetailId;
	
	//供应商申请ids
	private Integer[] appIds;
	
	//供应商模块页面	
	public String index() throws Exception{
		return "index";
	}
	
	//供应商功能模块列表
	public String anchorM() throws Exception{
		//查询供应商管理功能列表
		List<SpEmployeePrivilege> spEmployeePrivileges = iSysPMService.findSpEmployeePrivilegeByParentId(100);
		//封装json
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
		//easyui total总数列表
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}

	//供应商申请
	public String application() throws Exception{
		return "application";
	}
	
	//供应商申请列表
	public String applicationList() throws Exception{
		//查询申请供应商分页信息
		PageBean<SpSupplierApplication> spSupplierApplicationList = iSysUAMService .findSpSupplierApplicationList(rows,page);
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
		for (SpSupplierApplication spSupplierApplication : spSupplierApplicationList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spSupplierApplication.getSpId());
			rowMap.put("sp_SuSup", spSupplierApplication.getSpSusup());//商家名称
			rowMap.put("sp_SuCont", spSupplierApplication.getSpSucont());//商家联系人姓名
			rowMap.put("sp_SuTel", spSupplierApplication.getSpSutel());//商家联系电话
			rowMap.put("sp_SuMobie", spSupplierApplication.getSpSumobie());//商家手机号码
			rowMap.put("sp_SuDistrict", spSupplierApplication.getSpSudistrict());//商家所在地
			rowMap.put("sp_SuAddress", spSupplierApplication.getSpSuaddress());//商家详细地址
			rowMap.put("sp_SuTraId", spSupplierApplication.getSpSutraid());//商家所属行业ID
			listMaps.add(rowMap);
		}
		//easyui total 总数rows列表
		pageListMap.put("total", spSupplierApplicationList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}

	//供应商申请详细
	public String applicationDetail() throws Exception{
		SpSupplierApplication spSupplierApplication = iSysUAMService.getById(appDetailId);
		ActionContext.getContext().put("spSupplierApplication", spSupplierApplication);
		return "applicationDetail";
	}
	
	//删除供应商申请信息
	public String delete() throws Exception{
		iSysUAMService.delete(appIds);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
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
