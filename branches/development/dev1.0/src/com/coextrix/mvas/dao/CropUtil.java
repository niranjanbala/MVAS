package com.brightwell.mvas.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CropUtil {
	
	public static String integerListToString(Set<Integer> set) {
		return arrayToString(set.toArray());
	}
	public static String integerListToString(List<Integer> list) {
		return arrayToString(list.toArray());
	}
	private static String arrayToString(Object [] arrays){
		String str = Arrays.toString(arrays);
		str = str.substring(1, str.length());
		str = str.substring(0, str.length() - 1);
		return str;
	}

}
