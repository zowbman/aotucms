package com.aotuspace.aotucms.web.spsumcenter.service;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsumcenter.hbm.SpSupplierBinfo;
/**
 * 
 * Title:ISysSMMService
 * Description:系统供应商管理service接口
 * Company:aotuspace
 * @author    sida
 * @date      2015-10-12 下午3:47:44
 *
 */
public interface ISysUMMService extends DaoSupport<SpSupplierBinfo>  {
	
	//判断供应商与密码是否重复
	SpSupplierBinfo findBySpSuAccount(String spSuaccount) throws Exception;

	//根据供应商id  spSuid最大值进行自增
	Integer findByMaxSuId() throws Exception;

	//查询供应商列表（分页）
	PageBean<SpSupplierBinfo> findSpSupplierBinfoList(int rows, int page) throws Exception;

}
