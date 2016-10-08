package com.aotuspace.aotucms.web.spdictionary.service.bank.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBankBranch;
import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBanks;
import com.aotuspace.aotucms.web.spdictionary.service.bank.IBankService;

/**
 * 
 * Title:BankServiceImpl
 * Description:银行service实现类
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-12-2 下午2:36:50
 *
 */

@Transactional
@Service
public class BankServiceImpl extends DaoSupportImpl<SpBanks> implements IBankService {

	@Override
	public List<SpBankBranch> findBankByIdAndProIdAndCityId(Integer bankId, Integer proId, Integer cityId)
			throws Exception {
		return getSession()
				.createQuery(
						"From SpBankBranch spbb WHERE spbb.bankid=:bankid AND spbb.proid=:proid And spbb.cityid=:cityid")
				.setParameter("bankid", bankId).setParameter("proid", proId).setParameter("cityid", cityId).list();
	}

}
