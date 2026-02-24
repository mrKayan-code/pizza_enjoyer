package util;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.function.Predicate;

public class FilterUtils {
    
    public static <T> List<T> filter(Collection<T> collection, Predicate<T> condition) {
        return collection.stream()
            .filter(condition)
            .collect(Collectors.toList());
    }
    
    public static <T> List<T> filterAndSort(Collection<T> collection, Predicate<T> condition, Comparator<T> comparator) {
        return collection.stream()
            .filter(condition)
            .sorted(comparator)
            .collect(Collectors.toList());
    }
    
    public static <T> boolean anyMatch(Collection<T> collection, Predicate<T> condition) {
        return collection.stream().anyMatch(condition);
    }

}
