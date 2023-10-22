package com.mateusferro.todolist.task;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private UUID idUser;
    private String description;
    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    @CreationTimestamp
    private LocalDateTime createAt;

    public void setTitle(String title) throws Exception{
        if(title.length() > 50){
            throw new Exception("Title must ma have max 50 characteres");
        }
            this.title = title;
    }
}
