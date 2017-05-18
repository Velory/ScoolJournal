package sirotkina.sjournal.utils;

public enum DaysOfWeek {
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

    private String value;

    DaysOfWeek(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
