package com.sparta.springmvc.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {
    private static long visitCount = 0;

    @GetMapping("/static-hello")
    public String hello(){
        return "hello.html"; //String인 html 뷰페이지를 반환한다.
    }
    /*
    http://localhost:8080/static-hello 로 접근하면 처음엔 이런 에러가 발생한다
    Whitelabel Error Page
    This application has no explicit mapping for /error, so you are seeing this as a fallback.

    Mon Aug 28 13:08:53 KST 2023
    There was an unexpected error (type=Internal Server Error, status=500).
    -------------
    이 페이지는 정적 페이지기 때문에
    http://localhost:8080/hello.html 로 접근하면 html이 보여진다.

    하지만 GetMapping으로 저 URL로 접근 할 수 없을까?
    build.gradle에서 이 부분을
    //    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    주석하면 URL경로에서 context/static-hello로 접속 가능
    하지만 근본적인 해결이 아니다.


    //----------------- REDIRECT ------------------------
    아래 처럼 redirect로 정적 페이지를 이동 할 수 있다.
    */

    @GetMapping("/html/redirect")
    public String htmlStatic(){
        return "redirect:/hello.html";
    }
    /*이제 다시
    * thymeleaf를 주석부분을 활성화 해서
    * localhost:8080/html/redirect 로 접속해보면,
    * hello.html으로 경로를 변경시키는 것을 볼 수 있다.
    * */

    //----------------- TEMPLATE FOLDER! --------------------

    //--------------STATIC PAGE----------------
    //templates폴더에 넣은 html페이지로 이동하는지 확인해보자.
    @GetMapping("/html/templates")
    public String htmlTemplates(){
        return "hello"; // Templates 폴더에 넣으면, "redirect:/hello.html" 처럼 작성 안해도,
        // hello처럼 ""이름""만 넣어도 hello.html을 찾아간다. 규칙임
    }
//    http://localhost:8080/html/templates로 접속하면,
    //hello.html을 찾아간다.

    //--------------DYNAMIC PAGE-------------------
    @GetMapping("/html/dynamic")
    public String htmlDynamic(Model model){ //<- Model 을 넣어준다.
        visitCount++; // 방문자 증감용 클래스변수(cv = 공통,객체없이,언제나접근가능)
        model.addAttribute("visits", visitCount); // <- html의 ${visits} 속성값에, visitCount를 넣어준다는 뜻
        return "hello-visit"; //view이름
    }
}
