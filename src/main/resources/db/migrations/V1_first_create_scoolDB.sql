CREATE DATABASE `scooldb` /*!40100 DEFAULT CHARACTER SET utf8 */;
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
  `kurs_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kurs_id_fk_idx` (`kurs_id`),
  KEY `class_id_fk_idx` (`class_id`),
  CONSTRAINT `class_id_teachers` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `kurs_id_teachers` FOREIGN KEY (`kurs_id`) REFERENCES `kurs` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `students` (
  `id` int(11) NOT NULL,
  `firstName` varchar(20) DEFAULT NULL,
  `midName` varchar(20) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `class_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id_fk_idx` (`class_id`),
  CONSTRAINT `class_id_students` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `lesson` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `homeTask` varchar(100) DEFAULT NULL,
  `class_id` int(11) NOT NULL,
  `teachers_id` int(11) NOT NULL,
  `kurs_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id_lesson_idx` (`class_id`),
  KEY `kurs_id_lesson_idx` (`kurs_id`),
  KEY `teachers_id_lesson` (`teachers_id`),
  CONSTRAINT `class_id_lesson` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `kurs_id_lesson` FOREIGN KEY (`kurs_id`) REFERENCES `kurs` (`id`),
  CONSTRAINT `teachers_id_lesson` FOREIGN KEY (`teachers_id`) REFERENCES `teachers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `marks` (
  `mark` int(11) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `lesson_id` int(11) NOT NULL,
  `students_id` int(11) NOT NULL,
  KEY `lesson_id_marks_idx` (`lesson_id`),
  KEY `students_id_marks_idx` (`students_id`),
  CONSTRAINT `lesson_id_marks` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `students_id_marks` FOREIGN KEY (`students_id`) REFERENCES `students` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
