package com.aotuspace.aotucms.web.spsumcenter.service;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsumcenter.hbm.SpSupplierApplication;
/**
 * 
 * Title:ISysUAMService
 * Description:��Ӧ���������
 * Company:aotuspace
 * @author    sida
 * @date      2015-11-2 ����11:56:45
 *
 */
public interface ISysUAMService extends DaoSupport<SpSupplierApplication> {

	//��ѯ��Ӧ���б�(��ҳ)
	PageBean<SpSupplierApplication> findSpSupplierApplicationList(int rows,
			int page);

}
