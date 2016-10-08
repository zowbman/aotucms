package com.aotuspace.aotucms.web.spaotumcenter.service;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpUsersIdentity;

/**
 * 
 * Title:IAotuUMService
 * Description:用户管理service
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-12 上午10:14:46
 *
 */
public interface IAotuUIMService extends DaoSupport<SpUsersIdentity> {
	//查询用户身份列表（分页）
	PageBean<SpUsersIdentity> findspUsersIdentityList(int rows, int page) throws Exception;
	
	//检查身份名称是否重复
	SpUsersIdentity findBySpIdentityn(String spIdentityn) throws Exception;
	
}
