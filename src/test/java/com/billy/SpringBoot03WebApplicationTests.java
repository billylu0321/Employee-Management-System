package com.billy;

import com.billy.dao.DepartmentMapper;
import com.billy.dao.EmployeeMapper;
import com.billy.pojo.Department;
import com.billy.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class SpringBoot03WebApplicationTests {

	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Test
	void contextLoads() {
		Department department = departmentMapper.queryDeptById(102);
		Employee lulu = new Employee("lulu", "lulu@gmail.com", 1, department);
		employeeMapper.addEmployee(lulu);
	}

}
