package com.billy.service;

import com.billy.dao.EmployeeMapper;
import com.billy.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> queryEmployeeList(){
        return employeeMapper.queryEmployeeList();
    }

    public Employee queryEmployeeById(Integer id){
        return employeeMapper.queryEmployeeById(id);
    }

    public int deleteEmployeeById(Integer id){
        return employeeMapper.deleteEmployeeById(id);
    }

    public int addEmployee(Employee employee){
       return employeeMapper.addEmployee(employee);
    }

    public int updateEmployee(Employee employee){
       return employeeMapper.updateEmployee(employee);
    }
}
