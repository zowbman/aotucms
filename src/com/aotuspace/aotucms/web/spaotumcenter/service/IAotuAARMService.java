package com.aotuspace.aotucms.web.spaotumcenter.service;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spaotumcenter.hbm.SpArtistApplication;

/**
 * 
 * Title:IAotuAMService
 * Description:����������������service�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-10-14 ����5:14:41
 *
 */
public interface IAotuAARMService extends DaoSupport<SpArtistApplication>{
	//��ѯ�������������б���ҳ��
	PageBean<SpArtistApplication> findSpArtistApplicationList(int rows, int page) throws Exception;
}
