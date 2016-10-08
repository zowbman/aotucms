package com.aotuspace.aotucms.web.spaotumcenter.service;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAotuspacePriv;

/**
 * 
 * Title:IAotuPMService
 * Description:��͹�ռ�Ȩ�޹���ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-13 ����4:09:35
 *
 */
public interface IAotuPMService extends DaoSupport<SpAotuspacePriv> {

	//��ѯȨ���б�(��ҳ)
	PageBean<SpAotuspacePriv> findSpAotuspaceTopPrivList(int rows, int page) throws Exception;
	
	//���Ȩ�޸��ڵ�TreeData
	List<SpAotuspacePriv> findSpAotuspacePrivTreeData() throws Exception;
	
	//���Ȩ�޸��ڵ�ComboTreeData
	List<SpAotuspacePriv> findSpAotuspacePrivComboTreeData() throws Exception;
}
