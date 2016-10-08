package com.aotuspace.aotucms.web.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.aotuspace.aotucms.web.model.JsonResult;
import com.aotuspace.aotucms.web.spaotumcenter.service.IAotuAANMService;
import com.aotuspace.aotucms.web.spaotumcenter.service.IAotuAARMService;
import com.aotuspace.aotucms.web.spaotumcenter.service.IAotuAnchorMService;
import com.aotuspace.aotucms.web.spaotumcenter.service.IAotuPMService;
import com.aotuspace.aotucms.web.spaotumcenter.service.IAotuRMService;
import com.aotuspace.aotucms.web.spaotumcenter.service.IAotuUIMService;
import com.aotuspace.aotucms.web.spaotumcenter.service.IAotuUMService;
import com.aotuspace.aotucms.web.spsumcenter.service.ISysUAMService;
import com.aotuspace.aotucms.web.spsumcenter.service.ISysUMMService;
import com.aotuspace.aotucms.web.spsysmcenter.service.ISysDMService;
import com.aotuspace.aotucms.web.spsysmcenter.service.ISysMMService;
import com.aotuspace.aotucms.web.spsysmcenter.service.ISysPMService;
import com.aotuspace.aotucms.web.spsysmcenter.service.ISysRMService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * Title:BaseAction Description: Company:aotuspace
 * 
 * @author sida
 * @date 2015-9-2 ����12:17:41
 * 
 */
@SuppressWarnings({ "unchecked", "serial" })
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>, ServletResponseAware {

	// =============JsonResult����===============
	protected JsonResult jsonResult = new JsonResult();

	// =============jackson josnת����===========
	protected ObjectMapper objectMapper = new ObjectMapper();

	// =============response����=================
	protected HttpServletResponse resp;

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		arg0.setContentType("application/json; charset=utf-8");
		resp = arg0;
	}

	// =============ModelDriven��֧��=============

	protected T model;

	public BaseAction() {
		try {
			// ͨ�������ȡModel����ʵ����
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazzz = (Class<T>) pt.getActualTypeArguments()[0];
			// ͨ�����䴴��Model��ʵ��
			model = clazzz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	// =============��ҳ=======================
	protected int page = 1;//��ǰҳ
	protected int rows = 20;//ÿҳ��ʾ������

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	// =============Service��ʵ������=============

	//Ա��service
	@Resource
	protected ISysMMService iSysMMService;

	@Resource
	protected ISysPMService iSysPMService;

	@Resource
	protected ISysRMService iSysRMService;

	@Resource
	protected ISysDMService iSysDMService;

	//��Ӧ��service
	@Resource
	protected ISysUMMService iSysUMMService;

	//��Ӧ������service
	protected ISysUAMService iSysUAMService;

	//�û�service
	@Resource
	protected IAotuUMService iAotuUMService;

	@Resource
	protected IAotuUIMService iAotuUIMService;

	@Resource
	protected IAotuRMService iAotuRMService;

	@Resource
	protected IAotuPMService iAotuPMService;

	//�����������
	@Resource
	protected IAotuAANMService iAotuAANMService;
	
	//����������������
	@Resource
	protected IAotuAARMService iAotuAARMService;

	//������������
	@Resource
	protected IAotuAnchorMService iAotuAnchorMService;

}
