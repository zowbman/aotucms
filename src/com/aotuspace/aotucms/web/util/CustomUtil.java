package com.aotuspace.aotucms.web.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 
 * Title:CustomUtil
 * Description:�Զ��幤����
 * Company:aotuspace
 * @author    ΰ��
 * @date      2015-9-15 ����3:04:19
 *
 */
public class CustomUtil {
	
	//��String�Զ���','�������ַ���תΪList
	public static List<String> stringToList(String string) {
		List<String> list = new ArrayList<String>();
		String[] s = string.split(",");
		//System.out.println(s[0]);
		for (String s1 : s) {
			list.add(s1);
		}
		return list;
	}
	//��String�Զ���','�������ַ�תΪInteger����
	public static Integer[] stringToIntegers(String string) {
		String[] s = string.split(",");
		int index = 0;
		Integer[] integers = new Integer[s.length];
		for (String s1 : s) {
			integers[index++] = Integer.valueOf(s1);
		}
		return integers;
	}
		

	//��String�Զ���','�������ַ���תΪCollection
	public static Collection<String> stringToCollection(String string) {
		Collection<String> collection = new HashSet<String>();
		String[] s = string.split(",");
		for (String s1 : s) {
			collection.add(s1);
		}
		return collection;
	}

}
