package com.emaster.portal.service.impl;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emaster.common.constant.EmasterURL;
import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.StatementDto;
import com.emaster.common.exception.PortalException;
import com.emaster.common.utils.UploadFileUtils;
import com.emaster.portal.dal.StatementDAL;
import com.emaster.portal.service.StatementService;

@Service
public class StatementServiceImpl implements StatementService {
	private StatementDAL statementDAL;
	
	@Autowired
	public void setStatementDAL(StatementDAL statementDAL) {
		this.statementDAL = statementDAL;
	}

	@Override
	public StatementDto create(StatementDto statementDto,
			MultipartFile imageFile,
			MultipartFile normalSoundFile,
			MultipartFile slowSoundFile) throws PortalException {
		statementDto.setImage(UploadFileUtils.upload(imageFile, EmasterURL.UPLOAD_PATH + File.separator + "images"));
		statementDto.setSound(UploadFileUtils.upload(normalSoundFile, EmasterURL.UPLOAD_PATH + File.separator + "sounds"));
		statementDto.setSlowSound(UploadFileUtils.upload(slowSoundFile, EmasterURL.UPLOAD_PATH + File.separator + "sounds"));
		
		return statementDAL.create(statementDto);
	}

	@Override
	public PageDto<StatementDto> findAll(Optional<Integer> page, Optional<Integer> size) throws PortalException {
		return statementDAL.findAll(page, size);
	}

	@Override
	public StatementDto update(StatementDto statementDto,
			MultipartFile imageFile,
			MultipartFile normalSoundFile,
			MultipartFile slowSoundFile) throws PortalException {

		statementDto.setImage(UploadFileUtils.upload(imageFile, EmasterURL.UPLOAD_PATH + File.separator + "images"));
		statementDto.setSound(UploadFileUtils.upload(normalSoundFile, EmasterURL.UPLOAD_PATH + File.separator + "sounds"));
		statementDto.setSlowSound(UploadFileUtils.upload(slowSoundFile, EmasterURL.UPLOAD_PATH + File.separator + "sounds"));
		
		return statementDAL.update(statementDto);
	}

	@Override
	public StatementDto findOne(String id) throws PortalException {
		return statementDAL.findOne(id);
	}

	@Override
	public void delete(String id) throws PortalException {
		statementDAL.delete(id);
	}

}
