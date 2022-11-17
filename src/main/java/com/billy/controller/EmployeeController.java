package com.billy.controller;


import com.billy.pojo.Department;
import com.billy.pojo.Employee;
import com.billy.service.DepartmentService;
import com.billy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;

    //list all employee
    @RequestMapping("/emps")
    public String list(Model model){
        List<Employee> employees = employeeService.queryEmployeeList();
        //save the employees list in the model and return to front end page
        model.addAttribute("employees",employees);
        return "emp/list";
    }

    //
    // here jump to add page, use @GetMapping
    @GetMapping("/emp")
    public String toAddPage(Model model){
        List<Department> departments = departmentService.queryDeptLists();
        //need to first get all the department information and save in the model, then in the html page customer can choose from the department list
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    //this is a add method, since in the html page, we use post method to submit the form information, so here must be postmapping
    @PostMapping("/emp")
    public String addEmployee(Employee employee){
        employeeService.addEmployee(employee);
        //after finished the add employee, redirect to /emps to show all the employee information
        return "redirect:/emps";
    }

    //here use the RESTFul API to get the input from front end
    @GetMapping("/delete/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeService.deleteEmployeeById(id);
        //after delete, redirect  to /emps to show all the employee information
        return "redirect:/emps";
    }

    /*
    jump to update page, here alsi use the RESTFul API, but before jumping to the update page, need to first get the
    information of the employee that need to be update
     */
    @GetMapping("/emp/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model){
        //get the employee information and all the department information, save in the model, then jump to update page
        Employee employee = employeeService.queryEmployeeById(id);
        model.addAttribute("employee",employee);
        List<Department> departments = departmentService.queryDeptLists();
        model.addAttribute("departments",departments);
        return "emp/update";
    }

    //after updated the employee info and click submit, will save the info in the MySql Database
    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeService.updateEmployee(employee);
        //after update, redirect  to /emps to show all the employee information
        return "redirect:/emps";
    }

}
