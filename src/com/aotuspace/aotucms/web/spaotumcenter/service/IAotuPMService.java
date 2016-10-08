package com.aotuspace.aotucms.web.spaotumcenter.service;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAotuspacePriv;

/**
 * 
 * Title:IAotuPMService
 * Description:凹凸空间权限管理接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-13 下午4:09:35
 *
 */
public interface IAotuPMService extends DaoSupport<SpAotuspacePriv> {

	//查询权限列表(分页)
	PageBean<SpAotuspacePriv> findSpAotuspaceTopPrivList(int rows, int page) throws Exception;
	
	//添加权限父节点TreeData
	List<SpAotuspacePriv> findSpAotuspacePrivTreeData() throws Exception;
	
	//添加权限父节点ComboTreeData
	List<SpAotuspacePriv> findSpAotuspacePrivComboTreeData() throws Exception;
}
