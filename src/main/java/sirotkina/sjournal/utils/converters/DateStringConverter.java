package sirotkina.sjournal.utils.converters;

import javafx.util.StringConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateStringConverter extends StringConverter<LocalDate> {

    private final String DATE_PATTERN = "dd/MM/yyyy";
    private final Locale locale = new Locale("ru", "RU", "Russian (Russia)");

    private DateTimeFormatter dateTimeFormatter;

    public DateStringConverter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    }

    @Override
    public String toString(LocalDate date) {
        String text = null;
        if (date != null) {
            text = dateTimeFormatter.format(date);
        }
        return text;
    }

    @Override
    public LocalDate fromString(String text) {
        LocalDate date = null;
        if (text != null && !text.trim().isEmpty()) {
            date = LocalDate.parse(text, dateTimeFormatter);
        }
        return date;
    }

    public String toDayOfWeek(LocalDate date) {
        LocalDate localDate = LocalDate.from(dateTimeFormatter.parse(toString(date)));
        return localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, locale);
    }

    /*public LocalDate fromDayOfWeek(String day) {
        LocalDate localDate = LocalDate.now();
        for (int i = 0; i < 7; i++) {
            if (localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, locale).trim().equals(day)) {

            }
        }
        return null;
    }*/
}
