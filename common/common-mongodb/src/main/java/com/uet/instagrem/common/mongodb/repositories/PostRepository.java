package com.uet.instagrem.common.mongodb.repositories;

import com.uet.instagrem.common.mongodb.entities.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<PostEntity, String> {
}
