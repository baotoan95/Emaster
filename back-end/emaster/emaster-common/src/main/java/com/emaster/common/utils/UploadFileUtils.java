package com.emaster.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UploadFileUtils {
	public static String upload(MultipartFile multipartFile, String destinationPath) throws FileNotFoundException, IOException {
		if(Objects.nonNull(multipartFile) && multipartFile.getSize() > 0) {
			destinationPath = destinationPath + File.pathSeparator + multipartFile.getOriginalFilename();
			log.info("Uploading to {}", destinationPath);
			File serverFile = new File(destinationPath);
			FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(serverFile));
			return serverFile.getPath();
		}
		return "";
	}
}
