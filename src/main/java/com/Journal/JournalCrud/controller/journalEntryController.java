package com.Journal.JournalCrud.controller;


import com.Journal.JournalCrud.entity.journalEntity;
import com.Journal.JournalCrud.service.journalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class journalEntryController {

    @Autowired
    private journalEntryService journalservice;
    @PostMapping
    public ResponseEntity<journalEntity> createEntry(@RequestBody journalEntity journalentity){
        try {
            journalentity.setDate(LocalDateTime.now());
            journalservice.saveEntity(journalentity);
            return new ResponseEntity<>(journalentity, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    };
    @GetMapping
    public ResponseEntity<?> getEntry(){
        List<journalEntity> all=journalservice.getAllEntity();
        if (all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("id/{myId}")
    public ResponseEntity<journalEntity> getJournalById(@PathVariable String myId){
        Optional<journalEntity> journalentity= Optional.ofNullable(journalservice.getEntityById(myId));
        if (journalentity.isPresent()) {
            return new ResponseEntity<>(journalentity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("id/{myId}")
    public ResponseEntity<?> update(@PathVariable String myId,@RequestBody journalEntity newEntity){
        journalEntity old=journalservice.getEntityById(myId);
        if (old!=null){
            old.setContent(newEntity.getContent()!=null && !newEntity.getContent().equals("")?newEntity.getContent():old.getContent());
            old.setTitle(newEntity.getTitle()!=null&& !newEntity.getTitle().equals("")?newEntity.getTitle():old.getTitle());
            journalservice.updateEntity(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournal(@PathVariable String myId){
        journalservice.deleteEntity(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
