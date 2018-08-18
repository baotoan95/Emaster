package com.emaster.portal.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("resources")
@Slf4j
public class ResourceController {
	
	@GetMapping("images")
	public void responseData(@RequestParam("url") String path, HttpServletResponse response) {
		FileInputStream inputStream = null;
		try {
			File file = new File(path);

			byte[] rs = new byte[(int) file.length()];
			inputStream = new FileInputStream(file);
			inputStream.read(rs);
			response.setContentType("image/" + FilenameUtils.getExtension(file.getName()));
			response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
			response.getOutputStream().write(rs);
			response.getOutputStream().close();
		} catch (IOException e) {
			log.error("Error: " + e.getMessage());
		} finally {
			try {
				if(Objects.nonNull(inputStream)) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
