package repository;

import model.annotation.Id;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Repository<T> {
    private final Map<String, T> storage = new HashMap<>();

    public T save(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        String id = extractId(entity);
        storage.put(id, entity);
        return entity;
    }

    public T findById(String id) {
        if (id == null) return null;
        return storage.get(id);
    }

    public ArrayList<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    public boolean delete(String id) {
        if (id == null) return false;
        return storage.remove(id) != null;
    }

    public int count() {
        return storage.size();
    }

    @SafeVarargs
    public final void saveAll(T... items) {
        if (items != null) {
            for (T item : items) {
                if (item != null) {
                    save(item);
                }
            }
        }
    }

    private String extractId(T entity) {
        Class<?> clazz = entity.getClass();
        while (clazz != null && clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    try {
                        Object val = field.get(entity);
                        if (val == null) {
                            throw new IllegalStateException("Field @" + field.getName() + " cannot have a null ID value.");
                        }
                        return val.toString();
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Failed to access @Id field: " + field.getName(), e);
                    }
                }
            }
            clazz = clazz.getSuperclass();
        }
        throw new IllegalStateException("Entity of type " + entity.getClass().getSimpleName() + " is missing an @Id annotated field.");
    }
}
