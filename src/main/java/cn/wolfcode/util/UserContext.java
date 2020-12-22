package cn.wolfcode.util;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.Permission;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserContext {
    public static final String  EMPLOYEE_IN_SESSION = "EMPLOYEE_IN_SESSION";
    public static final String  PERMISSION_IN_SESSION = "PERMISSION_IN_SESSION";

    private static HttpSession getSession() {
        return ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest().getSession();

    }

    public static void setCurrentUser(Employee current) {
        if (current != null) {
            getSession().setAttribute(EMPLOYEE_IN_SESSION, current);

        } else {
            getSession().invalidate();
        }
    }

    public static Employee getCurrentUser() {

        return (Employee) getSession().getAttribute(EMPLOYEE_IN_SESSION);

    }

    public static void setCurrentEmpPermission(List<Permission> permissions) {
        if (permissions != null) {
            getSession().setAttribute(PERMISSION_IN_SESSION, permissions);

        } else {
            getSession().invalidate();
        }
    }

    public static List<Permission> getCurrentEmpPermission() {

        return (List<Permission>)getSession().getAttribute(PERMISSION_IN_SESSION);

    }
}