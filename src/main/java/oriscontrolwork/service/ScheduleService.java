package oriscontrolwork.service;

import lombok.AllArgsConstructor;
import oriscontrolwork.model.Schedule;
import oriscontrolwork.repository.ScheduleRepository;


import java.util.List;

@AllArgsConstructor
public class ScheduleService {
    private ScheduleRepository scheduleRepository;
    public List<Schedule> getAll(int offset, int limit) {
        return scheduleRepository.findAll(offset, limit);
    }
    public Integer countResults() {
        return scheduleRepository.countResults();
    }
}
