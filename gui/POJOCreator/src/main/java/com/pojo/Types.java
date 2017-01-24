package com.pojo;

import java.util.HashMap;
import java.util.Map;

public class Types {
	private static Map<String, String> buildInTypes = new HashMap<String, String>();
	
	static {
		buildInTypes.put("Date", "java.util.Date");
		buildInTypes.put("List", "java.util.List");
		buildInTypes.put("ArrayList", "java.util.ArrayList");
		buildInTypes.put("LinkedList", "java.util.LinkedList");
		buildInTypes.put("Set", "java.util.Set");
		buildInTypes.put("HashSet", "java.util.HashSet");
		buildInTypes.put("Map", "java.util.Map");
		buildInTypes.put("HashMap", "java.util.HashMap");
		buildInTypes.put("Vector", "java.util.Vector");
		buildInTypes.put("Stack", "java.util.Stack");
		buildInTypes.put("Queue", "java.util.Queue");
	}
	
	public static String getType(String key) {
		return buildInTypes.get(key);
	}
}
