package com.aotuspace.aotucms.web.spaotumcenter.service;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpArtistApplication;

/**
 * 
 * Title:IAotuAMService
 * Description:申请其他代言主播service接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-14 下午5:14:41
 *
 */
public interface IAotuAARMService extends DaoSupport<SpArtistApplication>{
	//查询其他代言主播列表（分页）
	PageBean<SpArtistApplication> findSpArtistApplicationList(int rows, int page) throws Exception;
}
