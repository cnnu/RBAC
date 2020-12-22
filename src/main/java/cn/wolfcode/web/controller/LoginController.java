package cn.wolfcode.web.controller;

import cn.wolfcode.query.JSONResult;
import cn.wolfcode.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/emp")
public class LoginController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/login")
    @ResponseBody
    public JSONResult login(String username,String  password){


            employeeService.login(username,password);
            return new JSONResult();

    }

}
