package com.example.myproject.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DatatablesController {
	private List<Alarm> createPageData(int start, int length) {
		// 随便组织的查询结果
		List<Alarm> list = new ArrayList<Alarm>();
		for (int i = 0; i < length; i++) {
			Alarm alarm = new Alarm();
			alarm.setLevel(start + i);
			alarm.setTotal(100L + 5 * i);
			list.add(alarm);
		}
		return list;
	}

	private void showParams(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		for (Entry<String, String[]> entry : params.entrySet()) {
			System.out.println(entry.getKey() + "--->" + Arrays.toString(entry.getValue()));
		}
	}

	@RequestMapping(value = "/datatablesTest", method = RequestMethod.GET)
	@ResponseBody
	public DatatablesViewPage<Alarm> datatablesTest(HttpServletRequest request) {
		// 获取分页控件的信息
		String start = request.getParameter("start");
		String length = request.getParameter("length");
		// 获取前台额外传递过来的查询条件
		String extra_search = request.getParameter("extra_search");

		// showParams(request);
		System.out.printf("start: %s, length: %s, extra_search: %s\n", start, length, extra_search);
		// 随便组织的查询结果
		List<Alarm> list = createPageData(Integer.parseInt(start), Integer.parseInt(length));

		DatatablesViewPage<Alarm> view = new DatatablesViewPage<Alarm>();
		view.setDraw(Integer.parseInt(request.getParameter("draw")));
		view.setRecordsTotal(100);
		view.setRecordsFiltered(100);

		view.setAaData(list);
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "/emps", method = { RequestMethod.GET, RequestMethod.POST })
	public DatatablesViewPage<Employee> getEmployee(HttpServletRequest request,
			@RequestParam(value = "draw", defaultValue = "1", required = false) Integer draw,
			@RequestParam(value = "start", defaultValue = "0", required = false) Integer start,
			@RequestParam(value = "length", defaultValue = "10", required = false) Integer length, 
			@ModelAttribute Employee employee) {
		showParams(request);
		System.out.println(employee);
		DatatablesViewPage<Employee> view = DataRepo.getPageData(start, length);
		view.setDraw(draw);

		return view;
	}

	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public String show(@ModelAttribute("employee") Employee employee) {
		System.out.println(employee);
		return "emp";
	}
	
	@RequestMapping(value = "/emp2", method = RequestMethod.GET)
	public String show2(Employee employee) {
		System.out.println(employee);
		return "emp";
	}
	
	@ResponseBody
	@RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
	public String test(HttpServletRequest request,
			@RequestParam(value="num", required=false, defaultValue="1000") Integer num,
			@RequestParam(value="text", required=false, defaultValue="hello world") String text) {
		showParams(request);
		System.out.println("num:" + num + ", text:" + text);
		return "Success";
	}
}
