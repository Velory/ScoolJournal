package sirotkina.sjournal.utils;

import javafx.util.converter.DefaultStringConverter;
import sirotkina.sjournal.utils.converters.*;

public class ConvertersUtils {
    private static ClassStringConverter classConverter;
    private static KursStringConverter kursConverter;
    private static TeacherStringConverter teacherConverter;
    private static TimeStringConverter timeConverter;

    static {
        classConverter = new ClassStringConverter();
        kursConverter = new KursStringConverter();
        teacherConverter = new TeacherStringConverter();
        timeConverter = new TimeStringConverter();
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

    public static TimeStringConverter timeConverter(){
        return timeConverter;
    }

    public static TimeFormatter timeFormatter(){
        return new TimeFormatter(timeConverter());
    }

}
