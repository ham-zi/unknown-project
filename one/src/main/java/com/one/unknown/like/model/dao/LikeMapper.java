package com.one.unknown.like.model.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.one.unknown.like.model.dto.LikeDto;

@Mapper
public interface LikeMapper {
	
	@Insert("INSERT INTO LIKES VALUES(#{refUser}, #{refBno})")
	void insertLike(LikeDto likeDto); 
	
	@Delete("DELETE FROM LIKES WHERE REF_USER = #{refUser} AND REF_BNO = #{refBno}")
	void deleteLike(LikeDto likeDto);
	
	@Select("SELECT COUNT(*) FROM LIKES WHERE REF_USER = #{refUser} AND REF_BNO = #{refBno}")
	int countByUserBno(LikeDto likeDto);
	
	@Select("SELECT COUNT(*) FROM LIKES WHERE REF_BNO = #{refBno}")
	int countByBno(Long refBno);
}
