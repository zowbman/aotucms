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
 * Description:系统角色管理
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:18:20
 *
 */
@Controller
@Scope("prototype")
public class SysRMAction extends BaseAction<SpEmployeeStation> {
	
	//员工ids
	public List<SpEmployeeBinfoKey> empIds;
	
	//角色列表页面
	public String list() throws Exception{
		return "list";
	}
	
	//角色列表
	public String listData() throws Exception{
	//查询角色列表数据
	PageBean<SpEmployeeStation>  spEmployeeStationList = iSysRMService.findSpEmployeeTopStationList(rows, page);
	//封装Json
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
	
	//父结点角色comboTreeData数据
	public String parentTreeData() throws Exception{
		boolean flag = true;
		//查询角色列表数据
		List<SpEmployeeStation> spEmployeeStationList = iSysRMService.findSpEmployeeStationTreeData();
		//封装json
		List<Map<String, Object>> listMaps = new ArrayList<Map<String,Object>>();
		for (SpEmployeeStation spEmployeeStation : spEmployeeStationList) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spEmployeeStation.getSpId());
			rowMap.put("text", spEmployeeStation.getSpEpstn());
			if(flag){
				//默认选项
				Map<String, Object> defaultrowMap = new HashMap<String, Object>();
				defaultrowMap.put("id", 0);
				defaultrowMap.put("text", "请选择所属角色");
				listMaps.add(defaultrowMap);
				flag=false;
			}
			listMaps.add(rowMap);
		}
		//easyui total总数列表
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}
	
	// 角色添加
	public String add() throws Exception {
		if (model.getSpEpstparent().getSpId() == 0) {
			model.setSpEpstparent(null);
		}
		iSysRMService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	// 修改回显数据
	public String edit() throws Exception {
		// 从数据库获取源对象
		SpEmployeeStation spEmployeeStation = iSysRMService.getById(model
				.getSpId());
		// 如果对象不为空，则
		if (spEmployeeStation != null) {
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
			jsonResult.setData("["+ objectMapper.writeValueAsString(spEmployeeStation) + "]");
		} else { // 否则
			jsonResult.setCode(10001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}
	
	//角色修改
	public String editSubmit() throws Exception{
		//从数据库取出对象
		SpEmployeeStation spEmployeeStation = iSysRMService.getById(model.getSpId());
		//设置要修改的属性
		spEmployeeStation.setSpEpstn(model.getSpEpstn());
		if(model.getSpEpstparent().getSpId()==0){
			spEmployeeStation.setSpEpstparent(null);
		}else{
			spEmployeeStation.setSpEpstparent(model.getSpEpstparent());
		}
		//更新到数据库
		iSysRMService.update(spEmployeeStation);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	// 角色删除
	public String delete() throws Exception {
		iSysRMService.delete(model.getSpId());
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//角色员工管理
	public String roleEmplEdit() throws Exception{
		//System.out.println(model.getSpId());
		//查询角色，并且返回角色所拥有的员工
		SpEmployeeStation spEmployeeStation = iSysRMService.getById(model.getSpId());
		objectMapper.registerModule(new Hibernate4Module());
		Map<String, Object> spEmplStaJSONMap=new HashMap<String, Object>();
		spEmplStaJSONMap.put("spId", spEmployeeStation.getSpId());
		spEmplStaJSONMap.put("spEpstn", spEmployeeStation.getSpEpstn());
		Set<Map<String, Object>> emplBinfoJSONSetMap=new HashSet<Map<String,Object>>();
		if(spEmployeeStation.getSpEmployeeBinfos()!=null){
			for (SpEmployeeBinfo spEmployeeBinfo : spEmployeeStation.getSpEmployeeBinfos()) {
				Map<String, Object> emplBinfoMap=new HashMap<String, Object>();
				emplBinfoMap.put("id", spEmployeeBinfo.getSpEmployeeBinfoKey().getSpId()+"-"+ spEmployeeBinfo.getSpEmployeeBinfoKey().getSpEpid());//id 员工编号
				emplBinfoMap.put("account", spEmployeeBinfo.getSpEpaccount());//用户名 
				emplBinfoMap.put("realname", spEmployeeBinfo.getSpEmployeeDinfo().getSpEprealname());//真实姓名
				emplBinfoJSONSetMap.add(emplBinfoMap);
			}
		}
		spEmplStaJSONMap.put("employeeBinfos", emplBinfoJSONSetMap);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		jsonResult.setData("["+objectMapper.writeValueAsString(spEmplStaJSONMap)+"]");
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}
	
	//添加员工列表
	public String emplAll() throws Exception{
		//全部还没有选择的员工
		List<SpEmployeeBinfo> spEmployeeBinfos=iSysMMService.findSpEmployeeBinfosNoStation(model.getSpId());
		Map<String, Object> spEmplStaJSONMap=new HashMap<String, Object>();
		if(spEmployeeBinfos!=null){
			Set<Map<String, Object>> emplBinfoJSONSetMap=new HashSet<Map<String,Object>>();
			for (SpEmployeeBinfo spEmployeeBinfo : spEmployeeBinfos) {
				Map<String, Object> emplBinfoMap=new HashMap<String, Object>();
				emplBinfoMap.put("id", spEmployeeBinfo.getSpEmployeeBinfoKey().getSpId()+"-"+ spEmployeeBinfo.getSpEmployeeBinfoKey().getSpEpid());//id 员工编号
				emplBinfoMap.put("account", spEmployeeBinfo.getSpEpaccount());//用户名 
				emplBinfoMap.put("realname", spEmployeeBinfo.getSpEmployeeDinfo().getSpEprealname());//真实姓名
				emplBinfoJSONSetMap.add(emplBinfoMap);
			}
			spEmplStaJSONMap.put("employeeBinfos", emplBinfoJSONSetMap);
		}
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		jsonResult.setData("["+objectMapper.writeValueAsString(spEmplStaJSONMap)+"]");
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}
	
	//移除角色员工
	public String deleteRoleEmpl() throws Exception{
		//本身角色
		SpEmployeeStation spEmployeeStation=iSysRMService.getById(model.getSpId());
		//要移除角色员工的id
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
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//清空角色员工
	public String clearRoleEmpl() throws Exception{
		//本身角色
		SpEmployeeStation spEmployeeStation=iSysRMService.getById(model.getSpId());
		spEmployeeStation.setSpEmployeeBinfos(null);
		iSysRMService.update(spEmployeeStation);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//添加角色员工
	public String addRoleEmpl() throws Exception{
		SpEmployeeStation spEmployeeStation=iSysRMService.getById(model.getSpId());
		Map<String, Object> spEmplStaJSONMap=new HashMap<String, Object>();
		if(empIds!=null){
			Set<Map<String, Object>> emplBinfoJSONSetMap=new HashSet<Map<String,Object>>();
			for (SpEmployeeBinfoKey spEmployeeBinfoKey : empIds) {
				SpEmployeeBinfo spEmployeeBinfo=iSysMMService.getByObject(spEmployeeBinfoKey);
				spEmployeeStation.getSpEmployeeBinfos().add(spEmployeeBinfo);
				//回传json
				Map<String, Object> emplBinfoMap=new HashMap<String, Object>();
				emplBinfoMap.put("id", spEmployeeBinfo.getSpEmployeeBinfoKey().getSpId()+"-"+ spEmployeeBinfo.getSpEmployeeBinfoKey().getSpEpid());//id 员工编号
				emplBinfoMap.put("account", spEmployeeBinfo.getSpEpaccount());//用户名 
				emplBinfoMap.put("realname", spEmployeeBinfo.getSpEmployeeDinfo().getSpEprealname());//真实姓名
				emplBinfoJSONSetMap.add(emplBinfoMap);
			}
			spEmplStaJSONMap.put("employeeBinfos", emplBinfoJSONSetMap);
			iSysRMService.update(spEmployeeStation);
		}
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
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
