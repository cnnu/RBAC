package cn.wolfcode.web.controller;

import cn.wolfcode.anno.RequiresPermission;
import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Role;
import cn.wolfcode.query.EmployeeQueryObject;
import cn.wolfcode.query.JSONResult;
import cn.wolfcode.service.IDepartmentService;
import cn.wolfcode.service.IEmployeeService;
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
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;

    @RequiresPermission({"员工列表权限","cn.wolfcode.web.controller.EmployeeController.list"})
    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") EmployeeQueryObject qo){
        PageInfo pageInfo = employeeService.listAll(qo);
        model.addAttribute("result",pageInfo);

        //高级查询框，共享数据
        List<Department> depts = departmentService.listAll();
        model.addAttribute("depts",depts);

        return "/employee/list";
    }
    @RequiresPermission({"员工删除权限","cn.wolfcode.web.controller.EmployeeController.delete"})
    @RequestMapping("/delete")
    @ResponseBody  //响应对象数据，实时刷新网页
    public JSONResult delete(Long id){
            employeeService.deleteById(id);
            return new JSONResult();
    }

    @RequestMapping("/input")
    public String input(Long id,Model model){
        if (id != null) {
            //编辑时获取员工数据
            Employee employee = employeeService.findById(id);
            model.addAttribute("entity",employee);
        }

        //获取部门下拉框数据
        List<Department> depts = departmentService.listAll();
        model.addAttribute("depts",depts);

        //获取当前用户所拥有的角色信息->放到entity ，回到62行
        //获取所有的roles角色
        List<Role> roles = roleService.listAll();
        model.addAttribute("roles",roles);

        return "/employee/input";
    }
    @RequiresPermission({"员工新增或编辑权限","cn.wolfcode.web.controller.EmployeeController.saveOrUpdate"})
    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Employee employee,Long[] ids){
        employeeService.saveOrUpdate(employee,ids);
        return "redirect:/employee/list";
    }


}
