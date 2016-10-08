package com.aotuspace.aotucms.web.spsysmcenter.service;

import java.util.Collection;
import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeePrivilege;

/**
 * 
 * Title:ISysPMService
 * Description:系统权限管理service接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:27:00
 *
 */
public interface ISysPMService extends DaoSupport<SpEmployeePrivilege>	{
	/**
	 * 查询所有顶的级权限
	 * @return
	 */
	List<SpEmployeePrivilege> findTopList();
	
	/**
	 * 查询所有权限
	 * @return
	 */
	Collection<String> getAllPrivilegeUrls();

	//查询权限列表(分页)
	PageBean<SpEmployeePrivilege> findSpemployeeTopPrivilegeList(int rows, int page) throws Exception;
	
	//添加权限父节点comboTreeData
	List<SpEmployeePrivilege> findSpEmployeePrivilegeTreeData() throws Exception;
	
	//根据父节点查询权限
	List<SpEmployeePrivilege> findSpEmployeePrivilegeByParentId(int parentId) throws Exception;
}
