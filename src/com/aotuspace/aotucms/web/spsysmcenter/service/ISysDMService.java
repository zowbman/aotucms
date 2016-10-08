package com.aotuspace.aotucms.web.spsysmcenter.service;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeDepart;

public interface ISysDMService extends DaoSupport<SpEmployeeDepart> {

	//��ѯ�����б�(��ҳ)
	PageBean<SpEmployeeDepart> findSpEmployeeTopDepartList(int rows, int page) throws Exception;
	
	//��Ӳ��Ÿ��ڵ�comboTreeData
	List<SpEmployeeDepart> findSpEmployeeDepartTreeData() throws Exception;

}
