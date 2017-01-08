package com.example.json;

import com.alibaba.fastjson.JSON;
import com.example.file.IOUtils;

public class FastjsonTest {
	
	public static <T> void showArray(T[] array) {
		System.out.println("length:" + array.length);
		for (Object o : array) {
			System.out.println(o);
		}
	}

	public static void main(String[] args) {
		Object[] data = new Object[] {"string,a,b,c", null, 100, true, 0.05, 'X', "{\"key\":\"value\"}"};
		
		String dataString = JSON.toJSONString(data);
		System.out.println(dataString);
		
		System.out.println("-------parse from string----------");
		Object[] array = JSON.parseArray(dataString).toArray();
		showArray(array);
		
		System.out.println("-------parse from file----------");
		String text = IOUtils.fileToString("test.json");
		System.out.println(text);
		Object[] array2 = JSON.parseArray(text).toArray();
		showArray(array2);
	}

}
