package com.chuchu.admin;

import com.chuchu.admin.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
//RubWith 테스트를 진행할 때 JUnit 에 내장된 실행자 외에 다른 실행자를 실행 시킵니다.
//여기서는 SpringRunner 이라는 실행자를 사용
// 스프링 부트 테스트와 Junit 사이에 연결자 역활
@WebMvcTest(controllers = HelloController.class)
// WebMvcTest
// 여러 스프링 테스트 어노테이션 중 Web(spring MVC)에 집중할 수 있는 어노테이션
// 선언할 경우 @Controller, @ControllerAdvice 등을 사용 할 수 있다.
// 단 @Service, @Component, @Repository 사용 불가
// 여기서는 컨트롤러만 사용하기 때문에 선언
public class HelloControllerTest {

    //스프링이 관리하는 Bean(빈)을 주입 받습니다.
    @Autowired
    private MockMvc mvc;
    // 웹 API를 테스할 때 사용합니다. 스프링 MVC 테스트의 시작점 입니다.
    // 이 클래스를 통해서 HTTP GET, POST 등에 대한 API 테스트 가능합니다.

    @Test
    public void hello_is_return() throws Exception{
        String hello = "hello";
        //mvc.perform(get("/hello")를 통해서 MockMVC를 통해 /hello 주소로 HTTP GET을 요청합니다.
        //체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언 할 수 있습니다.
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                // mvc.perform의 결과를 검증합니다.
                // HTTP Header의 Status를 검증합니다.
                // 우리가 흔히 아록 있는 200,404,500 등의 상태를 검증 합니다.
                // 여기선 OK 즉, 200인지 아닌지 검증합니다.
                .andExpect(content().string(hello));
                //mvc.perform의 결과를 검증합니다.
                //응답 본문의 내용을 검증합니다.
                //Controller에서 "hello"를 리턴하기 때문에 이값이 맞는지 검증합니다.

    }
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                                .param("name",name)
                                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }

}
// param API테스트할 때 사용될 요청 파라미터를 설정합니다.
// 단 String만 허용
// 그래서 날짜 숫자등의 데이터 등록할 때는 문자열로 변경해야만 합니다
// jsonPath Json 응답값을 필드별로 검증할 수 있는 메소드 입니다.
// $를 기준으로 필드명을 명시
// 여기서는 name과 amount를 검증하니 $.name, $.amount로 검증