package com.sparta.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello/request")
public class RequestController {
    @GetMapping("/form/html")
    public String helloForm() {
        return "hello-request-form";
    }
    //----------[1]GET 방식---------
    // [1-1] Path Variable 방식
    // [Request sample]
    // GET http://localhost:8080/hello/request/star/Robbie/age/95
    @GetMapping("/star/{name}/age/{age}") //필요한 데이터 {}중괄호
    @ResponseBody
    public String helloRequestPath(@PathVariable String name, @PathVariable int age) //@PathVariable 어노테이션으로 URL에 있는 {name}을 매개변수 변수 name으로 받을 것이며, 아래 포맷에서 name으로도 사용 된다는 뜻
    {
        return String.format("Hello, @PathVariable.<br> name = %s, age = %d", name, age);
    }
    // http://localhost:8080/hello/request/star/Robbie/age/95
    // 이것은 그럼 form HTML에서 태그의 name="name" 이 onclick 버튼으로 Get 요청 되도록 프론트 엔드가 설계 될 것이기 때문에
    // 백엔드는 "/star/{name}/age/{age}" 이러한 방식으로 소통 할 것을 의견을 공유해야 한다.



    // [1-2] Request Param 방식
    // [Request sample]
    // GET http://localhost:8080/hello/request/form/param?name=Robbie&age=95
    @GetMapping("/form/param")
    @ResponseBody
    public String helloGetRequestParam(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }
    // http://localhost:8080/hello/request/form/param?name=hi&age=23
    // 이것은 그럼 form HTML에서 태그의 name="name" 이 onclick 버튼으로 Get 요청 되도록 프론트 엔드가 설계 될 것이기 때문에
    // 백엔드는 "?name=hi&age=00" 이러한 방식으로 소통 할 것을 의견을 공유해야 한다.


    //----------[2]POST 방식---------
    // [2-1] Request Param 방식
    // [Request sample]
    // POST http://localhost:8080/hello/request/form/param
    // Header
    //  Content type: application/x-www-form-urlencoded
    // Body
    //  name=Robbie&age=95
    @PostMapping("/form/param")
    @ResponseBody
    //public String helloPostRequestParam(@RequestParam(required = false) String name, @RequestParam int age) { // required = false 를 하면 매개변수 필수가 아니라 오류는 안난다. default 가 true임, 안들어온 값은 null로 초기화 됨.
    public String helloPostRequestParam(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }
    //http://localhost:8080/hello/request/form/param POST 방식이기 때문에 URL로 확인 할 수 없다.
    //개발자 도구에서 Network의 Payload를 확인하면 name: asdf , age: 12 가 들어온 것을 확인 할 수 있다.
}