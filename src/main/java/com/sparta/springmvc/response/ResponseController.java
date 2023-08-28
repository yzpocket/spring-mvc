package com.sparta.springmvc.response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //컨트롤러입니다~! 알림
@RequestMapping("/response") // /response로 시작하는 url 이 컨트롤러가 처리
public class ResponseController {
    // Response Body는 아래처럼 반환됨
    // Context-Type: text/html //하지만 텍스트임 String이니까.
    // Response Body
    // {"name":"Robbie", "age":95}
    @GetMapping("/json/string")
    @ResponseBody
    public String helloStringJson() {
        return "{\"name\":\"Robbie\",\"age\":95}"; // 문자열을 강제로 Json 모습의 text로 반환해봄
    }

    // Response Body는 아래처럼 반환됨
    // Context-Type: application/json // <JSON 형태임
    // Response Body
    // {"name":"Robbie", "age":95}
    // Json 형태에서는 Key : Value 라고 부른다.
    @GetMapping("/json/class")
    @ResponseBody
    public Star helloCalssJson() {
        return new Star("Robbie", 95);
    } // Spring은 객체를 ResponseBody에 실어서 반환하면, 내부적으로 자동으로 Java의 객체를 Json 형태로 바꾼다.
}
