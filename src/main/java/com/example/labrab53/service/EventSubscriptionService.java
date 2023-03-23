package com.example.labrab53.service;

import com.example.labrab53.dao.EventDao;
import com.example.labrab53.dao.EventSubscriptionDao;
import com.example.labrab53.dto.EventSubscriptionDto;
import com.example.labrab53.entity.EventSubscription;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class EventSubscriptionService {
    private final EventSubscriptionDao eventSubscriptionDao;
    private final EventDao eventDao;

    public EventSubscriptionService(EventSubscriptionDao eventSubscriptionDao, EventDao eventDao) {
        this.eventSubscriptionDao = eventSubscriptionDao;
        this.eventDao = eventDao;
    }

    public String addEventSubscriptionDto(int eventId, String email) {
        var eventSubs = EventSubscription.builder()
                .id(eventSubscriptionDao.getAllSubscriptionsEvents().size()+1)
                .eventId(eventId)
                .email(email)
                .registrationDateTime(LocalDateTime.now())
                .build();
        String message = "";
        for (int i = 0; i < eventSubscriptionDao.getAllSubscriptionsEvents().size(); i++) {
            if (Objects.equals(eventSubs.getEventId(), eventSubscriptionDao.getAllSubscriptionsEvents().get(i).getEventId()) &&
                    Objects.equals(eventSubs.getEmail(), eventSubscriptionDao.getAllSubscriptionsEvents().get(i).getEmail())) {
                message = "были зарегистрированы";
                break;
            }
        }
        if(!message.contains("были зарегистрированы")) {
            for (int j = 0; j < eventDao.getFutureEvents().size(); j++) {
                if (Objects.equals(eventSubs.getEventId(), eventDao.getFutureEvents().get(j).getId())) {
                    eventSubscriptionDao.save(eventSubs);
                    message = eventSubs.getId() + " вы зарегистрировались";
                    break;
                }
            }
        }
        if(message.isEmpty()){
            message = "ввели некорректные данные";
        }
        return message;
    }
    public List<EventSubscriptionDto> getMySubscriptions(String email){
        return eventSubscriptionDao.getAllMyEvents(email);
    }

    public boolean deleteSubsEvent(int id, String email){
        eventSubscriptionDao.deleteSubscriptionEvent(id, email);
        return true;
    }
}
