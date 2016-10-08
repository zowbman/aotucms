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
 * Description:凹凸空间权限管理
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:19:52
 *
 */

@Controller
@Scope("prototype")
public class AotuPMAction extends BaseAction<SpAotuspacePriv> {
	
	//权限管理列表页面
	public String list() throws Exception {
		return "list";
	}
	
	//权限列表数据
	public String listData() throws Exception {
		//查询角色列表数据
		PageBean<SpAotuspacePriv> spAotuspacePrivList = iAotuPMService.findSpAotuspaceTopPrivList(rows,page);
		//封装Json
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuspacePriv spAotuspacePriv : spAotuspacePrivList.getRecordList()) {//一级权限（菜单分类级别）
			List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String, Object>>();
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spAotuspacePriv.getSpId());
			rowMap.put("sp_Name", spAotuspacePriv.getSpName());
			rowMap.put("sp_Url", spAotuspacePriv.getSpUrl());
			if(spAotuspacePriv.getPrivsChildren().size()>0){
				for (SpAotuspacePriv childrenPriv : spAotuspacePriv.getPrivsChildren()) {//二级权限（菜单级别）
					List<Map<String, Object>> listChildren2Maps = new ArrayList<Map<String, Object>>();
					Map<String, Object> rowChildrenMap = new HashMap<String, Object>();
					rowChildrenMap.put("sp_id", childrenPriv.getSpId());
					rowChildrenMap.put("sp_Name", childrenPriv.getSpName());
					rowChildrenMap.put("sp_Url", childrenPriv.getSpUrl());
					if(childrenPriv.getPrivsChildren().size()>0){
						for (SpAotuspacePriv children2Priv : childrenPriv.getPrivsChildren()) {//三级权限（按钮级别）
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
	
	//父节点权限comboTreeData数据
	public String parentTreeData() throws Exception {
		//查询权限列表数据
		List<SpAotuspacePriv> spAotuspacePrivList = iAotuPMService.findSpAotuspacePrivComboTreeData();
		//封装json
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		Map<String, Object> rowMap;
		//默认选项
		rowMap = new HashMap<String, Object>();
		rowMap.put("id", 0);
		rowMap.put("text", "请选择所属上级权限");
		listMaps.add(rowMap);
		for (SpAotuspacePriv spAotuspacePriv : spAotuspacePrivList) {
			rowMap = new HashMap<String, Object>();
			rowMap.put("id", spAotuspacePriv.getSpId());
			rowMap.put("text", spAotuspacePriv.getSpName());
			listMaps.add(rowMap);
		}
		
		//easyui total总数列表
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}

	//操作权限添加
	public String add() throws Exception {
		if (model.getPrivParent().getSpId() == 0) {
			model.setPrivParent(null);
		}
		iAotuPMService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//操作权限修改回显
	@SuppressWarnings("unused")
	public String edit() throws Exception {
		//从数据库取出源对象
		SpAotuspacePriv spAotuspacePriv = iAotuPMService.getById(model.getSpId());
		//非空
		if (spAotuspacePriv != null) {
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
			jsonResult.setData("[" + objectMapper.writeValueAsString(spAotuspacePriv) + "]");
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
		SpAotuspacePriv spAotuspacePriv = iAotuPMService.getById(model.getSpId());
		//设置要修改的属性
		spAotuspacePriv.setSpName(model.getSpName());
		spAotuspacePriv.setSpUrl(model.getSpUrl());
		if (model.getPrivParent().getSpId() == 0) {
			spAotuspacePriv.setPrivParent(null);
			spAotuspacePriv.setSpUrl(null);
		} else {
			spAotuspacePriv.setPrivParent(model.getPrivParent());
			spAotuspacePriv.setSpUrl(model.getSpUrl());
		}
		//更新到数据库
		iAotuPMService.update(spAotuspacePriv);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//操作权限删除
	public String delete() throws Exception {
		iAotuPMService.delete(model.getSpId());
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
}
