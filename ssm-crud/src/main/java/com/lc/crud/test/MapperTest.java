package com.lc.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lc.crud.bean.Department;
import com.lc.crud.bean.Employee;
import com.lc.crud.dao.DepartmentMapper;
import com.lc.crud.dao.EmployeeMapper;

/**
 * 数据访问层测试
 * @author lenovo
 * 
 * 推荐Spring的项目就使用Spring的单元测试，可以自动注入我们需要的组件
 * 1.导入springtest包
 * 2.标上注解ContextConfiguration，指定spring配置文件的位置，自动创建IoC容器
 * 3.使用注解Runwith指定使用的单元测试模块
 * 4.直接autowired要使用的组件即可
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	SqlSession sqlSession;

	@Test
	public void testCRUD() {
//		//1.创建springIOC容器
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext");
//		String[] s = ioc.getBeanDefinitionNames();
//		for(String ss:s) {
//			System.out.println(ss);
//		}
//		//2.从容器中获取mapper实例
//		DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
		System.out.println(departmentMapper);
		
//		3.插入部门
//		departmentMapper.insertSelective(new Department(5,"研发部门"));
		
		//4.插入员工数据
		//employeeMapper.insertSelective(new Employee(0,"haha","M","2452316988@qq.com",1));
		
		//5.批量插入多条员工数据，使用可以执行批量操作的SQLSession，在ioc中配置一个sqlSession
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<100;i++) {
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Employee(null,uid,"M",uid+"@qq.com",1));
		}
	}
}
