package sirotkina.sjournal.entity;

public class Marks extends Entity {

    private int mark;
    private String comment;
    private Lesson lessonFKId;
    private Users studentsFKId;

    public Marks(Integer id, int mark, String comment, Lesson lessonFKId, Users studentsFKId) {
        setId(id);
        this.mark = mark;
        this.comment = comment;
        this.lessonFKId = lessonFKId;
        this.studentsFKId = studentsFKId;
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

    public Lesson getLessonFKId() {
        return lessonFKId;
    }

    public void setLessonFKId(Lesson lessonFKId) {
        this.lessonFKId = lessonFKId;
    }

    public Users getStudentsFKId() {
        return studentsFKId;
    }

    public void setStudentsFKId(Users studentsFKId) {
        this.studentsFKId = studentsFKId;
    }

    @Override
    public String toString() {
        return "JournalBean{" +
                "id=" + getId() +
                "mark=" + mark +
                ", comment='" + comment + '\'' +
                ", lessonId=" + lessonFKId +
                ", studentsId=" + studentsFKId +
                '}';
    }
}
