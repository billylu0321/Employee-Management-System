package com.billy.dao;

import com.billy.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper {

    List<Department> queryDeptLists();

    Department queryDeptById(Integer id);
}
