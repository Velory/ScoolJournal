package sirotkina.sjournal.entity;

import java.sql.Time;

public class Schedule extends Entity{

    private String weekDay;
    private String scoolClass;
    private Integer id;
    private Time lessonTime;
    private String nameOfKurs;
    private String teacherOfLesson;

    public Schedule(String weekDay, String scoolClass, Integer id, Time lessonTime,
                    String nameOfKurs, String teacherOfLesson) {
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

    public String getScoolClass() {
        return scoolClass;
    }

    public void setScoolClass(String scoolClass) {
        this.scoolClass = scoolClass;
    }

    public Time getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(Time lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getNameOfKurs() {
        return nameOfKurs;
    }

    public void setNameOfKurs(String nameOfKurs) {
        this.nameOfKurs = nameOfKurs;
    }

    public String getTeacherOfLesson() {
        return teacherOfLesson;
    }

    public void setTeacherOfLesson(String teacherOfLesson) {
        this.teacherOfLesson = teacherOfLesson;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "weekDay='" + weekDay + '\'' +
                ", scoolClass=" + scoolClass +
                ", id=" + id +
                ", lessonTime=" + lessonTime +
                ", nameOfKurs=" + nameOfKurs +
                ", teacherOfLesson=" + teacherOfLesson +
                '}';
    }
}