package cn.wolfcode.service;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IEmployeeService {
    PageInfo listAll(QueryObject qo);

    void deleteById(Long id);

    Employee findById(Long id);

    void saveOrUpdate(Employee employee,Long[] ids);

    Employee login(String username, String password);

    List<Employee> findByRoleId();
}
