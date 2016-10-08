package com.aotuspace.aotucms.web.spsysmcenter.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeDepart;
import com.aotuspace.aotucms.web.spsysmcenter.service.ISysDMService;

@Transactional
@Service
@SuppressWarnings("unchecked")
public class SysDMServiceImpl extends DaoSupportImpl<SpEmployeeDepart> implements ISysDMService{
	
	//��ѯ�����б�(��ҳ)
	@Override
	public PageBean<SpEmployeeDepart> findSpEmployeeTopDepartList(int rows,
			int page) throws Exception {
		//�б�����
		List<SpEmployeeDepart> spEmployeeDepartList = getSession().createQuery
				("FROM SpEmployeeDepart s WHERE s.spEpdeparent.spId IS NULL")
				.setFirstResult(rows * (page - 1))
				.setMaxResults(rows)
				.list();
		//�ܼ�¼��
		Long count = (Long) getSession().createQuery("SELECT COUNT(s.spId) FROM SpEmployeeDepart s WHERE s.spEpdeparent.spId IS NULL").uniqueResult();
		return new PageBean<SpEmployeeDepart>(count.intValue(),spEmployeeDepartList);
	}

	//����treeData
	@Override
	public List<SpEmployeeDepart> findSpEmployeeDepartTreeData() throws Exception {
		return getSession().createQuery
				("FROM SpEmployeeDepart s WHERE s.spEpdeparent.spId IS NULL")
				.list();
	}
}
