package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/my")
@RequiredArgsConstructor // 이거 쓰면
public class MyUserController {
    private final UserService userService;

    // 이거 안써도됩니다.
//    @Autowired
//    MyUserController(UserService userService) {
//        this.userService = userService;
//    }
    @PostMapping("/insert")
    public String insert(@ModelAttribute User user, Model model) {
        boolean isSucceed= userService.saveUser(user.getName(),user.getAge());
        // 예외처리는 userService
        if (isSucceed){
            model.addAttribute("user",user);
            return "redirect:/Details/list";
        }
        return "redirect:/error";
    }
//    @PostMapping("/save")
//    public String save(@ModelAttribute User user, Model model) {
//        boolean isSucceed= userService.updateUserAgeByName(user.getName(), user.getAge());
//        boolean apiServer = true;
//        if (isSucceed){
//            model.addAttribute("user",user);
//            return "redirect:/Details/list"; // 웹서버의 경우
//        }
//        else return "redirect:/error";
//    }

    //api 서버의 경우
    @PostMapping("/save1")
    public ResponseEntity<String> save1(@ModelAttribute User user, Model model) {
        boolean isSucceed= userService.updateUserAgeByName(user.getName(), user.getAge());
        boolean apiServer = true;
        if (isSucceed){
            model.addAttribute("user",user);
            return  ResponseEntity.ok("ok");
        }
        else return  ResponseEntity.ok("error");
    }
    @GetMapping("/test1")
    public String test1() {
        System.out.println("test요청1");
        return "redirect:/list";
    }
    @PostMapping("/manage")
    public ResponseEntity<String> delete(@RequestBody Map<String ,Integer> map) {
        Integer userId=map.get("userId");
        boolean isSucceed= userService.deleteUserById(userId);
        if (isSucceed){
            return ResponseEntity.status(200).body("저장 성공");
        }
        return ResponseEntity.status(500).body("error");

    }
    @GetMapping("/list")
    public String GetAll2(Model model, Authentication auth) {
        List<User> userProxy = userService.findAll();
        System.out.println(auth.isAuthenticated());
        model.addAttribute("User", userProxy);

        return "list.html";
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        User user =userService.findUserById(id);
        model.addAttribute("User", user);
        return "detail.html";
    }
    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody User user) {
        boolean isSucceed=userService.updateUserNameAgeById(user.getUserId(),user.getName(),user.getAge());

        if (isSucceed){
            return ResponseEntity.status(200).body("저장 성공");
        }
        return ResponseEntity.status(500).body("error");

    }
    @GetMapping("/save")
    public String save() {
        return "save.html";
    }

    @PostMapping("/saveuser")
    public ResponseEntity<String> save(@RequestBody User user) {
        boolean isSucceed=userService.saveUser( user.getName() , user.getAge());

        if (isSucceed){
            return ResponseEntity.status(200).body("저장 성공");
        }
        return ResponseEntity.status(500).body("error");

    }

}
