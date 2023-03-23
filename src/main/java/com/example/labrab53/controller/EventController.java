package com.example.labrab53.controller;

import com.example.labrab53.dto.EventDto;
import com.example.labrab53.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDto> findAllEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/{eventId}")
    public EventDto findAllEvents(@PathVariable int eventId) {
        return eventService.findOne(eventId);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int eventId) {
        if (eventService.deleteEvent(eventId))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
