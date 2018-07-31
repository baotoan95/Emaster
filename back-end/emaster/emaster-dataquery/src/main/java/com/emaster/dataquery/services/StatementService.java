package com.emaster.dataquery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.emaster.common.exception.DataQueryException;
import com.emaster.dataquery.entities.Statement;

public interface StatementService {
	Statement create(Statement Statement) throws DataQueryException;

	Page<Statement> findAll(Optional<Integer> page, Optional<Integer> size) throws DataQueryException;

	Statement update(Statement Statement) throws DataQueryException;

	Statement findOne(String id);

	void delete(String id);

	List<Statement> getStatementsForASession(String email, String categoryId);
}
