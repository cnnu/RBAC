package cn.wolfcode.service;

import cn.wolfcode.domain.Department;
import cn.wolfcode.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDepartmentService {
    PageInfo findAll(QueryObject queryObject);

    void deleteById(Long id);

    void saveOrUpdate(Department department);

    Department findById(Long id);

    List<Department> listAll();
}
