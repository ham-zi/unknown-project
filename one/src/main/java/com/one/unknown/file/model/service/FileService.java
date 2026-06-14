package com.one.unknown.file.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.one.unknown.file.model.FileMapper;
import com.one.unknown.file.model.dto.FileDto;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {
	private  Path fileLocation;
	private final FileMapper fileMapper;


	
	@PostConstruct
    public void init() {
        this.fileLocation = Paths.get("uploads").toAbsolutePath().normalize();
    }
	
	public String store(MultipartFile file) {
		String changeName = renameFile(file.getOriginalFilename());
		
		
		Path targetLocation = this.fileLocation.resolve(changeName);
		
		try {
			Files.copy(file.getInputStream(),
					   targetLocation,
					   StandardCopyOption.REPLACE_EXISTING);
			
			return "http://localhost/uploads/" + changeName;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("이상한 파일쓰~~");
		}
	}
	
	private String renameFile(String originName) {
		StringBuilder sb = new StringBuilder();
		sb.append("one_");
		sb.append(new SimpleDateFormat("yyyyMMddmmss").format(new Date()));
		sb.append("_");
		sb.append((int)(Math.random() * 900) + 100);
		sb.append(originName.substring(originName.lastIndexOf(".")));
		return sb.toString();
	}
	
	public String findMainFile(Long refBno) {
		List<FileDto> files = fileMapper.findByBno(refBno);
		if(files.isEmpty()) {
			return null;
		}
		return files.get(0).getFileUrl();
	}
	
	public void deleteFiles(Long refBno) {
		fileMapper.deleteFiles(refBno);
	}
	
	public List<FileDto> findByBno(Long refBno) {
		return fileMapper.findByBno(refBno);
	}
	
	public void saveFiles(List<MultipartFile> files, Long boardNo) {
		if(files.get(0).getOriginalFilename() != null || files.size() > 0) {
			for(int i = 0; i < files.size(); i++) {
				String url = store(files.get(i));
				FileDto file = FileDto.builder()
									  .refBno(boardNo)
									  .fileUrl(url)
									  .fileOrder(i+1)
									  .build();
				saveFile(file);
			}
		}		
	}
	private void saveFile(FileDto file) {
		fileMapper.saveFile(file);
	}

}
