package com.one.unknown.file.model;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.one.unknown.file.model.dto.FileDto;

@Mapper
public interface FileMapper {
	@Insert("INSERT INTO UNKNOWN_FILE VALUES(SEQ_UF.NEXTVAL, #{refBno}, #{fileUrl}, #{fileOrder})")
	int saveFile(FileDto file);
	
	@Select("SELECT FILE_NO, REF_BNO, FILE_URL, FILE_ORDER FROM UNKNOWN_FILE WHERE REF_BNO = #{refBno} ORDER BY FILE_ORDER")
	List<FileDto> findByBno(Long refBno);
	
	@Select("SELECT FILE_URL FROM UNKNOWN_FILE WHERE REF_BNO = #{refBno} AND FILE_ORDER = (SELECT MAX(FILE_ORDER) FROM UNKNOWN_FILE WHERE REF_BNO = #{refBno})")
	String  findMaxFileOrder(Long refBno);
	
	@Delete("DELETE FROM UNKNOWN_FILE WHERE REF_BNO = #{refBno}")
	int deleteFiles(Long refBno);
}
