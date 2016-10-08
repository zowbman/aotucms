package com.aotuspace.aotucms.web.spaotumcenter.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAnchorApplication;
import com.aotuspace.aotucms.web.spaotumcenter.service.IAotuAANMService;

/**
 * 
 * Title:AotuAMServiceImpl
 * Description: 申请代言主播service实现类
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-14 下午5:16:09
 *
 */

@Service
@Transactional
@SuppressWarnings("unchecked")
public class AotuAANMServiceImpl extends DaoSupportImpl<SpAnchorApplication> implements IAotuAANMService {
	//申请代言主播列表
	@Override
	public PageBean<SpAnchorApplication> findSpAnchorApplicationList(int rows, int page) throws Exception {
		//列表数据
		List<SpAnchorApplication> spAnchorApplicationList = getSession()
				.createQuery("FROM SpAnchorApplication a WHERE a.spApplicationResult.spId=1")
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
		//总记录数
		Long count = (Long) getSession().createQuery(
				"SELECT COUNT(spId) FROM SpAnchorApplication a WHERE a.spApplicationResult.spId=1").uniqueResult();
		return new PageBean<SpAnchorApplication>(count.intValue(), spAnchorApplicationList);
	}
}
