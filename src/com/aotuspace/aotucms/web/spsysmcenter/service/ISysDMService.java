package com.aotuspace.aotucms.web.spsysmcenter.service;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeDepart;

public interface ISysDMService extends DaoSupport<SpEmployeeDepart> {

	//查询部门列表(分页)
	PageBean<SpEmployeeDepart> findSpEmployeeTopDepartList(int rows, int page) throws Exception;
	
	//添加部门父节点comboTreeData
	List<SpEmployeeDepart> findSpEmployeeDepartTreeData() throws Exception;

}
