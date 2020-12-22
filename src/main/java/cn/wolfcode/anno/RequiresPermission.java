package cn.wolfcode.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //存活周期，SOURCE<CLASS<RUNTIME
@Target(ElementType.METHOD)//指定作用目标
public @interface RequiresPermission {
    String[] value();
}
