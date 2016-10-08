package com.aotuspace.aotucms.web.spsysmcenter.service;

import java.util.List;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
import com.aotuspace.aotucms.web.model.PageBean;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeBinfo;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeBinfoKey;
import com.aotuspace.aotucms.web.spsysmcenter.hbm.SpEmployeeDinfo;

/**
 * 
 * Title:ISysMMService
 * Description:ϵͳ����Ա����service�ӿ�
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-21 ����5:26:40
 *
 */

public interface ISysMMService extends DaoSupport<SpEmployeeBinfo> {

	//���ݵ�¼����������в�ѯ�û�(��¼)
	SpEmployeeBinfo findByEpAccountAndEpPassword(String spEpaccount,String spEppassword) throws Exception;

	//��ѯϵͳ����Ա�б���ҳ��
	PageBean<SpEmployeeBinfo> findSpEmployeeBinfoList(int rows, int page) throws Exception;
	
	//������֯������ѯϵͳ����Ա�б�
	PageBean<SpEmployeeBinfo> findSpEmployeeBinfoListByDepart(int rows, int page,Integer[] departIds) throws Exception;

	//�ж��û����Ƿ��ظ�
	SpEmployeeBinfo findBySpEpAccount(String spEpaccount)throws Exception;

	//����Ա��id spEpid���ֵ��������
	Integer findByMaxEpId()throws Exception;
	
	//����ɾ��Ա������ hql����ɾ���޷�ʵ�ּ���ɾ�� 
	/*int deleteSpEmployeeBinfoByIds(Integer ids[])throws Exception;*/
	SpEmployeeDinfo findSpEmployeeDinfoById (SpEmployeeBinfoKey key)throws Exception;
	
	//��ѯ��û�д˽�ɫ��Ա���б�
	List<SpEmployeeBinfo> findSpEmployeeBinfosNoStation(int spId) throws Exception;
}
