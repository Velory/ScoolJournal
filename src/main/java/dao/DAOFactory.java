package dao;

import java.util.Map;

public interface DAOFactory <T, K, V>{

    T create ();
    T read ();
    void update ();
    void delete ();
    Map <K,V> getAll();
}
