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

	public static String upload(MultipartFile multipartFile, String destinationPath) throws IOException {
		if (Objects.nonNull(multipartFile) && multipartFile.getSize() > 0) {
			// Create folder if not existed
			log.info("Uploading to {}", destinationPath);
			
			File folderContainer = new File(destinationPath);
			if(!folderContainer.exists()) {
				log.info("Create folder container {}", folderContainer.mkdirs());
			}
			
			// Create file
			destinationPath = destinationPath + File.separator + multipartFile.getOriginalFilename();
			File serverFile = new File(destinationPath);
			if(serverFile.createNewFile()) {
				FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(serverFile));
				log.info("Uploaded to {}", serverFile.getPath());
				return serverFile.getAbsolutePath();
			}
		}
		log.error("Fail to upload file");
		return "";
	}
}
