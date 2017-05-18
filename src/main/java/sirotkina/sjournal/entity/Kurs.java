package sirotkina.sjournal.entity;

public class Kurs extends Entity {

    private String title;

    public Kurs(Integer id, String title) {
        setId(id);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Kurs{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                '}';
    }
}
