package sirotkina.sjournal.utils.enums;

/**
 * Created by Марина on 02.07.2017.
 */
public enum Month {
    JANUARY ("Январь", 1),
    FEBRUARY ("Февраль", 2),
    MARCH ("Март", 3),
    APRIL ("Апрель", 4),
    MAY ("Май", 5),
    JUNE ("Июнь", 6),
    JULY ("Июль", 7),
    AUGUST ("Август", 8),
    SEPTEMBER ("Сентябрь", 9),
    OCTOBER ("Октябрь", 10),
    NOVEMBER ("Ноябрь", 11),
    DECEMBER ("Декабрь", 12);

    private String value;
    private int number;

    Month(String value, int number) {
        this.value = value;
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return value;
    }
}
