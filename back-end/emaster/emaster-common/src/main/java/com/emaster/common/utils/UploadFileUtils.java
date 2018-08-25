package com.emaster.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadFileUtils {
	private UploadFileUtils() {

	}

	public static String upload(MultipartFile multipartFile, String destinationPath) {
		if (Objects.nonNull(multipartFile) && multipartFile.getSize() > 0) {
			// Create folder if not existed
			log.info("Uploading {} to {}", multipartFile.getOriginalFilename(), destinationPath);
			
			File folderContainer = new File(destinationPath);
			if(!folderContainer.exists()) {
				log.info("Create folder container {}", folderContainer.mkdirs());
			}
			
			// Create file
			destinationPath = destinationPath + File.separator + multipartFile.getOriginalFilename();
			File serverFile = new File(destinationPath);
			try {
				if(!serverFile.exists()) {
					if(serverFile.createNewFile()) {
						return copyFileToServer(multipartFile, serverFile);
					}
				}
				return copyFileToServer(multipartFile, serverFile);
			} catch (IOException e) {
				log.error("Error {}", e.toString());
			}
		}
		log.error("Fail to upload file");
		return "";
	}
	
	private static String copyFileToServer(MultipartFile multipartFile, File serverFile) throws IOException {
		FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(serverFile));
		log.info("Uploaded to {}", serverFile.getPath());
		return serverFile.getAbsolutePath();
	}
}
