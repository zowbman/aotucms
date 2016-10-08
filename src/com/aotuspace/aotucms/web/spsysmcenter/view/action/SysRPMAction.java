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
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeePrivilege;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeStation;

/**
 * 
 * Title:SysRPMAction
 * Description:角色权限管理
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:22:59
 *
 */

@Controller
@Scope("prototype")
public class SysRPMAction extends BaseAction<SpEmployeePrivilege> {
	
	private Integer selectedRoleId;
	
	private Integer[] privIds;
	//角色权限管理页面
	public String list()throws Exception{
		return "list";
	}
	
	//权限treegrid
	public String privTree() throws Exception {
		//查询角色数据
		List<SpEmployeePrivilege> spEmployeePrivList = iSysPMService.findSpEmployeePrivilegeTreeData();
		//根据id查询权限
		/*SpEmployeePrivilege spEmployeePrivilege =iSysPMService.getById(id)*/
		//封装json
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpEmployeePrivilege spEmployeePrivilege : spEmployeePrivList) {
			List<Map<String, Object>> listChildrenMaps = new ArrayList<Map<String, Object>>();
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("id", spEmployeePrivilege.getSpId());
			rowMap.put("text", spEmployeePrivilege.getSpEpname());
			if(spEmployeePrivilege.getSpEpchildren().size()==0){
				if(selectedRoleId!=null){
					for(SpEmployeeStation spEmployeeStation :spEmployeePrivilege.getSpEmployeeStations()){
						if(selectedRoleId.equals(spEmployeeStation.getSpId())){
							rowMap.put("checked", "true");
							/*break;*/
						}
					}
				}
			}
			for (SpEmployeePrivilege childrenPriv : spEmployeePrivilege.getSpEpchildren()) {
				Map<String, Object> rowChildrenMap = new HashMap<String, Object>();
				rowChildrenMap.put("id", childrenPriv.getSpId());
				rowChildrenMap.put("text", childrenPriv.getSpEpname());
				if(childrenPriv.getSpEpchildren().size()>0){
					List<Map<String, Object>> listChildren2Maps = new ArrayList<Map<String, Object>>();
					for(SpEmployeePrivilege chilren2Priv :childrenPriv.getSpEpchildren()){
						Map<String, Object> rowChilren2Map = new HashMap<String, Object>();
						rowChilren2Map.put("id", chilren2Priv.getSpId());
						rowChilren2Map.put("text", chilren2Priv.getSpEpname());
						if(selectedRoleId!=null){
							for(SpEmployeeStation spEmployeeStation :chilren2Priv.getSpEmployeeStations()){
								if(selectedRoleId.equals(spEmployeeStation.getSpId())){
									rowChilren2Map.put("checked", "true");
									/*break;*/
								}
							}
						}
						listChildren2Maps.add(rowChilren2Map);
					}
					rowChildrenMap.put("children", listChildren2Maps);
				}else{
					if(selectedRoleId!=null){
						for(SpEmployeeStation spEmployeeStation:childrenPriv.getSpEmployeeStations()){
							if(selectedRoleId.equals(spEmployeeStation.getSpId())){
								rowChildrenMap.put("checked", true);
							}
						}
					}
				}
				listChildrenMaps.add(rowChildrenMap);
				
			}
			rowMap.put("children", listChildrenMaps);
			listMaps.add(rowMap);
		}
		resp.getWriter().write(objectMapper.writeValueAsString(listMaps));
		return NONE;
	}
	
	//角色-操作权限（权限设置）
	public String editSubmit()throws Exception{
		SpEmployeeStation spEmployeeStation =iSysRMService.getById(selectedRoleId);
		List<SpEmployeePrivilege> spEmployeePrivileges =iSysPMService.getByIds(privIds);
		spEmployeeStation.setSpEmployeePrivileges(new HashSet<SpEmployeePrivilege>(spEmployeePrivileges));
		iSysRMService.update(spEmployeeStation);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}

	public Integer getSelectedRoleId() {
		return selectedRoleId;
	}

	public void setSelectedRoleId(Integer selectedRoleId) {
		this.selectedRoleId = selectedRoleId;
	}

	public Integer[] getPrivIds() {
		return privIds;
	}

	@JSON(serialize = true, deserialize = true)
	public void setPrivIds(Integer[] privIds) {
		this.privIds = privIds;
	}
}
