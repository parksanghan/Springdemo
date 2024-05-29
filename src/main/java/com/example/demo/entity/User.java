package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

// 테이블 생성하려면 @Entity로 생성
@Getter
@Setter
@NoArgsConstructor// 기본 생성자 제공
@Entity // 독립체라고 함
@Table(name = "User",indexes =
          //index로 할 컬럼  ,  해당 인덱스의 이름
@Index(columnList = "age",name = "age_Index"))
public class User {//해당 이름으로 테이블 하나생성해줌
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId; // 컬럼용 변수에는 Ineger를 써야함
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    Integer age;


    @Builder
    public User(  String name, Integer age) {

        this.name = name;
        this.age = age;
    }

//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    } @Getter로 쓸필요 X
}

