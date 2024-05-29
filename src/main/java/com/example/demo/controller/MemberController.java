package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.dto.MemberDTO;
import com.example.demo.model.MemberModel;
import com.example.demo.service.MemberService;
import com.example.demo.service.MyMemberDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Controller
@RequiredArgsConstructor

public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder bCryptPasswordEncoder;


    @GetMapping("/member")
    public String index() {
        return "login.html";
    }

    @PostMapping("/member/join")
    public ResponseEntity<String> join(@RequestBody Member member) {

        String encodedPassword=  bCryptPasswordEncoder.encode(member.getPassWord());
        boolean isSucceed=memberService.save(member.getUserName(), encodedPassword, member.getDisplayName());
        if (isSucceed){
            return ResponseEntity.status(200).body("succeed");
        }

        return ResponseEntity.status(500).body("error");
    }
    @PostMapping("/member/login")
    public ResponseEntity<String> login(@RequestBody Member member, HttpServletResponse response) throws IOException {


        Member targetMember=memberService.login2(member.getUserName() );
        boolean is_matched =new BCryptPasswordEncoder().matches(member.getPassWord(),targetMember.getPassWord());
        if (is_matched){

            return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION,"/member/list").build();
            // Post 요청에서 바로 리다이랙션을 해주는것이 아닌  Get 요청을 한번더 요청해서 리다이랙션해야합니다.
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
    }// 로그인 기능은 Spring Security 에서 지원해줍니다.
    @GetMapping("/member/list")
    public String list() {
        return "redirect:/my/list";
    }



    @GetMapping("/")
    public RedirectView basicPage(){
        return  new RedirectView("http://localhost:8080/default");
    }

    @GetMapping("/login")
    public String login() {
        return "login2.html";
    }
    @ResponseBody
    @GetMapping("/default")
    public String basicPage1s(Authentication authentication){
        MemberModel member=(MemberModel) authentication.getPrincipal();
//        member.getDisplayName();
//        member.getUsername();
//        member.getAuthorities();
//        member.getPassword();
        // 데이터 전송을 위한 객체 생성
        MemberDTO dto = new MemberDTO(member.getUsername());
        if (authentication.isAuthenticated()){ // 로그인을 한 유저에게만 전송
        return  "로그인 성공하였습니다."+authentication+ dto ;

        }
        return "error";
    }

    // 로그인한 유저만 볼수 있게 하고 싶다면
    @GetMapping("/my-page") // Authentication auth 로그인이 완료 된 유저 정보가 담겨저 있습니다.
    public String myPage(Authentication auth) {
        if (auth.isAuthenticated()) {
            MemberModel user =  (MemberModel)auth.getPrincipal();
            String displayname =  user.getDisplayName();
            System.out.println(displayname);
            return "mypage.html";
        }
        //auth // 현재 로그인 정보를 출력가능
        return "login.html";
    }
    // 로그인시 데이터 베이스에서
    @GetMapping("/DtoTest") // Authentication auth 로그인이 완료 된 유저 정보가 담겨저 있습니다.
    public ResponseEntity<?> DtoTest(Authentication auth) {
        if (auth.isAuthenticated()) {
            MemberModel user =  (MemberModel)auth.getPrincipal();
            String displayName =  user.getDisplayName();
            String userName = user.getUsername();
            System.out.println(displayName+userName);
            MemberDTO userDto =  new MemberDTO(userName);
            //
            return ResponseEntity.status(HttpStatus.FOUND).body(userDto);
        }
        //auth // 현재 로그인 정보를 출력가능
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail");

    }
}
