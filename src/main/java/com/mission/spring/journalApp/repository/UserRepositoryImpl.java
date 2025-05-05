package com.mission.spring.journalApp.repository;

import com.mission.spring.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> getUsersSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is("ali"));
        return mongoTemplate.find(query, User.class);
    }
}
