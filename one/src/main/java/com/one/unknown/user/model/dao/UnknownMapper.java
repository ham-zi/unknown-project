package com.one.unknown.user.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.one.unknown.user.model.dto.UnknownDto;

@Mapper
public interface UnknownMapper {
	public int getUserName(UnknownDto dto);

}
