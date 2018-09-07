package com.emaster.dataquery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.Statement;

public interface StatementService {
	Statement create(Statement statement) throws DataQueryException;

	Page<Statement> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException;

	Statement update(Statement statement) throws DataQueryException;

	Statement findOne(String id);

	void delete(String id);

	List<Statement> searchByContent(String content);
	
	List<Statement> findTopByCategory(int limit, String categoryId);
	
	Page<Statement> findByCategory(String categoryId, Optional<Integer> page, Optional<Integer> size) throws DataQueryException;
}
