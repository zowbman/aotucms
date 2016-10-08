package com.aotuspace.aotucms.web.spdictionary.service.livestation.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.spdictionary.hbm.livestation.SpLivestationInfo;
import com.aotuspace.aotucms.web.spdictionary.service.livestation.ILiveStationService;

/**
 * 
 * Title:LiveStationServiceImpl
 * Description:ֱ��ƽ̨serviceʵ����
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-12-3 ����4:18:55
 *
 */

@Transactional
@Service
public class LiveStationServiceImpl  extends DaoSupportImpl<SpLivestationInfo> implements ILiveStationService {

}
