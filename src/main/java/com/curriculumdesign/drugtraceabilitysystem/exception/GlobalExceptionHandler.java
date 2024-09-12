package com.curriculumdesign.drugtraceabilitysystem.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * 全局异常拦截类
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception e) {
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException(NullPointerException e) {
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {SQLException.class})
    public ResponseEntity<Object> handleSQLException(NullPointerException e) {
        log.error("数据库操作异常，异常信息：{}", e.getMessage());
        String message = e.getMessage();
        String[] split = message.split("###");
        return new ResponseEntity<>("Error: " + split[0], HttpStatus.BAD_REQUEST);
    }
}