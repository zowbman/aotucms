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
 * Description:aotucmsAction����¼ҳ����ҳ��
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����4:34:29
 *
 */

@Controller
@Scope("prototype")
@SuppressWarnings({ "unchecked", "serial","unused" })
public class AotucmsAction extends ActionSupport implements ServletResponseAware{
	
	// ========��ȡjackson����==========
	private ObjectMapper objectMapper=new ObjectMapper();
	
	// ========��ȡHttpServletResponse����==========
	private HttpServletResponse resp;
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		arg0.setContentType("application/json; charset=utf-8");
		resp=arg0;
		
	}
	
	//��¼ҳ��
	public String login(){
		return "aotucms_login";
	}
	
	//��ҳ
	public String admin() {
		return "aotucms_admin";
	}
	
	//����tree����
	public String nav() throws Exception {
		//��ȡ��ǰϵͳ����Ա
		SpEmployeeBinfo currentUser  = (SpEmployeeBinfo) ActionContext
				.getContext().getSession().get("spEmployeeBinfo");
		//��װjson
		List<SpEmployeePrivilege> topPrivilegeList= (List<SpEmployeePrivilege>) ActionContext.getContext().getApplication().get("topPrivilegeList");
		List<HashMap<String, Object>> listParent = new ArrayList<HashMap<String, Object>>();
		for (SpEmployeePrivilege parenPriv : topPrivilegeList) {//��ȡtree���ڵ�Ȩ��
			if(currentUser.hasPrivilegeByName(parenPriv.getSpEpname())){//�жϵ�ǰ�û��Ƿ���������ڵ�Ȩ��
				List<HashMap<String, Object>> listChildren = new ArrayList<HashMap<String, Object>>();
				HashMap<String, Object> mapParent = new HashMap<String, Object>();
				mapParent.put("id", parenPriv.getSpId());
				mapParent.put("text", parenPriv.getSpEpname());
				mapParent.put("iconCls", parenPriv.getSpIconcls());
				mapParent.put("url", parenPriv.getSpEpurl());
				for(SpEmployeePrivilege childrenPriv : parenPriv.getSpEpchildren()){//��ȡtree���ڵ�Ȩ���µ��ӽڵ�Ȩ��
					if(currentUser.hasPrivilegeByName(childrenPriv.getSpEpname())){//�жϵ�ǰ�û��Ƿ���������ڵ�Ȩ���µ��ӽڵ�Ȩ��
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
