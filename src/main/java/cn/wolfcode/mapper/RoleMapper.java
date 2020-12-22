package cn.wolfcode.mapper;

import cn.wolfcode.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<Role> findAll();

    void deleteById(Long id);

    Role findById(Long id);

    void updateById(Role role);

    void insert(Role role);

    void deleteRelation(Long id);

    void insertRelation(@Param("rId") Long rId, @Param("pId") Long pId);
}
