package com.aotuspace.aotucms.web.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 
 * Title:CustomUtil
 * Description:自定义工具类
 * Company:aotuspace
 * @author    伟宝
 * @date      2015-9-15 下午3:04:19
 *
 */
public class CustomUtil {
	
	//将String以逗号','隔开的字符串转为List
	public static List<String> stringToList(String string) {
		List<String> list = new ArrayList<String>();
		String[] s = string.split(",");
		//System.out.println(s[0]);
		for (String s1 : s) {
			list.add(s1);
		}
		return list;
	}
	//将String以逗号','隔开的字符转为Integer数组
	public static Integer[] stringToIntegers(String string) {
		String[] s = string.split(",");
		int index = 0;
		Integer[] integers = new Integer[s.length];
		for (String s1 : s) {
			integers[index++] = Integer.valueOf(s1);
		}
		return integers;
	}
		

	//讲String以逗号','隔开的字符串转为Collection
	public static Collection<String> stringToCollection(String string) {
		Collection<String> collection = new HashSet<String>();
		String[] s = string.split(",");
		for (String s1 : s) {
			collection.add(s1);
		}
		return collection;
	}

}
