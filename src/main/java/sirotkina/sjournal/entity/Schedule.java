package sirotkina.sjournal.entity;

public class Schedule extends Entity{
    private String weekDay;
    private Class scoolClass;
    private String lessonTime;
    private Kurs nameOfKurs;
    private Teachers teacherOfLesson;

    public Schedule(String weekDay, Class scoolClass, Integer id,
                    String lessonTime, Kurs nameOfKurs, Teachers teacherOfLesson) {
        this.weekDay = weekDay;
        this.scoolClass = scoolClass;
        setId(id);
        this.lessonTime = lessonTime;
        this.nameOfKurs = nameOfKurs;
        this.teacherOfLesson = teacherOfLesson;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public Class getScoolClass() {
        return scoolClass;
    }

    public void setScoolClass(Class scoolClass) {
        this.scoolClass = scoolClass;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public Kurs getNameOfKurs() {
        return nameOfKurs;
    }

    public void setNameOfKurs(Kurs nameOfKurs) {
        this.nameOfKurs = nameOfKurs;
    }

    public Teachers getTeacherOfLesson() {
        return teacherOfLesson;
    }

    public void setTeacherOfLesson(Teachers teacherOfLesson) {
        this.teacherOfLesson = teacherOfLesson;
    }
}
