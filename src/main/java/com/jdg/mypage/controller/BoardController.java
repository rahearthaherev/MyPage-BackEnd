package com.jdg.mypage.controller;

import com.jdg.mypage.dto.BoardDTO;
import com.jdg.mypage.entity.BoardList;
import com.jdg.mypage.entity.MenuDetail;
import com.jdg.mypage.repository.BoardRepository;
import com.jdg.mypage.repository.MenuDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
@Slf4j
@RequestMapping("/board/")
@Transactional
public class BoardController {
    @Autowired
    private MenuDetailRepository menuDetailRepository;
    @Autowired
    private BoardRepository boardRepository;
    @PostMapping("header")
    public List<MenuDetail> GetMenuList(@RequestBody MenuDetail menu) {
        List<MenuDetail> menuList = menuDetailRepository.findSameCatagoryMenu(menu.getMenu_sub_key());
        log.info(menuList.toString());
        return menuList;
    }
    @PostMapping("list")
    public Iterable<BoardList> getBoardList(@RequestBody BoardDTO boardDTO) {
        String key = boardDTO.getMenu_sub_key();
        String search = boardDTO.getSearch();
        Iterable<BoardList> boardLists = boardRepository.getBoardList(key, search);
        log.info(boardLists.toString());
        return  boardLists;
    }

    @PostMapping("submit")
    public void submitBoard(@RequestBody BoardDTO boardDTO) {
        log.info(boardDTO.toString());
    }

    @PostMapping("getpage")
    public BoardList getBoard(@RequestBody String[] key) {
        log.info(key[0]);
        BoardList boardList = boardRepository.getBoard(key[0]);
        log.info(boardList.toString());
        return boardList;
    }
}