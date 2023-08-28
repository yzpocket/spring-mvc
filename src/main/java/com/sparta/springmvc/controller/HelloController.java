package com.sparta.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api") // @RequestMapping("URL")을 통해 중복되는 /api라는 URL 경로를 해당 컨트롤러 클래스 내부 메소드들에 모두 적용 시킬 수 있다.
@Controller
public class HelloController {
    //@GetMapping("/api/hello") //[1]이 Get요청에 대한
    @GetMapping("/hello") // <@RequestMapping("URL")을 통해 중복되는 /api라는 URL 경로 제거
    @ResponseBody//[3] 이것을 통해서 단순 String 문자열을 반환 할 수 있음.
    public String hello(){
        return "Hello World";// [2]View 네임의 정보를 반환 (HTML) 그러나 현재는 단순 문자열을 반환하고자 하므로
    }

    //@GetMapping("/api/get")
    @GetMapping("/get")
    @ResponseBody
    public String get(){
        return "GET Method 요청";
    }

    //@PostMapping("/api/post")
    @PostMapping("/post")
    //@PostMapping("/api/hello") //URL 경로는 중복될 수 있다. 하지만 동시에 요청방식도 중복되면 안된다.
    @ResponseBody
    public String post(){
        return "POST Method 요청"; //POSTMAN에서 GET요청 확인을하면 405에러가 발생 4xx = 요청이 잘못된 경우 [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'GET' is not supported]우
    }

    //@PutMapping("/api/put")
    @PutMapping("/put")
    @ResponseBody
    public String put(){
        return "PUT Method 요청";
    }

    //@DeleteMapping("/api/delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public String delete(){
        return "DELETE Method 요청";
    }
}
