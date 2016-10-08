package com.aotuspace.aotucms.web.spsysmcenter.service;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeStation;

/**
 * 
 * Title:ISysRMService
 * Description:ϵͳ��ɫ����service�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:27:19
 *
 */
public interface ISysRMService extends DaoSupport<SpEmployeeStation> {

	//��ѯ��ɫ�б���ҳ��
	PageBean<SpEmployeeStation> findSpEmployeeTopStationList(int rows, int page) throws Exception;

	//��ӽ�ɫ�����comboTreeData
	List<SpEmployeeStation> findSpEmployeeStationTreeData() throws Exception;
	
	
	

}
