package dao;

public abstract class DAOFactory {

    public abstract ClassDAO getClassDAO();
    public abstract KursDAO getKursDAO();
    public abstract LessonDAO getLessonDAO();
    public abstract MarksDAO getMarksDAO();
    public abstract StudentsDAO getStudentsDAO();
    public abstract TeachersDAO getTeachersDAO();

}
