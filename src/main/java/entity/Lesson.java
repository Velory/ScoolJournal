package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Lesson {
    private int id;
    private LocalDate date;
    private LocalTime time;
    private String homeTask;
    private int classId;
    private int teachersId;
    private int kursId;

    public Lesson(int id, LocalDate date, LocalTime time, String homeTask, int classId, int teachersId, int kursId) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
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
