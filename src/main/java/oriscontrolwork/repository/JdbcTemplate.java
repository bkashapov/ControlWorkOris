package oriscontrolwork.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class JdbcTemplate {
    private DataSource dataSource;

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object ... objects) {
        ResultSet resultSet = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            if (objects != null) {
                for (int i = 0; i < objects.length; i++) {
                    preparedStatement.setObject(i + 1, objects[i]);
                }
            }
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();

            List<T> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(rowMapper.mapRow(resultSet, 0));
            }
            return list;
        } catch (SQLException e) {
            return null;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
