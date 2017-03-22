package sirotkina.sjournal.entity;

public class Class extends Entity{

    private int num;
    private  String letter;

    public Class(Integer id, int num, String letter) {
        this.num = num;
        this.letter = letter;
        setId(id);
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
                "id=" + getId() +
                ", num=" + num +
                ", letter='" + letter + '\'' +
                '}';
    }
}
