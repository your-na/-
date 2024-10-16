package com.study.board.contoller;
import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;


    @GetMapping("/board/write")
    public String boardWriteForm(){
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model) {
        boardService.write(board);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message"; // "message.html" 템플릿 파일을 반환
    }


    @GetMapping("/board/list")
    public  String boardList(Model model){
        List<Board> boards = boardService.boardList();
        long totalBoards = boardService.countBoards(); // 전체 게시글 수
        int pageSize = 10; // 한 페이지에 표시할 게시글 수
        int totalPages = (int) Math.ceil((double) totalBoards / pageSize); // 전체 페이지 수 계산

        model.addAttribute("list", boards);
        model.addAttribute("totalPages", totalPages);
        return "boardList"; // 게시글 리스트를 보여줄 템플릿
    }

    @GetMapping("/board/view")
    public String boardView(Model model, @RequestParam("id") int id) {
        model.addAttribute("board", boardService.boardView(id));
        // localhost/board/view?id=1
        return "boardView"; // 뷰를 담당하는 템플릿 파일 이름 리턴에 써주면 된다..
    }
    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam("id") int id){
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    @GetMapping("board/modify/{id}")
    public String boardModify(@PathVariable("id") int id, Model model) {

        model.addAttribute("board",boardService.boardView(id));

        return "boardmodify";
    }
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") int id, Board board){
        Board boardTemp =boardService.boardView(id);// 기존 내용 가져오기
        boardTemp.setTitle(board.getTitle()); //덮어 싀우기
        boardTemp.setContent(board.getContent()); //덮어 싀우기

        boardService.write(boardTemp);
        return "redirect:/board/list";
    }

}
