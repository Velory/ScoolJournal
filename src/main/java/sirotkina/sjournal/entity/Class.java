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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Class aClass = (Class) o;

        if (num != aClass.num) return false;
        return letter != null ? letter.equals(aClass.letter) : aClass.letter == null;
    }

    @Override
    public int hashCode() {
        int result = num;
        result = 31 * result + (letter != null ? letter.hashCode() : 0);
        return result;
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
