package com.one.unknown.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.one.unknown.board.model.dto.BoardDto;
import com.one.unknown.board.model.vo.Board;

@Mapper
public interface BoardMapper {
	int saveBoard(Board board);
	List<BoardDto>findAll(RowBounds rb);
	List<BoardDto>findAllByAdmin(RowBounds rb);
	int updateBoard(BoardDto board);
	int deleteBoard(BoardDto board);
	BoardDto findByBno(Long boardNo);
	int increaseViews(Long boardNo);
}
