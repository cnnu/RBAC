package cn.wolfcode.service;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IPermissionService {
    PageInfo findAll(QueryObject qo);

    void reload();

    void deleteById(Long id);
    //000
    List<Permission> listAll();
}
