package com.one.unknown.board.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.one.unknown.board.model.dao.BoardMapper;
import com.one.unknown.board.model.dto.BoardDto;
import com.one.unknown.board.model.vo.Board;
import com.one.unknown.exception.DatabaseOperationException;
import com.one.unknown.exception.NotFoundBoardException;
import com.one.unknown.exception.NotMatchesPasswordException;
import com.one.unknown.exception.RequestFailBoardException;
import com.one.unknown.file.model.dto.FileDto;
import com.one.unknown.file.model.service.FileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardMapper boardMapper;
	private final PasswordEncoder passwordEncoder;
	private final FileService fileService;
	
	@Transactional
	public void saveBoard(BoardDto board, List<MultipartFile> files) {
		Board b = Board.builder()
				       .boardTitle(board.getBoardTitle())
				       .boardContent(board.getBoardContent())
				       .refUser(board.getRefUser())
				       .password(passwordEncoder.encode(board.getPassword()))
				       .build();
		int result = boardMapper.saveBoard(b);
		
		if(result != 1) {
			throw new DatabaseOperationException("게시글 작성 실패하였습니다. 잠시 후에 다시 시도해주세요.");
		}
		//파일 저장
		fileService.saveFiles(files, b.getBoardNo());
	}
	
	
	//게시글 전체 조회 / 대표이미지 첨부
	public List<BoardDto> findAll(int page) {
		RowBounds rb = new RowBounds((page-1)*5,5); //페이징처리 나중에 바꾸기 앞단이랑 맞추기
		List<BoardDto> boards = boardMapper.findAll(rb);
		for(BoardDto b : boards) {
			String fileUrl = fileService.findMainFile(b.getBoardNo());
			b.setFileUrl(fileUrl);
		}
		return boards;
	}

	
	//게시글 업데이트
	@Transactional
	public void updateBoard(BoardDto board, List<MultipartFile> files) {
		//수정하려는 게시판 있는거야?
		BoardDto b = existsBoard(board.getBoardNo());
		
		checkMatchesPassword(board.getPassword(), b.getPassword());
		
		//게시판 수정
		int result = boardMapper.updateBoard(board);
		//게시판 수정이 성공했으면
		if(result != 1) {
			throw new RequestFailBoardException("게시글 업데이트에 실패했습니다.");
		}
		//파일들을 삭제
		fileService.deleteFiles(board.getBoardNo());
		
		//파일이 존재하면 파일인서트
		if(files.isEmpty()) {
			return;
		}
		//파일 저장
		fileService.saveFiles(files, board.getBoardNo());
	}
	
	//게시글 삭제
	@Transactional
	public void deleteBoard(BoardDto board) {
		BoardDto b = existsBoard(board.getBoardNo());
		
		checkMatchesPassword(board.getPassword(), b.getPassword());
		
		int result = boardMapper.deleteBoard(board);
		if(result != 1) {
			throw new RequestFailBoardException("게시글 삭제에 실패했습니다.");
		}
	}
	
	private BoardDto existsBoard(Long boardNo) {
		BoardDto board = boardMapper.findByBno(boardNo);
		if(board == null) {
			throw new NotFoundBoardException("게시글이 존재하지 않습니다.");
		};
		return board;
	}
	
	private void checkMatchesPassword(String rawPwd, String endPwd) {
		if (!passwordEncoder.matches(rawPwd, endPwd)) {
			throw new NotMatchesPasswordException("비밀번호가 일치하지 않습니다.");
		}
	}
	
	
	@Transactional
	public BoardDto findByBno(Long boardNo) {	
	
	//조회수 올리기
	increaseViews(boardNo);
	//게시판 조회
	BoardDto board = boardMapper.findByBno(boardNo);
	//이미 좋아요를 했는지 확인
	
	//파일 조회
	List<FileDto>files =fileService.findByBno(boardNo);
	board.setImages(files);
	
	
	return board;
	}

	private void increaseViews(Long boardNo) {
		existsBoard(boardNo);
		boardMapper.increaseViews(boardNo);
	}
	

}
