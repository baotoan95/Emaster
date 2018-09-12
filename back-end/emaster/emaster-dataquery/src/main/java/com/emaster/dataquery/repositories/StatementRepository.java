package com.emaster.dataquery.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.emaster.common.enums.StatementType;
import com.emaster.dataquery.entities.Statement;

public interface StatementRepository extends MongoRepository<Statement, String> {
	List<Statement> findByCreatedByEmailAndCategoryId(String email, String categoryId);
	Page<Statement> findAllByOrderByCreatedDateDesc(Pageable pageable);
	List<Statement> findAllBy(TextCriteria criteria);
	Page<Statement> findByCategoryId(String categoryId, Pageable pageable);
	Page<Statement> findByCategoryIdAndIdNotInAndTypeNot(String categoryId, List<String> excepted, StatementType type, Pageable pageable);
}
