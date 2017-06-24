package sirotkina.sjournal.domain;

public class ScheduleBean {

    private String weekDay;
    private String scoolClass;
    private Integer id;
    private String lessonTime;
    private String nameOfKurs;
    private String teacherOfLesson;

    public ScheduleBean(String weekDay, String scoolClass, Integer id, String lessonTime,
                        String nameOfKurs, String teacherOfLesson) {
        this.weekDay = weekDay;
        this.scoolClass = scoolClass;
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
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
        return "ScheduleBean{" +
                "weekDay='" + weekDay + '\'' +
                ", scoolClass=" + scoolClass +
                ", id=" + id +
                ", lessonTime=" + lessonTime +
                ", nameOfKurs=" + nameOfKurs +
                ", teacherOfLesson=" + teacherOfLesson +
                '}';
    }
}
