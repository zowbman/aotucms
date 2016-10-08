package com.aotuspace.aotucms.web.spaotumcenter.view.action;

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
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAotuspacePriv;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAotuspaceRole;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersBinfo;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersBinfoKey;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 
 * Title:AotuURMAction
 * Description:凹凸空间角色管理
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-12 下午7:13:52
 *
 */

@Controller
@Scope("prototype")
public class AotuRMAction extends BaseAction<SpAotuspaceRole> {
	
	//权限ids
	private Integer[] privIds;
	
	//用户身份id
	private Integer selectedUserRoleId;
	
	//员工ids
	public List<SpUsersBinfoKey> userIds;
	
	//凹凸空间角色列表页面
	public String list() throws Exception {
		return "list";
	}
	
	//角色列表
	public String listData() throws Exception {
		//查询角色列表数据
		PageBean<SpAotuspaceRole> spAotuspaceRoleList = iAotuRMService.findSpAotuspaceTopRoleList(rows, page);
		//封装Json
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuspaceRole spAotuspaceRole : spAotuspaceRoleList.getRecordList()) {
			List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String, Object>>();
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spAotuspaceRole.getSpId());
			rowMap.put("sp_RoleName", spAotuspaceRole.getSpRolename());
			for (SpAotuspaceRole childrenRole : spAotuspaceRole.getSpAotuspaceRolechildren()) {
				Map<String, Object> rowChildrenMap = new HashMap<String, Object>();
				rowChildrenMap.put("sp_id", childrenRole.getSpId());
				rowChildrenMap.put("sp_RoleName", childrenRole.getSpRolename());
				listChildrenMaps.add(rowChildrenMap);
			}
			rowMap.put("children", listChildrenMaps);
			listMaps.add(rowMap);
		}
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}

	//父结点角色comboTreeData数据
	public String parentTreeData() throws Exception {
		boolean flag = true;
		//查询角色列表数据
		List<SpAotuspaceRole> spAotuspaceRoleList = iAotuRMService.findSpAotuspaceRoleTreeData();
		//封装json
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuspaceRole spAotuspaceRole : spAotuspaceRoleList) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spAotuspaceRole.getSpId());
			rowMap.put("text", spAotuspaceRole.getSpRolename());
			if (flag) {
				//默认选项
				Map<String, Object> defaultrowMap = new HashMap<String, Object>();
				defaultrowMap.put("id", 0);
				defaultrowMap.put("text", "请选择所属角色");
				listMaps.add(defaultrowMap);
				flag = false;
			}
			listMaps.add(rowMap);
		}
		//easyui total总数列表
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}
	
	// 角色添加
	public String add() throws Exception {
		if (model.getSpAotuspaceRoleparent().getSpId() == 0) {
			model.setSpAotuspaceRoleparent(null);
		}
		iAotuRMService.save(model);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	// 修改回显数据
	public String edit() throws Exception {
		// 从数据库获取源对象
		SpAotuspaceRole spAotuspaceRole = iAotuRMService.getById(model.getSpId());
		// 如果对象不为空，则
		if (spAotuspaceRole != null) {
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
			jsonResult.setData("[" + objectMapper.writeValueAsString(spAotuspaceRole) + "]");
		} else { // 否则
			jsonResult.setCode(10001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write("[" + objectMapper.writeValueAsString(jsonResult) + "]");
		return NONE;
	}

	//角色修改
	public String editSubmit() throws Exception {
		//从数据库取出对象
		SpAotuspaceRole spAotuspaceRole = iAotuRMService.getById(model.getSpId());
		//设置要修改的属性
		spAotuspaceRole.setSpRolename(model.getSpRolename());
		if (model.getSpAotuspaceRoleparent().getSpId() == 0) {
			spAotuspaceRole.setSpAotuspaceRoleparent(null);
		} else {
			spAotuspaceRole.setSpAotuspaceRoleparent(model.getSpAotuspaceRoleparent());
		}
		//更新到数据库
		iAotuRMService.update(spAotuspaceRole);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//角色账号管理
	public String roleUserEdit() throws Exception {
		//查询角色，并且返回角色所拥有的员工
		SpAotuspaceRole spAotuspaceRole = iAotuRMService.getById(model.getSpId());
		objectMapper.registerModule(new Hibernate4Module());
		Map<String, Object> spUserRoleJSONMap = new HashMap<String, Object>();
		spUserRoleJSONMap.put("spId", spAotuspaceRole.getSpId());
		spUserRoleJSONMap.put("spRolename", spAotuspaceRole.getSpRolename());
		Set<Map<String, Object>> userBinfoJSONSetMap = new HashSet<Map<String, Object>>();
		if (spAotuspaceRole.getSpUsersBinfos() != null) {
			for (SpUsersBinfo spUsersBinfo : spAotuspaceRole.getSpUsersBinfos()) {
				Map<String, Object> userBinfoMap = new HashMap<String, Object>();
				userBinfoMap.put("id", spUsersBinfo.getSpUsersBinfoKey().getSpId());//id
				userBinfoMap.put("account", spUsersBinfo.getSpAccount());//用户名 
				userBinfoMap.put("realname", spUsersBinfo.getSpUsersDinfo().getSpRealname());//真实姓名
				userBinfoJSONSetMap.add(userBinfoMap);
			}
		}
		spUserRoleJSONMap.put("userBinfos", userBinfoJSONSetMap);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		jsonResult.setData("[" + objectMapper.writeValueAsString(spUserRoleJSONMap) + "]");
		resp.getWriter().write("[" + objectMapper.writeValueAsString(jsonResult) + "]");
		return NONE;
	}
	
	//添加员工列表
	public String userAll() throws Exception {
		//全部还没有选择的员工
		List<SpUsersBinfo> spUsersBinfos = iAotuUMService.findSpUsersBinfosNoRole(model.getSpId());
		Map<String, Object> spUserRoleJSONMap = new HashMap<String, Object>();
		if (spUsersBinfos != null) {
			Set<Map<String, Object>> userBinfoJSONSetMap = new HashSet<Map<String, Object>>();
			for (SpUsersBinfo spUsersBinfo : spUsersBinfos) {
				Map<String, Object> userBinfoMap = new HashMap<String, Object>();
				userBinfoMap.put("id", spUsersBinfo.getSpUsersBinfoKey().getSpId()+"-"+spUsersBinfo.getSpUsersBinfoKey().getSpAtuid());//id 员工编号
				userBinfoMap.put("account", spUsersBinfo.getSpAccount());//用户名 
				userBinfoMap.put("realname", spUsersBinfo.getSpUsersDinfo().getSpRealname());//真实姓名
				userBinfoJSONSetMap.add(userBinfoMap);
			}
			spUserRoleJSONMap.put("userBinfos", userBinfoJSONSetMap);
		}
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		jsonResult.setData("[" + objectMapper.writeValueAsString(spUserRoleJSONMap) + "]");
		resp.getWriter().write("[" + objectMapper.writeValueAsString(jsonResult) + "]");
		return NONE;
	}
	
	//添加角色用户
	public String addRoleUser() throws Exception {
		SpAotuspaceRole spAotuspaceRole = iAotuRMService.getById(model.getSpId());
		Map<String, Object> spUserRoleJSONMap = new HashMap<String, Object>();
		if (userIds != null) {
			Set<Map<String, Object>> userBinfoJSONSetMap = new HashSet<Map<String, Object>>();
			for (SpUsersBinfoKey spUsersBinfoKey : userIds) {
				SpUsersBinfo spUsersBinfo = iAotuUMService.getByObject(spUsersBinfoKey);
				spAotuspaceRole.getSpUsersBinfos().add(spUsersBinfo);
				//回传json
				Map<String, Object> userBinfoMap = new HashMap<String, Object>();
				userBinfoMap.put("id", spUsersBinfo.getSpUsersBinfoKey().getSpId() + "-"
						+ spUsersBinfo.getSpUsersBinfoKey().getSpAtuid());//id 凹凸id
				userBinfoMap.put("account", spUsersBinfo.getSpAccount());//用户名 
				userBinfoMap.put("realname", spUsersBinfo.getSpUsersDinfo().getSpRealname());//真实姓名
				userBinfoJSONSetMap.add(userBinfoMap);
			}
			spUserRoleJSONMap.put("userBinfos", userBinfoJSONSetMap);
			iAotuRMService.update(spAotuspaceRole);
		}
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		jsonResult.setData("[" + objectMapper.writeValueAsString(spUserRoleJSONMap) + "]");
		resp.getWriter().write("[" + objectMapper.writeValueAsString(jsonResult) + "]");
		return NONE;
	}
	
	//移除角色员工
	public String deleteRoleUser() throws Exception {
		//本身角色
		SpAotuspaceRole spAotuspaceRole = iAotuRMService.getById(model.getSpId());
		//要移除角色员工的id
		if (userIds != null) {
			for (SpUsersBinfoKey key : userIds) {
				for (SpUsersBinfo spBinfo : spAotuspaceRole.getSpUsersBinfos()) {
					if (spBinfo.getSpUsersBinfoKey().getSpId().equals(key.getSpId())) {
						spAotuspaceRole.getSpUsersBinfos().remove(spBinfo);
						break;
					}
				}
			}
			iAotuRMService.update(spAotuspaceRole);
		}
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//清空角色员工
	public String clearRoleUser() throws Exception {
		//本身角色
		SpAotuspaceRole spAotuspaceRole = iAotuRMService.getById(model.getSpId());
		spAotuspaceRole.setSpUsersBinfos(null);
		iAotuRMService.update(spAotuspaceRole);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//权限treegrid
	public String privTree() throws Exception {
		//查询权限数据
		List<SpAotuspacePriv> SpAotuspacePrivList = iAotuPMService.findSpAotuspacePrivTreeData();
		//根据id查询权限
		/*SpEmployeePrivilege spEmployeePrivilege =iSysPMService.getById(id)*/
		//封装json
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuspacePriv spAotuspacePriv : SpAotuspacePrivList) {
			List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String, Object>>();
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spAotuspacePriv.getSpId());
			rowMap.put("text", spAotuspacePriv.getSpName());
			if (spAotuspacePriv.getPrivsChildren().size() == 0) {
				if (selectedUserRoleId != null) {
					for (SpAotuspaceRole spAotuspaceRole : spAotuspacePriv.getSpUserRoles()) {
						if (selectedUserRoleId.equals(spAotuspaceRole.getSpId())) {
							rowMap.put("checked", "true");
							break;
						}
					}
				}
			}
			for (SpAotuspacePriv childrenPriv : spAotuspacePriv.getPrivsChildren()) {
				Map<String, Object> rowChildrenMap = new HashMap<String, Object>();
				rowChildrenMap.put("id", childrenPriv.getSpId());
				rowChildrenMap.put("text", childrenPriv.getSpName());
				if (childrenPriv.getPrivsChildren().size() == 0) {
					if (selectedUserRoleId != null) {
						for (SpAotuspaceRole spAotuspaceRole : childrenPriv.getSpUserRoles()) {
							if (selectedUserRoleId.equals(spAotuspaceRole.getSpId())) {
								rowMap.put("checked", "true");
								break;
							}
						}
					}
				} else if (childrenPriv.getPrivsChildren().size() > 0) {
					List<Map<String, Object>> listChildren2Maps = new ArrayList<Map<String, Object>>();
					for (SpAotuspacePriv chilren2Priv : childrenPriv.getPrivsChildren()) {
						Map<String, Object> rowChilren2Map = new HashMap<String, Object>();
						rowChilren2Map.put("id", chilren2Priv.getSpId());
						rowChilren2Map.put("text", chilren2Priv.getSpName());
						if (selectedUserRoleId != null) {
							for (SpAotuspaceRole spAotuspaceRole : chilren2Priv.getSpUserRoles()) {
								if (selectedUserRoleId.equals(spAotuspaceRole.getSpId())) {
									rowChilren2Map.put("checked", "true");
									break;
								}
							}
						}
						listChildren2Maps.add(rowChilren2Map);
					}
					rowChildrenMap.put("children", listChildren2Maps);
				}
				listChildrenMaps.add(rowChildrenMap);

			}
			rowMap.put("children", listChildrenMaps);
			listMaps.add(rowMap);
		}
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}

	//用户角色-操作权限（权限设置）
	public String userRolePrivSet() throws Exception {
		SpAotuspaceRole spAotuspaceRole = iAotuRMService.getById(selectedUserRoleId);
		List<SpAotuspacePriv> spAotuspacePrivs = iAotuPMService.getByIds(privIds);
		spAotuspaceRole.setSpUsersRolePrivileges(new HashSet<SpAotuspacePriv>(spAotuspacePrivs));
		iAotuRMService.update(spAotuspaceRole);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	public List<SpUsersBinfoKey> getUserIds() {
		return userIds;
	}

	@JSON(serialize = true, deserialize = true)
	public void setUserIds(List<SpUsersBinfoKey> userIds) {
		this.userIds = userIds;
	}

	public Integer[] getPrivIds() {
		return privIds;
	}

	public void setPrivIds(Integer[] privIds) {
		this.privIds = privIds;
	}

	public Integer getSelectedUserRoleId() {
		return selectedUserRoleId;
	}

	public void setSelectedUserRoleId(Integer selectedUserRoleId) {
		this.selectedUserRoleId = selectedUserRoleId;
	}
	
	
}
