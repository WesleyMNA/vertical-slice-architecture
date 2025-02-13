package com.example.vertical_slice_architecture.task.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tsk_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tsk_id", updatable = false, insertable = false)
    private UUID id;

    @Column(name = "usr_id", nullable = false, updatable = false)
    private UUID userId;

    @Column(name = "tsk_title", nullable = false, length = 100)
    private String title;

    @Column(name = "tsk_description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "tsk_day", nullable = false)
    private LocalDate day;

    @Column(name = "tsk_hour")
    private LocalTime hour;

    public Task(UUID userId, String title, String description, LocalDate day, LocalTime hour) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.day = day;
        this.hour = hour;
    }
}
