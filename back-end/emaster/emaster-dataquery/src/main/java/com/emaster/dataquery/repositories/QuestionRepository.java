package com.emaster.dataquery.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.emaster.dataquery.entities.Question;

public interface QuestionRepository extends MongoRepository<Question, ObjectId> {

}
