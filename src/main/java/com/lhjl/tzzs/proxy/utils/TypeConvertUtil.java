package com.lhjl.tzzs.proxy.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeConvertUtil {
	/**
	 * 将一个list转换为一个Integer的数组
	 * @param 泛型为Integer的List
	 * @return
	 */
	public static Integer[] ListToIntegerArr(List<Integer> list) {
		Integer[] intArr =new Integer[list.size()];
//		Object[] array = list.toArray();
		for(int i=0;i<intArr.length;i++) {
			
		}
		return intArr;
		
	} 
	
	public static void main(String[] args) {
		List<Integer> list=new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(2);
		list.forEach((e)->{
			System.out.println(e);
		});
	}
}
