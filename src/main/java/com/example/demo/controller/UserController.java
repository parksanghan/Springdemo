package com.example.demo.controller;


import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.*;

// 내부 상세 페이지를 담당
@Controller
@RequestMapping(value = "/Details") // 이렇게하면 기본값이 되는 엔드 포인트가 됨
@RequiredArgsConstructor
// Getmaaping이전의 Method 를 정의해야 하는 이전의 방법이지만 Method를 정의하지 않으면 내부에서
// Getmapping , DeleteMapping 등 모두 다 사용가능
public class UserController {
    private final UserRepository userRepository;


//    @Autowired
//    public DetailController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    } // @RequiredArgsConstructor 의 역할이에요 인자가 필요한 생성자를 정의해줍니다.
// lombok의 어노테이션 메서드 사용하기 싫으면 이렇게하면됩니다.


    @GetMapping("/detailsd")
    public String detailsd(Model model) {
        return "index.html";
    }
    @GetMapping("/detailsa")
    public String detailsa(Model model) {
        // User 테이블 꺼내주세요

        String DBdata =  "서버에서 데이터에요";
        model.addAttribute("title", DBdata);
        // 첫번째 인자 변수 , 두번째 인자 넣을 데이터
        return "home.html";
    }
    @GetMapping("/detailsb")
    public String detailsb(Model model) {
        // User 테이블 꺼내주세요
        User userProxy=userRepository.getReferenceById(1); // ID 값이 1인 유저를 가져옵니다.
        Integer age= userProxy.getAge();
        String name = userProxy.getName();
        model.addAttribute("title", age+name);
        // 첫번째 인자 변수 , 두번째 인자 넣을 데이터
        return "home.html";
    }
    @GetMapping("/GetAll")
    public String GetAll(Model model) {
        List<User> userProxy = userRepository.findAll();
        List<String> nameList = new ArrayList<>();
        List<Integer> ageList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (User user: userProxy ){
            nameList.add(user.getName());
            ageList.add((user.getAge()));
        }
        if (nameList.size() == ageList.size()){
            for (int i = 0 ; i<nameList.size(); i++){
                map.put(nameList.get(i),ageList.get(i));
            }
        }
        model.addAttribute("title", map);
        return "index.html";
    }
    @GetMapping
    public String submit(){
        return "submit.html";
    }
    @PostMapping("/user")
    public String submit(@RequestParam  String name,
                         @RequestParam  Integer age){
        User user = new User(name,age);

        if(userRepository.save(user)!=null){
            return  "redirect:/home";
        }
        return  null;

    }
    @PostMapping("/user1")
    public String submit1(@ModelAttribute User user ){

        if(userRepository.save(user)!=null){
            return  "redirect:/home";
        }
        return  null;

    }
    @GetMapping("/list")
    public String GetAll2(Model model) {
        List<User> userProxy = userRepository.findAll();





        model.addAttribute("User", userProxy);

        return "list.html";
    }
    @GetMapping("/mydetail/{id}")
    public String getDetail(@PathVariable Integer id , Model model){
        User user =userRepository.getReferenceById(id);
        // id 에 해당하는 엔티티 인데요
        Optional<User> optUser = userRepository.findById(id);
        // id 에 해당하는 Optional로 래핑된 엔티티 인데요

        // 차이점 :
        // getReferenceById의 경우
        // id에 해당하는 엔티티 반환 , 엔티티 없으면 에러 발생

        // findById의 경우: 해당 하는 Optional 로 래핑하여
        // entity 또는 없는 경우 Optional.empty를 반환한다.
        // 내부적으로 예외를 발생시키지 않음
//        model.addAttribute ("User",optUser.get());
            // 또는
        optUser.ifPresent(idxUser->model.addAttribute("User",idxUser));
        // 이렇게 사용가능

        return "detail.html";
    }
    // Rest API의 예외처리 방법
    @GetMapping("error/{id}")
    public ResponseEntity error(@PathVariable Integer id )
    {
        try{
            throw new Exception(); // 테스트 용
        }
        catch (Exception e){
            System.out.println(e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getCause());
        }

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity springError(){
        // 모든  API를 캐치하여 발생시켜줌 해당 컨트롤러 클래스 내부에서만

        return ResponseEntity.status(400).body("error");
    }


}
