package cn.wolfcode.service.impl;

import cn.wolfcode.anno.RequiresPermission;
import cn.wolfcode.domain.Permission;
import cn.wolfcode.mapper.PermissionMapper;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public PageInfo findAll(QueryObject qo) {
//        处理分页
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
//      List<Permission> permissionList = permissionMapper.findAll(qo);
        List<Permission> permissionList = permissionMapper.findAll();

        return new PageInfo(permissionList);
    }
    //获取Spring 容器
    @Autowired
    private ApplicationContext ctx;
    @Override
    public void reload() {
        //清理权限表中数据
        permissionMapper.deleteAll();
        //1.获取所有的Controller
        Map<String, Object> beansWithAnnotation = ctx.getBeansWithAnnotation(Controller.class);
        Collection<Object> controllers = beansWithAnnotation.values();
        //2.扫描所有的Controller中的所有方法
        for (Object controller : controllers) {
            Method[] methods = controller.getClass().getDeclaredMethods();
            //在每个方法中获取注解为RequestPermion的值
            for (Method method : methods) {
                if (method.isAnnotationPresent(RequiresPermission.class)) {
                    RequiresPermission annotation = method.getAnnotation(RequiresPermission.class);
                    String[] strings = annotation.value();
                    //3.获取所有贴了自定义注解的内容
                    Permission permission = new Permission();
                    permission.setName(strings[0]);
                    permission.setExpression(strings[1]);
                    //4.把内容加入到数据库
                    permissionMapper.insert(permission);
                }
            }
        }

    }

    @Override
    public void deleteById(Long id) {
        permissionMapper.deleteById(id);
    }
    //0000
    @Override
    public List<Permission> listAll() {
        return permissionMapper.findAll();
    }
}
