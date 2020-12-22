package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Permission;
import cn.wolfcode.exception.MyException;
import cn.wolfcode.mapper.EmployeeMapper;
import cn.wolfcode.mapper.PermissionMapper;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.IEmployeeService;
import cn.wolfcode.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public PageInfo listAll(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Employee> employeeList = employeeMapper.listAll(qo);

        return new PageInfo(employeeList);
    }

    @Override
    public void deleteById(Long id) {
        // 删除员工应该先删除中间表中员工和角色的关系
        int result = employeeMapper.deleteRelation(id);
        if (result == 0) {
            throw new MyException("删除员工失败，请稍后重试");
        }
        employeeMapper.deleteById(id);
    }

    @Override
    public Employee findById(Long id) {
        return employeeMapper.findById(id);
    }

    @Override
    public void saveOrUpdate(Employee employee,Long[] ids) {
        if (employee.getId() != null) {
            //删除员工和角色的旧关系
            employeeMapper.deleteRelation(employee.getId());


            employeeMapper.updateById(employee);
        }else {
            employeeMapper.insert(employee);
        }

        //维护员工和角色的关系,   插入员4工和角色信息到中间表与3行一同执行
        if (ids != null) {
            for (Long id : ids) {
                employeeMapper.insertRelation(employee.getId(),id);
            }
        }

    }

    @Override
    public Employee login(String username, String password) {
        Employee employee = employeeMapper.selectEmpByUsernameAndPassword(username,password);

        if (employee == null) {
            throw new MyException("登录失败，请稍后重试");
        }

        if (employee != null) {
            //发送用户信息
            UserContext.setCurrentUser(employee);
            //查找用户权限
            List<Permission> list = permissionMapper.selectPermissionsByEmpId(employee.getId());
            //session储存
            UserContext.setCurrentEmpPermission(list);
        }
        return employee;

    }

    @Override
    public List<Employee> findByRoleId() {
        return employeeMapper.findByRoleId();
    }
}
