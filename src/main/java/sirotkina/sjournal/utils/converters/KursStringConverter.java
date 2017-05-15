package sirotkina.sjournal.utils.converters;

import javafx.util.StringConverter;
import sirotkina.sjournal.entity.Kurs;

import java.util.List;

import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class KursStringConverter extends StringConverter<Kurs> {
    @Override
    public String toString(Kurs kurs) {
        return kurs == null ? null : kurs.getTitle();
    }

    @Override
    public Kurs fromString(String str) {
        if (str == null) return null;
        return new Kurs(null, str);
    }

    public Kurs checkKursInDB(Kurs kurs){
        List<Kurs> kurses = kursDAO().getAll();
        for (Kurs kursFromDB: kurses) {
            if(kurs.getTitle().equals(kursFromDB.getTitle())){
                return kursFromDB;
            }
        }
        return null;
    }
}
