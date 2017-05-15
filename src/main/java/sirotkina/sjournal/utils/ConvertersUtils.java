package sirotkina.sjournal.utils;

import sirotkina.sjournal.utils.converters.*;

public class ConvertersUtils {
    private static ClassStringConverter classConverter;
    private static KursStringConverter kursConverter;
    private static TeacherStringConverter teacherConverter;
    private static StudentStringConverter  studentConverter;

    static {
        classConverter = new ClassStringConverter();
        kursConverter = new KursStringConverter();
        teacherConverter = new TeacherStringConverter();
        studentConverter = new StudentStringConverter();
    }

    public static ClassStringConverter classConverter() {
        return classConverter;
    }

    public static KursStringConverter kursConverter() {
        return kursConverter;
    }

    public static TeacherStringConverter teacherConverter() {
        return teacherConverter;
    }

    public static StudentStringConverter studentConverter() {
        return studentConverter;
    }
}
