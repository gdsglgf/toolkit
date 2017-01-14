package com.example.myproject.page;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;

class Search {
	private String value;
	private String regex;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
}

class Column {
	private String data;
	private String name;
	private boolean searchable;
	private boolean orderable;
	private Search search;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSearchable() {
		return searchable;
	}
	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}
	public boolean isOrderable() {
		return orderable;
	}
	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}
	public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
}

class Order {
	private int column;
	private String dir;
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
}

class RequestModel {
	private Integer draw;
	private List<Column> columns;
	private List<Order> order;
	private Integer start;
	private Integer length;
	private Search search;

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
}

public class RequestParser {
	public static void main(String[] args) throws Exception {
		String dataFile = "request.json";
		String jsonData = new String(Files.readAllBytes(Paths.get(dataFile)));
		System.out.println(jsonData);
		Map<String, Object> map = JacksonUtil.readValue(jsonData, new TypeReference<Map<String, Object>>() {});
		System.out.println(map);
		System.out.println(map.get("draw").getClass().getName());
		System.out.println(map.get("columns").getClass().getName());
		System.out.println(map.get("search").getClass().getName());
		
		RequestModel rm = JacksonUtil.readValue(jsonData, RequestModel.class);
		System.out.println(rm.getDraw());
		System.out.println(JacksonUtil.toJSon(rm));
	}
}
