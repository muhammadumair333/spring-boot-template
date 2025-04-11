package com.mission.spring.journalApp.controller;

import com.mission.spring.journalApp.entity.JournalEntity;
import com.mission.spring.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping
    public JournalEntity setData(@RequestBody JournalEntity singleEntry) {
        singleEntry.setUpdatedAt(LocalDateTime.now());
        journalEntryService.saveJournalEntry(singleEntry);
        return singleEntry;
    }

    @GetMapping
    public List<JournalEntity> getAll() {
        return journalEntryService.getAll();
    }

    @GetMapping("/id/{myId}")
    public JournalEntity getById(@PathVariable ObjectId myId) {
        return journalEntryService.getById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteById(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
        return true;
    }

    @PutMapping("/id/{myId}")
    public JournalEntity updateById(@PathVariable ObjectId myId, @RequestBody JournalEntity updatedEntry) {
        JournalEntity oldEntry = journalEntryService.getById(myId).orElse(null);
        if (oldEntry != null) {
            oldEntry.setTitle(updatedEntry.getTitle() != null && !updatedEntry.getTitle().isEmpty()
                    ? updatedEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(updatedEntry.getContent() != null && !updatedEntry.getContent().isEmpty()
                    ? updatedEntry.getContent() : oldEntry.getContent());
        }
        journalEntryService.saveJournalEntry(oldEntry);
        return oldEntry;
    }
}
