package sirotkina.sjournal.utils.converters;

import javafx.util.StringConverter;
import sirotkina.sjournal.entity.Class;

import java.util.List;
import static sirotkina.sjournal.utils.DatabaseUtils.*;

public class ClassStringConverter extends StringConverter <Class>{
    @Override
    public String toString(Class cl) {
        return cl == null ? null : cl.getNum() + "-" + cl.getLetter();
    }

    @Override
    public Class fromString(String str) {
        Class cl = null;
        if (str == null){
            return cl;
        }
        int index = str.indexOf("-");
        if (index == -1){
            cl = new Class(null, 0, null);
        } else {
            int num = Integer.parseInt(str.substring(0, index));
            String letter = str.substring(index + 1);
            cl = new Class(null, num, letter);
        }
        return cl;
    }

    public Class checkClassInDB(Class cl){
        List<Class> classes = classDAO().getAll();
        for (Class clFromDB: classes) {
            if(cl.getNum() == clFromDB.getNum() && cl.getLetter().equals(clFromDB.getLetter())){
                return clFromDB;
            }
        }
        return null;
    }
}
