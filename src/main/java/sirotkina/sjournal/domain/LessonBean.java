package sirotkina.sjournal.domain;

import java.io.Serializable;

public class LessonBean implements Serializable {

    private String date;
    private String time;
    private String classId;
    private String lastHomeTask;
    private String newHomeTask;
    private int num;
    private String fio;
    private String mark;
    private String comment;

    public LessonBean(String date, String time, String classId, String lastHomeTask,
                      String newHomeTask, int num, String fio, String mark, String comment) {
        this.date = date;
        this.time = time;
        this.classId = classId;
        this.lastHomeTask = lastHomeTask;
        this.newHomeTask = newHomeTask;
        this.num = num;
        this.fio = fio;
        this.mark = mark;
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getLastHomeTask() {
        return lastHomeTask;
    }

    public void setLastHomeTask(String lastHomeTask) {
        this.lastHomeTask = lastHomeTask;
    }

    public String getNewHomeTask() {
        return newHomeTask;
    }

    public void setNewHomeTask(String newHomeTask) {
        this.newHomeTask = newHomeTask;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "LessonBean{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", classId='" + classId + '\'' +
                ", lastHomeTask='" + lastHomeTask + '\'' +
                ", newHomeTask='" + newHomeTask + '\'' +
                ", num=" + num +
                ", fio='" + fio + '\'' +
                ", mark='" + mark + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
