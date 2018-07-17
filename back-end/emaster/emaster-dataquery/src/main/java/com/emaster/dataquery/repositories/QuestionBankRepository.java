package com.emaster.dataquery.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emaster.dataquery.entities.QuestionBank;

public interface QuestionBankRepository extends MongoRepository<QuestionBank, String> {

}
