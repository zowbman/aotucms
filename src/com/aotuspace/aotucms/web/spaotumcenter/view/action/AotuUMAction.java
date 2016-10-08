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
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersBinfo;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersBinfoKey;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersDinfo;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * Title:AotuUMAction
 * Description:凹凸空间用户管理
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:17:02
 *
 */

@Controller
@Scope("prototype")
public class AotuUMAction extends BaseAction<SpUsersBinfo> {
	
	//权限ids
	private Integer[] privIds;
	
	//用户id
	private SpUsersBinfoKey spUsersBinfoKey;
	
	//用户ids
	public List<SpUsersBinfoKey> userIds;
	
	//用户信息列表页面
	public String list() throws Exception {
		return "list";
	}
	
	//用户信息data
	public String listData() throws Exception {
		//查询用户分页信息
		PageBean<SpUsersBinfo> spUsersBinfoList = iAotuUMService.findSpUsersBinfoList(rows, page);
		//封装json
		Map<String, Object> pageListMap = new HashMap<String, Object>();
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		for (SpUsersBinfo spUsersBinfo : spUsersBinfoList.getRecordList()) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			rowMap.put("sp_id", spUsersBinfo.getSpUsersBinfoKey().getSpId());
			rowMap.put("sp_AtuId", spUsersBinfo.getSpUsersBinfoKey().getSpAtuid());
			rowMap.put("sp_Account", spUsersBinfo.getSpAccount());//账号
			rowMap.put("sp_Identity", spUsersBinfo.getSpUsersIdentity().getSpIdentityn());//身份
			rowMap.put("sp_ReDate", spUsersBinfo.getSpRedate());//注册时间
			rowMap.put("sp_Status", spUsersBinfo.getSpUsersStatus().getSpStatusn());//账号状态
			rowMap.put("sp_RePlace", spUsersBinfo.getSpReplace());//注册地
			rowMap.put("sp_ReIp", spUsersBinfo.getSpReip());//注册Ip
			listMaps.add(rowMap);
		}
		//easyui total 总数  rows列表
		pageListMap.put("total", spUsersBinfoList.getPageCount());//总条数
		pageListMap.put("rows", listMaps);//列表
		resp.getWriter().write(objectMapper.writeValueAsString(pageListMap));
		return NONE;
	}
	
	//员工信息详细
	public String detail() throws Exception {
		//查询员工信息详细
		SpUsersBinfo spUsersBinfoDetail = iAotuUMService.getByObject(model
				.getSpUsersBinfoKey());
		ActionContext.getContext().put("spUsersBinfoDetail", spUsersBinfoDetail);
		return "detail";
	}

	//检查用户名是否重复
	public String checkSpAccount() throws Exception{
		//服务器端查询用户名是否存在
		SpUsersBinfo spUsersBinfo = iAotuUMService.findBySpAccount(model.getSpAccount());
		if(spUsersBinfo==null){
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("请求失败");
		}
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//添加用户
	public String add() throws Exception {
		//如果用户名没有重复，则保存到数据库
		SpUsersBinfo spUsersBinfo = iAotuUMService.findBySpAccount(model.getSpAccount());
		if (spUsersBinfo == null) {
			//1:把最大的AutoId查出
			Integer maxAutoId = iAotuUMService.findByMaxAutoid();
			SpUsersBinfoKey newKey=new SpUsersBinfoKey();
			if (maxAutoId == null) {
				newKey.setSpAtuid(100000);
			}else{
				newKey.setSpAtuid(maxAutoId + 1);
			}
			model.setSpUsersBinfoKey(newKey);
			//详细信息
			SpUsersDinfo spUsersDinfo=model.getSpUsersDinfo();
			spUsersDinfo.setSpUsersBinfoKey(newKey);
			model.setSpUsersDinfo(spUsersDinfo);
			//2:增加到数据库
			iAotuUMService.save(model);
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

	//删除用户
	public String delete() throws Exception {
		iAotuUMService.deleteByList(userIds);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//更改用户页面
	public String edit() throws Exception{
		//获取原对象回显
		SpUsersBinfo spUsersBinfo=iAotuUMService.getByObject(model.getSpUsersBinfoKey());
		if(spUsersBinfo!=null){
			objectMapper.registerModule(new Hibernate4Module());
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
			jsonResult.setData("["+objectMapper.writeValueAsString(spUsersBinfo)+"]");
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
		SpUsersBinfo spUsersBinfo = iAotuUMService.getByObject(model.getSpUsersBinfoKey());
		if(spUsersBinfo!=null){
			//设置要修改的属性
			spUsersBinfo.setSpPassword(model.getSpPassword());
			//详细信息
			spUsersBinfo.getSpUsersDinfo().setSpRealname(model.getSpUsersDinfo().getSpRealname());;
			//更新到数据库
			iAotuUMService.update(spUsersBinfo);
			jsonResult.setCode(0);
			jsonResult.setMsg("请求成功");
		}else{
			jsonResult.setCode(10001);
			jsonResult.setMsg("请求失败");
		}
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
				if (model.getSpUsersBinfoKey() != null) {
					for (SpUsersBinfo spUsersBinfo : spAotuspacePriv.getSpUsers()) {
						if (model.getSpUsersBinfoKey().equals(spUsersBinfo.getSpUsersBinfoKey())) {
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
					if (model.getSpUsersBinfoKey() != null) {
						for (SpUsersBinfo spUsersBinfo : childrenPriv.getSpUsers()) {
							if (model.getSpUsersBinfoKey().equals(spUsersBinfo.getSpUsersBinfoKey())) {
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
						if (model.getSpUsersBinfoKey() != null) {
							for (SpUsersBinfo spUsersBinfo : chilren2Priv.getSpUsers()) {
								if (model.getSpUsersBinfoKey().equals(spUsersBinfo.getSpUsersBinfoKey())) {
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
	public String userPrivSet() throws Exception {
		SpUsersBinfo spUsersBinfo = iAotuUMService.getByObject(spUsersBinfoKey);
		List<SpAotuspacePriv> spAotuspacePrivs = iAotuPMService.getByIds(privIds);
		spUsersBinfo.setSpUsersPrivileges(new HashSet<SpAotuspacePriv>(spAotuspacePrivs));
		iAotuUMService.update(spUsersBinfo);
		jsonResult.setCode(0);
		jsonResult.setMsg("请求成功");
		resp.getWriter().write(objectMapper.writeValueAsString(jsonResult));
		return NONE;
	}
	
	//getter、setter
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

	public SpUsersBinfoKey getSpUsersBinfoKey() {
		return spUsersBinfoKey;
	}

	@JSON(serialize = true, deserialize = true)
	public void setSpUsersBinfoKey(SpUsersBinfoKey spUsersBinfoKey) {
		this.spUsersBinfoKey = spUsersBinfoKey;
	}
	
	
}
