package com.example.demo.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
//ResController 와 Controller 차이
// Controler 의 경우 GetMAapping 에서 @ResponseBody 이런식으로 해줘야했던반면
// RestController 의 경우 @Controller @ResponseBody가 결합된어노테이션
// view를 거치지 않고 HTTP ResonseBody 에 직접 Return 값을 보냄
public class HelloController {
    //get 메서드
    @GetMapping("/api/test")
    public String hello()
    {return "X";}
    @GetMapping("/api/redirect")
    public RedirectView redirectView(){
        String targetURL =  "https://parksanghan.github.io";
        return new RedirectView(targetURL);

    }
    @GetMapping("/TEST/{var}")
    public String Test(@PathVariable("var") String vvar2){
        return null;
    }
    @GetMapping("Request")
    public String Request(@RequestParam String name){


        return name;
    }
    @GetMapping(value = "/unknown")// 어떤 요청이 들어올지 모르는 경우
    public String unknown(@RequestParam Map<String, String> param){
        StringBuilder sb =new StringBuilder();
        param.entrySet().forEach(map->{sb.append(map.getKey()+map.getValue());});
        StringBuilder nb = new StringBuilder();
        param.entrySet().forEach(map->{nb.append(map.getKey()+map.getValue());});
        return sb.toString();

    }

    record Person(String name , String Email){

    }
    // DTO 사용
    @GetMapping(value = "/request3")
    public String request3(Person personDTO){
            return  personDTO.Email() + personDTO.name();
    }

    // RedirectView // 매핑된 엔드포인로요청시  리다이렉션을 통해 해당 url로  이동시킴
    //
    //스프링 4.3 버전 부터는 메서드를 지정하는 방식보다 간단하게 사용할 수 있는 어노테이션이 있음
    @PostMapping("/{id}")
    public String post(@PathVariable String id){
        return  "login"+ id;
    } // insert 요청 시에 사용
    @DeleteMapping("/idx")
    public String delete(@PathVariable String id)
        { return  id; }
    
    @PutMapping("/{name}")
    public String put(@PathVariable String name){
        return name;
    }
    @PatchMapping("/namex")
    public String patch(@PathVariable String name){
        return name;
    }
    @RequestMapping()
    public String index(){  return  index();}
}
