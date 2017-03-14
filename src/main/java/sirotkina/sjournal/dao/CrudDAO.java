package sirotkina.sjournal.dao;

import java.util.List;

public interface CrudDAO <T> {

    void create (T object);
    T read (int id);
    void update (T object, int id);
    void delete (int id);
    List<T> getAll();

}
