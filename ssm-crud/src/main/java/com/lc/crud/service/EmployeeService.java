package com.lc.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.lc.crud.bean.Employee;
import com.lc.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> getAll(){
		System.out.println("daozhele");
		return employeeMapper.selectByExampleWithDept(null);
		
	}

}
