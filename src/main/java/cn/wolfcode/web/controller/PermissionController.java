package cn.wolfcode.web.controller;

import cn.wolfcode.anno.RequiresPermission;
import cn.wolfcode.query.JSONResult;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.IPermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequiresPermission({"权限列表权限","cn.wolfcode.web.controller.PermissionController.list"})
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo){
        PageInfo pageInfo = permissionService.findAll(qo);
        model.addAttribute("result",pageInfo);
        return "/permission/list";
    }

    @RequestMapping("/reload")
    public String reload(){
        permissionService.reload();
        return "redirect:/permission/list";
    }

    @RequiresPermission({"权限删除权限","cn.wolfcode.web.controller.PermissionController.delete"})
    @RequestMapping("/delete")
    @ResponseBody  //响应对象数据，实时刷新网页
    public JSONResult delete(Long id){
            permissionService.deleteById(id);
            return new JSONResult();
    }


}
