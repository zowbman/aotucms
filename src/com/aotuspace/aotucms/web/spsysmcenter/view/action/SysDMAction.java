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
 * Description:组织机构管理
 * Company:aotuspace
 * @author    sida
 * @date      2015-9-28 下午4:29:55
 *
 */
@Controller
@Scope("prototype")
public class SysDMAction extends BaseAction<SpEmployeeDepart> {
	
	//部门列表页面
	public String list() throws Exception {
		return "list";
	}
	
	//组织机构列表
	public String listData() throws Exception {
		//查询组织机构列表数据
		PageBean<SpEmployeeDepart> spEmployeeDepartList = iSysDMService.findSpEmployeeTopDepartList(rows, page);
		//封装json
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
		//easyui total总数列表
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}
	
	//父节点部门comboTreeData数据
	public String parentTreeData() throws Exception{
		boolean flag=true;
		//查询组织机构列表数据
		List<SpEmployeeDepart> spEmployeeDepartList = iSysDMService.findSpEmployeeDepartTreeData();
		//封装json
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeeDepart spEmployeeDepart : spEmployeeDepartList) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spEmployeeDepart.getSpId());
			rowMap.put("text", spEmployeeDepart.getSpEpdepartn());
			if(flag){
				//默认选项
				Map<String, Object> defaultrowMap = new HashMap<String, Object>();
				defaultrowMap.put("id", 0);
				defaultrowMap.put("text", "请选择所属组织机构");
				listMaps.add(defaultrowMap);
				flag=false;
			}
			listMaps.add(rowMap);
		}
		//easyui total总数列表
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}
	
	//组织机构添加
	public String add() throws Exception {
		if(model.getSpEpdeparent().getSpId()==0){
			model.setSpEpdeparent(null);
		}
		iSysDMService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//修改回显数据
	public String edit() throws Exception {
		//从数据库获取源对象
		SpEmployeeDepart spEmployeeDepart = iSysDMService.getById(model.getSpId());
		if(spEmployeeDepart!=null){
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
			jsonResult.setData("["+objectMapper.writeValueAsString(spEmployeeDepart)+"]");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}
	
	//组织机构修改
	public String editSubmit() throws Exception{
		//从数据库获取源对象
		SpEmployeeDepart spEmployeeDepart = iSysDMService.getById(model.getSpId());
		//设置要修改的属性
		spEmployeeDepart.setSpEpdepartn(model.getSpEpdepartn());
		if(model.getSpEpdeparent().getSpId()==0){
			spEmployeeDepart.setSpEpdeparent(null);
		}else{
			spEmployeeDepart.setSpEpdeparent(model.getSpEpdeparent());
		}
		//更新到数据库
		iSysDMService.update(spEmployeeDepart);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//组织机构删除
	public String delete() throws Exception {
		iSysDMService.delete(model.getSpId());
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
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
