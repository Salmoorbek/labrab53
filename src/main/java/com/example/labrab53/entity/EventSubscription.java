package com.example.labrab53.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventSubscription {
    private int id;
    private Integer eventId;
    private String email;
    private LocalDateTime registrationDateTime;
}
