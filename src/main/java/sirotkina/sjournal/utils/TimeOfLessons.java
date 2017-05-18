package sirotkina.sjournal.utils;

public enum TimeOfLessons {
    ONE("8:00 - 8:45"),
    TWO("9:00 - 9:45"),
    THREE("10:00 - 10:45"),
    FOUR("11:00 - 11:45"),
    FIVE("12:00 - 12:45"),
    SIX("12:55 - 13:40"),
    SEVEN("13:50 - 14:35");

    private String value;

    TimeOfLessons(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
