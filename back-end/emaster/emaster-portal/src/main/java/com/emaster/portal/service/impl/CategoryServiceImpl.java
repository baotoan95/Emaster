package com.emaster.portal.service.impl;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emaster.common.constant.EmasterURL;
import com.emaster.common.dto.CategoryDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.PortalException;
import com.emaster.common.utils.UploadFileUtils;
import com.emaster.portal.dal.CategoryDAL;
import com.emaster.portal.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDAL categoryDAL;
	
	@Override
	public PageDto<CategoryDto> findAll(Optional<Integer> page, Optional<Integer> size) throws PortalException {
		return categoryDAL.findAll(page, size);
	}

	@Override
	public CategoryDto create(CategoryDto categoryDto) throws PortalException {
		categoryDto.setIcon(UploadFileUtils.upload(categoryDto.getIconFile(), EmasterURL.UPLOAD_PATH + File.separator + "images"));
		return categoryDAL.create(categoryDto);
	}

	@Override
	public CategoryDto update(CategoryDto categoryDto) throws PortalException {
		return categoryDAL.update(categoryDto);
	}

	@Override
	public CategoryDto findOne(String id) throws PortalException {
		return categoryDAL.findOne(id);
	}

	@Override
	public void delete(String id) throws PortalException {
		categoryDAL.delete(id);
	}

}
