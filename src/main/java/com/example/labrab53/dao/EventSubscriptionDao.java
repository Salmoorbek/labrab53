package com.example.labrab53.dao;

import com.example.labrab53.dto.EventSubscriptionDto;
import com.example.labrab53.entity.EventSubscription;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Component
public class EventSubscriptionDao extends BaseDao{
    public EventSubscriptionDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE if not exists subscriptions (" +
                "id SERIAL PRIMARY KEY, " +
                "eventId SERIAL REFERENCES events(id), " +
                "email VARCHAR," +
                "registrationDateTime TIMESTAMP)");
    }
    public void saveAll(List<EventSubscription> eventSubscriptions) {
        String sql = "insert into subscriptions(eventId, email, registrationDateTime) " +
                "values(?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, eventSubscriptions.get(i).getEventId());
                ps.setString(2, eventSubscriptions.get(i).getEmail());
                ps.setTimestamp(3, Timestamp.valueOf(eventSubscriptions.get(i).getRegistrationDateTime()));
            }

            @Override
            public int getBatchSize() {
                return eventSubscriptions.size();
            }
        });
    }
    public void AlertSequenceEventSubscribe(){
        String sqlAlterSequence = "alter sequence events_id_seq restart with 1";
        jdbcTemplate.update(sqlAlterSequence);
    }
    public void deleteAll() {
        String sql = "delete from subscriptions";
        jdbcTemplate.update(sql);
    }
    public void save(EventSubscription eventSubscription) {
        String sql = "insert into subscriptions (eventId, email, registrationDateTime) " +
                "values(?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, eventSubscription.getEventId());
            ps.setString(2, eventSubscription.getEmail());
            ps.setTimestamp(3, Timestamp.valueOf(eventSubscription.getRegistrationDateTime()));
            return ps;
        });
    }
    public List<EventSubscription> getAllSubscriptionsEvents() {
        String sql = "SELECT * FROM subscriptions";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EventSubscription.class));
    }
    public List<EventSubscriptionDto> getAllMyEvents(String email) {
        String sql = "SELECT * FROM subscriptions where email = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EventSubscriptionDto.class), email);
    }

    public void deleteSubscriptionEvent(int id, String email){
        String sql = "DELETE FROM subscriptions where id = ? and email = ?";
        jdbcTemplate.update(sql, id, email);
    }
}
