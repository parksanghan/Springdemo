package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor// 기  본 생성자 제공
@Entity // 독립체라고 함
@Table(name = "Member")
public class Member {
    @Id
    @GeneratedValue
    Integer Id;
    @Column(name = "username" , nullable = false)

    String userName;
    @Column(name = "password", nullable = false )
    String passWord;
    @Column(name = "displayname", nullable = false)
    String displayName;

    @Builder
    public Member(String UserName, String password, String displayName) {
        this.userName = UserName;
        this.passWord = password;
        this.displayName = displayName;
    }

}
