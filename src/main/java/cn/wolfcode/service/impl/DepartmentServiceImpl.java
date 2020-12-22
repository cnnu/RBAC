package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Department;
import cn.wolfcode.mapper.DepartmentMapper;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.IDepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public PageInfo findAll(QueryObject queryobject) {
        //分页
        PageHelper.startPage(queryobject.getCurrentPage(),queryobject.getPageSize());
        List<Department> departmentList = departmentMapper.findAll();
        return new PageInfo(departmentList);
    }

    @Override
    public void deleteById(Long id) {
        departmentMapper.deleteById(id);
    }

    @Override
    public void saveOrUpdate(Department department) {
        if (department.getId() !=null) {
            departmentMapper.updateById(department);
        }else {
            departmentMapper.insert(department);
        }
    }

    @Override
    public Department findById(Long id) {

        return departmentMapper.findById(id);
    }

    @Override
    public List<Department> listAll() {
        return   departmentMapper.findAll();
    }
}
