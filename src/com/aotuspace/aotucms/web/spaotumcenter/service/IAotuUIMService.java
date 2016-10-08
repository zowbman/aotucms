package com.aotuspace.aotucms.web.spaotumcenter.service;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersIdentity;

/**
 * 
 * Title:IAotuUMService
 * Description:�û�����service
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-12 ����10:14:46
 *
 */
public interface IAotuUIMService extends DaoSupport<SpUsersIdentity> {
	//��ѯ�û�����б���ҳ��
	PageBean<SpUsersIdentity> findspUsersIdentityList(int rows, int page) throws Exception;
	
	//�����������Ƿ��ظ�
	SpUsersIdentity findBySpIdentityn(String spIdentityn) throws Exception;
	
}
