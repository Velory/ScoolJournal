package sirotkina.sjournal.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Lesson implements Serializable{

    private LocalDate date;
    private LocalTime time;
    private String homeTask;
    private int classId;
    private int teacherId;
    private int kursId;

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

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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
        if (teacherId != lesson.teacherId) return false;
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
        result = 31 * result + teacherId;
        result = 31 * result + kursId;
        return result;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "date=" + date +
                ", time=" + time +
                ", homeTask='" + homeTask + '\'' +
                ", classId=" + classId +
                ", teacherId=" + teacherId +
                ", kursId=" + kursId +
                '}';
    }
}
