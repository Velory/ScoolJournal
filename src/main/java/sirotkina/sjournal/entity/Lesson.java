package sirotkina.sjournal.entity;

import java.sql.Date;
import java.sql.Time;

public class Lesson extends Entity{

    private Date date;
    private Time time;
    private String homeTask;
    private Class classFKId;
    private Teachers teachersFKId;
    private Kurs kursFKId;

    public Lesson(Integer id, Date date, Time time, String homeTask, Class classFKId,
                  Teachers teachersFKId, Kurs kursFKId) {
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
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

    public Teachers getTeachersFKId() {
        return teachersFKId;
    }

    public void setTeachersFKId(Teachers teachersFKId) {
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
