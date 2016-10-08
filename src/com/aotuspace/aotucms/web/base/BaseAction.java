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
 * @date 2015-9-2 下午12:17:41
 * 
 */
@SuppressWarnings({ "unchecked", "serial" })
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>, ServletResponseAware {

	// =============JsonResult对象===============
	protected JsonResult jsonResult = new JsonResult();

	// =============jackson josn转换器===========
	protected ObjectMapper objectMapper = new ObjectMapper();

	// =============response对象=================
	protected HttpServletResponse resp;

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		arg0.setContentType("application/json; charset=utf-8");
		resp = arg0;
	}

	// =============ModelDriven的支持=============

	protected T model;

	public BaseAction() {
		try {
			// 通过反射获取Model的真实类型
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazzz = (Class<T>) pt.getActualTypeArguments()[0];
			// 通过反射创建Model的实例
			model = clazzz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

	// =============分页=======================
	protected int page = 1;//当前页
	protected int rows = 20;//每页显示多少条

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

	// =============Service的实例声明=============

	//员工service
	@Resource
	protected ISysMMService iSysMMService;

	@Resource
	protected ISysPMService iSysPMService;

	@Resource
	protected ISysRMService iSysRMService;

	@Resource
	protected ISysDMService iSysDMService;

	//供应商service
	@Resource
	protected ISysUMMService iSysUMMService;

	//供应商申请service
	protected ISysUAMService iSysUAMService;

	//用户service
	@Resource
	protected IAotuUMService iAotuUMService;

	@Resource
	protected IAotuUIMService iAotuUIMService;

	@Resource
	protected IAotuRMService iAotuRMService;

	@Resource
	protected IAotuPMService iAotuPMService;

	//申请代言主播
	@Resource
	protected IAotuAANMService iAotuAANMService;
	
	//申请其他代言主播
	@Resource
	protected IAotuAARMService iAotuAARMService;

	//代言主播管理
	@Resource
	protected IAotuAnchorMService iAotuAnchorMService;

}
