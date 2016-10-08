package com.aotuspace.aotucms.web.spaotumcenter.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAotuspaceRole;
import com.aotuspace.aotucms.web.spaotumcenter.service.IAotuRMService;


@Transactional
@Service
@SuppressWarnings("unchecked")
public class AotuRMServiceImpl extends DaoSupportImpl<SpAotuspaceRole>  implements IAotuRMService {

	@Override
	public PageBean<SpAotuspaceRole> findSpAotuspaceTopRoleList(int rows, int page) throws Exception {
		//列表数据
		List<SpAotuspaceRole> spAotuspaceRoleList = getSession()
				.createQuery("FROM SpAotuspaceRole s WHERE s.spAotuspaceRoleparent.spId IS NULL")
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
		//总记录数
		Long count = (Long) getSession().createQuery(
				"SELECT COUNT(s.spId) FROM SpAotuspaceRole s WHERE s.spAotuspaceRoleparent.spId IS NULL").uniqueResult();
		return new PageBean<SpAotuspaceRole>(count.intValue(), spAotuspaceRoleList);
	}

	//角色treeData
	@Override
	public List<SpAotuspaceRole> findSpAotuspaceRoleTreeData() throws Exception {
		return getSession().createQuery("FROM SpAotuspaceRole s WHERE s.spAotuspaceRoleparent.spId IS NULL").list();
	}

}
