package oriscontrolwork.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@Builder
public class Schedule {
    Long id;
    Long auditory_num;
    String time;
    String weekday;
    String teacher_last_name;
    String group;
}
