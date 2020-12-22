package cn.wolfcode.mapper;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Permission;
import cn.wolfcode.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    List<Permission> findAll();

    void insert(Permission permission);

    void deleteAll();

    void deleteById(Long id);

    List<Permission> selectPermissionsByEmpId(Long id);
}
