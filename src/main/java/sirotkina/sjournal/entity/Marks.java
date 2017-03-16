package sirotkina.sjournal.entity;

public class Marks {
    private int id;
    private int mark;
    private String comment;
    private int lessonId;
    private int studentsId;

    public Marks(int id, int mark, String comment, int lessonId, int studentsId) {
        this.id = id;
        this.mark = mark;
        this.comment = comment;
        this.lessonId = lessonId;
        this.studentsId = studentsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(int studentsId) {
        this.studentsId = studentsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Marks marks = (Marks) o;

        if (mark != marks.mark) return false;
        if (lessonId != marks.lessonId) return false;
        if (studentsId != marks.studentsId) return false;
        return comment != null ? comment.equals(marks.comment) : marks.comment == null;
    }

    @Override
    public int hashCode() {
        int result = mark;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + lessonId;
        result = 31 * result + studentsId;
        return result;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "mark=" + mark +
                ", comment='" + comment + '\'' +
                ", lessonId=" + lessonId +
                ", studentsId=" + studentsId +
                '}';
    }
}
