package oriscontrolwork.repository;

import lombok.AllArgsConstructor;
import oriscontrolwork.model.Schedule;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
public class ScheduleRepository {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Schedule> mapper;
    private final static String GET_ALL_PAGEABLE = "select * from schedule offset ? limit ?";
    private final static String COUNT = "select count(*) as all from schedule";
    public List<Schedule> findAll(int offset, int limit) {
        return jdbcTemplate.query(GET_ALL_PAGEABLE, mapper, offset, limit);
    }
    public Integer countResults() {
        DataSource dataSource = jdbcTemplate.getDataSource();
        ResultSet resultSet = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(COUNT);) {
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getInt("all");
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
