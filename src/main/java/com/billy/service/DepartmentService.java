package com.billy.service;

import com.billy.dao.DepartmentMapper;
import com.billy.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

   public List<Department> queryDeptLists(){
       return departmentMapper.queryDeptLists();
   }
   public Department queryDeptById(Integer id){
       return departmentMapper.queryDeptById(id);
   }
}
