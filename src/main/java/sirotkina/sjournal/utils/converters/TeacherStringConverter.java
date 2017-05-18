package sirotkina.sjournal.utils.converters;

import javafx.util.StringConverter;
import sirotkina.sjournal.entity.Teachers;

import java.util.List;

import static sirotkina.sjournal.utils.DatabaseUtils.teachersDAO;

public class TeacherStringConverter extends StringConverter<Teachers> {
    @Override
    public String toString(Teachers teacher) {
        return teacher == null ? null : teacher.getFirstName() + " " + teacher.getLastName();
    }

    @Override
    public Teachers fromString(String str) {
        Teachers teacher = null;
        if (str == null) {
            return teacher;
        }
        int index = str.indexOf(" ");
        if (index == -1) {
            teacher = new Teachers(null, null, null,
                    null, null, null, null, null, null);
        } else {
            String firstName = str.substring(0, index);
            String lastName = str.substring(index + 1);
            teacher = new Teachers(null, firstName, null,
                    lastName, null, null, null, null, null);
        }
        return teacher;
    }

    public Teachers checkTeacherInDB(Teachers teacher) {
        List<Teachers> teachersList = teachersDAO().getAll();
        for (Teachers t : teachersList) {
            if (teacher.getFirstName().equals(t.getFirstName())
                    && teacher.getLastName().equals(t.getLastName())) {
                return t;
            }
        }
        return null;
    }
}
