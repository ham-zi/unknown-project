package com.one.unknown.token.model.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.one.unknown.token.model.vo.RefreshToken;

@Mapper
public interface TokenMapper {

	@Insert("INSERT INTO UNKNOWN_TOKEN VALUES (#{token}, #{refUser}, #{expiration})")
	int saveToken(RefreshToken token);
	
	@Select("SELECT TOKEN, REF_USER, EXPIRATION FROM UNKNOWN_TOKEN WHERE TOKEN = #{token}")
	RefreshToken findByToken(String token);
	
	@Delete("DELETE FROM UNKNOWN_TOKEN WHERE TOKEN = #{refreshToken}")
	void deleteToken(String refreshToken);
}
