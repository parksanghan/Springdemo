package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    //@RequiredArgsConstructor 사용하면
//    @Autowired
//    UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    } // 사용안해도됨

    public boolean saveUser(String name , Integer age) {
        try {
            if(name!=null&&age!=null) {
                User user = new User(name, age);
                User userSucceed= userRepository.save(user);
                return  true;
            }


         return false;
        }
        catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }

    }
    public User findUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }
    public boolean updateUserAgeByName(String targetName , Integer updateAge) {
        try {
            if (targetName != null && updateAge != null) {
                User user = userRepository.findByName(targetName);
                user.setAge(1);
                User savedUser = userRepository.save(user);
                return savedUser != null;
            }
            else {
                throw new IllegalArgumentException("인자 예외 발생");
            }
        }
        catch (IllegalArgumentException e){
            return false;
        }
    }
    public boolean updateUserNameAgeById(Integer targetID,String updateName , Integer updateAge) {
        User user = userRepository.findById(targetID).orElse(null);
        if (user!= null){
            user.setName(updateName);
            user.setAge(updateAge);
            User savedUser = userRepository.save(user);
            if (savedUser != null) {
                return true;
            }
        }
        return false;
    }
    public boolean deleteUserById(Integer id) {
        try{
            User user= userRepository.findById(id).orElse(null);
            if(user!=null) {
                userRepository.delete(user);
                if (userRepository.findById(id).isEmpty()){
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<User> findAll(){
        List<User> users= userRepository.findAll();
        return users;
    }

}
