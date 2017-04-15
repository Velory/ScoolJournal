package sirotkina.sjournal.utils;

import javafx.util.StringConverter;
import sirotkina.sjournal.entity.Teachers;

public class TeacherStringConverter extends StringConverter<Teachers> {
    @Override
    public String toString(Teachers teacher) {
        return teacher == null ? null : teacher.getFirstName() + " " + teacher.getLastName();
    }

    @Override
    public Teachers fromString(String str) {
        Teachers teacher = null;
        if (str == null){
            return teacher;
        }
        int index = str.indexOf(" ");
        if (index == -1){
            teacher = new Teachers(null, null, null,
                    null, null, null, null, null);
        } else {
            String firstName = str.substring(0, index);
            String lastName  = str.substring(index + 2);
            teacher =  new Teachers(null, null, null,
                    null, null, null, null, null);;
        }
        return teacher;
    }
}
