package com.sparta.springmvc.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello/request")
public class RequestController {
    //-------------------Path Variable과 Request Param--------------------
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



    // [1-2] Request Param 방식 (쿼리스트링방식이라고도함)
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




    //-----------------HTTP 데이터를 객체로 처리하는 방법------------------
    // Request Param 방식 form 태그 POST
    // [Request sample]
    // POST http://localhost:8080/hello/request/form/param
    // Header
    //  Content type: application/x-www-form-urlencoded
    // Body
    //  name=Robbie&age=95
    @PostMapping("/form/param")
    @ResponseBody
    public String helloPostRequestParam2(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }

    // [Request sample]
    // Query String 방식
    // GET http://localhost:8080/hello/request/form/param/model?name=Robbie&age=95
    @GetMapping("/form/param/model")
    @ResponseBody                       //@ModelAttribute는 생략 되긴한다. Spring은 Simple Value Type들은 @RequestParam을 생략했다고 인지하고 자동 붙여준다. 사용자 객체인 경우 @ModelAttribute를 생략한줄 알고 붙여준다.
    public String helloRequestParam(@ModelAttribute Star star) { // 들어오는 쿼리스트링이 많아지면 위처럼 @RequestParam을 계속 써줘야되서 코드가 너무 길어진다. 따라서 객체로 받을 수 있다.
        return String.format("Hello, @ModelAttribute.<br> (name = %s, age = %d) ", star.name, star.age); // 객체 클래스에 @Setter또는 생성자가 무조건 있어야 한다.
    }

    // [Request sample]
    // POST http://localhost:8080/hello/request/form/json
    // Header
    //  Content type: application/json
    // Body
    //  {"name":"Robbie","age":"95"}
    @PostMapping("/form/json")
    @ResponseBody
    public String helloPostRequestJson(@RequestBody Star star) { //바디에 json 형태가 넘어오는 경우 사용. json형태인줄 알려주려면 @RequestBody 어노테이션을 꼭 붙여줘야 한다. (Jackson 반대!)
        return String.format("Hello, @RequestBody.<br> (name = %s, age = %d) ", star.name, star.age);
    }
}