package com.example.n2eServer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;


@Entity(name = "email")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "`from`")
    private String from;
    @Column(name = "`to`")
    private String to;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    public EmailEntity(String from, String to, @NonNull String title, @NonNull String content) {
        this.from = from;
        this.to = to;
        this.title = title;
        this.content = content;
        this.sentAt = LocalDateTime.now();
    }
}
