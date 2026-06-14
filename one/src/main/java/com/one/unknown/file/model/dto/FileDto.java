package com.one.unknown.file.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDto {
	private Long fileNo;
	private Long refBno;
	private String fileUrl;
	private int fileOrder;

}
