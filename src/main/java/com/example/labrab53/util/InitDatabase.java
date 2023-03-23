package com.example.labrab53.util;

import com.example.labrab53.dao.EventDao;
import com.example.labrab53.dao.EventSubscriptionDao;
import com.example.labrab53.entity.Event;
import com.example.labrab53.entity.EventSubscription;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class InitDatabase {

    private static final Random r = new Random();

    @Bean
    CommandLineRunner init(EventDao eventDao, EventSubscriptionDao eventSubscriptionDao){
        return (args) -> {
            eventDao.createTable();
            eventSubscriptionDao.createTable();

            eventDao.deleteAll();
            eventSubscriptionDao.deleteAll();

            eventDao.saveAll(saveEvents());
            eventSubscriptionDao.saveAll(saveSubscriptions());
        };
    }
    public List<Event> saveEvents(){
        List<Event> events = new ArrayList<>();
        events.add(new Event(1, LocalDateTime.of(LocalDate.of(2022,3,5), LocalTime.of(12,23)), "play football", "play play play"));
        events.add(new Event(2, LocalDateTime.of(LocalDate.of(2024,3,5), LocalTime.of(12,23)), "play basketball", "play play"));
        events.add(new Event(3, LocalDateTime.of(LocalDate.of(2024,5,12), LocalTime.of(22,23)), "play computer", "play"));
        return events;
    }
    public List<EventSubscription> saveSubscriptions(){
        List<EventSubscription> subscriptions = new ArrayList<>();
        subscriptions.add(new EventSubscription(1,2,"sasasa@mail.ru", LocalDateTime.of(LocalDate.of(2022,3,5), LocalTime.of(12,23))));
        subscriptions.add(new EventSubscription(2,1,"sasa@mail.ru", LocalDateTime.of(LocalDate.of(2023,3,5), LocalTime.of(12,23))));

        return subscriptions;
    }
}
