package com.aotuspace.aotucms.web.spsumcenter.service;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsumcenter.hbm.SpSupplierBinfo;
/**
 * 
 * Title:ISysSMMService
 * Description:ϵͳ��Ӧ�̹���service�ӿ�
 * Company:aotuspace
 * @author    sida
 * @date      2015-10-12 ����3:47:44
 *
 */
public interface ISysUMMService extends DaoSupport<SpSupplierBinfo>  {
	
	//�жϹ�Ӧ���������Ƿ��ظ�
	SpSupplierBinfo findBySpSuAccount(String spSuaccount) throws Exception;

	//���ݹ�Ӧ��id  spSuid���ֵ��������
	Integer findByMaxSuId() throws Exception;

	//��ѯ��Ӧ���б���ҳ��
	PageBean<SpSupplierBinfo> findSpSupplierBinfoList(int rows, int page) throws Exception;

}
