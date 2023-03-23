package com.example.labrab53.service;

import com.example.labrab53.dao.EventDao;
import com.example.labrab53.dto.EventDto;
import com.example.labrab53.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventDao eventDao;

    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public List<EventDto> getEvents() {
        var movieList = eventDao.getAllEvents();
        return movieList.stream().map(EventDto::from).collect(Collectors.toList());
    }
    public EventDto findOne(int eventId) {
        var event = eventDao.getEventById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Такой ID не существует: " + eventId));
        return EventDto.from(event);
    }

    public boolean deleteEvent(int id){
        eventDao.deleteEvent(id);
        return true;
    }
}
