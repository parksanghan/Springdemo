package com.example.demo.service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean  save(String username , String password, String displayname){
        Member member = new Member(username,password,displayname);
        Member existUser=memberRepository.findByuserName(username);
        if (existUser==null) {
            memberRepository.save(member);
            return true;
        }
        return false;
    }
    public Member login(String userName , String password){
        Member member=  memberRepository.findByUserNameAndPassWord(userName,password);

        if (member==null) {
            return null;
        }
        return member;
    }
    public Member login2(String userName){
        Member member=  memberRepository.findByuserName(userName);

        if (member==null) {
            return null;
        }
        return member;
    }

}
