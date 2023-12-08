package com.jdg.mypage.service;

import com.jdg.mypage.dto.BoardDTO;
import com.jdg.mypage.repository.BoardRepository;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@ToString
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardService {
    @Autowired
    BoardRepository boardRepository;
    public BoardDTO addBoard(BoardDTO boardDTO) {
        try {
            if (boardDTO.getBoard_key() == null) {
                int nextIndex = boardRepository.findCurrentIndex();
                String board_key = String.format("%05d", nextIndex);
                boardDTO.setBoard_key(board_key);
            }
            if (boardDTO.getCreate_time() == null) {
                boardDTO.setCreate_time(new Timestamp(System.currentTimeMillis()));
            }
            else{
                boardDTO.setModified_time(new Timestamp(System.currentTimeMillis()));
            }
            log.info(boardDTO.toBoardList().toString());
            boardRepository.save(boardDTO.toBoardList());
            return boardDTO;
        }catch (Exception e){
            log.info(e.getMessage());
            return null;
        }
    }
}
