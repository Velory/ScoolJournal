package sirotkina.sjournal.entity;

import java.sql.Date;
import java.sql.Time;

public class Lesson {
    private int id;
    private Date date;
    private Time time;
    private String homeTask;
    private int classId;
    private int teachersId;
    private int kursId;

    public Lesson(int id, Date date, Time time, String homeTask, int classId, int teachersId, int kursId) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.homeTask = homeTask;
        this.classId = classId;
        this.teachersId = teachersId;
        this.kursId = kursId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (classId != lesson.classId) return false;
        if (teachersId != lesson.teachersId) return false;
        if (kursId != lesson.kursId) return false;
        if (date != null ? !date.equals(lesson.date) : lesson.date != null) return false;
        if (time != null ? !time.equals(lesson.time) : lesson.time != null) return false;
        return homeTask != null ? homeTask.equals(lesson.homeTask) : lesson.homeTask == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (homeTask != null ? homeTask.hashCode() : 0);
        result = 31 * result + classId;
        result = 31 * result + teachersId;
        result = 31 * result + kursId;
        return result;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", homeTask='" + homeTask + '\'' +
                ", classId=" + classId +
                ", teachersId=" + teachersId +
                ", kursId=" + kursId +
                '}';
    }
}
