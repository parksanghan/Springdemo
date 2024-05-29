
 package  com.example.demo;

 import com.example.demo.annotation.test;
 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.SpringBootApplication;

 import java.util.ArrayList;
 import java.util.Arrays;

 @SpringBootApplication
 public class DemoApplication{
 public static void main(String[] args) {
     String dd = "DDD";
     final int dds =  0; // 머여 못바꿈 큰일남
     int leeenth = dds;
     final  String 여자친구이름 = "null"; // 없으니깐 못바꿈 머임
     int len= 여자친구이름.length();
     System.out.println(dd);
     SpringApplication.run(DemoApplication.class,args
     );

     ArrayList<Integer> ints = new ArrayList<>();

     int size=  ints.size();
     test ts=  new test();
     String str= "Dddd";
     ts.validateWithDefault(str);
     ts.validateString(str);
//
//     String str2= "d";
//     ts.validateWithDefault(str2);
//     ts.validateString(str2);

   }
 }