package sirotkina.sjournal.utils.converters;

import javafx.util.StringConverter;
import sirotkina.sjournal.entity.Users;


import java.util.List;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class TeacherStringConverter extends StringConverter<Users> {
    @Override
    public String toString(Users teacher) {
        return teacher == null ? null : teacher.getFirstName() + " " + teacher.getLastName();
    }

    @Override
    public Users fromString(String str) {
        Users teacher = null;
        if (str == null) {
            return teacher;
        }
        int index = str.indexOf(" ");
        if (index == -1) {
            teacher = new Users(null, null, null,
                    null, 0, null, null, null,
                    null, null, null);
        } else {
            String firstName = str.substring(0, index);
            String lastName = str.substring(index + 1);
            teacher = new Users(null, firstName, null,
                    lastName, 0, null, null, null,
                    null, null, null);
        }
        return teacher;
    }

    public Users checkTeacherInDB(Users teacher) {
        List<Users> teachersList = usersDAO().getAllByRole(2);
        for (Users t : teachersList) {
            if (teacher.getFirstName().equals(t.getFirstName())
                    && teacher.getLastName().equals(t.getLastName())) {
                return t;
            }
        }
        return null;
    }
}
