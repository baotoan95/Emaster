package com.emaster.dataquery.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emaster.dataquery.entities.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

}
