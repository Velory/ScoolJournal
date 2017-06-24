package sirotkina.sjournal.utils.enums;

public enum RoleEnum {
    ADMIN("Администратор"),
    STUDENT("Ученик"),
    TEACHER("Учитель"),
    PARENT("Родитель");

    private String value;

    RoleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
