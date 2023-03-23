package com.example.labrab53.dto;

import com.example.labrab53.entity.Event;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class EventDto {
    public static EventDto from(Event event){
        return builder()
                .id(event.getId())
                .dateTime(event.getDateTime())
                .name(event.getName())
                .description(event.getDescription())
                .build();
    }
    private Integer id;
    private LocalDateTime dateTime;
    private String name;
    private String description;
}
