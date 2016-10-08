package com.aotuspace.aotucms.web.spaotumcenter.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAotuspacePriv;
import com.aotuspace.aotucms.web.spaotumcenter.service.IAotuPMService;

/**
 * 
 * Title:AotuPMServiceImpl
 * Description:凹凸空间权限管理接口实现类
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-13 下午4:09:52
 *
 */

@Service
@Transactional
@SuppressWarnings("unchecked")
public class AotuPMServiceImpl extends DaoSupportImpl<SpAotuspacePriv> implements IAotuPMService {

	//查询权限列表(分页)
	@Override
	public PageBean<SpAotuspacePriv> findSpAotuspaceTopPrivList(int rows, int page) throws Exception {
		// 列表数据
		List<SpAotuspacePriv> spAotuspacePrivList = getSession()
				.createQuery("FROM SpAotuspacePriv s WHERE s.privParent.spId IS NULL")
				.setFirstResult(rows * (page - 1))
				.setMaxResults(rows)
				.list();
		//总记录数
		Long count = (Long) getSession().createQuery(
				"Select COUNT(s.spId) FROM SpAotuspacePriv s WHERE s.privParent.spId IS NULL").uniqueResult();
		return new PageBean<SpAotuspacePriv>(count.intValue(), spAotuspacePrivList);
	}

	//添加权限父节点TreeData
	@Override
	public List<SpAotuspacePriv> findSpAotuspacePrivTreeData() throws Exception {
		return getSession().createQuery("FROM SpAotuspacePriv s WHERE s.privParent.spId IS NULL").list();
	}
	
	//添加权限父节点comboTreeData
	@Override
	public List<SpAotuspacePriv> findSpAotuspacePrivComboTreeData() throws Exception {
		return getSession().createQuery("FROM SpAotuspacePriv s")
				.list();
		/*return getSession()
				.createCriteria(SpAotuspacePriv.class)
				.createAlias("privsChildren", "pc")
				.add(Restrictions.or(Restrictions.isNull("privParent.spId")
						,Restrictions.and(Restrictions.isNotNull("privParent.spId"),Restrictions.isNotNull("pc.spId")))).list();*/
	}
	
}
