package com.aotuspace.aotucms.web.spsysmcenter.service;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeStation;

/**
 * 
 * Title:ISysRMService
 * Description:系统角色管理service接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-21 下午5:27:19
 *
 */
public interface ISysRMService extends DaoSupport<SpEmployeeStation> {

	//查询角色列表（分页）
	PageBean<SpEmployeeStation> findSpEmployeeTopStationList(int rows, int page) throws Exception;

	//添加角色父结点comboTreeData
	List<SpEmployeeStation> findSpEmployeeStationTreeData() throws Exception;
	
	
	

}
