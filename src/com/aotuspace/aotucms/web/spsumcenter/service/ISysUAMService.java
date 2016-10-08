package com.aotuspace.aotucms.web.spsumcenter.service;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsumcenter.hbm.SpSupplierApplication;
/**
 * 
 * Title:ISysUAMService
 * Description:供应商申请管理
 * Company:aotuspace
 * @author    sida
 * @date      2015-11-2 上午11:56:45
 *
 */
public interface ISysUAMService extends DaoSupport<SpSupplierApplication> {

	//查询供应商列表(分页)
	PageBean<SpSupplierApplication> findSpSupplierApplicationList(int rows,
			int page);

}
