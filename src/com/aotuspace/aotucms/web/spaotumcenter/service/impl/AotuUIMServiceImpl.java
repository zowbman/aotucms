package com.aotuspace.aotucms.web.spaotumcenter.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersIdentity;
import com.aotuspace.aotucms.web.spaotumcenter.service.IAotuUIMService;

@SuppressWarnings("unchecked")
@Transactional
@Service
public class AotuUIMServiceImpl extends DaoSupportImpl<SpUsersIdentity> implements IAotuUIMService {
	
	@Override
	public PageBean<SpUsersIdentity> findspUsersIdentityList(int rows, int page) throws Exception {
		//列表数据
		List<SpUsersIdentity> spUsersIdentityList = getSession().createQuery("FROM SpUsersIdentity s")
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
		//总记录数
		Long count = (Long) getSession().createQuery("SELECT COUNT(spId) FROM SpUsersIdentity")
				.uniqueResult();
		return new PageBean<SpUsersIdentity>(count.intValue(), spUsersIdentityList);
	}

	@Override
	public SpUsersIdentity findBySpIdentityn(String spIdentityn) throws Exception {
		return (SpUsersIdentity) getSession().createQuery("FROM SpUsersIdentity s WHERE s.spIdentityn=?")//
				.setParameter(0, spIdentityn)
				.uniqueResult();
	}
	
}
