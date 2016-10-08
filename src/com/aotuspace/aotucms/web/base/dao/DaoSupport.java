package com.aotuspace.aotucms.web.base.dao;

import java.util.List;

/**
 * 
 * Title:DaoSupport
 * Description:ͨ��dao�ӿ�
 * Company:aotuspace
 * @author    sida
 * @date      2015-9-2 ����12:17:21
 *
 */
public interface DaoSupport<T> {
	
	//����ʵ��
	void save(T entity)throws Exception;
	
	//ɾ��ʵ��
	void delete(Integer id)throws Exception;
	
	//ɾ�����id
	void delete(Integer[] ids) throws Exception;
	
	//ɾ��������������
	void delete(Object object)throws Exception;
	
	//����ɾ��������������
	void deleteByList(Object objects)throws Exception;

	//����ʵ��
	void update(T entity)throws Exception;
	
	//��Id��ѯ
	T getById(Integer id)throws Exception;
	
	//��Id��ѯ
	List<T> getByIds(Integer[] ids)throws Exception;
	
	//��object��ѯ(��������)
	T getByObject(Object object)throws Exception;
	
	//������ѯ(��������)
	List<T> getByObjects(List<Object> objects)throws Exception;
	
	//��ѯ����
	List<T> findAll()throws Exception;
}
