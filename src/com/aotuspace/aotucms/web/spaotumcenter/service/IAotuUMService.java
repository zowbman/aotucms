package com.aotuspace.aotucms.web.spaotumcenter.service;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersBinfo;

/**
 * 
 * Title:IAotuUMService
 * Description:�û�����service
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-12 ����10:14:46
 *
 */
public interface IAotuUMService extends DaoSupport<SpUsersBinfo> {
	//��ѯ�û��б���ҳ��
	PageBean<SpUsersBinfo> findSpUsersBinfoList(int rows, int page) throws Exception;
	
	//�ж��û����Ƿ��ظ�
	SpUsersBinfo findBySpAccount(String spAccount)throws Exception;
	
	//���ݰ�͹id��ѯ�û�
	SpUsersBinfo findBySpAtuId(int spAtuId) throws Exception;
	
	//���� ��͹id spAutoid���ֵ��������
	Integer findByMaxAutoid()throws Exception;
	
	//��ѯ��û�д˽�ɫ��Ա���б�
	List<SpUsersBinfo> findSpUsersBinfosNoRole(int spId) throws Exception;
	
	
}
