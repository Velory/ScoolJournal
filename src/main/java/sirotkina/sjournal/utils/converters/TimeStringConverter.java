package sirotkina.sjournal.utils.converters;

import javafx.util.StringConverter;
import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
public class TimeStringConverter extends StringConverter<Time> {

    //private final String DATE_PATTERN = "dd MM uuuu";
    private final String TIME_PATTERN = "HH:mm:ss";

    private DateTimeFormatter dateTimeFormatter;
    private Locale locale = new Locale("uk","UA", "Ukrainian (Ukraine)");

    public TimeStringConverter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN, locale);
    }

    @Override
    public String toString(Time time) {
        String text = null;
        if (time!= null) {
            text = dateTimeFormatter.format(time.toLocalTime());
        }
        return text;
    }

    @Override
    public Time fromString(String text) {
        Time time = null;
        if (text != null && !text.trim().isEmpty()){
            time =  Time.valueOf(text);
        }
        return time;
    }

}
