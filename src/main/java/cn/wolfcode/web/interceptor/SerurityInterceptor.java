package cn.wolfcode.web.interceptor;

import cn.wolfcode.anno.RequiresPermission;
import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Permission;
import cn.wolfcode.util.UserContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public class SerurityInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 放行
        // 1.身份是管理员 放行
        Employee employee = UserContext.getCurrentUser();
        if(employee.isAdmin()){
            return true;
        }

        // 2.该功能不需要权限,放行
        if(! (handler instanceof HandlerMethod) ){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        if(!method.isAnnotationPresent(RequiresPermission.class)){
            return true;
        }

        // 3.该员工拥有该功能的权限,放行
        // a. 获取该方法的权限
        RequiresPermission annotation = method.getAnnotation(RequiresPermission.class);
        String expression = annotation.value()[1];
        // b. 从session中获取用户拥有的权限
        List<Permission> currentEmpPermission = UserContext.getCurrentEmpPermission();

        //由于currentEmpPermission是对象无法与字符串比较，利用流和函数接口转换为字符串
        List<String> expressions = currentEmpPermission.stream().map(x->x.getExpression()).collect(Collectors.toList());

        // c. 比较,查看用户拥有的权限中是否包含该方法的权限,如果有放行
        if(expressions.contains(expression)){
            return  true;
        }
        // 跳转到指定页面
        System.out.println("expression.split(\":\")[0] = " + expression.split(":")[0]);
        request.setAttribute("currentMenu",expression.split(":")[0]);
        request.getRequestDispatcher("/WEB-INF/views/common/nopermission.jsp").forward(request,response);
        // 拦截
        return false;
    }
}