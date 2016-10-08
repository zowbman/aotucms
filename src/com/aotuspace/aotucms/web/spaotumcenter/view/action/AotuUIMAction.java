package com.aotuspace.aotucms.web.spaotumcenter.view.action;

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
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAotuspacePriv;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersIdentity;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 
 * Title:AotuUMAction
 * Description:凹凸空间用户身份管理
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:17:02
 *
 */

@Controller
@Scope("prototype")
public class AotuUIMAction extends BaseAction<SpUsersIdentity> {
	
	//用户ids
	public List<Integer> useridentIds;
	
	//权限ids
	private Integer[] privIds;
	
	//用户身份id
	private Integer selectedUserIdentId;
	
	//用户身份列表页面
	public String list() throws Exception {
		return "list";
	}
	
	//用户身份data
	public String listData() throws Exception {
		//查询用户分页信息
		PageBean<SpUsersIdentity> spUsersIdentityList = iAotuUIMService.findspUsersIdentityList(rows, page);
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpUsersIdentity spUsersIdentity : spUsersIdentityList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spUsersIdentity.getSpId());
			rowMap.put("sp_IdentityN", spUsersIdentity.getSpIdentityn());
			listMaps.add(rowMap);
		}
		//easyui total 总数  rows列表
		pageListMap.put("total", spUsersIdentityList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	
	//添加用户身份
	public String add() throws Exception {
		//如果用户名没有重复，则保存到数据库
		SpUsersIdentity spUsersIdentity = iAotuUIMService.findBySpIdentityn(model.getSpIdentityn());
		if (spUsersIdentity == null) {
			//增加到数据库
			iAotuUIMService.save(model);
			//成功
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");	
		} else {//如果名称重复，则提示重新输入
			//失败
			jsonResult.setCode(100001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//删除用户
	public String delete() throws Exception {
		Integer[] ids=new Integer[useridentIds.size()];
		for (int i = 0; i < useridentIds.size(); i++) {
			ids[i]=useridentIds.get(i);
		}
		iAotuUIMService.delete(ids);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//更改用户页面
	public String edit() throws Exception{
		//获取原对象回显
		SpUsersIdentity spUsersIdentity=iAotuUIMService.getById(model.getSpId());;
		if(spUsersIdentity!=null){
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
			jsonResult.setData("["+objectMapper.writeValueAsString(spUsersIdentity)+"]");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write("["+objectMapper.writeValueAsString(jsonResult)+"]");
		return NONE;
	}

	//更改用户
	public String editSubmit() throws Exception {
		//从数据库获取源对象
		SpUsersIdentity spUsersIdentity = iAotuUIMService.getById(model.getSpId());
		if(spUsersIdentity!=null){
			//设置要修改的属性
			spUsersIdentity.setSpIdentityn(model.getSpIdentityn());
			//更新到数据库
			iAotuUIMService.update(spUsersIdentity);
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//权限combotree
	public String privComboTree() throws Exception{
		List<SpAotuspacePriv> SpAotuspacePrivList = iAotuPMService.findSpAotuspacePrivComboTreeData();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpAotuspacePriv spAotuspacePriv : SpAotuspacePrivList) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spAotuspacePriv.getSpId());
			rowMap.put("text", spAotuspacePriv.getSpName());
			listMaps.add(rowMap);
		}
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
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
				if (selectedUserIdentId != null) {
					for (SpUsersIdentity spUsersIdentity : spAotuspacePriv.getSpUserIdents()) {
						if (selectedUserIdentId.equals(spUsersIdentity.getSpId())) {
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
					if (selectedUserIdentId != null) {
						for (SpUsersIdentity spUsersIdentity : childrenPriv.getSpUserIdents()) {
							if (selectedUserIdentId.equals(spUsersIdentity.getSpId())) {
								rowMap.put("checked", "true");
								break;
							}
						}
					}
				}else if (childrenPriv.getPrivsChildren().size() > 0) {
					List<Map<String, Object>> listChildren2Maps = new ArrayList<Map<String, Object>>();
					for (SpAotuspacePriv chilren2Priv : childrenPriv.getPrivsChildren()) {
						Map<String, Object> rowChilren2Map = new HashMap<String, Object>();
						rowChilren2Map.put("id", chilren2Priv.getSpId());
						rowChilren2Map.put("text", chilren2Priv.getSpName());
						if (selectedUserIdentId != null) {
							for (SpUsersIdentity spUsersIdentity : chilren2Priv.getSpUserIdents()) {
								if (selectedUserIdentId.equals(spUsersIdentity.getSpId())) {
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
	
	//用户身份-操作权限（权限设置）
	public String userIdentPrivSet() throws Exception {
		SpUsersIdentity spUsersIdentity = iAotuUIMService.getById(selectedUserIdentId);
		List<SpAotuspacePriv> spAotuspacePrivs = iAotuPMService.getByIds(privIds);
		spUsersIdentity.setSpUsersIdentPrivileges(new HashSet<SpAotuspacePriv>(spAotuspacePrivs));
		iAotuUIMService.update(spUsersIdentity);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	//getter、setter
	public List<Integer> getUseridentIds() {
		return useridentIds;
	}

	public void setUseridentIds(List<Integer> useridentIds) {
		this.useridentIds = useridentIds;
	}

	public Integer[] getPrivIds() {
		return privIds;
	}

	@JSON(serialize = true, deserialize = true)
	public void setPrivIds(Integer[] privIds) {
		this.privIds = privIds;
	}

	public Integer getSelectedUserIdentId() {
		return selectedUserIdentId;
	}

	public void setSelectedUserIdentId(Integer selectedUserIdentId) {
		this.selectedUserIdentId = selectedUserIdentId;
	}
}
