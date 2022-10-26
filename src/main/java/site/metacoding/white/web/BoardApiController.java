package site.metacoding.white.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.Board;
import site.metacoding.white.dto.BoardReqDto.BoardSaveReqDto;
import site.metacoding.white.dto.BoardReqDto.BoardUpdateReqDto;
import site.metacoding.white.dto.BoardRespDto.BoardSaveRespDto;
import site.metacoding.white.dto.ResponseDto;
import site.metacoding.white.dto.SessionUser;
import site.metacoding.white.service.BoardService;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;
    private final HttpSession session;

    @PostMapping("/board")
    public ResponseDto<?> save(@RequestBody BoardSaveReqDto boardSaveReqDto) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        boardSaveReqDto.setSessionUser(sessionUser);
        BoardSaveRespDto boardSaveRespDto = boardService.save(boardSaveReqDto); // 서비스에는 단 하나의 객체만 전달한다.
        return new ResponseDto<>(1, "성공", boardSaveRespDto);
    }

    // 게시글 상세보기
    @GetMapping("/board/{id}")
    public ResponseDto<?> findById(@PathVariable Long id) {
        return new ResponseDto<>(1, "성공", boardService.findById(id));
    }

    @GetMapping("/board")
    public ResponseDto<?> findAll() {
        return new ResponseDto<>(1, "성공", boardService.findAll());
    }

    @PutMapping("/board/{id}")
    public ResponseDto<?> update(@PathVariable Long id, @RequestBody BoardUpdateReqDto boardUpdateReqDto) {
        boardUpdateReqDto.setId(id);
        return new ResponseDto<>(1, "성공", boardService.update(boardUpdateReqDto));
    }

    @DeleteMapping("/board/{id}")
    public ResponseDto<?> deleteById(@PathVariable Long id) {
        boardService.deleteById(id);
        return new ResponseDto<>(1, "성공", null);
    }

    // @GetMapping("/v2/board/{id}")
    // public String findByIdV2(@PathVariable Long id) {
    // System.out.println("현재 open-in-view는 true 인가 false 인가 생각해보기!!");
    // Board boardPS = boardService.findById(id);
    // System.out.println("board.id : " + boardPS.getId());
    // System.out.println("board.title : " + boardPS.getTitle());
    // System.out.println("board.content : " + boardPS.getContent());
    // System.out.println("board.user.username : " +
    // boardPS.getUser().getUsername());
    // System.out.println("open-in-view가 false이면 Lazy 로딩 못함");

    // // 날라감)
    // return "ok";
    // }
}
