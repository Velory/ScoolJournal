package sirotkina.sjournal.utils;

public enum Role {
    ADMIN("Администратор"),
    STUDENT("Ученик"),
    TEACHER("Учитель"),
    PARENT("Родитель");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
