package sirotkina.sjournal.entity;

import java.sql.Date;
import java.sql.Time;

public class Lesson extends Entity{

    private Date date;
    private Time time;
    private String homeTask;
    private int classId;
    private int teachersId;
    private int kursId;

    public Lesson(Integer id, Date date, Time time, String homeTask, int classId, int teachersId, int kursId) {
        setId(id);
        this.date = date;
        this.time = time;
        this.homeTask = homeTask;
        this.classId = classId;
        this.teachersId = teachersId;
        this.kursId = kursId;
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

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTeachersId() {
        return teachersId;
    }

    public void setTeachersId(int teachersId) {
        this.teachersId = teachersId;
    }

    public int getKursId() {
        return kursId;
    }

    public void setKursId(int kursId) {
        this.kursId = kursId;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + getId() +
                ", date=" + date +
                ", time=" + time +
                ", homeTask='" + homeTask + '\'' +
                ", classId=" + classId +
                ", teachersId=" + teachersId +
                ", kursId=" + kursId +
                '}';
    }
}
