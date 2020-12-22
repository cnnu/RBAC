package cn.wolfcode.service;

import cn.wolfcode.domain.Role;
import cn.wolfcode.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IRoleService {
    PageInfo findAll(QueryObject queryObject);

    void deleteById(Long id);

    Role findById(Long id);

    void saveOrUpdate(Role role,Long[] ids);

    List<Role> listAll();
}
