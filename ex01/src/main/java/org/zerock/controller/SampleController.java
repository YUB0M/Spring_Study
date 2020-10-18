package org.zerock.controller;


import lombok.extern.log4j.Log4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

//servlet-context.xml과 같은 역할을 함
//지정된 패키지를 조사(스캔)하도록 설정되어 있으며 해당 패키지에 선언된 클래스들을
//조사하면서 스프링에서 객체(Bean)설정에 사용되는 어노테이션을 관리
@Controller
@RequestMapping("/STS/*")
@Log4j
public class SampleController {

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class, new
    CustomDateEditor(dateFormat, false));
    }


    // RequestMapping을 줄여서 사용할 수 있는 @GetMapping과 @PostMapping
    // RequestMapping은 GET,POST를 모두 지원해야 하는 경우 배열로 처리해서 지정
    @RequestMapping("")
    public void basic(){
        log.info("basic..............");
    }

    @RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
    public void basicGet() {
        log.info("basic get..............");
    }

    @GetMapping("/basicOnlyGet")
    public void basicGet2(){
        log.info("basic get only get.................");
    }

    @GetMapping("/ex01")
    public String ex01(SampleDTO dto) {
        log.info("" + dto);
    return "ex01";
    }

    @GetMapping("/ex02")
    public String ex02(@RequestParam("name") String name,
        @RequestParam("age") int age) {
        log.info("name: " + name);
        log.info("age: " + age);
        //http://localhost:8080/STS/ex02?name=BOM&age=22
        //33867 [http-nio-8080-exec-5] INFO  org.zerock.controller.SampleController  - name: BOM
        //33867 [http-nio-8080-exec-5] INFO  org.zerock.controller.SampleController  - age: 22
    return "ex02";
    }

    @RequestMapping("/ex02List")
    public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
        log.info("ids: " + ids);
        //http://localhost:8080/STS/ex02List?ids=111&ids=222&ids=333
        //134044 [http-nio-8080-exec-6] INFO  org.zerock.controller.SampleController  - ids: [111, 222, 333]
    return "ex02List";
    }

    @GetMapping("/ex02Array")
    public String ex02Array(@RequestParam("ids") String[] ids) {
        log.info("array ids: " + Arrays.toString(ids));
    return "ex02Array";
    }

    // SampleDTOList 타입을 파라미터로 사용하는 메서드
    @GetMapping("/ex02Bean")
    public String ex02Bean(SampleDTOList list){
        log.info("list dtos: " + list);
        //http://localhost:8080/STS/ex02Bean?list[0].name=aaa&list[2].name=bbb
        //여기서 [는 %5B ]는 %5D로 변경하여 아래와 같이 주소를 변경
        //http://localhost:8080/STS/ex02Bean?list%5B0%5D.name=aaa&list%5B2%5D.name=bbb
        //(list=[SampleDTO(name=aaa, age=0), SampleDTO(name=null, age=0), SampleDTO(name=bbb, age=0)])

        //http://localhost:8080/STS/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=BBB&list%5B2%5D.name=CCC
        //(list=[SampleDTO(name=aaa, age=0), SampleDTO(name=BBB, age=0), SampleDTO(name=CCC, age=0)])
        // 3개의 객체가 생성된 것을 볼 수 있고 [ ] 안에 인덱스 번호에 맞게 속성값이 셋팅되어 있음
    return "ex02Bean";
    }

    @GetMapping("/ex03")
    public String ex03(TodoDTO todo){
        log.info("todo: " + todo);

        //http://localhost:8080/STS/ex03?title=test&dueDate=2020/01/01
    return "ex03";
    }

//    @GetMapping("/ex04")
//    public String ex04(SampleDTO dto, int page){
//        log.info("dto: " + dto);
//        log.info("page:" + page);
//        //http://localhost:8080/STS/ex04?name=aaa&age=11&page=9
//        //SAMPLEDTO SampleDTO(name=aaa, age=11)
//        //PAGE
//        //int 타입으로 전달된 page는 화면(브라우저)에 전달되지 않은 것을 확인할 수 있음
//    return "/STS/ex04";
//    }

    @GetMapping("/ex04")
    public String ex04(SampleDTO dto, @ModelAttribute("page") int page){
        log.info("dto: " + dto);
        log.info("page:" + page);
        //@ModelAttribute 어노테이션을 사용하면 타입에 관계 없이 모델에 담아서 전달함
        //SAMPLEDTO SampleDTO(name=aaa, age=11)
        //PAGE 9
        return "/STS/ex04";
    }

}
