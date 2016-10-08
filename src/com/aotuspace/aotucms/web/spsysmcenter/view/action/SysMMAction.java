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
 * Description:系统管理员管理
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:17:02
 *
 */

@Controller
@Scope("prototype")
public class SysMMAction extends BaseAction<SpEmployeeBinfo> {
	
	//岗位选中Ids
	public Integer[] stationsIds;
	
	//组织机构Ids
	public Integer[] departIds;
	
	//员工ids
	public List<SpEmployeeBinfoKey> empIds;
	
	//登录功能
	public String login() throws Exception {
		//从数据库中搜索用户名密码
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.findByEpAccountAndEpPassword(model.getSpEpaccount(),
				model.getSpEppassword());
		if (spEmployeeBinfo == null) {//登录失败
			jsonResult.setCode(100001);//code
			jsonResult.setMsg("请求失败");//msg
			jsonResult.setData(null);//data
		}else{
			//登录成功
			//保存Session
			ActionContext.getContext().getSession().put("spEmployeeBinfo", spEmployeeBinfo);
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
			jsonResult.setData(null);
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//注销
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("spEmployeeBInfo");
		return NONE;
	}
	
	//员工信息列表页面
	public String list() throws Exception {
		return "list";
	}
	
	//员工信息data
	public String listData() throws Exception {
		PageBean<SpEmployeeBinfo> spEmployeeBinfoList;
		if(model.getSpEmployeeDepart()!=null && model.getSpEmployeeDepart().getSpId()!=0){
			//查询组织机构的子节点
			SpEmployeeDepart spEmployeeDepart=iSysDMService.getById(model.getSpEmployeeDepart().getSpId());
			if(spEmployeeDepart.getSpEpdechildren()!=null&&spEmployeeDepart.getSpEpdechildren().size()>0){//如果是父
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
			//查询管理员列表数据
			spEmployeeBinfoList = iSysMMService.findSpEmployeeBinfoList(rows, page);
		}
		
		//封装json
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
		//easyui total 总数  rows列表
		pageListMap.put("total", spEmployeeBinfoList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	
	//员工信息详细
	public String detail() throws Exception {
		//System.out.println(model.getSpEmployeeBinfoKey().getSpId()+"---"+model.getSpEmployeeBinfoKey().getSpEpid());
		//查询员工信息详细
		SpEmployeeBinfo spEmployeeBinfoDetail = iSysMMService.getByObject(model
				.getSpEmployeeBinfoKey());
		ActionContext.getContext().put("spEmployeeBinfoDetail", spEmployeeBinfoDetail);
		return "detail";
	}

	//检查用户名是否重复
	public String checkSpEpAccount() throws Exception{
		System.out.println(model.getSpEpaccount());
		//服务器端查询用户名是否存在
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.findBySpEpAccount(model.getSpEpaccount());
		if(spEmployeeBinfo==null){
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//添加员工
	public String add() throws Exception {
		//如果用户名没有重复，则保存到数据库
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.findBySpEpAccount(model.getSpEpaccount());
		if (spEmployeeBinfo == null) {
			//1:把最大的Epid查处(service接口实现类，根据员工信息的epid进行降序，并且获取一条select top 1 from 表 order by desc <-- 是一个对象，而不是一个list)
			Integer maxEpId = iSysMMService.findByMaxEpId();
			SpEmployeeBinfoKey newKey=new SpEmployeeBinfoKey();
			if (maxEpId == null) {
				newKey.setSpEpid(100000);
				//spEmployeeBinfo.setSpEmployeeBinfoKey(newKey);
			}else{
				newKey.setSpEpid(maxEpId + 1);
			}
			model.setSpEmployeeBinfoKey(newKey);
			//详细信息
			SpEmployeeDinfo spEmployeeDinfo=model.getSpEmployeeDinfo();
			spEmployeeDinfo.setSpEmployeeBinfoKey(newKey);
			model.setSpEmployeeDinfo(spEmployeeDinfo);
			//获取组织机构数据
			if(model.getSpEmployeeDepart().getSpId()==0){
				model.setSpEmployeeDepart(null);
			}
			//2:增加到数据库
			iSysMMService.save(model);
			//成功
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");	
		} else {//如果用户名重复，则提示重新输入
			//失败
			jsonResult.setCode(100001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//删除员工
	public String delete() throws Exception {
		iSysMMService.deleteByList(empIds);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//更改员工页面
	public String edit() throws Exception{
		//获取原对象回显
		SpEmployeeBinfo spEmployeeBinfo=iSysMMService.getByObject(model.getSpEmployeeBinfoKey());
		if(spEmployeeBinfo!=null){
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
			jsonResult.setData("["+objectMapper.writeValueAsString(spEmployeeBinfo)+"]");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}

	//更改员工
	public String editSubmit() throws Exception {
		//从数据库获取源对象
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.getByObject(model.getSpEmployeeBinfoKey());
		if(spEmployeeBinfo!=null){
			//设置要修改的属性
			spEmployeeBinfo.setSpEppassword(model.getSpEppassword());
			//详细信息
			spEmployeeBinfo.getSpEmployeeDinfo().setSpEprealname(model.getSpEmployeeDinfo().getSpEprealname());;
			//组织机构
			SpEmployeeDepart spEmployeeDepart=iSysDMService.getById(model.getSpEmployeeDepart().getSpId());
			spEmployeeBinfo.setSpEmployeeDepart(spEmployeeDepart);
			//更新到数据库
			iSysMMService.update(spEmployeeBinfo);
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//角色分配(岗位分配)
	public String editPrivilege() throws Exception{
		//从数据库取出源对象
		SpEmployeeBinfo spEmployeeBinfo = iSysMMService.getByObject(model);
		//设置关联的岗位属性
		List<SpEmployeeStation> spemployeestationList = iSysRMService.getByIds(stationsIds);
		spEmployeeBinfo.setSpEmployeeStations(new HashSet<SpEmployeeStation>(spemployeestationList));
		//更新到数据库
		iSysMMService.update(spEmployeeBinfo);
		return NONE;
	}
	
	//员工组织机构分类tree
	public String departTree()throws Exception{
		//查询组织机构数据
		List<SpEmployeeDepart> spEmployeeDepartList = iSysDMService.findSpEmployeeDepartTreeData();
		//封装json
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
		allMap.put("text", "凹凸空间");
		allMap.put("checked", true);
		allMap.put("children", listMaps);
		allListMaps.add(allMap);
		//easyui total总数列表
		resp.getWriter().write(objectMapper.writeValueAsString(allListMaps));
		return NONE;
	}
	
	//员工组织机构treeData（添加、编辑combotree）
	public String departTreeData() throws Exception {
		boolean flag=true;
		//查询部门列表数据
		List<SpEmployeeDepart> spEmployeeDepartList = iSysDMService.findSpEmployeeDepartTreeData();
		//封装json
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
				//默认选项
				Map<String, Object> defaultrowMap = new HashMap<String, Object>();
				defaultrowMap.put("id", 0);
				defaultrowMap.put("text", "请选择所属组织机构");
				listMaps.add(defaultrowMap);
				flag=false;
			}
			rowMap.put("children", listChildrenMaps);
			listMaps.add(rowMap);
		}
		//easyui total总数列表
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}

	//getter、setter
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
