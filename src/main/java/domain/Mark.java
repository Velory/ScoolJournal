package domain;

import java.io.Serializable;

public class Mark implements Serializable{
    private int mark;
    private String comment;
    private int lessonID;
    private int studentID;

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int lessonID) {
        this.lessonID = lessonID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mark mark1 = (Mark) o;

        if (mark != mark1.mark) return false;
        if (lessonID != mark1.lessonID) return false;
        if (studentID != mark1.studentID) return false;
        return comment != null ? comment.equals(mark1.comment) : mark1.comment == null;
    }

    @Override
    public int hashCode() {
        int result = mark;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + lessonID;
        result = 31 * result + studentID;
        return result;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "mark=" + mark +
                ", comment='" + comment + '\'' +
                ", lessonID=" + lessonID +
                ", studentID=" + studentID +
                '}';
    }
}
