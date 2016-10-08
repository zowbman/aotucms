package com.aotuspace.aotucms.web.spaotumcenter.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.impl.DaoSupportImpl;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpAnchorBinfo;
import com.aotuspace.aotucms.web.spaotumcenter.service.IAotuAnchorMService;

/**
 * 
 * Title:AotuAMServiceImpl
 * Description: 代言主播service实现类
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-10-14 下午5:16:09
 *
 */

@Service
@Transactional
public class AotuAnchorMServiceImpl extends DaoSupportImpl<SpAnchorBinfo> implements IAotuAnchorMService {

}
