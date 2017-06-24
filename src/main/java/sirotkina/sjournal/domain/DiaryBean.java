package sirotkina.sjournal.domain;

import sirotkina.sjournal.utils.myValidator.NotEmpty;

public class DiaryBean {

    @NotEmpty(message = "выберите студента")
    private String selectStudentDiary;
    @NotEmpty(message = "выберите класс")
    private String selectClassDiary;
    @NotEmpty(message = "выберите начало периода")
    private String period;

    private Integer numDiary;
    private String kursDiary;
    private String homeTaskDiary;
    private String markDiary;
    private String commentDiary;

    public DiaryBean(String selectStudentDiary, String selectClassDiary, String period) {
        this.selectStudentDiary = selectStudentDiary;
        this.selectClassDiary = selectClassDiary;
        this.period = period;
    }

    public DiaryBean(Integer numDiary, String kursDiary, String homeTaskDiary,
                     String markDiary, String commentDiary) {
        this.numDiary = numDiary;
        this.kursDiary = kursDiary;
        this.homeTaskDiary = homeTaskDiary;
        this.markDiary = markDiary;
        this.commentDiary = commentDiary;
    }

    public Integer getNumDiary() {
        return numDiary;
    }

    public void setNumDiary(Integer numDiary) {
        this.numDiary = numDiary;
    }

    public String getKursDiary() {
        return kursDiary;
    }

    public void setKursDiary(String kursDiary) {
        this.kursDiary = kursDiary;
    }

    public String getHomeTaskDiary() {
        return homeTaskDiary;
    }

    public void setHomeTaskDiary(String homeTaskDiary) {
        this.homeTaskDiary = homeTaskDiary;
    }

    public String getMarkDiary() {
        return markDiary;
    }

    public void setMarkDiary(String markDiary) {
        this.markDiary = markDiary;
    }

    public String getCommentDiary() {
        return commentDiary;
    }

    public void setCommentDiary(String commentDiary) {
        this.commentDiary = commentDiary;
    }
}
