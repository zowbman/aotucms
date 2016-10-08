package com.aotuspace.aotucms.web.spsumcenter.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsumcenter.hbm.SpSupplierApplication;
import com.aotuspace.aotucms.web.spsumcenter.service.ISysUAMService;
/**
 * 
 * Title:SysUAMServiceImpl
 * Description:供应商申请管理
 * Company:aotuspace
 * @author    sida
 * @date      2015-11-2 上午11:57:11
 *
 */
@Transactional
@Service
public class SysUAMServiceImpl extends DaoSupportImpl<SpSupplierApplication> implements ISysUAMService {

	//申请供应商列表
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public PageBean<SpSupplierApplication> findSpSupplierApplicationList(
			int rows, int page) {
		//列表数据
		List<SpSupplierApplication> spSupplierApplicationList = getSession().createQuery("FROM SpSupplierApplication")
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
		//总记录数
		Long count = (Long) getSession().createQuery("SELECT COUNT(spId) FROM SpSupplierApplication").uniqueResult();
		return new PageBean<SpSupplierApplication>(count.intValue(),spSupplierApplicationList);
	}
	
}
