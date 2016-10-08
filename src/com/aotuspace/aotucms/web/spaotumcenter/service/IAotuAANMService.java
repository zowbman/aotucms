package com.aotuspace.aotucms.web.spaotumcenter.service;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAnchorApplication;

/**
 * 
 * Title:IAotuAANMService
 * Description:�����������service�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-14 ����5:14:41
 *
 */
public interface IAotuAANMService extends DaoSupport<SpAnchorApplication>{
	//��ѯ���������б���ҳ��
	PageBean<SpAnchorApplication> findSpAnchorApplicationList(int rows, int page) throws Exception;
}
