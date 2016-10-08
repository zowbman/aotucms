package com.aotuspace.aotucms.web.spdictionary.service.bank.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBankRegion;
import com.aotuspace.aotucms.web.spdictionary.service.bank.IBankRegionService;

/**
 * 
 * Title:BankServiceImpl
 * Description:银行地域service实现类
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-12-2 下午2:36:50
 *
 */

@Transactional
@Service
public class BankRegionServiceImpl extends DaoSupportImpl<SpBankRegion> implements IBankRegionService {

	@Override
	public List<SpBankRegion> findSpBankRegionByLevelId(Integer levelId) throws Exception {
		return getSession().createQuery("FROM SpBankRegion spbr WHERE spbr.level=:levelId")
				.setParameter("levelId", levelId)
				.list();
	}
	
}
