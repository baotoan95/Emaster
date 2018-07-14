package com.emaster.dataquery.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emaster.dataquery.entities.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
