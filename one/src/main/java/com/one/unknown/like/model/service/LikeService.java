package com.one.unknown.like.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.one.unknown.like.model.dao.LikeMapper;
import com.one.unknown.like.model.dto.LikeDto;
import com.one.unknown.user.model.service.UnknownService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {
	private final LikeMapper likeMapper;
	private final UnknownService unknownService;
	
	private void insertLike(LikeDto likeDto) {
		likeMapper.insertLike(likeDto);
	}
	
	private void deleteLike(LikeDto likeDto) {
		likeMapper.deleteLike(likeDto);
	}
	
	private int countByUserBno(LikeDto likeDto) {
		return likeMapper.countByUserBno(likeDto);
	}
	
	private int countByBno(Long refBno) {
		return likeMapper.countByBno(refBno);
	}
	
	@Transactional
	public int requestLike (LikeDto likeDto) {
		unknownService.hasUserName(likeDto.getRefUser());
		if(countByUserBno(likeDto) == 1) {
			deleteLike(likeDto);
			return  countByBno(likeDto.getRefBno());
		} else {
			insertLike(likeDto);
			return countByBno(likeDto.getRefBno());
		}
	}
	

	
}
