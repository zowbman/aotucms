package com.aotuspace.aotucms.web.spsysmcenter.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeePrivilege;
import com.aotuspace.aotucms.web.spsysmcenter.service.ISysPMService;

/**
 * 
 * Title:SysPMServiceImpl
 * Description:系统权限管理service实现类
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:28:06
 *
 */
@Service
@Transactional
@SuppressWarnings("unchecked")
public class SysPMServiceImpl extends DaoSupportImpl<SpEmployeePrivilege> implements ISysPMService {

	@Override
	public List<SpEmployeePrivilege> findTopList() {
		return getSession().createQuery("From SpEmployeePrivilege p Where p.spEpurl Is Null AND p.spEpparent.spId Is NULL AND p.spEpname<>'凹凸空间进销存管理中心'").list();
	}
	
	

	@Override
	public Collection<String> getAllPrivilegeUrls() {
		return getSession().createQuery("SELECT DISTINCT p.spEpurl From SpEmployeePrivilege p Where p.spEpurl Is not Null AND p.spEpparent.spId Is NULL AND p.spEpname<>'凹凸空间进销存管理中心'").list();
	}



	//查询权限列表(分页)
	@Override
	public PageBean<SpEmployeePrivilege> findSpemployeeTopPrivilegeList(int rows, int page) throws Exception {
		// 列表数据
		List<SpEmployeePrivilege> spEmployeePrivilegeList = getSession()
				.createQuery("FROM SpEmployeePrivilege s WHERE s.spEpparent.spId IS NULL")
				.setFirstResult(rows * (page - 1))
				.setMaxResults(rows)
				.list();
		//总记录数
		Long count = (Long) getSession().createQuery(
				"Select COUNT(s.spId) FROM SpEmployeePrivilege s WHERE s.spEpparent.spId IS NULL").uniqueResult();
		return new PageBean<SpEmployeePrivilege>(count.intValue(), spEmployeePrivilegeList);
	}

	//权限treeData
	@Override
	public List<SpEmployeePrivilege> findSpEmployeePrivilegeTreeData() throws Exception {
		return getSession().createQuery("FROM SpEmployeePrivilege s WHERE s.spEpparent.spId IS NULL").list();
	}

	//根据父节点查询权限
	@Override
	public List<SpEmployeePrivilege> findSpEmployeePrivilegeByParentId(int parentId) throws Exception {
		return getSession().createQuery("FROM SpEmployeePrivilege s WHERE s.spEpparent.spId=:parentId")
				.setParameter("parentId", parentId)
				.list();
	}
	
	
}
