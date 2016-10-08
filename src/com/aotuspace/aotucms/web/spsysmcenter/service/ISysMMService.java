package com.aotuspace.aotucms.web.spsysmcenter.service;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeBinfo;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeBinfoKey;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeDinfo;

/**
 * 
 * Title:ISysMMService
 * Description:系统管理员管理service接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:26:40
 *
 */

public interface ISysMMService extends DaoSupport<SpEmployeeBinfo> {

	//根据登录名与密码进行查询用户(登录)
	SpEmployeeBinfo findByEpAccountAndEpPassword(String spEpaccount,String spEppassword) throws Exception;

	//查询系统管理员列表（分页）
	PageBean<SpEmployeeBinfo> findSpEmployeeBinfoList(int rows, int page) throws Exception;
	
	//根据组织机构查询系统管理员列表
	PageBean<SpEmployeeBinfo> findSpEmployeeBinfoListByDepart(int rows, int page,Integer[] departIds) throws Exception;

	//判断用户名是否重复
	SpEmployeeBinfo findBySpEpAccount(String spEpaccount)throws Exception;

	//根据员工id spEpid最大值进行自增
	Integer findByMaxEpId()throws Exception;
	
	//批量删除员工数据 hql批量删除无法实现级联删除 
	/*int deleteSpEmployeeBinfoByIds(Integer ids[])throws Exception;*/
	SpEmployeeDinfo findSpEmployeeDinfoById (SpEmployeeBinfoKey key)throws Exception;
	
	//查询还没有此角色的员工列表
	List<SpEmployeeBinfo> findSpEmployeeBinfosNoStation(int spId) throws Exception;
}
