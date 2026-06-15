package com.one.unknown.auth.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.one.unknown.auth.model.dto.UserDto;

@Mapper
public interface LoginMapper {
	
	@Select("""
  		SELECT	
				 USER_ID
			   , USER_PWD
			   , USER_NAME
			   , EMAIL
			   , ROLE
			   , STATUS
	      FROM
			     UNKNOWN_ADMIN
		 WHERE	     
			 	STATUS = 'Y'
		   AND 
		   		USER_ID = #{username}
			""")
	UserDto login(String username);

}
