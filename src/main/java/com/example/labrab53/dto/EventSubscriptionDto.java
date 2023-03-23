package com.example.labrab53.dto;

import com.example.labrab53.entity.EventSubscription;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class EventSubscriptionDto {
    private int id;
    private Integer eventId;
    private String email;
    private LocalDateTime registrationDateTime;
    public static EventSubscriptionDto from(EventSubscription eventSubscription){
        return builder()
                .id(eventSubscription.getId())
                .eventId(eventSubscription.getEventId())
                .email(eventSubscription.getEmail())
                .registrationDateTime(LocalDateTime.now())
                .build();
    }
}
