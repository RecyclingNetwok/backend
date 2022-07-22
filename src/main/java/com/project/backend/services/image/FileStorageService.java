package com.project.backend.services.image;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
	@Autowired
	private FileDBRepository fileDBRepository;

	public FileDB store(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
		return fileDBRepository.save(FileDB);
	}

	public FileDB getFile(String id) {
		return fileDBRepository.findById(id).get();
	}

	public Stream<FileDB> getAllFiles() {
		return fileDBRepository.findAll().stream();
	}
	
	public ResponseEntity<?> deleteFile(String uuid){
		fileDBRepository.deleteById(uuid);
		return new ResponseEntity<String>("File With uuid : "+ uuid+" Deleted" , HttpStatus.OK);
	}
	
	public ResponseEntity<?> deleteFiles(){
		fileDBRepository.deleteAll();
		return new ResponseEntity<String>("All Files Deleted" , HttpStatus.OK);
	}
}
