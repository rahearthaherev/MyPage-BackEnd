package com.jdg.mypage.controller;

import com.jdg.mypage.dto.BoardDTO;
import com.jdg.mypage.entity.BoardList;
import com.jdg.mypage.entity.MenuDetail;
import com.jdg.mypage.repository.BoardRepository;
import com.jdg.mypage.repository.MenuDetailRepository;
import com.jdg.mypage.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(originPatterns = "*")
@RestController
@Slf4j
@RequestMapping("/board/")
@Transactional
public class BoardController {
    @Autowired
    private MenuDetailRepository menuDetailRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;
    @PostMapping("header")
    public List<MenuDetail> GetMenuList(@RequestBody MenuDetail menu) {
        List<MenuDetail> menuList = menuDetailRepository.findSameCatagoryMenu(menu.getMenu_sub_key());
        return menuList;
    }
    @PostMapping("list")
    public Iterable<BoardList> getBoardList(@RequestBody BoardDTO boardDTO) {
        String key = boardDTO.getMenu_sub_key();
        String search = boardDTO.getSearch();
        Iterable<BoardList> boardLists = boardRepository.getBoardList(key, search);
        return  boardLists;
    }

    @PostMapping("submit")
    public BoardDTO submitBoard(@RequestBody BoardDTO boardDTO) {
        return boardService.addBoard(boardDTO);
    }

    @PostMapping("getpage")
    public BoardList getBoard(@RequestBody String[] key) {
        BoardList boardList = boardRepository.getBoard(key[0]);
        return boardList;
    }

    @PostMapping("deleteboard")
    public boolean deleteBoard(@RequestBody BoardList board) {
        boardRepository.delete(board);
        return true;
    }
}
