package com.mission.spring.journalApp.service;

import com.mission.spring.journalApp.entity.JournalEntry;
import com.mission.spring.journalApp.entity.User;
import com.mission.spring.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveJournalEntry(JournalEntry entity, String userName){
        try {
            User user = userService.getUserByName(userName);
            entity.setUpdatedAt(LocalDateTime.now());
            JournalEntry journalEntry = journalEntryRepository.save(entity);
            user.getJournalEntries().add(journalEntry);
            userService.saveUser(user);
        } catch (Exception e){
            throw new RuntimeException("An Error occurred while adding entry", e);
        }
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.getUserByName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);
    }
}
