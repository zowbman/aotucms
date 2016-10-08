package com.aotuspace.aotucms.web.spdictionary.service.bank;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBankBranch;
import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBanks;

/**
 * 
 * Title:IBankService
 * Description:银行service接口
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-12-2 下午2:36:12
 *
 */

public interface IBankService extends DaoSupport<SpBanks> {
	
	/**
	 * 根据总行，省份，市查询分行信息
	 * @param bankId总行id
	 * @param proId省
	 * @param cityId市
	 * @return
	 * @throws Exception
	 */
	List<SpBankBranch> findBankByIdAndProIdAndCityId(Integer bankId,Integer proId,Integer cityId)throws Exception;
}
