package sirotkina.sjournal.utils.enums;

/**
 * Created by Марина on 02.07.2017.
 */
public enum Month {
    JANUARY ("Январь"),
    FEBRUARY ("Февраль"),
    MARCH ("Март"),
    APRIL ("Апрель"),
    MAY ("Май"),
    JUNE ("Июнь"),
    JULY ("Июль"),
    AUGUST ("Август"),
    SEPTEMBER ("Сентябрь"),
    OCTOBER ("Октябрь"),
    NOVEMBER ("Ноябрь"),
    DECEMBER ("Декабрь");

    private String value;

    Month(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
