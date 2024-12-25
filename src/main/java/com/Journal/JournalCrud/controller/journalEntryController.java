package com.Journal.JournalCrud.controller;

import com.Journal.JournalCrud.entity.journalEntity;
import com.Journal.JournalCrud.service.journalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String createEntry(@RequestBody journalEntity journalentity){
        journalentity.setDate(LocalDateTime.now());
        journalservice.saveEntity(journalentity);
        return "data save successfully..";
    };
    @GetMapping
    public List<journalEntity> getEntry(){
        return journalservice.getAllEntity();
    }
    @GetMapping("id/{myId}")
    public journalEntity getJournalById(@PathVariable String myId){
        return journalservice.getEntityById(myId);
    }
    @PutMapping("id/{myId}")
    public journalEntity update(@PathVariable String myId,@RequestBody journalEntity newEntity){
        journalEntity old=journalservice.getEntityById(myId);
        if (old!=null){
            old.setContent(newEntity.getContent()!=null && !newEntity.getContent().equals("")?newEntity.getContent():old.getContent());
            old.setTitle(newEntity.getTitle()!=null&& !newEntity.getTitle().equals("")?newEntity.getTitle():old.getTitle());
        }
        return journalservice.updateEntity(old);
    }
    @DeleteMapping("id/{myId}")
    public String deleteJournal(@PathVariable String myId){
        journalservice.deleteEntity(myId);
        return "deleted succesfully.";
    }
}
