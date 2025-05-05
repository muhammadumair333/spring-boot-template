package com.mission.spring.journalApp.controller;

import com.mission.spring.journalApp.entity.JournalEntry;
import com.mission.spring.journalApp.entity.User;
import com.mission.spring.journalApp.service.JournalEntryService;
import com.mission.spring.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<JournalEntry> setData(@RequestBody JournalEntry singleEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveJournalEntry(singleEntry, userName);
            return new ResponseEntity<>(singleEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getJournalEntriesByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getUserByName(userName);
        List<JournalEntry> journalEntities = user.getJournalEntries();
        if (journalEntities != null && !journalEntities.isEmpty())
            return new ResponseEntity<>(journalEntities, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getUserByName(userName);
        List<JournalEntry> journalEntryList = user.getJournalEntries().stream().filter(
                x -> x.getId().equals(myId)).collect(Collectors.toList());
        if (!journalEntryList.isEmpty()) {
            Optional<JournalEntry> journalEntity = journalEntryService.getById(myId);
            if (journalEntity.isPresent()) {
                return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean removed = journalEntryService.deleteById(myId, userName);
        if (removed)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> updateById(@PathVariable ObjectId myId, @RequestBody JournalEntry updatedEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getUserByName(userName);
        List<JournalEntry> journalEntryList = user.getJournalEntries().stream().filter(
                x -> x.getId().equals(myId)).collect(Collectors.toList());
        if (!journalEntryList.isEmpty()) {
            Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);
            if (journalEntry.isPresent()) {
                JournalEntry oldEntry = journalEntry.get();
                oldEntry.setTitle(updatedEntry.getTitle() != null && !updatedEntry.getTitle().isEmpty()
                        ? updatedEntry.getTitle() : oldEntry.getTitle());
                oldEntry.setContent(updatedEntry.getContent() != null && !updatedEntry.getContent().isEmpty()
                        ? updatedEntry.getContent() : oldEntry.getContent());
                journalEntryService.saveJournalEntry(oldEntry, userName);
                return new ResponseEntity<>(oldEntry, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
