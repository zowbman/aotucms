package com.aotuspace.aotucms.web.spsysmcenter.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeStation;
import com.aotuspace.aotucms.web.spsysmcenter.service.ISysRMService;

/**
 * 
 * Title:SysRMServiceImpl
 * Description:ϵͳ��ɫ����serviceʵ����
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:28:20
 *
 */
@Service
@Transactional
@SuppressWarnings("unchecked")
public class SysRMServiceImpl extends DaoSupportImpl<SpEmployeeStation> implements ISysRMService{

	//��ѯ��ɫ�б�(��ҳ)
	@Override
	public PageBean<SpEmployeeStation> findSpEmployeeTopStationList(int rows,
			int page) throws Exception {
		//�б�����
		List<SpEmployeeStation> spEmployeeStationList = getSession().createQuery
				("FROM SpEmployeeStation s WHERE s.spEpstparent.spId IS NULL")
				.setFirstResult(rows * (page - 1))
				.setMaxResults(rows)
				.list();
		//�ܼ�¼��
		Long count  = (Long) getSession().createQuery("SELECT COUNT(s.spId) FROM SpEmployeeStation s WHERE s.spEpstparent.spId IS NULL").uniqueResult();
		return new PageBean<SpEmployeeStation>(count.intValue(),spEmployeeStationList);
	}

	//��ɫtreeData
	@Override
	public List<SpEmployeeStation> findSpEmployeeStationTreeData()
			throws Exception {
		return getSession().createQuery
				("FROM SpEmployeeStation s WHERE s.spEpstparent.spId IS NULL")
				.list();
	}

	
	
}
