CREATE TABLE `schedule` (
  `weekDay` varchar(20) DEFAULT NULL,
  `scoolClass` varchar(10) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `lessonTime` time DEFAULT NULL,
  `nameOfKurs` varchar(200) DEFAULT NULL,
  `teacherOfLesson` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
