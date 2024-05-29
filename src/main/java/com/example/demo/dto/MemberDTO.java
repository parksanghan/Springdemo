package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class MemberDTO {
    private Integer id;
    private String userName;
    private String password;
    private String displayName;

    public MemberDTO(Integer id){
        this.id = id;
    }
    public MemberDTO(Integer id, String userName, String password, String displayName){
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.displayName = displayName;
    }
    public MemberDTO(String userName){
        this.userName = userName;
    }

}
