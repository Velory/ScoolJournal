package sirotkina.sjournal.utils.converters;

import javafx.util.StringConverter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Locale;

public class DateStringConverter extends StringConverter<Date> {

    private final String DATE_PATTERN = "dd/MM/yyyy";
    private final Locale locale = new Locale("uk","UA", "Ukrainian (Ukraine)");

    private DateTimeFormatter dateTimeFormatter;

    public DateStringConverter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    }

    @Override
    public String toString(Date date) {
        String text = null;
        if (date != null){
            text = dateTimeFormatter.format(date.toLocalDate());
        }
        return text;
    }

    @Override
    public Date fromString(String text) {
        Date date = null;
        if (text != null && !text.trim().isEmpty()){
            date = Date.valueOf(LocalDate.parse(text, dateTimeFormatter));
        }
        return date;
    }

    public String toDayOfWeek(Date date){
        LocalDate localDate = LocalDate.from(dateTimeFormatter.parse(toString(date)));
        return localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, locale);
    }

    public Date fromDayOfWeek(String day){
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < 7; i++){
            if (localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, locale).trim().equals(day)){

            }
        }
        return null;
    }

}
