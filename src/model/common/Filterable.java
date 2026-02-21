package model.common;

import java.util.function.Predicate;

public interface Filterable<T> {
    boolean matches(Predicate<T> condition);
}