package cn.wolfcode.web.advice;

import cn.wolfcode.exception.MyException;
import cn.wolfcode.query.JSONResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

    @ControllerAdvice
    public class HandlerExceptionControllerAdvice {
        @ExceptionHandler(MyException.class)
        @ResponseBody
        public JSONResult error(MyException ex) {
            return new JSONResult().mark(ex.getMessage());
        }

        @ExceptionHandler(Exception.class)
        @ResponseBody
        public JSONResult error(Exception ex) {
            return new JSONResult().mark("程序员正在被殴打！");
        }
}