package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// 이런 형식은 Derived Query methods 라고 합니다.
@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByuserName(String username);
    Member findByUserNameAndPassWord(String userName, String passWord);
//    Optional<Member> findByUsername(String userName);
    Page<Member> findPageBy( Pageable pageable);
    @Query(value = "select * from TABLE ( member )where username = ?1 ", nativeQuery = true)
    Member rawQuery(String username);
}
