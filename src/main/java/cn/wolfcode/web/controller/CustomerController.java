package cn.wolfcode.web.controller;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.query.CustomerQueryObject;
import cn.wolfcode.service.ICustomerService;
import cn.wolfcode.service.IEmployeeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") CustomerQueryObject qo){
        PageInfo pageInfo= customerService.findAll(qo);
        model.addAttribute("result",pageInfo);

        //高级查找，市场专员
        List<Employee> emps = employeeService.findByRoleId();
        model.addAttribute("emps",emps);

        return "/customer/list";
    }
    @RequestMapping("/failList")
    public String failList(Model model ,@ModelAttribute("qo") CustomerQueryObject qo){

        //设置失败客户
        qo.setStatus(3);
        PageInfo pageInfo= customerService.findByStatus(qo);
        model.addAttribute("result",pageInfo);

        //高级查找，市场专员
        List<Employee> emps = employeeService.findByRoleId();
        model.addAttribute("emps",emps);

        return "/customerFail/list";
    }

    @RequestMapping("/potentialList")
    public String formalList(Model model ,@ModelAttribute("qo") CustomerQueryObject qo){

        //设置潜在客户
        qo.setStatus(0);
        PageInfo pageInfo= customerService.findByStatus(qo);
        model.addAttribute("result",pageInfo);

        //高级查找，市场专员
        List<Employee> emps = employeeService.findByRoleId();
        model.addAttribute("emps",emps);

        return "/customerPotential/list";
    }

    @RequestMapping("/poolList")
    public String poolList(Model model ,@ModelAttribute("qo") CustomerQueryObject qo){

        //设置客户池
        qo.setStatus(2);
        PageInfo pageInfo= customerService.findByStatus(qo);
        model.addAttribute("result",pageInfo);

        //高级查找，市场专员
        List<Employee> emps = employeeService.findByRoleId();
        model.addAttribute("emps",emps);

        return "/customerPool/list";
    }
}
