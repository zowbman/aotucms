package com.aotuspace.aotucms.web.spsysmcenter.service;

import java.util.Collection;
import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeePrivilege;

/**
 * 
 * Title:ISysPMService
 * Description:ϵͳȨ�޹���service�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:27:00
 *
 */
public interface ISysPMService extends DaoSupport<SpEmployeePrivilege>	{
	/**
	 * ��ѯ���ж��ļ�Ȩ��
	 * @return
	 */
	List<SpEmployeePrivilege> findTopList();
	
	/**
	 * ��ѯ����Ȩ��
	 * @return
	 */
	Collection<String> getAllPrivilegeUrls();

	//��ѯȨ���б�(��ҳ)
	PageBean<SpEmployeePrivilege> findSpemployeeTopPrivilegeList(int rows, int page) throws Exception;
	
	//���Ȩ�޸��ڵ�comboTreeData
	List<SpEmployeePrivilege> findSpEmployeePrivilegeTreeData() throws Exception;
	
	//���ݸ��ڵ��ѯȨ��
	List<SpEmployeePrivilege> findSpEmployeePrivilegeByParentId(int parentId) throws Exception;
}
