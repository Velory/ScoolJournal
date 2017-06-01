package sirotkina.sjournal.domain;

public class ClassBean {
    private Integer id;
    private String num;
    private String letter;

    public ClassBean(Integer id, String num, String letter) {
        this.id = id;
        this.num = num;
        this.letter = letter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
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
        return "ClassBean{" +
                "id=" + id +
                ", num='" + num + '\'' +
                ", letter='" + letter + '\'' +
                '}';
    }
}
