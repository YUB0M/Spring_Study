package org.zerock.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

    // Task     URL           Method    Parameter   From        URL 이동
    // 전체목록 /board/list     GET
    // 등록처리 /board/register POST       모든항목      입력화면 필요    이동
    // 조회    /board/read     GET        bno=123
    // 삭제처리 /board/remove   POST        bno         입력화면 필요    이동
    // 수정처리 /board/modify   POST        모든항목       입력화면 필요    이동

    private BoardService service;

//    @GetMapping("/list")
//    public void list(Model model){
//        log.info("list");
//        model.addAttribute("list", service.getList());
//    }

    @GetMapping("/list")
    public void getlist(Model model, Criteria cri){

        log.info("list: " + cri);
        model.addAttribute("list", service.getList(cri));
        // model.addAttribute("pageMaker", new PageDTO(cri, 123));
        int total = service.getTotal(cri);
        log.info("total: " + total);
        model.addAttribute("pageMaker", new PageDTO(cri, total));
    }

    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr) {
        /* 등록 작업이 끝난 후에 다시 목록 화면으로 이동하기 위해서 RedirectAttributes를 파라미터로 지정 */

        log.info("register : " + board);
        service.register(board);
        rttr.addFlashAttribute("result", board.getBno());

        /* 리턴 시 redirect:를 사용하면 스프링 MVC가 내부적으로 response.sendRedirect()를 처리함 */
        return "redirect:/board/list";
    }

    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model)
    {
        /* 1. 수정/삭제가 가능한 화면으로 이동하는 것은 조회 페이지와 같으므로 get()메서드를 수정
        *  2. 조회->목록으로 이동하기 위해 Criteria 파라미터 추가
        * */
        log.info("/get or modify");
        model.addAttribute("board", service.get(bno));
    }


    @PostMapping("/modify")
    public String modify(BoardVO board, RedirectAttributes rttr) {
        log.info("modify: " + board);  //디버깅

        //수정코드
        if (service.modify(board)) {
            rttr.addFlashAttribute("result", "success");
        }

        return "redirect:/board/list";  //주소 변경
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
        log.info("remove...." + bno);
        if (service.remove(bno)) {
            rttr.addFlashAttribute("result","success");
            //addFlashAttribute로 보관된 데이터는 단 한번만 사용할 수 있게 보관
            //내부적으로 HttpSession을 이용해서 처리
        }
        return "redirect:/board/list";
    }

    @GetMapping("/register")
    public void register(){

    }
    // 게시글의 등록 작업은 화면에서 입력을 받아야 하므로 GET 방식으로
    // 입력페이지를 볼 수 있도록 메서드를 추가(입력페이지를 보여주는 역할만 하므로 별도의 처리가 필요하지 않음)
}
