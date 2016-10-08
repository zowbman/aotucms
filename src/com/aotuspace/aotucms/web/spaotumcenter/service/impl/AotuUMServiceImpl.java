package com.aotuspace.aotucms.web.spaotumcenter.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAotuspaceRole;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersBinfo;
import com.aotuspace.aotucms.web.spaotumcenter.service.IAotuRMService;
import com.aotuspace.aotucms.web.spaotumcenter.service.IAotuUMService;

@SuppressWarnings("unchecked")
@Transactional
@Service
public class AotuUMServiceImpl extends DaoSupportImpl<SpUsersBinfo> implements IAotuUMService {

	@Resource
	IAotuRMService iAotuRMService;
	
	//用户列表
	@Override
	public PageBean<SpUsersBinfo> findSpUsersBinfoList(int rows, int page) throws Exception {
		//列表数据
		List<SpUsersBinfo> spUsersBinfoList = getSession().createQuery("FROM SpUsersBinfo s")
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
		//总记录数
		Long count = (Long) getSession().createQuery("SELECT COUNT(spUsersBinfoKey.spId) FROM SpUsersBinfo")
				.uniqueResult();
		return new PageBean<SpUsersBinfo>(count.intValue(), spUsersBinfoList);
	}

	//判断用户名是否重复
	@Override
	public SpUsersBinfo findBySpAccount(String spAccount) throws Exception {
		return (SpUsersBinfo) getSession().createQuery("FROM SpUsersBinfo s WHERE s.spAccount=?")//
				.setParameter(0, spAccount).uniqueResult();
	}
	
	//判断用户名是否重复
	@Override
	public SpUsersBinfo findBySpAtuId(int spAtuId) throws Exception {
		return (SpUsersBinfo) getSession().createQuery("FROM SpUsersBinfo s WHERE s.spUsersBinfoKey.spAtuid=?")//
				.setParameter(0, spAtuId).uniqueResult();
	}

	//根据 凹凸id spAutoid最大值进行自增
	@Override
	public Integer findByMaxAutoid() throws Exception {
		return (Integer) getSession().createQuery("SELECT MAX(spUsersBinfoKey.spAtuid) FROM SpUsersBinfo")
				.uniqueResult();
	}

	//查询还没有此角色的用户列表
	@Override
	public List<SpUsersBinfo> findSpUsersBinfosNoRole(int spId) throws Exception {
		//获取当前角色
		SpAotuspaceRole spAotuspaceRole = iAotuRMService.getById(spId);
		Set<Integer> ids = new HashSet<Integer>();
		for (SpUsersBinfo spBinfo : spAotuspaceRole.getSpUsersBinfos()) {
			ids.add(spBinfo.getSpUsersBinfoKey().getSpId());
		}
		if (ids == null || ids.size() == 0) {
			return (List<SpUsersBinfo>) getSession().createQuery("FROM SpUsersBinfo").list();
		} else {
			return (List<SpUsersBinfo>) getSession()
					.createQuery("FROM SpUsersBinfo sb WHERE sb.spUsersBinfoKey.spId not in (:ids)")
					.setParameterList("ids", ids).list();
		}
	}
}
