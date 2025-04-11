package com.mission.spring.journalApp.repository;

import com.mission.spring.journalApp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntity, ObjectId> {


}
