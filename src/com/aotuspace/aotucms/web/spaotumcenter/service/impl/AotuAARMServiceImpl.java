package com.aotuspace.aotucms.web.spaotumcenter.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpArtistApplication;
import com.aotuspace.aotucms.web.spaotumcenter.service.IAotuAARMService;

/**
 * 
 * Title:AotuAMServiceImpl
 * Description: �����������serviceʵ����
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-14 ����5:16:09
 *
 */

@Service
@Transactional
@SuppressWarnings("unchecked")
public class AotuAARMServiceImpl extends DaoSupportImpl<SpArtistApplication> implements IAotuAARMService {
	//�����������������б�
	@Override
	public PageBean<SpArtistApplication> findSpArtistApplicationList(int rows, int page) throws Exception {
		//�б�����
		List<SpArtistApplication> spArtistApplicationList = getSession()
				.createQuery("FROM SpArtistApplication a WHERE a.spApplicationResult.spId=1")
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
		//�ܼ�¼��
		Long count = (Long) getSession().createQuery(
				"SELECT COUNT(spId) FROM SpArtistApplication a WHERE a.spApplicationResult.spId=1").uniqueResult();
		return new PageBean<SpArtistApplication>(count.intValue(), spArtistApplicationList);
	}

}
