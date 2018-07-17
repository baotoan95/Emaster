package com.emaster.common.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageDto<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int currentPage;
	private int pageSize;
	private int totalPage;
	private List<T> content;
	
	public PageDto<T> build(Page<T> page) {
		if(page != null) {
			PageDto<T> pageDto = new PageDto<>();
			pageDto.setCurrentPage(page.getNumber());
			pageDto.setPageSize(page.getSize());
			pageDto.setTotalPage(page.getTotalPages());
			pageDto.setContent(page.getContent());
			return pageDto;
		}
		return null;
	}

}
