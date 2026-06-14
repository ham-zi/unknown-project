package com.one.unknown.auth.model.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.one.unknown.auth.model.dao.LoginMapper;
import com.one.unknown.auth.model.dto.UserDto;
import com.one.unknown.auth.model.vo.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	private final LoginMapper loginMapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto userDto = loginMapper.login(username);
		
		return CustomUserDetails.builder()
				                .username(userDto.getUserId())
				                .password(userDto.getUserPwd())
				                .authorities(Collections.singletonList(new SimpleGrantedAuthority(userDto.getRole())))
				                .status(userDto.getStatus())
				                .build();
				                
	}
	
	
	
	

}
	