package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import model.common.Identifiable;

public class ProductCatalog<T extends Identifiable> {
    private final HashMap<UUID, T> catalog = new HashMap<>();


    public void add(T item) {
        catalog.put(item.getId(), item);
    }

    public T getById(UUID id) {
        return catalog.get(id);
    }

    public ArrayList<T> getAll() {
        return new ArrayList<>(catalog.values());
    }

    public boolean remove(UUID id) {
        return catalog.remove(id) != null;
    }

    public List<T> getByFilter(java.util.function.Predicate<T> condition) {
        return catalog.values().stream()
            .filter(condition)
            .collect(Collectors.toList());
    }

    public int size() {
        return catalog.size();
    }
    
    public void clear() {
        catalog.clear();
    }

}
