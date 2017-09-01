package sirotkina.sjournal.entity;

import java.sql.Date;

public class Lesson extends Entity {

    private Date date;
    private String time;
    private String homeTask;
    private Class classFKId;
    private Users teachersFKId;
    private Kurs kursFKId;

    public Lesson(Integer id, Date date, String time, String homeTask, Class classFKId,
                  Users teachersFKId, Kurs kursFKId) {
        setId(id);
        this.date = date;
        this.time = time;
        this.homeTask = homeTask;
        this.classFKId = classFKId;
        this.teachersFKId = teachersFKId;
        this.kursFKId = kursFKId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHomeTask() {
        return homeTask;
    }

    public void setHomeTask(String homeTask) {
        this.homeTask = homeTask;
    }

    public Class getClassFKId() {
        return classFKId;
    }

    public void setClassFKId(Class classFKId) {
        this.classFKId = classFKId;
    }

    public Users getTeachersFKId() {
        return teachersFKId;
    }

    public void setTeachersFKId(Users teachersFKId) {
        this.teachersFKId = teachersFKId;
    }

    public Kurs getKursFKId() {
        return kursFKId;
    }

    public void setKursFKId(Kurs kursFKId) {
        this.kursFKId = kursFKId;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + getId() +
                ", date=" + date +
                ", time=" + time +
                ", homeTask='" + homeTask + '\'' +
                ", classId=" + classFKId +
                ", teachersId=" + teachersFKId +
                ", kursId=" + kursFKId +
                '}';
    }
}
