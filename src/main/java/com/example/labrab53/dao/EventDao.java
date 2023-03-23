package com.example.labrab53.dao;

import com.example.labrab53.entity.Event;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Component
public class EventDao extends BaseDao{
    public EventDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("CREATE TABLE if not exists events (" +
                        "id SERIAL PRIMARY KEY, " +
                        "dateTime TIMESTAMP, " +
                        "name VARCHAR," +
                        "description TEXT)");
    }
    public void saveAll(List<Event> events) {
        String sql = "insert into events(dateTime, name, description) " +
                "values(?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setTimestamp(1, Timestamp.valueOf(events.get(i).getDateTime()));
                ps.setString(2, events.get(i).getName());
                ps.setString(3, events.get(i).getDescription());
            }

            @Override
            public int getBatchSize() {
                return events.size();
            }
        });
    }
    public void alterSequenceEvent(){
        String sqlAlterSequence = "alter sequence subscriptions_id_seq restart with 1";
        jdbcTemplate.update(sqlAlterSequence);
    }
    public void deleteAll() {
        String sql = "delete from events";
        jdbcTemplate.update(sql);
    }

    public List<Event> getAllEvents() {
        String sql = "SELECT * FROM events";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Event.class));
    }

    public Optional<Event> getEventById(int movieId) {
        String sql = "select * " +
                "from events " +
                "where id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Event.class), movieId)
        ));
    }

    public List<Event> getFutureEvents() {
        String sql = "SELECT * FROM events WHERE datetime > now()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Event.class));
    }

    public void deleteEvent(int id) {
        String sql = "DELETE FROM events WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
