package com.mission.spring.journalApp.service;

import com.mission.spring.journalApp.entity.JournalEntity;
import com.mission.spring.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveJournalEntry(JournalEntity entity){
        journalEntryRepository.save(entity);
    }

    public List<JournalEntity> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }
}
