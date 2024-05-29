package com.example.demo.controller;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
public class BasicController {


    @GetMapping("/hello/{id}")
    @ResponseBody()
    public String helloPage(){
        try {
            return "hello";
        }
        catch (Exception e ){
            throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR.name());
        }
    }
    @GetMapping("/hello2")
    @ResponseStatus()
    public void throwError() {
        // 에러가 발생하는 로직
        throw new RuntimeException(HttpStatus.INTERNAL_SERVER_ERROR.name());
    }
    @PostMapping("/hello3/{input}") // 이렇게하면 fetch로 post 요청할때만 작동합니다.
    public String  postTest(@PathVariable String input){
        return input + "입나다";
    }

    @GetMapping("/hello4/{input}")
    @ResponseBody()
    public String dds(@PathVariable String input){
        return  input + "dds";
    }

    @GetMapping("/index")
    public String indexPage(){
        Date date = new Date();
        int mydate =  date.getDay();

        return  "index.html";
    }
}