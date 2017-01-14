package com.example.myproject.page3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DTRequestParams {
	private Integer draw = Constants.draw;

	private Integer start = Constants.start;

	private Integer length = Constants.length;

	// 搜索框数据"search":{"value":"","regex":false}
	private Map<Search, String> search = new HashMap<Search, String>();

	// 点击列进行排序"order":[{"column":0,"dir":"asc"}]
	private List<Map<Order, String>> order = new ArrayList<Map<Order, String>>();

	// 表格列数据描述"columns":[{"data":"firstName","name":"first_name","searchable":false,"orderable":true,"searchRegex":false,"searchValue":""},。。。]
	private List<Map<Column, String>> columns = new ArrayList<Map<Column, String>>();

	public DTRequestParams() {}

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

	public Map<Search, String> getSearch() {
		return search;
	}

	public void setSearch(Map<Search, String> search) {
		this.search = search;
	}

	public List<Map<Order, String>> getOrder() {
		return order;
	}

	public void setOrder(List<Map<Order, String>> order) {
		this.order = order;
	}

	public List<Map<Column, String>> getColumns() {
		return columns;
	}

	public void setColumns(List<Map<Column, String>> columns) {
		this.columns = columns;
	}

	public enum Search {
		value, regex
	}

	public enum Order {
		column, dir
	}

	public enum Column {
		data, name, searchable, orderable, searchValue, searchRegex
	}
	
	public String toString() {
		System.out.println("-------columns---------");
		System.out.println(columns);
		System.out.println("-------search---------");
		System.out.println(search);
		System.out.println("-------order---------");
		System.out.println(order);
		return String.format("draw:%d, start: %d, length: %d", draw, start, length);
	}
}
