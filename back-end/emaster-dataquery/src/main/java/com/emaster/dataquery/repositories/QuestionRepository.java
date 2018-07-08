package com.emaster.dataquery.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.emaster.common.entities.Question;

public interface QuestionRepository extends PagingAndSortingRepository<Question, ObjectId> {

}
