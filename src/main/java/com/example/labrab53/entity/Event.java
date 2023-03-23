package com.example.labrab53.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Integer id;
    private LocalDateTime dateTime;
    private String name;
    private String description;
}
