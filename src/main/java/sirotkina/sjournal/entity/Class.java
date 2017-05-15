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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Class aClass = (Class) o;

        if (num != aClass.num) return false;
        return letter.equals(aClass.letter);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + num;
        result = 31 * result + letter.hashCode();
        return result;
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
