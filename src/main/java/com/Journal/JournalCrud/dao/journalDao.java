package com.Journal.JournalCrud.dao;

import com.Journal.JournalCrud.entity.journalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;


public interface journalDao extends MongoRepository<journalEntity,String> {
}
