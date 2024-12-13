package oriscontrolwork.listener;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import oriscontrolwork.config.DbConfig;
import oriscontrolwork.repository.JdbcTemplate;
import oriscontrolwork.repository.ScheduleMapper;
import oriscontrolwork.repository.ScheduleRepository;
import oriscontrolwork.service.ScheduleService;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DataSource dataSource = new DbConfig(properties).hikariDataSource();

        ScheduleRepository scheduleRepository =
                new ScheduleRepository(new JdbcTemplate(dataSource), new ScheduleMapper());


        ScheduleService scheduleService = new ScheduleService(scheduleRepository);

        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("dataSource", dataSource);
        servletContext.setAttribute("scheduleService", scheduleService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        HikariDataSource dataSource = (HikariDataSource) servletContext.getAttribute("dataSource");
        dataSource.close();
    }
}
