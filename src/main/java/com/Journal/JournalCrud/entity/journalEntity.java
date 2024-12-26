package com.Journal.JournalCrud.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
@Document(collection = "journal_entries")
//@Getter
//@Setter
@Data
public class journalEntity {
    @Id
    private String id;
    private String title;
    private String content;
    private LocalDateTime date;

}
