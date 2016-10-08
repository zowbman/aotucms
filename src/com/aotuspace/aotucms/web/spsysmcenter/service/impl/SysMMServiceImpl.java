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
 * Description:系统管理员管理service实现类
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:27:49
 *
 */

@Service
@Transactional
@SuppressWarnings("unchecked")
public class SysMMServiceImpl extends DaoSupportImpl<SpEmployeeBinfo> implements ISysMMService {
	
	@Resource
	ISysRMService iSysRMService;
	
	//登录
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
		//列表数据
		List<SpEmployeeBinfo> spEmployeeBinfoList = getSession()
				.createQuery("FROM SpEmployeeBinfo s")
				.setFirstResult(rows*(page-1))
				.setMaxResults(rows)
				.list();
		//总记录数
		Long count = (Long) getSession().
				createQuery("SELECT COUNT(spEmployeeBinfoKey.spId) FROM SpEmployeeBinfo")
				.uniqueResult();
		return new PageBean<SpEmployeeBinfo>(count.intValue(), spEmployeeBinfoList);
	}
	
	

	@Override
	public PageBean<SpEmployeeBinfo> findSpEmployeeBinfoListByDepart(int rows, int page, Integer[] departIds) throws Exception {
		//列表数据
		List<SpEmployeeBinfo> spEmployeeBinfoList = getSession()
				.createQuery("FROM SpEmployeeBinfo s WHERE spEmployeeDepart.spId in (:departIds)")
				.setFirstResult(rows * (page - 1))
				.setMaxResults(rows)
				.setParameterList("departIds", departIds)
				.list();
		//总记录数
		Long count = (Long) getSession()
				.createQuery("SELECT COUNT(spEmployeeBinfoKey.spId) FROM SpEmployeeBinfo WHERE spEmployeeDepart.spId in (:departIds)")
				.setParameterList("departIds", departIds)
				.uniqueResult();
		return new PageBean<SpEmployeeBinfo>(count.intValue(), spEmployeeBinfoList);
	}

	//根据用户名是否重复给予用户注册
	public SpEmployeeBinfo findBySpEpAccount(String spEpaccount) throws Exception{
		return (SpEmployeeBinfo) getSession().createQuery("FROM SpEmployeeBinfo s WHERE s.spEpaccount=?")//
				.setParameter(0, spEpaccount)
				.uniqueResult();
	}

	//根据员工id进行查询
	public Integer findByMaxEpId()throws Exception{
		return (Integer) getSession()
				.createQuery("SELECT MAX(spEmployeeBinfoKey.spEpid) FROM SpEmployeeBinfo")
				.uniqueResult();
	}

	//批量删除员工数据（无法级联操作）
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

	//查询还没有此角色的员工列表
	@Override
	public List<SpEmployeeBinfo> findSpEmployeeBinfosNoStation(int spId) throws Exception {
		//获取当前角色
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
