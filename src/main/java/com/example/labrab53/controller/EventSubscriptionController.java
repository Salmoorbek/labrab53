package com.example.labrab53.controller;

import com.example.labrab53.entity.EventSubscription;
import com.example.labrab53.service.EventSubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class EventSubscriptionController {
    private final EventSubscriptionService eventSubscriptionService;

    public EventSubscriptionController(EventSubscriptionService eventSubscriptionService) {
        this.eventSubscriptionService = eventSubscriptionService;
    }

    @GetMapping("/login/{eventId}/{email}")
    public String subscriptionToEvent(@PathVariable int eventId, @PathVariable String email) {
        return eventSubscriptionService.addEventSubscriptionDto(eventId, email);
    }

    @GetMapping("/mySubscriptions/{email}")
    public List<EventSubscription> getEmail(@PathVariable String email) {
        return eventSubscriptionService.getMySubscriptions(email);
    }

    @DeleteMapping("/delete/{eventId}/{email}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable int eventId, @PathVariable String email) {
        if (eventSubscriptionService.deleteSubsEvent(eventId, email))
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }
}
