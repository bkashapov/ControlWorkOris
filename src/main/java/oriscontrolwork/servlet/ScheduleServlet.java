package oriscontrolwork.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import oriscontrolwork.model.Schedule;
import oriscontrolwork.service.ScheduleService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {
    private ScheduleService scheduleService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        scheduleService = (ScheduleService) context.getAttribute("scheduleService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        if (page == null || page.isEmpty()) {
            page = "1";
        }
        int countPages = scheduleService.countResults() / 5;
        List<Integer> cpList = new ArrayList<>();

        for (int i = 1; i <= countPages; ++i) {
            cpList.add(i);
        }
        List<Schedule> list = scheduleService.getAll((Integer.parseInt(page) - 1) * 5, 5);
        req.setAttribute("schedule", list);
        req.setAttribute("countPages", cpList);
        req.getRequestDispatcher("/jsp/schedule.jsp").forward(req, resp);

    }
}
