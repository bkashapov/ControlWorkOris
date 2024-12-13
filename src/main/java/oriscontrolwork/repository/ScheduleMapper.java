package oriscontrolwork.repository;


import oriscontrolwork.model.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleMapper implements RowMapper<Schedule> {

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Schedule.builder()
                .id(rs.getLong("id"))
                .auditory_num(rs.getLong("auditory_num"))
                .time(rs.getString("vremya"))
                .weekday(rs.getString("weekday"))
                .teacher_last_name(rs.getString("teacher_last_name"))
                .group(rs.getString("gruppa"))
                .build();
    }
}
