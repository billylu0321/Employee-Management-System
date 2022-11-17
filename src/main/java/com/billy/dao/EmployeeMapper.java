package com.billy.dao;

import com.billy.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmployeeMapper {

    List<Employee> queryEmployeeList();

    Employee queryEmployeeById(Integer id);

    int deleteEmployeeById(Integer id);

    int addEmployee(Employee employee);

    int updateEmployee(Employee employee);


}
