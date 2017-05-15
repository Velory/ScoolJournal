package sirotkina.sjournal.utils.converters;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.sql.Time;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class TimeFormatter extends TextFormatter<Time> {

    private final String TIME_REGEXP =
            "^([0-2])([0-9]):([0-5])([0-9]):([0-5])([0-9])";

    private TextFormatter<Time> textFormatter;
    private Pattern pattern;

    public TimeFormatter(StringConverter<Time> valueConverter) {
        super(valueConverter);
        pattern = Pattern.compile(TIME_REGEXP);
    }

    public TextFormatter<Time> getTimeFormatter() {
        return textFormatter = new TextFormatter<Time>(new UnaryOperator<Change>() {
            @Override
            public Change apply(Change change) {
                if(change.getText().matches(TIME_REGEXP)){
                    return change;
                }
                return null;
            }
        });
    }

    /*private final String TIME_REGEXP =
            "^(?:(?:([01]?\\d|2[0-3]):)?([0-5]?\\d):)?([0-5]?\\d)$";

    private TextFormatter<Time> textFormatter;
    private Pattern pattern;

    public TimeFormatter(StringConverter<Time> valueConverter) {
        super(valueConverter);
        pattern = Pattern.compile(TIME_REGEXP);
    }


    public TextFormatter<Time> getTimeFormatter() {
        return textFormatter = new TextFormatter<>(new TimeStringConverter(),Time.valueOf("00:00:00"), new UnaryOperator<Change>() {
            @Override
            public Change apply(Change change) {
                String newText = change.getControlNewText();
                if (pattern.matcher(newText).matches()) {
                    return change;
                } else return null;
            }
        });
    }*/

}
