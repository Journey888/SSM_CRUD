package com.lc.crud.controller;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lc.crud.bean.Employee;
import com.lc.crud.bean.Msg;
import com.lc.crud.service.EmployeeService;

/*
 * 处理员工CRUD请求
 */
@Controller
@RequestMapping("/haha")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pagenum",defaultValue="1")Integer pagenum,
			Model model) {
		System.out.println("收到请求");
		//处理查询员工数据（分页查询）的请求
		//List<Employee> emps = employeeService.getAll();
		
		//引入PageHelper分页插件
		//在查询之前只需要调用PageHelper.startPage(页码，页面数据条数),
		PageHelper.startPage(pagenum,5);
		//startPage后紧跟的查询就是一个分页查询
		List<Employee> emps = employeeService.getAll();
		//使用PageInfo对结果进行包装,只需要将pageInfo交给页面就行了
		//封装了详细的分页信息，包括查询出来的数据
		//3是连续显示的页数
		PageInfo page = new PageInfo(emps,3);
		model.addAttribute("pageInfo",page);
		return "list";
	}
	
	//@ResponseBody自动的把返回的对象转为Json字符串,使用@ResponseBody需要导入jackson包
	@RequestMapping("/empsJson")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pagenum",defaultValue="1")Integer pagenum) {
		
		System.out.println("收到请求");
		//处理查询员工数据（分页查询）的请求
		//List<Employee> emps = employeeService.getAll();
		
		//引入PageHelper分页插件
		//在查询之前只需要调用PageHelper.startPage(页码，页面数据条数),
		PageHelper.startPage(pagenum,5);
		//startPage后紧跟的查询就是一个分页查询
		List<Employee> emps = employeeService.getAll();
		//使用PageInfo对结果进行包装,只需要将pageInfo交给页面就行了
		//封装了详细的分页信息，包括查询出来的数据
		//3是连续显示的页数
		PageInfo page = new PageInfo(emps,3);
		return Msg.success().add("PageInfo", page);
	}

}
