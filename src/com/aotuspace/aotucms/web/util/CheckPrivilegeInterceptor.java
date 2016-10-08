package com.aotuspace.aotucms.web.util;

import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeBinfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//��ȡ��Ϣ
		SpEmployeeBinfo spEmployeeBinfo = (SpEmployeeBinfo) ActionContext.getContext().getSession().get("spEmployeeBinfo");//��ȡ��ǰ��¼���û�
		
		//String namespace = invocation.getProxy().getNamespace();//�����ռ�
		String actionName = invocation.getProxy().getActionName();//action����
		//String privUrl = namespace + actionName;
		//���δ��¼������ת����¼ҳ
		if(spEmployeeBinfo == null){
			if(actionName.startsWith("sysmm_login")){
				//�����ȥ��¼���ͷ���
				return invocation.invoke();
			}else{
				//�������ȥ��¼���ͷ��ص�¼ҳ
				return "aotucms_login";
			}
		}else{ 
			//����ѵ�¼�����ж�Ȩ��
			if(spEmployeeBinfo.hasPrivilegeByUrl(actionName)){
				//�����Ȩ�ޣ��ͷ���
				return invocation.invoke();
			}else{
				//���û��Ȩ�ޣ�����ת����ʾҲ
				return "noPrivilegeError";
			}
		}
	}

}
