package com.aotuspace.aotucms.web.spaotumcenter.service;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAotuspaceRole;

/**
 * 
 * Title:IAotuURService
 * Description:��͹�ռ��û���ɫservice
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-12 ����7:07:57
 *
 */
public interface IAotuRMService extends DaoSupport<SpAotuspaceRole> {
	
	//��ѯ��ɫ�б���ҳ��
	PageBean<SpAotuspaceRole> findSpAotuspaceTopRoleList(int rows, int page) throws Exception;
	
	//��ӽ�ɫ�����comboTreeData
	List<SpAotuspaceRole> findSpAotuspaceRoleTreeData() throws Exception;
}
