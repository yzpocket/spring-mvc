package com.sparta.springmvc.response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// RestController? 결국엔 @Controller + @ResponseBody인 어노테이션이다.
// @ResponseBody를 메소드마다 써줄 필요가 없다!!
// 하지만 View를 반환해야 하는 경우엔 @Controller를 써야 한다. html을 자동 반환하니까!
// 느슨한 결합 이라는것?
// front server(말그대로 @Controller만 쓰는 부분)과
// data를 주로 다루는 (json으로 반환해야 하는 @RestController를 쓰는 곳)을 구분하는것.
@RestController
@RequestMapping("/response/rest") // /resonse/rest까지 이 컨트롤러가 담당한다.
public class ResponseRestController {
    // [Response header]
    //   Content-Type: text/html
    // [Response body]
    //   {"name":"Robbie","age":95}
    @GetMapping("/json/string")
    public String helloStringJson() {
        return "{\"name\":\"Robbie\",\"age\":95}";
    }

    // [Response header]
    //   Content-Type: application/json
    // [Response body]
    //   {"name":"Robbie","age":95}
    @GetMapping("/json/class")
    public Star helloClassJson() {
        return new Star("Robbie", 95);
    }
}