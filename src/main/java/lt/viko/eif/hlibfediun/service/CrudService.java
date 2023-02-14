package lt.viko.eif.hlibfediun.service;

import java.util.List;

public interface CrudService<T> {

    T create(T type);

    T getById(String id);

    List<T> getAll();

    void delete(String id);

    T update(T type);
}
