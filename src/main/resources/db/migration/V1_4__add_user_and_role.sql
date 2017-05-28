CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `firstName` varchar(20) DEFAULT NULL,
  `midName` varchar(20) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `classId` int(11) DEFAULT NULL,
  `kursId` int(11) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `class_id_users_idx` (`classId`),
  KEY `kurs_id_users_idx` (`kursId`),
  KEY `role_id_users_idx` (`roleId`),
  CONSTRAINT `class_id_users` FOREIGN KEY (`classId`) REFERENCES `class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `kurs_id_users` FOREIGN KEY (`kursId`) REFERENCES `kurs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `role_id_users` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE `lesson` DROP FOREIGN KEY `teachers_id_lesson`;
ALTER TABLE `lesson` DROP INDEX `teachers_id_lesson` ,
ADD INDEX `teachers_id_lesson_idx` (`teachersId` ASC);
ALTER TABLE `lesson`
ADD CONSTRAINT `teachers_id_lesson`
  FOREIGN KEY (`teachersId`)
  REFERENCES `users` (`id`);
ALTER TABLE `marks` DROP FOREIGN KEY `students_id_marks`;
ALTER TABLE `marks`
ADD INDEX `students_id_marks_idx` (`studentsId` ASC),
DROP INDEX `students_id_marks_idx` ;
ALTER TABLE `marks`
ADD CONSTRAINT `students_id_marks`
  FOREIGN KEY (`studentsId`)
  REFERENCES `users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
ALTER TABLE `schedule`
DROP FOREIGN KEY `teacherOfLesson_id_schedule`;
ALTER TABLE `schedule`
ADD INDEX `teacherOfLesson_id_schedule_idx` (`teacherOfLesson` ASC),
DROP INDEX `teacherOfLesson_id_schedule_idx` ;
ALTER TABLE `schedule`
ADD CONSTRAINT `teacherOfLesson_id_schedule`
  FOREIGN KEY (`teacherOfLesson`)
  REFERENCES `users` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
drop table students;
drop table teachers;