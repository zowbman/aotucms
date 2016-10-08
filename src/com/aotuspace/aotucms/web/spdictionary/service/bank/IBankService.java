package com.aotuspace.aotucms.web.spdictionary.service.bank;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBankBranch;
import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBanks;

/**
 * 
 * Title:IBankService
 * Description:����service�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-12-2 ����2:36:12
 *
 */

public interface IBankService extends DaoSupport<SpBanks> {
	
	/**
	 * �������У�ʡ�ݣ��в�ѯ������Ϣ
	 * @param bankId����id
	 * @param proIdʡ
	 * @param cityId��
	 * @return
	 * @throws Exception
	 */
	List<SpBankBranch> findBankByIdAndProIdAndCityId(Integer bankId,Integer proId,Integer cityId)throws Exception;
}
