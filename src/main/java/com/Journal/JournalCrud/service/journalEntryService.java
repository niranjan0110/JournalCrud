package com.Journal.JournalCrud.service;

import com.Journal.JournalCrud.dao.journalDao;
import com.Journal.JournalCrud.entity.journalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class journalEntryService {
    @Autowired
    private journalDao journaldao;
    public void saveEntity(journalEntity journalentity){
        journaldao.save(journalentity);
    }
    public List<journalEntity> getAllEntity(){
        return journaldao.findAll();
    };
    public journalEntity getEntityById(String id){
        return journaldao.findById(id).orElse(null);
    }
    public journalEntity updateEntity(journalEntity journalentity){
        return journaldao.save(journalentity);
    }
    public void deleteEntity(String myId){
        journaldao.deleteById(myId);
    }
}
