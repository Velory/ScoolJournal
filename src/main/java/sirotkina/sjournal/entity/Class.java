package sirotkina.sjournal.entity;

public class Class {
    private int id;
    private int num;
    private  String letter;

    public Class(int id, int num, String letter) {
        this.id = id;
        this.num = num;
        this.letter = letter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", num=" + num +
                ", letter='" + letter + '\'' +
                '}';
    }
}
