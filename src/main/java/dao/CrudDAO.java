package dao;

import java.util.List;

public interface CrudDAO {

    void create (Object o);
    Object read (int id);
    void update (Object o, int id);
    void delete (int id);
    List<Object> getAll();

}
