create table schedule (
                          id serial primary key,
                          auditory_num int,
                          vremya varchar(10) check (vremya like '__:__'),
                          weekday varchar(10),
                          teacher_last_name varchar(50),
                          gruppa varchar(6)
);
INSERT INTO schedule (auditory_num, vremya, weekday, teacher_last_name, gruppa) VALUES
                                                                                    (101, '09:00', 'Monday', 'Ivanov', '11-301'),
                                                                                    (202, '11:30', 'Tuesday', 'Petrov', '11-302'),
                                                                                    (303, '13:45', 'Wednesday', 'Sidorov', '11-303'),
                                                                                    (404, '15:00', 'Thursday', 'Kuznetsova', 'D401'),
                                                                                    (105, '10:15', 'Friday', 'Smirnov', 'A102'),
                                                                                    (201, '12:00', 'Monday', 'Volkova', 'B202'),
                                                                                    (305, '14:30', 'Tuesday', 'Morozov', 'C303'),
                                                                                    (402, '16:45', 'Wednesday', 'Romanova', 'D402'),
                                                                                    (110, '08:30', 'Thursday', 'Belova', 'A103'),
