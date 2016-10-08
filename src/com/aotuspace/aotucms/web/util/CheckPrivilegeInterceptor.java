package com.aotuspace.aotucms.web.util;

import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeBinfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//获取信息
		SpEmployeeBinfo spEmployeeBinfo = (SpEmployeeBinfo) ActionContext.getContext().getSession().get("spEmployeeBinfo");//获取当前登录的用户
		
		//String namespace = invocation.getProxy().getNamespace();//命名空间
		String actionName = invocation.getProxy().getActionName();//action名称
		//String privUrl = namespace + actionName;
		//如果未登录，就跳转到登录页
		if(spEmployeeBinfo == null){
			if(actionName.startsWith("sysmm_login")){
				//如果是去登录，就放行
				return invocation.invoke();
			}else{
				//如果不是去登录，就返回登录页
				return "aotucms_login";
			}
		}else{ 
			//如果已登录，就判断权限
			if(spEmployeeBinfo.hasPrivilegeByUrl(actionName)){
				//如果有权限，就放行
				return invocation.invoke();
			}else{
				//如果没有权限，就跳转到提示也
				return "noPrivilegeError";
			}
		}
	}

}
