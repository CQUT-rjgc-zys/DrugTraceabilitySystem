package com.curriculumdesign.drugtraceabilitysystem.exception;

import com.curriculumdesign.drugtraceabilitysystem.util.common.RequestResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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

//    @ExceptionHandler(value = {NullPointerException.class})
//    public ResponseEntity<Object> handleNullPointerException(NullPointerException e) {
//        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(value = {SQLException.class})
//    public ResponseEntity<Object> handleSQLException(SQLException e) {
//        log.error("数据库操作异常，异常信息：{}", e.getMessage());
//        String message = e.getMessage();
//        String[] split = message.split("###");
//        return new ResponseEntity<>(split[0], HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException e) {
//        // 获取出准确的错误信息，使用集合存储
//        BindingResult bindingResult = e.getBindingResult();
//        List<String> errorList = new ArrayList<>();
//        for (FieldError error : bindingResult.getFieldErrors()) {
//            errorList.add(error.getDefaultMessage());
//        }
//        log.error("参数验证失败，错误信息：{}", errorList);
//        String errorMessage = String.join(",", errorList);
//        return new ResponseEntity<>(RequestResult.fail(errorMessage), HttpStatus.BAD_REQUEST);
//    }
}
