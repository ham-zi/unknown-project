package com.one.unknown.user.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.one.unknown.exception.UserNotFoundException;
import com.one.unknown.user.model.dao.UnknownMapper;
import com.one.unknown.user.model.dto.UnknownDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnknownService {
	private final UnknownMapper userMapper;
	
	@Transactional
	public String getUserName() {
		UnknownDto dto = new UnknownDto();
		int userName = userMapper.getUserName(dto);
		log.info("{}", dto);
		if(userName != 1) {
			throw new UserNotFoundException("아이디 발급에 실패했습니다. 잠시 후에 다시 시도해주세요.");
		}
		
		return dto.getUserName();
	}

}
