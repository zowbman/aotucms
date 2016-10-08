package com.aotuspace.aotucms.web.spsysmcenter.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeBinfo;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeBinfoKey;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeDinfo;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeStation;
import com.aotuspace.aotucms.web.spsysmcenter.service.ISysMMService;
import com.aotuspace.aotucms.web.spsysmcenter.service.ISysRMService;

/**
 * 
 * Title:SysMMServiceImpl
 * Description:ϵͳ����Ա����serviceʵ����
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:27:49
 *
 */

@Service
@Transactional
@SuppressWarnings("unchecked")
public class SysMMServiceImpl extends DaoSupportImpl<SpEmployeeBinfo> implements ISysMMService {
	
	@Resource
	ISysRMService iSysRMService;
	
	//��¼
	public SpEmployeeBinfo findByEpAccountAndEpPassword(String spEpaccount,
			String spEppassword) throws Exception {
		return (SpEmployeeBinfo) getSession().createQuery(
				"FROM SpEmployeeBinfo s WHERE s.spEpaccount=? AND s.spEppassword=? ")
				.setParameter(0,spEpaccount)
				.setParameter(1,spEppassword)
				.uniqueResult();
	}

	@Override
	public PageBean<SpEmployeeBinfo> findSpEmployeeBinfoList(int rows, int page) throws Exception {
		//�б�����
		List<SpEmployeeBinfo> spEmployeeBinfoList = getSession()
				.createQuery("FROM SpEmployeeBinfo s")
				.setFirstResult(rows*(page-1))
				.setMaxResults(rows)
				.list();
		//�ܼ�¼��
		Long count = (Long) getSession().
				createQuery("SELECT COUNT(spEmployeeBinfoKey.spId) FROM SpEmployeeBinfo")
				.uniqueResult();
		return new PageBean<SpEmployeeBinfo>(count.intValue(), spEmployeeBinfoList);
	}
	
	

	@Override
	public PageBean<SpEmployeeBinfo> findSpEmployeeBinfoListByDepart(int rows, int page, Integer[] departIds) throws Exception {
		//�б�����
		List<SpEmployeeBinfo> spEmployeeBinfoList = getSession()
				.createQuery("FROM SpEmployeeBinfo s WHERE spEmployeeDepart.spId in (:departIds)")
				.setFirstResult(rows * (page - 1))
				.setMaxResults(rows)
				.setParameterList("departIds", departIds)
				.list();
		//�ܼ�¼��
		Long count = (Long) getSession()
				.createQuery("SELECT COUNT(spEmployeeBinfoKey.spId) FROM SpEmployeeBinfo WHERE spEmployeeDepart.spId in (:departIds)")
				.setParameterList("departIds", departIds)
				.uniqueResult();
		return new PageBean<SpEmployeeBinfo>(count.intValue(), spEmployeeBinfoList);
	}

	//�����û����Ƿ��ظ������û�ע��
	public SpEmployeeBinfo findBySpEpAccount(String spEpaccount) throws Exception{
		return (SpEmployeeBinfo) getSession().createQuery("FROM SpEmployeeBinfo s WHERE s.spEpaccount=?")//
				.setParameter(0, spEpaccount)
				.uniqueResult();
	}

	//����Ա��id���в�ѯ
	public Integer findByMaxEpId()throws Exception{
		return (Integer) getSession()
				.createQuery("SELECT MAX(spEmployeeBinfoKey.spEpid) FROM SpEmployeeBinfo")
				.uniqueResult();
	}

	//����ɾ��Ա�����ݣ��޷�����������
	/*@Override
	public int deleteSpEmployeeBinfoByIds(Integer[] ids) throws Exception {
		Query queryDelete = getSession().createQuery(
				"Delete FROM SpEmployeeBinfo Where spEmployeeBinfoKey.spId in (:ids)");
		queryDelete.setParameterList("ids", ids);
		int resultDelete=queryDelete.executeUpdate();
		return resultDelete;
	}*/

	@Override
	public SpEmployeeDinfo findSpEmployeeDinfoById(SpEmployeeBinfoKey key) throws Exception {
		return (SpEmployeeDinfo) getSession().get(SpEmployeeDinfo.class, key);
	}

	//��ѯ��û�д˽�ɫ��Ա���б�
	@Override
	public List<SpEmployeeBinfo> findSpEmployeeBinfosNoStation(int spId) throws Exception {
		//��ȡ��ǰ��ɫ
		SpEmployeeStation spEmployeeStation =iSysRMService.getById(spId);
		Set<Integer> ids=new HashSet<Integer>();
		for (SpEmployeeBinfo spBinfo : spEmployeeStation.getSpEmployeeBinfos()) {
			ids.add(spBinfo.getSpEmployeeBinfoKey().getSpId());
		}
		if(ids==null||ids.size()==0){
			return (List<SpEmployeeBinfo>) getSession().createQuery("FROM SpEmployeeBinfo").list();
		}else{
			return (List<SpEmployeeBinfo>) getSession().createQuery("FROM SpEmployeeBinfo sb WHERE spEmployeeBinfoKey.spId not in (:ids)")
					.setParameterList("ids", ids).list();
		}
	}
}
