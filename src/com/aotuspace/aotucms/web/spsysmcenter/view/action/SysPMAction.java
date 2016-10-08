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
 * Description:系统权限管理
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:19:52
 *
 */

@Controller
@Scope("prototype")
public class SysPMAction extends BaseAction<SpEmployeePrivilege> {
	
	//权限管理列表页面
	public String list() throws Exception {
		return "list";
	}
	
	//权限列表数据
	public String listData() throws Exception {
		//查询角色列表数据
		PageBean<SpEmployeePrivilege> spEmployeePrivilegeList = iSysPMService.findSpemployeeTopPrivilegeList(rows,
				page);
		//封装Json
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeePrivilege spEmployeePrivilege : spEmployeePrivilegeList.getRecordList()) {//一级权限（菜单分类级别）
			List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String, Object>>();
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spEmployeePrivilege.getSpId());
			rowMap.put("sp_EpName", spEmployeePrivilege.getSpEpname());
			rowMap.put("sp_EpUrl", spEmployeePrivilege.getSpEpurl());
			rowMap.put("sp_IconCls", spEmployeePrivilege.getSpIconcls());
			if(spEmployeePrivilege.getSpEpchildren().size()>0){
				for (SpEmployeePrivilege childrenPrivilege : spEmployeePrivilege.getSpEpchildren()) {//二级权限（菜单级别）
					List<Map<String, Object>> listChildren2Maps = new ArrayList<Map<String, Object>>();
					Map<String, Object> rowChildrenMap = new HashMap<String, Object>();
					rowChildrenMap.put("sp_id", childrenPrivilege.getSpId());
					rowChildrenMap.put("sp_EpName", childrenPrivilege.getSpEpname());
					rowChildrenMap.put("sp_EpUrl", childrenPrivilege.getSpEpurl());
					rowChildrenMap.put("sp_IconCls", spEmployeePrivilege.getSpIconcls());
					if(childrenPrivilege.getSpEpchildren().size()>0){
						for (SpEmployeePrivilege children2Privilege : childrenPrivilege.getSpEpchildren()) {//三级权限（按钮级别）
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
	
	//父节点权限comboTreeData数据
	public String parentTreeData() throws Exception {
		boolean flag = true;
		//查询权限列表数据
		List<SpEmployeePrivilege> spEmployeePrivilegeList = iSysPMService.findSpEmployeePrivilegeTreeData();
		//封装json
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeePrivilege spEmployeePrivilege : spEmployeePrivilegeList) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spEmployeePrivilege.getSpId());
			rowMap.put("text", spEmployeePrivilege.getSpEpname());
			if (flag) {
				//默认选项
				Map<String, Object> defaultrowMap = new HashMap<String, Object>();
				defaultrowMap.put("id", 0);
				defaultrowMap.put("text", "请选择所属上级权限");
				listMaps.add(defaultrowMap);
				flag = false;
			}
			listMaps.add(rowMap);
		}
		//easyui total总数列表
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}

	//操作权限添加
	public String add() throws Exception {
		if (model.getSpEpparent().getSpId() == 0) {
			model.setSpEpparent(null);
		}
		iSysPMService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//操作权限修改回显
	public String edit() throws Exception {
		//从数据库取出源对象
		SpEmployeePrivilege spEmployeePrivilege = iSysPMService.getById(model.getSpId());
		//非空
		if (spEmployeePrivilege != null) {
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
			jsonResult.setData("[" + objectMapper.writeValueAsString(spEmployeePrivilege) + "]");
		} else {
			//空
			jsonResult.setCode(10001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write("[" + objectMapper.writeValueAsString(jsonResult) + "]");
		return NONE;
	}

	//操作权限修改
	public String editSubmit() throws Exception {
		//从数据库取出源对象
		SpEmployeePrivilege spEmployeePrivilege = iSysPMService.getById(model.getSpId());
		//设置要修改的属性
		spEmployeePrivilege.setSpEpname(model.getSpEpname());
		spEmployeePrivilege.setSpEpurl(model.getSpEpurl());
		if (model.getSpEpparent().getSpId() == 0) {
			spEmployeePrivilege.setSpEpparent(null);
			spEmployeePrivilege.setSpEpurl(null);
		} else {
			spEmployeePrivilege.setSpEpparent(model.getSpEpparent());
			spEmployeePrivilege.setSpEpurl(model.getSpEpurl());
		}
		//更新到数据库
		iSysPMService.update(spEmployeePrivilege);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//操作权限删除
	public String delete() throws Exception {
		iSysPMService.delete(model.getSpId());
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
}
