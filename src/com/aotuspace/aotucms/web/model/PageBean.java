package com.aotuspace.aotucms.web.model;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 
 * Title:PageBean
 * Description:��ҳ
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-23 ����8:23:59
 *
 */
public class PageBean<T> {
	private Class<T> clazz;
	
	private List<T> recordList;
	private int pageCount;
	
	public PageBean() {
		// ʹ�÷��似���õ�T����ʵ����
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();// ��ȡ��ǰnew�Ķ���ķ��͵ĸ�������
		// �õ���һ���������������Ϣ�Ķ���
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];// ���ص�ǰ������ʵ�����Ͳ��������ͣ���һ�����飬Ϊʲô�����飬map�������������Ͳ�����
		System.out.println("class-->>" + clazz.getSimpleName());
		// .getSuperclass() ���ࡢ����
		// .getClass()��ǰ�½��Ķ����������������
	}
	
	public PageBean(int pageCount, List<T> recordList) {
		this.pageCount=pageCount;
		this.recordList=recordList;
	}

	public List<T> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

}
