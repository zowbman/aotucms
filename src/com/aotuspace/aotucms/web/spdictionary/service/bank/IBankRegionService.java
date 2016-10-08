package com.aotuspace.aotucms.web.spdictionary.service.bank;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBankRegion;

/**
 * 
 * Title:IBankService
 * Description:银行地域service接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-12-2 下午2:36:12
 *
 */

public interface IBankRegionService extends DaoSupport<SpBankRegion> {
	
	/**
	 * 根据LevelId查询银行地域列表
	 * @param LevelId (1:省份，4:市)
	 * @return
	 * @throws Exception
	 */
	List<SpBankRegion> findSpBankRegionByLevelId(Integer levelId)throws Exception;
}
