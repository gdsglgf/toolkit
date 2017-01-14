package com.example.myproject.page2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
v1.10版本之前的ajax请求相关参数
客户端请求第1页数据
aoData = [
  {"name":"sEcho","value":1},
  {"name":"iColumns","value":2},
  {"name":"sColumns","value":","},
  {"name":"iDisplayStart","value":0},
  {"name":"iDisplayLength","value":10},
  {"name":"mDataProp_0","value":0},
  {"name":"sSearch_0","value":""},
  {"name":"bRegex_0","value":false},
  {"name":"bSearchable_0","value":true},
  {"name":"bSortable_0","value":true},
  {"name":"mDataProp_1","value":1},
  {"name":"sSearch_1","value":""},
  {"name":"bRegex_1","value":false},
  {"name":"bSearchable_1","value":true},
  {"name":"bSortable_1","value":true},
  {"name":"sSearch","value":""},
  {"name":"bRegex","value":false},
  {"name":"iSortCol_0","value":0},
  {"name":"sSortDir_0","value":"asc"},
  {"name":"iSortingCols","value":1}
]

客户端请求第5页数据
aoData = [
  {"name":"sEcho","value":2},
  {"name":"iColumns","value":2},
  {"name":"sColumns","value":","},
  {"name":"iDisplayStart","value":40},
  {"name":"iDisplayLength","value":10},
  {"name":"mDataProp_0","value":0},
  {"name":"sSearch_0","value":""},
  {"name":"bRegex_0","value":false},
  {"name":"bSearchable_0","value":true},
  {"name":"bSortable_0","value":true},
  {"name":"mDataProp_1","value":1},
  {"name":"sSearch_1","value":""},
  {"name":"bRegex_1","value":false},
  {"name":"bSearchable_1","value":true},
  {"name":"bSortable_1","value":true},
  {"name":"sSearch","value":""},
  {"name":"bRegex","value":false},
  {"name":"iSortCol_0","value":0},
  {"name":"sSortDir_0","value":"asc"},
  {"name":"iSortingCols","value":1}
]
*/

@Controller
public class PageDemoController {
	@RequestMapping("/tableDemoAjax")
	@ResponseBody
	public String tableDemoAjax(@RequestParam String aoData) {
		System.out.println(aoData);
		JSONArray jsonarray = JSONArray.fromObject(aoData);
	
		String sEcho = null;
		int iDisplayStart = 0; // 起始索引
		int iDisplayLength = 0; // 每页显示的行数
	
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject obj = (JSONObject) jsonarray.get(i);
			if (obj.get("name").equals("sEcho"))
				sEcho = obj.get("value").toString();
	
			if (obj.get("name").equals("iDisplayStart"))
				iDisplayStart = obj.getInt("value");
	
			if (obj.get("name").equals("iDisplayLength"))
				iDisplayLength = obj.getInt("value");
		}
	
		// 生成测试数据
		List<String[]> lst = new ArrayList<String[]>();
		for (int i = 0; i < 100; i++) {
			String[] d = { "co1_data" + i, "col2_data" + i };
			lst.add(d);
		}
	
		JSONObject getObj = new JSONObject();
		getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
		getObj.put("iTotalRecords", lst.size());//实际的行数
		getObj.put("iTotalDisplayRecords", lst.size());//显示的行数,这个要和上面写的一样
	
		getObj.put("aaData", lst.subList(iDisplayStart,iDisplayStart + iDisplayLength));//要以JSON格式返回
		return getObj.toString();
	}
}
