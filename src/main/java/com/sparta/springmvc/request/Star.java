package com.sparta.springmvc.request;

public class Star {
    //HTTP로 넘어온 데이터를 객체로 만들기
    String name;
    int age;

    public Star(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
