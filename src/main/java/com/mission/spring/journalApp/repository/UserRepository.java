package com.mission.spring.journalApp.repository;

import com.mission.spring.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);

    User deleteByUserName(String userName);
}
