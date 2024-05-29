package com.example.demo;


import jdk.incubator.vector.VectorOperators;

import java.io.IOException;

public class Testd {
    public static Testd singtonMethod;

    public static Testd getSingtonMethod() {
        return singtonMethod;
    }
    private Testd(){

    }
    public static  Testd setInstance(){
        if (singtonMethod==null){
            singtonMethod = new Testd();
        }
        return singtonMethod;
    }

}
