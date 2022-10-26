package site.metacoding.white.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.BoardRepository;
import site.metacoding.white.dto.BoardReqDto.BoardSaveReqDto;
import site.metacoding.white.dto.BoardRespDto.BoardAllRespDto;
import site.metacoding.white.dto.BoardRespDto.BoardDetailRespDto;
import site.metacoding.white.dto.BoardRespDto.BoardSaveRespDto;

// 트랜잭션 관리
// DTO 변환해서 컨트롤러에게 돌려줘야함

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardSaveRespDto save(BoardSaveReqDto boardSaveReqDto) {
        // 핵심 로직
        Board boardPS = boardRepository.save(boardSaveReqDto.toEntity());

        // DTO 전환
        BoardSaveRespDto boardSaveRespDto = new BoardSaveRespDto(boardPS);

        return boardSaveRespDto;
    } // DB커넥션을 종료

    @Transactional(readOnly = true) // 트랜잭션을 걸면 OSIV가 false여도 디비 커넥션이 유지됨.
    public BoardDetailRespDto findById(Long id) {

        Optional<Board> boardOP = boardRepository.findById(id);
        if (boardOP.isPresent()) {
            BoardDetailRespDto boardDetailRespDto = new BoardDetailRespDto(boardOP.get());
            return boardDetailRespDto;
        } else {
            throw new RuntimeException("해당 " + id + "로 상세보기를 할 수 없습니다.");
        }
    }

    @Transactional
    public void update(Long id, Board board) {
        Optional<Board> boardOP = boardRepository.findById(id);
        if (boardOP.isPresent()) {
            boardOP.get().update(board.getTitle(), board.getContent());
        } else {
            throw new RuntimeException("해당 " + id + "로 수정을 할 수 없습니다.");
        }

    } // 트랜잭션 종료시 -> 더티체킹을 함

    @Transactional(readOnly = true)
    public List<BoardAllRespDto> findAll() {
        List<Board> boardList = boardRepository.findAll();

        List<BoardAllRespDto> boardAllRespDtoList = new ArrayList<>();
        for (Board board : boardList) {
            boardAllRespDtoList.add(new BoardAllRespDto(board));
        }

        return boardAllRespDtoList;
    }

    // return boardRepository.findAll()
    // .stream().map((board) -> new
    // BoardAllRespDto(board)).collect(Collectors.toList());

    // delete는 리턴 안함.
    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

}
