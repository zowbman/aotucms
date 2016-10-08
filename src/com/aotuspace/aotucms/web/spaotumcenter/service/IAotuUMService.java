package com.aotuspace.aotucms.web.spaotumcenter.service;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersBinfo;

/**
 * 
 * Title:IAotuUMService
 * Description:用户管理service
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-12 上午10:14:46
 *
 */
public interface IAotuUMService extends DaoSupport<SpUsersBinfo> {
	//查询用户列表（分页）
	PageBean<SpUsersBinfo> findSpUsersBinfoList(int rows, int page) throws Exception;
	
	//判断用户名是否重复
	SpUsersBinfo findBySpAccount(String spAccount)throws Exception;
	
	//根据凹凸id查询用户
	SpUsersBinfo findBySpAtuId(int spAtuId) throws Exception;
	
	//根据 凹凸id spAutoid最大值进行自增
	Integer findByMaxAutoid()throws Exception;
	
	//查询还没有此角色的员工列表
	List<SpUsersBinfo> findSpUsersBinfosNoRole(int spId) throws Exception;
	
	
}
