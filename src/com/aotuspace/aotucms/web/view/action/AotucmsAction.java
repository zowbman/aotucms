package com.aotuspace.aotucms.web.view.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.Invocation;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeBinfo;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeePrivilege;

import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeStation;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * Title:AotucmsAction
 * Description:aotucmsAction（登录页，首页）
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午4:34:29
 *
 */

@Controller
@Scope("prototype")
@SuppressWarnings({ "unchecked", "serial","unused" })
public class AotucmsAction extends ActionSupport implements ServletResponseAware{
	
	// ========获取jackson对象==========
	private ObjectMapper objectMapper=new ObjectMapper();
	
	// ========获取HttpServletResponse对象==========
	private HttpServletResponse resp;
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		arg0.setContentType("application/json; charset=utf-8");
		resp=arg0;
		
	}
	
	//登录页面
	public String login(){
		return "aotucms_login";
	}
	
	//首页
	public String admin() {
		return "aotucms_admin";
	}
	
	//导航tree数据
	public String nav() throws Exception {
		//获取当前系统管理员
		SpEmployeeBinfo currentUser  = (SpEmployeeBinfo) ActionContext
				.getContext().getSession().get("spEmployeeBinfo");
		//封装json
		List<SpEmployeePrivilege> topPrivilegeList= (List<SpEmployeePrivilege>) ActionContext.getContext().getApplication().get("topPrivilegeList");
		List<HashMap<String, Object>> listParent = new ArrayList<HashMap<String, Object>>();
		for (SpEmployeePrivilege parenPriv : topPrivilegeList) {//获取tree根节点权限
			if(currentUser.hasPrivilegeByName(parenPriv.getSpEpname())){//判断当前用户是否有这个根节点权限
				List<HashMap<String, Object>> listChildren = new ArrayList<HashMap<String, Object>>();
				HashMap<String, Object> mapParent = new HashMap<String, Object>();
				mapParent.put("id", parenPriv.getSpId());
				mapParent.put("text", parenPriv.getSpEpname());
				mapParent.put("iconCls", parenPriv.getSpIconcls());
				mapParent.put("url", parenPriv.getSpEpurl());
				for(SpEmployeePrivilege childrenPriv : parenPriv.getSpEpchildren()){//获取tree根节点权限下的子节点权限
					if(currentUser.hasPrivilegeByName(childrenPriv.getSpEpname())){//判断当前用户是否有这个根节点权限下的子节点权限
						HashMap<String, Object> mapChildren = new HashMap<String, Object>();
						mapChildren.put("id", childrenPriv.getSpId());
						mapChildren.put("text", childrenPriv.getSpEpname());
						mapChildren.put("iconCls", childrenPriv.getSpIconcls());
						mapChildren.put("url", childrenPriv.getSpEpurl());
						listChildren.add(mapChildren);
					}
				}
				mapParent.put("children", listChildren);		
				listParent.add(mapParent);
			}
		}
		resp.getWriter().write(objectMapper.writeValueAsString(listParent));
		return NONE;
	}
}
