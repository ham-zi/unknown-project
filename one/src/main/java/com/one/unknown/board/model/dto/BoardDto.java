package com.one.unknown.board.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.one.unknown.file.model.dto.FileDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardDto {
	private Long boardNo;
	private String refUser;
	private String boardTitle;
	private String boardContent;
	private String password;
	private LocalDateTime createDate;
	private int views;
	private int likes;
	private String fileUrl;
	private List<FileDto> images;
	
}
