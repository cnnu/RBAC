package cn.wolfcode.mapper;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    List<Employee> listAll(QueryObject qo);

    void deleteById(Long id);

    Employee findById(Long id);

    void insert(Employee employee);

    void updateById(Employee employee);

    int deleteRelation(Long id);

    void insertRelation(@Param("empId") Long empid, @Param("roleId") Long roleid);

    Employee selectEmpByUsernameAndPassword(@Param("username")String username, @Param("password")String password);

    List<Employee> findByRoleId();
}
