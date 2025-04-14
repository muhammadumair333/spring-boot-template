package com.mission.spring.journalApp.controller;

import com.mission.spring.journalApp.entity.JournalEntry;
import com.mission.spring.journalApp.entity.User;
import com.mission.spring.journalApp.service.JournalEntryService;
import com.mission.spring.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> setData(@RequestBody JournalEntry singleEntry, @PathVariable String userName) {
        try {
            journalEntryService.saveJournalEntry(singleEntry, userName);
            return new ResponseEntity<>(singleEntry, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getJournalEntriesByUser(@PathVariable String userName) {
        User user = userService.getUserByName(userName);
        List<JournalEntry> journalEntities = user.getJournalEntries();
        if(journalEntities != null && !journalEntities.isEmpty())
            return new ResponseEntity<>(journalEntities, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntity =  journalEntryService.getById(myId);
        if(journalEntity.isPresent()){
            return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}/{userName}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId, @PathVariable String userName) {
        journalEntryService.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable ObjectId myId, @RequestBody JournalEntry updatedEntry) {
        JournalEntry oldEntry = journalEntryService.getById(myId).orElse(null);
        if (oldEntry != null) {
            oldEntry.setTitle(updatedEntry.getTitle() != null && !updatedEntry.getTitle().isEmpty()
                    ? updatedEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(updatedEntry.getContent() != null && !updatedEntry.getContent().isEmpty()
                    ? updatedEntry.getContent() : oldEntry.getContent());
           // journalEntryService.saveJournalEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
