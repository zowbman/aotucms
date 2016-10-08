package com.aotuspace.aotucms.web.spaotumcenter.service;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAotuspaceRole;

/**
 * 
 * Title:IAotuURService
 * Description:凹凸空间用户角色service
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-12 下午7:07:57
 *
 */
public interface IAotuRMService extends DaoSupport<SpAotuspaceRole> {
	
	//查询角色列表（分页）
	PageBean<SpAotuspaceRole> findSpAotuspaceTopRoleList(int rows, int page) throws Exception;
	
	//添加角色父结点comboTreeData
	List<SpAotuspaceRole> findSpAotuspaceRoleTreeData() throws Exception;
}
