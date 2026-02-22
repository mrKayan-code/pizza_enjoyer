package model.pizza;

public enum Size {
    SMALL("Маленькая", 6, 1.0),
    MEDIUM("Средняя", 8, 1.3),
    LARGE("Большая", 12, 2);

    private final String name;
    private final int slices_count;
    private final double multiplier;

    Size(String name, int slices_count, double multiplier) {
        this.name = name;
        this.slices_count = slices_count;
        this.multiplier = multiplier;
    }

    public String getName() {
        return name;
    }

    public int getSlicesCount() {
        return slices_count;
    }

    public double getCostMultiplier() {
        return multiplier;
    }

}
