package com.example.json;

import com.example.file.IOUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {
	public static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 把object转换为json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
	 * (1)转换为普通JavaBean：readValue(json,Student.class)
	 * (2)转换为List,如List<Student>,将第二个参数传递为Student
	 * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
	 * 
	 * @param jsonStr
	 * @param valueType
	 * @return
	 */
	public static <T> T readValue(String jsonStr, Class<T> valueType) {
		try {
			return objectMapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * json数组转List 转成Map, 第二个参数为new TypeReference<Map<String, Object>>() {}
	 * 
	 * @param jsonStr
	 * @param valueTypeRef
	 * @return
	 */
	public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
		try {
			return objectMapper.readValue(jsonStr, valueTypeRef);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		Object[] data = new Object[] { "string,a,b,c", null, 100, true, 0.05, 'X', "{\"key\":\"value\"}" };
		System.out.println("length:" + data.length);

		String dataString = toJSONString(data);
		System.out.println(dataString);

		System.out.println("-------parse from string----------");
		Object[] array1 = readValue(dataString, Object[].class);
		FastjsonTest.showArray(array1);
		
		System.out.println("-------parse from file----------");
		String text = IOUtils.fileToString("test.json");
		System.out.println(text);
		Object[] array2 = readValue(text, Object[].class);
		FastjsonTest.showArray(array2);

	}

}
