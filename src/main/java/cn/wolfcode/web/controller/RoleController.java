package cn.wolfcode.web.controller;

import cn.wolfcode.anno.RequiresPermission;
import cn.wolfcode.domain.Permission;
import cn.wolfcode.domain.Role;
import cn.wolfcode.query.JSONResult;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.IPermissionService;
import cn.wolfcode.service.IRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService RoleService;
    @Autowired
    private IPermissionService permissionService;

    @RequiresPermission({"角色列表权限","cn.wolfcode.web.controller.RoleController.list"})
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo){
        PageInfo pageInfo = RoleService.findAll(qo);
        model.addAttribute("result",pageInfo);
        return "/role/list";
    }

    @RequiresPermission({"角色删除权限","cn.wolfcode.web.controller.RoleController.delete"})
    @RequestMapping("/delete")
    @ResponseBody  //响应对象数据，实时刷新网页
    public JSONResult delete(Long id){
            RoleService.deleteById(id);
            return new JSONResult();
    }

    @RequestMapping("/input")
    public String input(Long id,Model model){
        if (id != null) {
            Role role = RoleService.findById(id);
            model.addAttribute("entity",role);
        }
        //0000
        //获取所有的permissions权限
        List<Permission> permissions = permissionService.listAll();
        model.addAttribute("permissions",permissions);
        return "/role/input";
    }

    @RequiresPermission({"角色新增或修改权限","cn.wolfcode.web.controller.RoleController.saveOrUpdate"})
    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Role role,Long[] ids){
        RoleService.saveOrUpdate(role,ids);
        return "redirect:/role/list";
    }

}
