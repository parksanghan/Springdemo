package com.example.demo.excetion;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 모든 Controller 파일의 에러를 캐치
@ControllerAdvice
public class MyExcetionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class) // 에러타입에 따라 다르게 처리
    public ResponseEntity<String> exception1(Exception ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception ex) {
        return ResponseEntity.status(400).body(ex.getMessage());
    }
}
