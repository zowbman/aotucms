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
	
	//�û��б�
	@Override
	public PageBean<SpUsersBinfo> findSpUsersBinfoList(int rows, int page) throws Exception {
		//�б�����
		List<SpUsersBinfo> spUsersBinfoList = getSession().createQuery("FROM SpUsersBinfo s")
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
		//�ܼ�¼��
		Long count = (Long) getSession().createQuery("SELECT COUNT(spUsersBinfoKey.spId) FROM SpUsersBinfo")
				.uniqueResult();
		return new PageBean<SpUsersBinfo>(count.intValue(), spUsersBinfoList);
	}

	//�ж��û����Ƿ��ظ�
	@Override
	public SpUsersBinfo findBySpAccount(String spAccount) throws Exception {
		return (SpUsersBinfo) getSession().createQuery("FROM SpUsersBinfo s WHERE s.spAccount=?")//
				.setParameter(0, spAccount).uniqueResult();
	}
	
	//�ж��û����Ƿ��ظ�
	@Override
	public SpUsersBinfo findBySpAtuId(int spAtuId) throws Exception {
		return (SpUsersBinfo) getSession().createQuery("FROM SpUsersBinfo s WHERE s.spUsersBinfoKey.spAtuid=?")//
				.setParameter(0, spAtuId).uniqueResult();
	}

	//���� ��͹id spAutoid���ֵ��������
	@Override
	public Integer findByMaxAutoid() throws Exception {
		return (Integer) getSession().createQuery("SELECT MAX(spUsersBinfoKey.spAtuid) FROM SpUsersBinfo")
				.uniqueResult();
	}

	//��ѯ��û�д˽�ɫ���û��б�
	@Override
	public List<SpUsersBinfo> findSpUsersBinfosNoRole(int spId) throws Exception {
		//��ȡ��ǰ��ɫ
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
