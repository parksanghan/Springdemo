package com.example.demo.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

  @interface Validator {
    Class<?extends MyValidator> validator() default  DefaultValidator.class;
}

interface  MyValidator{
    boolean Myvalidate(String s);
}

class StrValidator implements MyValidator{


    @Override
    public boolean Myvalidate(String str) {
        return str != null && str.length()>= 3;
    }
}

class DefaultValidator implements  MyValidator{
    @Override
    public boolean Myvalidate(String s) {
        return true;
    }
}
public class test{
    @Validator(validator = StrValidator.class)
    public void validateString(String str)     {
        if (!new StrValidator().Myvalidate(str)){
            throw new IllegalArgumentException("Invalid string");
        }
        System.out.println("String is valid");
    }
    @Validator(validator = StrValidator.class)
    public void validateWithDefault(String value) {
        if (!new DefaultValidator().Myvalidate(value)) {
            throw new IllegalArgumentException("Invalid string");
        }
        System.out.println("String is valid");
    }
}

