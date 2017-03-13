CREATE TABLE `class` (
  `id` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `letter` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `kurs` (
  `id` int(11) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `teachers` (
  `id` int(11) NOT NULL,
  `firstName` varchar(20) DEFAULT NULL,
  `midName` varchar(20) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `kursId` int(11) NOT NULL,
  `classId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kurs_id_fk_idx` (`kursId`),
  KEY `class_id_fk_idx` (`classId`),
  CONSTRAINT `class_id_teachers` FOREIGN KEY (`classId`) REFERENCES `class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `kurs_id_teachers` FOREIGN KEY (`kursId`) REFERENCES `kurs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `firstName` varchar(20) DEFAULT NULL,
  `midName` varchar(20) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `classId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id_fk_idx` (`classId`),
  CONSTRAINT `class_id_students` FOREIGN KEY (`classId`) REFERENCES `class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `lesson` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `homeTask` varchar(100) DEFAULT NULL,
  `classId` int(11) NOT NULL,
  `teachersId` int(11) NOT NULL,
  `kursId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id_lesson_idx` (`classId`),
  KEY `kurs_id_lesson_idx` (`kursId`),
  KEY `teachers_id_lesson` (`teachersId`),
  CONSTRAINT `class_id_lesson` FOREIGN KEY (`classId`) REFERENCES `class` (`id`),
  CONSTRAINT `kurs_id_lesson` FOREIGN KEY (`kursId`) REFERENCES `kurs` (`id`),
  CONSTRAINT `teachers_id_lesson` FOREIGN KEY (`teachersId`) REFERENCES `teachers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `marks` (
  `mark` int(11) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `lessonId` int(11) NOT NULL,
  `studentsId` int(11) NOT NULL,
  KEY `lesson_id_marks_idx` (`lessonId`),
  KEY `students_id_marks_idx` (`studentsId`),
  CONSTRAINT `lesson_id_marks` FOREIGN KEY (`lessonId`) REFERENCES `lesson` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `students_id_marks` FOREIGN KEY (`studentsId`) REFERENCES `students` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

