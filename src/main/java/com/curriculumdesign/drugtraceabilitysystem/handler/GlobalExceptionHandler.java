package com.curriculumdesign.drugtraceabilitysystem.handler;

import com.curriculumdesign.drugtraceabilitysystem.util.common.RequestResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局异常拦截类
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RequestResult<String> handleSQLException(SQLException e) {
        log.error("数据库操作异常，异常信息：{}", e.getMessage());
        String message = e.getMessage();
        String[] split = message.split("###");
        return RequestResult.fail(split[0]);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public RequestResult<String> handleValidationExceptions(MethodArgumentNotValidException e) {
        // 获取出准确的错误信息，使用集合存储
        BindingResult bindingResult = e.getBindingResult();
        List<String> errorList = new ArrayList<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errorList.add(error.getDefaultMessage());
        }
        log.error("参数验证失败，错误信息：{}", errorList);
        String errorMessage = String.join(",", errorList);
        return RequestResult.fail(errorMessage);
    }
}
