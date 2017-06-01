package sirotkina.sjournal.utils.converters;

import javafx.util.StringConverter;
import sirotkina.sjournal.entity.Users;

import java.util.List;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class StudentStringConverter extends StringConverter<Users> {

    @Override
    public String toString(Users student) {
        return student == null ? null : student.getLastName() + " " + student.getFirstName();
    }

    @Override
    public Users fromString(String str) {
        Users student = null;
        if (str == null) {
            return student;
        }
        int index = str.indexOf(" ");
        if (index == -1) {
            student = new Users(null, null, null,
                    null, null, null, null, null,
                    null, null, null);
        } else {
            String lastName = str.substring(0, index);
            String firstName = str.substring(index + 1);
            student = new Users(null, firstName, null,
                    lastName, null, null, null, null,
                    null, null, null);
        }
        return student;
    }

    public Users checkStudentInDB(Users student) {
        List<Users> studentsList = usersDAO().getAllByRole(3);
        for (Users s : studentsList) {
            if (student.getFirstName().equals(s.getFirstName())
                    && student.getLastName().equals(s.getLastName())) {
                return s;
            }
        }
        return null;
    }
}
