package com.example.myproject.page;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

public class DataRepo {
	public static String dataFile = "data.json";
	public static int recordsTotal;
	public static List<Employee> employees;
	
	static {
		try {
			String jsonData = new String(Files.readAllBytes(Paths.get(dataFile)));
			Map<String, Object> map = JacksonUtil.readValue(jsonData, new TypeReference<Map<String, Object>>(){});
//			System.out.println(map);
			System.out.println(map.get("draw"));
			System.out.println(map.get("draw").getClass().getName());	// java.lang.Integer
			recordsTotal = (int) map.get("recordsTotal");
			System.out.println(map.get("recordsTotal"));
			System.out.println(map.get("recordsTotal").getClass().getName());	// java.lang.Integer
			System.out.println(map.get("recordsFiltered"));
			System.out.println(map.get("recordsFiltered").getClass().getName());	// java.lang.Integer
			
			System.out.println(map.get("data").getClass().getName());	// java.util.ArrayList
			List list = (List) map.get("data");
			System.out.println(list.get(0).getClass().getName());	// java.util.LinkedHashMap
			
			String emps = JacksonUtil.toJSon(list);
			System.out.println(emps);
			employees = JacksonUtil.readValue(emps, new TypeReference<List<Employee>>(){});
			System.out.println(employees);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static List<Employee> loadData(int start, int length) {
		if (length == -1) {
			return employees;
		}
		
		start = start >= 0 ? start : 0;
		List<Employee> emps = new ArrayList<Employee>();
		
		for (int i = 0, j = start; i < length && j < recordsTotal; i++, j++) {
			emps.add(employees.get(start + i));
		}
		return emps;
	}
	
	public static DatatablesViewPage<Employee> getPageData(int start, int length) {
		List<Employee> emps = loadData(start, length);
		DatatablesViewPage<Employee> view = new DatatablesViewPage<Employee>();
		view.setAaData(emps);
		view.setRecordsFiltered(recordsTotal);
		view.setRecordsTotal(recordsTotal);
		
		return view;
	}
	
	public static void main(String[] args) {
		new DataRepo();
	}
}
