package com.one.unknown.board.model.vo;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Board {
	private Long boardNo;
	private String refUser;
	private String boardTitle;
	private String boardContent;
	private LocalDateTime createDate;
	private String password;
	private int views;

}
