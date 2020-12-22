package cn.wolfcode.mapper;

import cn.wolfcode.domain.Department;

import java.util.List;

public interface DepartmentMapper {

    List<Department> findAll();

    void deleteById(Long id);

    void updateById(Department department);

    void insert(Department department);

    Department findById(Long id);
}
