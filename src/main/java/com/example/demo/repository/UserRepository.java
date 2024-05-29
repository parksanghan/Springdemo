package com.example.demo.repository;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                            //< Entity 명 ,id 컬럼타입>
public interface UserRepository  extends JpaRepository<User, Integer> {
    User findByName(String username);
    User findByAge(int age);
}
