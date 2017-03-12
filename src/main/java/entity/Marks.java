package entity;

public class Marks {
    private int mark;
    private String comment;
    private int lessonId;
    private int studentsId;

    public Marks(int mark, String comment, int lessonId, int studentsId) {
        this.mark = mark;
        this.comment = comment;
        this.lessonId = lessonId;
        this.studentsId = studentsId;
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
    public String toString() {
        return "Mark{" +
                "mark=" + mark +
                ", comment='" + comment + '\'' +
                ", lessonId=" + lessonId +
                ", studentsId=" + studentsId +
                '}';
    }
}
