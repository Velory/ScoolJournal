CREATE TABLE IF NOT EXISTS`schedule` (
  `weekDay` varchar(20) DEFAULT NULL,
  `scoolClass` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `lessonTime` varchar(20) DEFAULT NULL,
  `nameOfKurs` int(11) DEFAULT NULL,
  `teacherOfLesson` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `scoolClass_id_schedule_idx` (`scoolClass`),
  KEY `nameOfKurs_id_schedule_idx` (`nameOfKurs`),
  KEY `teacherOfLesson_id_schedule_idx` (`teacherOfLesson`),
  CONSTRAINT `nameOfKurs_id_schedule` FOREIGN KEY (`nameOfKurs`) REFERENCES `kurs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `scoolClass_id_schedule` FOREIGN KEY (`scoolClass`) REFERENCES `class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `teacherOfLesson_id_schedule` FOREIGN KEY (`teacherOfLesson`) REFERENCES `teachers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
