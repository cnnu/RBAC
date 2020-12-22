package cn.wolfcode.web.controller;

import cn.wolfcode.anno.RequiresPermission;
import cn.wolfcode.domain.Department;
import cn.wolfcode.query.JSONResult;
import cn.wolfcode.query.QueryObject;
import cn.wolfcode.service.IDepartmentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequiresPermission({"部门列表权限","cn.wolfcode.web.controller.DepartmentController.list"})
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") QueryObject qo) {
        PageInfo pageInfo= departmentService.findAll(qo);
        model.addAttribute("result",pageInfo);
        return "/department/list";
    }

    @RequiresPermission({"部门删除权限","cn.wolfcode.web.controller.DepartmentController.delete"})
    @RequestMapping("/delete")
    @ResponseBody  //响应对象数据，实时刷新网页
    public JSONResult delete(Long id){
            departmentService.deleteById(id);
            return new JSONResult();
    }

    @RequestMapping("/input")
    public String input(Long id,Model model){
        if (id != null) {
            Department department = departmentService.findById(id);
            model.addAttribute("entity",department);
        }
        return "department/input";
    }
    @RequiresPermission({"部门新增或编辑权限","cn.wolfcode.web.controller.DepartmentController.saveOrUpdate"})
    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Department department){
        departmentService.saveOrUpdate(department);
        return "redirect:/department/list";
    }
}
