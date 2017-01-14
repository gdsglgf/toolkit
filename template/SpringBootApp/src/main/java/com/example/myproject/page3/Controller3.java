package com.example.myproject.page3;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.myproject.page.DataRepo;
import com.example.myproject.page.DatatablesViewPage;
import com.example.myproject.page.Employee;

@Controller
public class Controller3 {
	@RequestMapping(value = "/empPage3", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public DatatablesViewPage<Employee> queryPageNewsForAdmin(@ModelAttribute DTRequestParams dtParams,
			HttpServletRequest request) {
		System.out.println(dtParams);
		DatatablesViewPage<Employee> view = DataRepo.getPageData(dtParams.getStart(), dtParams.getLength());
		view.setDraw(dtParams.getDraw());

		return view;
	}
}
