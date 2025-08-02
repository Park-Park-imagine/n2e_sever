package com.example.n2eServer.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
    private String[] to;
    @NonNull
    private String title;
    @NonNull
    private String content;
}
