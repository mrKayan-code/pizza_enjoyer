package model.pizza;

public enum Size {
    SMALL("Маленькая", 6),
    MEDIUM("Средняя", 8),
    LARGE("Большая", 12);

    private final String name;
    private final int slices_count;

    Size(String name, int slices_count) {
        this.name = name;
        this.slices_count = slices_count;
    }

    public String getName() {
        return name;
    }

    public int getSlicesCount() {
        return slices_count;
    }
}
