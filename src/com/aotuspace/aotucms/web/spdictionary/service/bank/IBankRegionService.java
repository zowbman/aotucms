package com.aotuspace.aotucms.web.spdictionary.service.bank;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.spdictionary.hbm.bank.SpBankRegion;

/**
 * 
 * Title:IBankService
 * Description:���е���service�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-12-2 ����2:36:12
 *
 */

public interface IBankRegionService extends DaoSupport<SpBankRegion> {
	
	/**
	 * ����LevelId��ѯ���е����б�
	 * @param LevelId (1:ʡ�ݣ�4:��)
	 * @return
	 * @throws Exception
	 */
	List<SpBankRegion> findSpBankRegionByLevelId(Integer levelId)throws Exception;
}
