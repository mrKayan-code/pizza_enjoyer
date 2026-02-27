package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import model.common.Identifiable;
import model.common.Named;

public class ProductCatalog<T extends Identifiable & Named> {
    private final HashMap<UUID, T> catalog = new HashMap<>();
    private HashMap<String, UUID> names_table = new HashMap<>();

    public void add(T item) {
        catalog.put(item.getId(), item);
        names_table.put(item.getName().toLowerCase(), item.getId());
    }

    public T getById(UUID id) {
        return catalog.get(id);
    }
    
    public T getByName(String name) {
        return getById(names_table.get(name.toLowerCase()));
    }

    public ArrayList<T> getAll() {
        return new ArrayList<>(catalog.values());
    }

    public boolean remove(UUID id) {
        names_table.remove(getById(id).getName().toLowerCase());
        return catalog.remove(id) != null;
    }

    // public List<T> getByFilter(java.util.function.Predicate<T> condition) {
    //     return catalog.values().stream()
    //         .filter(condition)
    //         .collect(Collectors.toList());
    // }

    public int size() {
        return catalog.size();
    }
    
    public void clear() {
        catalog.clear();
        names_table.clear();
    }

}
