package com.aotuspace.aotucms.web.spsumcenter.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsumcenter.hbm.SpSupplierBinfo;
import com.aotuspace.aotucms.web.spsumcenter.service.ISysUMMService;

@Transactional
@Service
public class SysUMMServiceImpl extends DaoSupportImpl<SpSupplierBinfo> implements ISysUMMService {

	//根据用户名是否重复给予供应商注册
	public SpSupplierBinfo findBySpSuAccount(String spSuaccount)
			throws Exception {
		return (SpSupplierBinfo) getSession().createQuery("FROM SpSupplierBinfo s WHERE s.spSuaccount=?").setParameter(0, spSuaccount).uniqueResult();
	}

	//根据员工id进行查询
	public Integer findByMaxSuId() throws Exception {
		return (Integer) getSession().createQuery("SELECT MAX (spSupplierBinfoKey.spSuid)FROM SpSupplierBinfo").uniqueResult();
	}

	//供应商列表
	@SuppressWarnings("unchecked")
	@Override
	public PageBean<SpSupplierBinfo> findSpSupplierBinfoList(int rows, int page)
			throws Exception {
		//列表数据
		List<SpSupplierBinfo> spSupplierBinfoList = getSession().createQuery("FROM SpSupplierBinfo s").setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
		
		//总记录数
		Long count = (Long) getSession().createQuery("SELECT COUNT(spSupplierBinfoKey.spId) FROM SpSupplierBinfo").uniqueResult();
		return new PageBean<SpSupplierBinfo>(count.intValue(),spSupplierBinfoList);
	}
	
	
}
