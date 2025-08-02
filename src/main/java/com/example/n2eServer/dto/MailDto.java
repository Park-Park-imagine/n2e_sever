package com.example.n2eServer.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
    private List<String> to;
    @NonNull
    private String title;
    @NonNull
    private String content;
}
