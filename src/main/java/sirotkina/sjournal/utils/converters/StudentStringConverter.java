package sirotkina.sjournal.utils.converters;

import javafx.util.StringConverter;
import sirotkina.sjournal.entity.Students;

import static sirotkina.sjournal.utils.DatabaseUtils.*;
import java.util.List;

public class StudentStringConverter extends StringConverter<Students>{

    @Override
    public String toString(Students student) {
        return student == null ? null : student.getLastName() + " " + student.getFirstName();
    }

    @Override
    public Students fromString(String str) {
        Students student = null;
        if (str == null){
            return student;
        }
        int index = str.indexOf(" ");
        if (index == -1){
            student = new Students(null, null, null,
                    null, 0, null, null, null, null);
        } else {
            String lastName = str.substring(0, index);
            String firstName = str.substring(index + 1);
            student =  new Students(null, firstName, null,
                    lastName, 0, null, null, null, null);
        }
        return student;
    }

    public Students checkStudentInDB(Students student){
        List<Students> studentsList = studentsDAO().getAll();
        for (Students s: studentsList) {
            if(student.getFirstName().equals(s.getFirstName())
                    && student.getLastName().equals(s.getLastName())){
                return s;
            }
        }
        return null;
    }
}
