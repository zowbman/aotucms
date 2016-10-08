package com.aotuspace.aotucms.web.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.aotuspace.aotucms.web.base.dao.DaoSupport;
/**
 * 
 * Title:DaoSupportImpl
 * Description:ͨ��daoʵ����
 * Company:aotuspace
 * @author    sida
 * @date      2015-9-2 ����12:28:14
 *
 */
@Transactional
@SuppressWarnings("unchecked")
public class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	private SessionFactory sessionFactory;
	
	private Class<T> clazz;
	
	public DaoSupportImpl(){
		//ʹ�÷��似���õ�T����ʵ����
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();//��ȡ��ǰnew�Ķ���ķ��͵ĸ�������
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];//��ȡ��һ�����Ͳ�������ʵ����
		System.out.println("clazz------>" + clazz);
	}
	
	//��ȡ��ǰ���õ�Session
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	
	public void save(T entity)throws Exception{
		getSession().save(entity);
		
	}

	public void update(T entity)throws Exception{
		getSession().update(entity);
		
	}

	public void delete(Integer id)throws Exception{
		Object obj = getById(id);
		if(obj != null){
			getSession().delete(obj);
		}
	}
	
	
	public void delete(Integer[] ids) throws Exception {
		List<T> list = getByIds(ids);
		if(list!=null){
			for (T t : list) {
				getSession().delete(t);
			}
		}
	}

	//��������
	public void delete(Object object)throws Exception{
		Object obj=getByObject(object);
		if(obj!=null){
			getSession().delete(obj);
		}
	}
	
	//����ɾ��������������
	@Override
	public void deleteByList(Object objects) throws Exception {
		List<T> list=getByObjects((List<Object>) objects);
		if(list!=null){
			for(int i=0;i<list.size();i++){
				getSession().delete(list.get(i));
			}
		}
	}

	public T getById(Integer id)throws Exception{
		if(id == null){
			return null;
		}else{
			return (T) getSession().get(clazz, id);
		}
	}
	
	//��������
	public T getByObject(Object object)throws Exception{
		if(object ==null){
			return null;
		}else{
			return (T) getSession().get(clazz, (Serializable) object);
		}
	}
	
	//������ѯ��������
	@Override
	public List<T> getByObjects(List<Object> objects) throws Exception {
		if(objects==null){
			return null;
		}else{
			List<T> list=new ArrayList<T>();
			for (Object object : objects) {
				list.add(getByObject(object));
			}
			return list;
		}
	}

	public List<T> getByIds(Integer[] ids) throws Exception {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		} else {
			return getSession().createQuery("From " + clazz.getSimpleName() + " Where id In(:ids)")
					.setParameterList("ids", ids).list();
		}
	}

	public List<T> findAll() throws Exception {
		return getSession().createQuery("From " + clazz.getSimpleName()).list();
	}
	
}
