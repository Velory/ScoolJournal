package dao;

import entity.Teachers;

import java.util.List;

public class TeachersDAO implements CrudDAO {


    @Override
    public void create(Object o) {
        Teachers teachers = (Teachers) o;

    }

    @Override
    public Object read(int id) {
        return null;
    }

    @Override
    public void update(Object o, int id) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Object> getAll() {
        return null;
    }
}
