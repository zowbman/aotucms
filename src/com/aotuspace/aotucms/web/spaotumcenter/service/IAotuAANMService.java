package com.aotuspace.aotucms.web.spaotumcenter.service;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAnchorApplication;

/**
 * 
 * Title:IAotuAANMService
 * Description:申请代言主播service接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-14 下午5:14:41
 *
 */
public interface IAotuAANMService extends DaoSupport<SpAnchorApplication>{
	//查询代言主播列表（分页）
	PageBean<SpAnchorApplication> findSpAnchorApplicationList(int rows, int page) throws Exception;
}
