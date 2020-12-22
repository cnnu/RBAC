package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Role;
import cn.wolfcode.mapper.PermissionMapper;
import cn.wolfcode.mapper.RoleMapper;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
//    @Autowired
//    private PermissionMapper permissionMapper;
    @Override
    public PageInfo findAll(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Role> roleList = roleMapper.findAll();

        return new PageInfo(roleList);
    }
    @Override
    public void deleteById(Long id) {
        // 删除角色，应该先删除中间表角色和权限的关系
        roleMapper.deleteRelation(id);

        roleMapper.deleteById(id);
    }

    @Override
    public Role findById(Long id) {
        return roleMapper.findById(id);
    }

    @Override
    public void saveOrUpdate(Role role,Long[] ids) {

        if (role.getId() !=null) {
            // 删除旧关系
            roleMapper.deleteRelation(role.getId());

            roleMapper.updateById(role);
        }else {
            roleMapper.insert(role);
        }
        //维护角色和权限的关系
        if (ids != null) {
            for (Long id : ids) {
                roleMapper.insertRelation(role.getId(),id);;
            }
        }
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.findAll();
    }
}
